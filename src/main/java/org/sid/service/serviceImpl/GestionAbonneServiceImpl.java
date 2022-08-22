package org.sid.service.serviceImpl;

import org.sid.dao.GestionAbonneRepository;
import org.sid.entite.GestionAbonne;
import org.sid.entite.Utilisateur;
import org.sid.service.GestionAbonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GestionAbonneServiceImpl implements GestionAbonneService {

    @Autowired
    GestionAbonneRepository gestionAbonneRepo ;

    @Override
    public void enregistrerGestionAbonne(Utilisateur admin, Utilisateur abonne ,String cause,String type) {
        GestionAbonne historique=new GestionAbonne();
        historique.setIdAdmin(admin.getUsername());
        historique.setAdmin(admin);
        historique.setAbonne(abonne);
        historique.setIdAbonne(abonne.getUsername());
        historique.setCause(cause);
        historique.setDateGestion(new Date());
        historique.setTypeGestion(type);
        gestionAbonneRepo.save(historique);
    }



}
