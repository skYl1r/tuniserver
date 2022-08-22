package org.sid.entite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document("Utilisateur")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Abonne extends Utilisateur{

    private boolean etatAbonne;

}
