package org.sid.dao;

import org.sid.entite.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ConversationRepository extends MongoRepository<Conversation, Long> {

	public Page<Conversation> findByOwnerOrInterlocatorOrderByLastMessage_SentDate(
			String owner, String interlocator, Pageable pageable);
	
}
