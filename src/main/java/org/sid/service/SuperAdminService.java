package org.sid.service;

import org.sid.entite.Utilisateur;

public interface SuperAdminService {
        public Utilisateur ajouterAdmin(String username);
        public Utilisateur retirerAdmin(String username);
        public Utilisateur addRoleToUser(String username, String rolename );
}
