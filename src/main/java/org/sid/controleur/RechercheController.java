package org.sid.controleur;


import org.sid.dao.AnnonceRepository;
import org.sid.entite.Annonce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RechercheController {

    @Autowired
    AnnonceRepository rechercheRepo;


    @GetMapping("/recherche/categorie")
    public List<Annonce> getByCategorie(@RequestParam String cat){
        return  rechercheRepo.findAnnonceByCategorie(cat);
    }


    @GetMapping("/recherche/motCle")
    public List<Annonce> getByAnnonceByMotCle(@RequestParam("motCle") String motCle){
        return rechercheRepo.findAnnonceByMotCle(motCle);
    }
}
