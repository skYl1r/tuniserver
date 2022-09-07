package org.sid.dao;

import java.util.List;

import org.sid.entite.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface UtilisateurRepository extends MongoRepository<Utilisateur,String>{
    public Utilisateur findUtilisateurByUsername(@Param("username") String username);
    public List<Utilisateur> findByRoles(String role);
}
