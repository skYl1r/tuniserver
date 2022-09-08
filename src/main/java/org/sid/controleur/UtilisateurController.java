package org.sid.controleur;

import org.sid.entite.Utilisateur;
import org.sid.service.AbonneService;
import org.sid.service.AdminService;
import org.sid.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class UtilisateurController {

	public static final String ERROR_MSG_FAVORIS = "Une error c'est produit lors du modification de la list des favoris";
	
    @Autowired
    private SuperAdminService superAdminService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AbonneService abonneService;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


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

    @PostMapping("/updateUser")
    public Utilisateur updateUser(@RequestBody Utilisateur user) {
    	return abonneService.updateUser(user);
    }
    
    @PatchMapping("/addFavoris")
    public ResponseEntity<String> addFavoris(@RequestParam String id) {
    	if(abonneService.addFavoris(id)) {
    		return ResponseEntity.ok().build();
    	}
    	return new ResponseEntity<String>(ERROR_MSG_FAVORIS,
    			HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @PatchMapping("/deleteFavoris")
    public ResponseEntity<String> deleteFavoris(@RequestParam String id) {
    	if(abonneService.deleteFavoris(id)) {
    		return ResponseEntity.ok().build();
    	}
    	return new ResponseEntity<String>(ERROR_MSG_FAVORIS,
    			HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/isFavoris")
    public Boolean isFavoris(@RequestParam String id) {
    	return abonneService.isFavoris(id);
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
