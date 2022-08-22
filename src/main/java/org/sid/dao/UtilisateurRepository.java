package org.sid.dao;

import org.sid.entite.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource
public interface UtilisateurRepository extends MongoRepository<Utilisateur,String>{
    public Utilisateur findUtilisateurByUsername(String username);
    public List<Utilisateur> findByRoles(String role);
}
