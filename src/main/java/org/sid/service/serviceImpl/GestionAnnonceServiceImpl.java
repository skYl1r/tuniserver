package org.sid.service.serviceImpl;

import org.sid.dao.GestionAnnonceRepository;
import org.sid.entite.Annonce;
import org.sid.entite.GestionAnnonce;
import org.sid.entite.Utilisateur;
import org.sid.service.GestionAnnonceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GestionAnnonceServiceImpl implements GestionAnnonceService {
    @Autowired
    GestionAnnonceRepository gestionAnnonceRepo;



    @Override
    public void enregistrerGestionAnnonce(Utilisateur admin, Annonce annonce, String cause, String type) {
        GestionAnnonce historique=new GestionAnnonce();
        historique.setIdAdmin(admin.getUsername());
        historique.setAdmin(admin);
        historique.setAnnonce(annonce);
        historique.setIdAnnonce(annonce.getId());
        historique.setCause(cause);
        historique.setDateGestion(new Date());
        historique.setTypeGestion(type);
        gestionAnnonceRepo.save(historique);
    }
}
