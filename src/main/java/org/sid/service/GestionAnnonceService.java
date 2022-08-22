package org.sid.service;

import org.sid.entite.Annonce;
import org.sid.entite.Utilisateur;

public interface GestionAnnonceService {
    public void enregistrerGestionAnnonce(Utilisateur admin, Annonce annonce, String cause, String type);
}
