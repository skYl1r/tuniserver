package org.sid.dao;

import org.sid.entite.GestionAnnonce;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface GestionAnnonceRepository extends MongoRepository<GestionAnnonce,String> {
}
