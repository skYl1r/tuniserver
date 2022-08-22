package org.sid.service;

import org.sid.entite.Annonce;
import org.sid.entite.Utilisateur;

public interface GestionAbonneService {
    public void enregistrerGestionAbonne(Utilisateur admin, Utilisateur utilisateur, String cause, String type);

}
