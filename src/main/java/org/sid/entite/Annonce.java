package org.sid.entite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Document
@CompoundIndex(name = "title_description_index", def = "{'titre' : 'text', 'description': 'text'}")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Annonce {
    @Id
    private String id;
    private String image1;
    private String image2;
    private String image3;
    private String titre;
    private String description;
    private String type;
    private String tel;
    private String gouvernorat;
    private String ville;
    private Date datePublication ;
    private Boolean disponible ;
    private String prixLocation;
    private boolean etatSuppression;
    private List<String> echangePar;
    private Collection<String> Suggestion=new ArrayList<>();
    private  Collection<Commentaire> commentaires=new ArrayList<>();
    private String categorie;
    private String sousCategorie;
    private String username;

}
