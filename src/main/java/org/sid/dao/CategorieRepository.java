package org.sid.dao;

import org.sid.entite.Categorie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;


@RepositoryRestResource
public interface CategorieRepository extends MongoRepository<Categorie,String> {

    Optional<Categorie> findById(String id);
    Categorie findByNom(String nom);
}
