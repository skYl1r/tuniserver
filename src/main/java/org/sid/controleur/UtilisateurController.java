package org.sid.controleur;

import lombok.Data;
import org.sid.entite.Utilisateur;
import org.sid.service.AbonneService;
import org.sid.service.AdminService;
import org.sid.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UtilisateurController {

    @Autowired
    private SuperAdminService superAdminService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AbonneService abonneService;


    @PostMapping("/inscription")
    public Utilisateur inscription(@RequestBody UserForm userForm){
        return abonneService.inscription(
                userForm.getUsername(),
                userForm.getPassword(),
                userForm.getPassword(),
                userForm.getNom(),
                userForm.getPrenom(),
                userForm.getEmail()

        );

    }

    @PostMapping("/bloquerAbonne")
    public Utilisateur bloquer(@RequestParam(name="admin") String admin,@RequestParam(name="abonne") String abonne,@RequestParam(name="cause") String cause){
        return adminService.bloquerAbonne(admin,abonne,cause);
    }
    @PostMapping("/debloquerAbonne")
    public Utilisateur debloquer(@RequestParam(name="admin") String admin,@RequestParam(name="abonne") String abonne,@RequestParam(name="cause") String cause){
        return adminService.debloquerAbonne(admin,abonne,cause);
    }

    @PostMapping("/supprimerAbonne")
    public Utilisateur supprimer(@RequestParam(name="admin") String admin,@RequestParam(name="abonne") String abonne,@RequestParam(name="cause") String cause){
        return adminService.supprimerAbonne(admin,abonne,cause);
    }

    @PostMapping("/ajouterAdmin")
    public Utilisateur ajouterAdmin(@RequestParam(name="username") String username){
        return superAdminService.ajouterAdmin(username);
    }
    @PostMapping("/retirerAdmin")
    public Utilisateur retirerAdmin(@RequestParam(name="username") String username){
        return superAdminService.retirerAdmin(username);
    }



}


@Data
class UserForm{
    private String nom;
    private String prenom;
    private String username;
    private String password;
    private String email;
}
