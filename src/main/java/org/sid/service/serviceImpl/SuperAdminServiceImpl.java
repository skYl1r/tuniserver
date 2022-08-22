package org.sid.service.serviceImpl;

import org.sid.dao.UtilisateurRepository;
import org.sid.entite.Utilisateur;
import org.sid.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {

    @Autowired
    private UtilisateurRepository userRepository;



    @Override
    public Utilisateur ajouterAdmin(String username) {
        Utilisateur appUser = userRepository.findUtilisateurByUsername(username);
        appUser.getRoles().add("ADMIN");
        appUser.setDateAdmin(new Date());
        appUser.setEtatAdmin(true);
        userRepository.save(appUser);
        return appUser;
    }

    @Override
    public Utilisateur retirerAdmin(String username) {
        Utilisateur appUser = userRepository.findUtilisateurByUsername(username);
        appUser.getRoles().remove("ADMIN");
        appUser.setDateAdmin(new Date());
        userRepository.save(appUser);
        return appUser;
    }

    @Override
    public Utilisateur addRoleToUser(String username, String rolename) {
        Utilisateur appUser = userRepository.findUtilisateurByUsername(username);
        appUser.getRoles().add(rolename);
        userRepository.save(appUser);
        return appUser;
    }

}
