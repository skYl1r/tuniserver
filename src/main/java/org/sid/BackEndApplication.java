package org.sid;

import org.sid.dao.AnnonceRepository;
import org.sid.dao.CategorieRepository;
import org.sid.dao.GestionAbonneRepository;
import org.sid.dao.GestionAnnonceRepository;
import org.sid.dao.SousCategorieRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entite.Annonce;
import org.sid.entite.Categorie;
import org.sid.entite.Utilisateur;
import org.sid.service.AbonneService;
import org.sid.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@EnableMongoRepositories(basePackages = "org.sid.dao")
public class BackEndApplication {

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }




    @Bean
    CommandLineRunner star(AbonneService abonneService, UtilisateurRepository userRepo ,SuperAdminService superAdminService){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Categorie.class);
            repositoryRestConfiguration.exposeIdsFor(Annonce.class);
            repositoryRestConfiguration.exposeIdsFor(Utilisateur.class);
//            userRepo.deleteAll();
//
//
//            Stream.of("user1","user2","user3","admin","root").forEach(un->{
//                abonneService.inscription(un,"1234","1234","Alaeddine","souissi","aladinsws@gmail.com");
//            });
//
//
//            userRepo.findAll().forEach(System.out::println);
//            superAdminService.addRoleToUser("root","SuperAdmin");
//            superAdminService.ajouterAdmin("root");
//            superAdminService.ajouterAdmin("admin");
//
//
//
        };
    }

    @Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }



    @Bean
    CommandLineRunner start(GestionAnnonceRepository gestAnnonce, GestionAbonneRepository gestAbonne, CategorieRepository categoryRepo, SousCategorieRepository subCategoryRepo, AnnonceRepository annonceRepo){
        return args -> {
//            List<Annonce> annoncesLis=annonceRepo.findAll();
//            rechercheRepo.saveAll(annoncesLis);
//            rechercheRepo.deleteAll();
//            rechercheRepo.findAnnonceByMotCle("moto").forEach(System.out::println);

//            gestAnnonce.deleteAll();
//            gestAbonne.deleteAll();
//           Annonce a=annonceRepo.findAnnonceById("5cfc3e80202ad13c189ad75b");
//        annonceRepo.delete(a);


//            categoryRepo.deleteAll();
//            Stream.of("1 Immeuble","2 Vehicule","3 Informatique").forEach( c->{
//                categoryRepo.save(new Categorie(c.split(" ")[0],c.split(" ")[1],new ArrayList<>()) );
//            } );
//            categoryRepo.findAll().forEach(System.out::println);
//
//            subCategoryRepo.deleteAll();
//            Categorie c1=categoryRepo.findById("1").get();
//            Stream.of("11 Maison","12 Appartement","13 Colocation").forEach(sc->{
//                SousCategorie sc1=subCategoryRepo.save(new SousCategorie(sc.split(" ")[0],sc.split(" ")[1],new ArrayList<>()) );
//                c1.getSousCategories().add(sc1);
//                categoryRepo.save(c1);
//            });
//
//            Categorie c2=categoryRepo.findById("2").get();
//            Stream.of("21 Voiture","22 Moto").forEach(sc->{
//                SousCategorie sc2=subCategoryRepo.save(new SousCategorie(sc.split(" ")[0],sc.split(" ")[1],new ArrayList<>()) );
//                c2.getSousCategories().add(sc2);
//                categoryRepo.save(c2);
//            });
//
//            Categorie c3=categoryRepo.findById("3").get();
//            Stream.of("31 Pc","32 playstation","33 ordinateur").forEach(sc->{
//                SousCategorie sc3=subCategoryRepo.save(new SousCategorie(sc.split(" ")[0],sc.split(" ")[1],new ArrayList<>()) );
//                c3.getSousCategories().add(sc3);
//                categoryRepo.save(c3);
//            });
//            subCategoryRepo.findAll().forEach(System.out::println);

//            annonceRepo.deleteAll();
//            SousCategorie sc1=subCategoryRepo.findById("11").get();
//            Stream.of("annonce1","annonce2","annonce3").forEach(an->{
//                Annonce annonce=new Annonce();
//                annonce.setTitle(an);
//                annonce.setSubCategory(sc1);
//                Annonce a=annonceRepo.save(annonce);
//                sc1.getAnnonces().add(a);
//                subCategoryRepo.save(sc1);
//            });
//            annonceRepo.findAll().forEach(System.out::println);
        };
    }
}

