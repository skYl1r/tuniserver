package org.sid.service.serviceImpl;

import java.util.Objects;
import java.util.Optional;

import org.sid.dao.SequenceRepository;
import org.sid.entite.Sequence;
import org.sid.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.MongoException;

@Service
public class SequenceServiceImpl implements SequenceService {

	@Autowired
	private MongoOperations mongoOperations;

	@Autowired
	private SequenceRepository sequenceRepository;

	@Override
	public Long getNextValue(String sequenceName) {

		Sequence seqeunce = addSequenceIfAbsent(sequenceName);

		if (Objects.isNull(seqeunce)) {
			// get sequence id
			Query query = new Query(Criteria.where(MONGO_ID_FIELD).is(sequenceName));

			// increase sequence id by 1
			Update update = new Update();
			update.inc(MONGO_NEXT_VALUE_FIELD, 1);

			// return new increased id
			FindAndModifyOptions options = new FindAndModifyOptions();
			options.returnNew(true);

			// this is where the magic happen.
			seqeunce = mongoOperations.findAndModify(query, update, options, Sequence.class);
		}

		// if no id, throws MongoException
		// optional, just a way to tell user when the sequence id is failed to generate.
		if (Objects.isNull(seqeunce)) {
			throw new MongoException("Unable to get sequence id for key : " + sequenceName);
		}

		return seqeunce.getNextVal();
	}

	private Sequence addSequenceIfAbsent(String sequenceName) {
		Optional<Sequence> sequence = sequenceRepository.findById(sequenceName);
		if (!sequence.isPresent()) {
			Sequence newSequence = new Sequence(sequenceName, 1L);
			return sequenceRepository.save(newSequence);
		}
		return null;
	}

}
