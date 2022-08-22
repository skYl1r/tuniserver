package org.sid.service.serviceImpl;


import org.sid.dao.CategorieRepository;
import org.sid.dao.SousCategorieRepository;
import org.sid.entite.SousCategorie;
import org.sid.entite.Categorie;
import org.sid.service.SousCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SousCategorieServiceImpl implements SousCategorieService {
    @Autowired
    SousCategorieRepository subCategoryRepo ;
    @Autowired
    CategorieRepository categoryRepo;


    @Override
    public SousCategorie saveSousCategorie(String idCat,String name) {

        Categorie c= categoryRepo.findById(idCat).get();
        SousCategorie sc=new SousCategorie();
        sc.setNom(name);
        sc=subCategoryRepo.save(sc);
        c.getSousCategories().add(sc);
        categoryRepo.save(c);

        return sc;
    }

}
