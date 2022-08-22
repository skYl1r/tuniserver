package org.sid.entite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Collection;

@Document                       //create collection Category
@Data                            //getters and setters
@AllArgsConstructor
@NoArgsConstructor
public class SousCategorie {
    @Id
    private String id;
    private String nom;

//    @JsonManagedReference(value="sc-annonces")
    @DBRef
    private Collection<Annonce> annonces=new ArrayList<>();

    @Override
    public String toString() {
        return "SubCategory{" +
                "id='" + id + '\'' +
                ", name='" + nom + '\'' +
                '}';
    }
}
