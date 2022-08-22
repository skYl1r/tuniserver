package org.sid.entite;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignalAnnonce {
    @Id
    private String id;
    private Date date;
    private String cause;
    private  String idAbonne;
    private  String idAnnonce;
}
