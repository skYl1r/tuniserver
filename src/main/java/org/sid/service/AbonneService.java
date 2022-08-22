package org.sid.service;

import org.sid.entite.Utilisateur;


public interface AbonneService {
    public Utilisateur inscription(String username,String password,String confirmedPassword,String firstName, String lastName,String email);
    public Utilisateur loadUserByUsername(String username);
}
