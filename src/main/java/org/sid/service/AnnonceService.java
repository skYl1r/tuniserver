package org.sid.service;

import org.sid.entite.Annonce;
import org.sid.entite.SignalAnnonce;

public interface AnnonceService {

    public Annonce enregistrerAnnonce(
            String username,
            String imageUrl1,
            String imageUrl2,
            String imageUrl3,
            String titre,
            String categorie,
            String subCat,
            String description,
            String telephone,
            String gouvernorat,
            String ville
    );

    public Annonce supprimerAnnonce(String admin, String idAnnonce, String cause);

    public Annonce commenterAnnonce(String idAnnonce,String contenu,String username);

    public SignalAnnonce signalerAnnonce(String idAnnonce, String cause, String username);

}
