package org.sid.dao;

import java.util.List;

import org.sid.entite.Annonce;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(path = "annonces")
public interface AnnonceRepository extends MongoRepository<Annonce,String> {

    public  Annonce findAnnonceById(String id);
    public List<Annonce> findByEtatSuppression(boolean etat);
    public List<Annonce> findAnnonceByCategorie(String cat);
    public List<Annonce> findAnnonceByVilleAndSousCategorie(String ville,String scat);
    public List<Annonce> findAnnonceByGouvernorat(String gov);

    @Query("{ $text: { $search: ?0 } }")
    public List<Annonce> findAnnonceByMotCle(String motCle);
}
