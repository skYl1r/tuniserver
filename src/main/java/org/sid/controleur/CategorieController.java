package org.sid.controleur;


import org.sid.entite.SousCategorie;
import org.sid.service.SousCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategorieController {



    @Autowired
    SousCategorieService subCategoryService;



    @PostMapping("/addSubCategory")
public SousCategorie ajouterSousCategorie(@RequestParam(name="idCat") String idCat, @RequestParam(name="nom") String name ){
        return  subCategoryService.saveSousCategorie(idCat,name);
    }


}
