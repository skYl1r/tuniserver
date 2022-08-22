package org.sid.service;

import org.sid.entite.Utilisateur;

public interface AdminService {
    public Utilisateur bloquerAbonne(String admin, String abonne, String cause);
    public Utilisateur debloquerAbonne(String admin, String abonne, String cause);
    public Utilisateur supprimerAbonne(String admin, String abonne, String cause);

}
