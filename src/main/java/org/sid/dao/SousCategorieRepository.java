package org.sid.dao;


import org.sid.entite.SousCategorie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource(path = "sousCategories")
public interface SousCategorieRepository extends MongoRepository<SousCategorie,String> {

    Optional<SousCategorie> findById(String id);
    SousCategorie findByNom(String nom);
}
