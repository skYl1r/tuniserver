package org.sid.entite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GestionAnnonce {
    @Id
    private String id;
    private Date dateGestion;
    private String typeGestion;
    private String cause;
    private  String idAnnonce;
    private  String idAdmin;

    @DBRef
    private Annonce annonce;
    @DBRef
    private Utilisateur admin;
}
