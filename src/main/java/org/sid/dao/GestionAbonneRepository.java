package org.sid.dao;

import org.sid.entite.GestionAbonne;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface GestionAbonneRepository extends MongoRepository<GestionAbonne,String> {

}
