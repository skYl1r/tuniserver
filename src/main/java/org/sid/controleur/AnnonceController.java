package org.sid.controleur;


import lombok.Data;
import org.sid.entite.Annonce;
import org.sid.entite.SignalAnnonce;
import org.sid.service.AnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnonceController {

    @Autowired
    private AnnonceService annonceService;


    @PostMapping("/publierAnnonce")
        public Annonce publierAnnonce(@RequestBody AnnonceForm annonceForm){

        return annonceService.enregistrerAnnonce(
                annonceForm.getUsername(),
                annonceForm.getImage1(),
                annonceForm.getImage2(),
                annonceForm.getImage3(),
                annonceForm.getTitre(),
                annonceForm.getCategorie(),
                annonceForm.getSousCategorie(),
                annonceForm.getDescription(),
                annonceForm.getTelephone(),
                annonceForm.getGouvernorat(),
                annonceForm.getVille()
        );
    }

    @PostMapping("/supprimerAnnonce")
    public Annonce supprimer(@RequestParam(name="admin") String admin, @RequestParam(name="annonce") String annonce, @RequestParam(name="cause") String cause){
        return annonceService.supprimerAnnonce(admin,annonce,cause);
    }

    @PostMapping("/commenter")
    public Annonce commenter(@RequestParam(name="idAnnonce") String idAnnonce, @RequestParam(name="contenu") String contenu, @RequestParam(name="username") String username){
        return annonceService.commenterAnnonce(idAnnonce,contenu,username);

    }


    @PostMapping("/signalerAnnonce")
    public SignalAnnonce signalerAnnonce(@RequestParam(name="idAnnonce") String idAnnonce, @RequestParam(name="cause") String cause, @RequestParam(name="username") String username){
        return annonceService.signalerAnnonce(idAnnonce,cause,username);
    }

}

@Data
class AnnonceForm {
    private String username;
    private String image1;
    private String image2;
    private String image3;
    private String titre;
    private String categorie;
    private String sousCategorie;
    private String description;
    private String telephone;
    private String gouvernorat;
    private String ville;
}
