package org.sid.entite;

import java.util.Date;

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
public class Message {

	@Id
	private Long id;
	
	private Interlocators interlocators;
	
	private Date sentDate;
	
	private String text;
	
	@DBRef(lazy = true)
	private Conversation conversation;
	
}
