package org.sid.service;

import org.sid.entite.Utilisateur;


public interface AbonneService {
    public Utilisateur inscription(String username,String password,String confirmedPassword,String firstName, String lastName,String email);
    public Utilisateur loadUserByUsername(String username);
    public Utilisateur updateUser(Utilisateur user);
    public boolean addFavoris(String id);
    public boolean deleteFavoris(String id);
    public boolean isFavoris(String id);
}
