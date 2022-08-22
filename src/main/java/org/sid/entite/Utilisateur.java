package org.sid.entite;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Utilisateur {
    @Id
    private String id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String nom;
    private String prenom;
    private String image;
    private String tel;
    private String email;
    private String adresse;
    private boolean etatAdmin ;
    private boolean etat;
    private Date dateAdmin;
    private Date dateInscription;
    private Collection<String> roles=new ArrayList<>();

    @DBRef
    private Collection<Annonce> annonces=new ArrayList<>();
    private Collection<String> favoris=new ArrayList<>();

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", etat=" + etat +
                ", dateInscription=" + dateInscription +
                ", roles=" + roles +
                ", annonces=" + annonces +
                '}';
    }
}
