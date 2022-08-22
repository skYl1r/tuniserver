package org.sid.service.serviceImpl;


import java.util.Date;

import org.sid.dao.AnnonceRepository;
import org.sid.dao.CategorieRepository;
import org.sid.dao.SignalAnnonceRepo;
import org.sid.dao.SousCategorieRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entite.Annonce;
import org.sid.entite.Categorie;
import org.sid.entite.Commentaire;
import org.sid.entite.SignalAnnonce;
import org.sid.entite.SousCategorie;
import org.sid.entite.Utilisateur;
import org.sid.service.AnnonceService;
import org.sid.service.GestionAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AnnonceServiceImpl implements AnnonceService {
    @Autowired
    private AnnonceRepository annonceRepository;
    @Autowired
    private CategorieRepository categoryRepository;
    @Autowired
    private SousCategorieRepository subCategoryRepository;
    @Autowired
    private UtilisateurRepository userRepo;
    @Autowired
    private GestionAnnonceService historique;
    @Autowired
    private SignalAnnonceRepo signalAnnonceRepo;

    @Override
    public Annonce enregistrerAnnonce(String username, String imageUrl1, String imageUrl2, String imageUrl3, String titre, String categorie, String sousCategorie, String description, String telephone, String gouvernorat, String ville) {
        Annonce annonce=new Annonce();

        String id= categoryRepository.findByNom(categorie).getId();
        Categorie cat=categoryRepository.findById(id).get();

        id= subCategoryRepository.findByNom(sousCategorie).getId();
        SousCategorie scat=subCategoryRepository.findById(id).get();

        Utilisateur appUser =userRepo.findUtilisateurByUsername(username);
        annonce.setEtatSuppression(true);
        annonce.setImage1(imageUrl1);
        annonce.setImage2(imageUrl2);
        annonce.setImage3(imageUrl3);
        annonce.setTitre(titre);
        annonce.setDescription(description);
        annonce.setTel(telephone);
        annonce.setGouvernorat(gouvernorat);
        annonce.setVille(ville);
        annonce.setDatePublication(new Date());
        annonce.setDisponible(true);
        annonce.setCategorie(categorie);
        annonce.setSousCategorie(sousCategorie);
        annonce.setUsername(username);


//        Annonce a=annonceRepository.save(new Annonce(null,imageUrl1,imageUrl2,imageUrl3,titre,description, telephone,gouvernorat,ville,new Date(),scat,appUser));
        Annonce a=annonceRepository.save(annonce);
        scat.getAnnonces().add(a);
        subCategoryRepository.save(scat);

        appUser.getAnnonces().add(a);
        userRepo.save(appUser);
        return a;
    }


    @Override
    public Annonce supprimerAnnonce(String admin, String idAnnonce, String cause) {
        Utilisateur ad = userRepo.findUtilisateurByUsername(admin);
        Annonce annonce = annonceRepository.findAnnonceById(idAnnonce);
        annonce.setEtatSuppression(false);
        historique.enregistrerGestionAnnonce(ad,annonce,cause,"a supprimé");
        annonceRepository.save(annonce);
        return annonce;
    }

    @Override
    public Annonce commenterAnnonce(String idAnnonce,String contenu, String username) {
        Annonce annonce=annonceRepository.findAnnonceById(idAnnonce);
        Commentaire commentaire=new Commentaire();
        commentaire.setContenu(contenu);
        commentaire.setDate(new Date());
        commentaire.setUsername(username);
        annonce.getCommentaires().add(commentaire);

        return annonceRepository.save(annonce);

    }

    @Override
    public SignalAnnonce signalerAnnonce(String idAnnonce, String cause, String username) {
        SignalAnnonce signal=new SignalAnnonce();
        signal.setIdAnnonce(idAnnonce);
        signal.setCause(cause);
        signal.setDate(new Date());
        signal.setIdAbonne(username);
        return signalAnnonceRepo.save(signal);
    }
}
