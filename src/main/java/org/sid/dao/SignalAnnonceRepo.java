package org.sid.dao;


import org.sid.entite.SignalAnnonce;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SignalAnnonceRepo extends MongoRepository<SignalAnnonce,String> {

}
