package org.sid.service;

public interface SequenceService {

	public static final String MONGO_ID_FIELD = "_id";
	
	public static final String MONGO_NEXT_VALUE_FIELD = "next_val";
	
	public Long getNextValue(String sequenceName);
	
}
