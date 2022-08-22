package org.sid.service.serviceImpl;


import org.sid.dao.UtilisateurRepository;
import org.sid.entite.Utilisateur;
import org.sid.service.AbonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AbonneServiceImpl implements AbonneService {
    @Autowired
    private UtilisateurRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public Utilisateur inscription(String username, String password, String confirmedPassword,String firstName, String lastName,String email) {
        Utilisateur user = userRepository.findUtilisateurByUsername(username);
        if (user != null) throw new RuntimeException("L utilisateur existe déjà");
        if (!password.equals(confirmedPassword)) throw new RuntimeException(" confirmer votre mot de passe");
        Utilisateur appUser = new Utilisateur();
        appUser.setUsername(username);
        appUser.setEmail(email);
        appUser.setNom(firstName);
        appUser.setPrenom(lastName);
        appUser.setEtat(true);
        appUser.setDateInscription(new Date());
        appUser.setImage("./assets/image/profile/default.png");
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        appUser.getRoles().add("ABONNE");
        userRepository.save(appUser);
        return appUser;
    }

    @Override
    public Utilisateur loadUserByUsername(String username) {
        return userRepository.findUtilisateurByUsername(username);
    }

}



