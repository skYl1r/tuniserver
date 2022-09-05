package org.sid.entite;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Conversation {

	@Id
	private Long id;
	
	private boolean containsUnreadMessages;
	
	private String owner;
	
	private String interlocator;
	
	@DBRef
	private Message lastMessage;
}
