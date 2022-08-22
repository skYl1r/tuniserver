package org.sid.service.serviceImpl;


import org.sid.dao.UtilisateurRepository;
import org.sid.entite.Utilisateur;
import org.sid.service.AdminService;
import org.sid.service.GestionAbonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UtilisateurRepository userRepository;

    @Autowired
    private GestionAbonneService gestionAbonneService;

    @Override
    public Utilisateur bloquerAbonne(String admin, String abonne, String cause) {
        Utilisateur ad = userRepository.findUtilisateurByUsername(admin);
        Utilisateur user = userRepository.findUtilisateurByUsername(abonne);
        user.setEtat(false);
        userRepository.save(user);
        gestionAbonneService.enregistrerGestionAbonne(ad,user,cause,"a bloqué");
        return user;
    }



    @Override
    public Utilisateur debloquerAbonne(String admin, String abonne, String cause) {
        Utilisateur ad = userRepository.findUtilisateurByUsername(admin);
        Utilisateur user = userRepository.findUtilisateurByUsername(abonne);
        user.setEtat(true);
        userRepository.save(user);
        gestionAbonneService.enregistrerGestionAbonne(ad,user,cause," a debloqué");
        return user;
    }

    @Override
    public Utilisateur supprimerAbonne(String admin, String abonne, String cause) {
        Utilisateur ad = userRepository.findUtilisateurByUsername(admin);
        Utilisateur user = userRepository.findUtilisateurByUsername(abonne);
        gestionAbonneService.enregistrerGestionAbonne(ad,user,cause,"a supprimé");
        userRepository.delete(user);
        return ad;

    }


}
