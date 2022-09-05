package org.sid.dao;

import org.sid.entite.Sequence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SequenceRepository extends MongoRepository<Sequence, String> {
	
}
