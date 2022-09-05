package org.sid.dao;

import org.sid.entite.Interlocators;
import org.sid.entite.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource()
public interface MessageRepository extends MongoRepository<Message, Long> {

	public Page<Message> findByConversation_IdOrderByIdDesc(Long conversationId, Pageable pageable);
	
}
