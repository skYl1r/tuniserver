package org.sid.entite;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire {
    String contenu;
    Date date;
    String username;
}
