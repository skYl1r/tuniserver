package org.sid.entite;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;


@Document                       //create  Category
@Data                            //getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {
    @Id
    private String id;
    private String nom;
    @DBRef
    private Collection<SousCategorie> sousCategories =new ArrayList<>();

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + nom + '\'' +
                '}';
    }


}
