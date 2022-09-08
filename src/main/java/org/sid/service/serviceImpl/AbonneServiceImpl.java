package org.sid.service.serviceImpl;

import java.util.Date;
import java.util.Objects;

import org.sid.dao.AnnonceRepository;
import org.sid.dao.UtilisateurRepository;
import org.sid.entite.Utilisateur;
import org.sid.service.AbonneService;
import org.sid.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AbonneServiceImpl implements AbonneService {
	@Autowired
	private UtilisateurRepository userRepository;
	@Autowired
	private AnnonceRepository annonceRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public Utilisateur inscription(String username, String password, String confirmedPassword, String firstName,
			String lastName, String email) {
		Utilisateur user = userRepository.findUtilisateurByUsername(username);
		if (user != null)
			throw new RuntimeException("L utilisateur existe déjà");
		if (!password.equals(confirmedPassword))
			throw new RuntimeException(" confirmer votre mot de passe");
		Utilisateur appUser = new Utilisateur();
		appUser.setUsername(username);
		appUser.setEmail(email);
		appUser.setNom(firstName);
		appUser.setPrenom(lastName);
		appUser.setEtat(true);
		appUser.setDateInscription(new Date());
		appUser.setImage("./assets/image/profile/default.png");
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		appUser.getRoles().add("ABONNE");
		userRepository.save(appUser);
		return appUser;
	}

	@Override
	public Utilisateur loadUserByUsername(String username) {
		return userRepository.findUtilisateurByUsername(username);
	}

	@Override
	public Utilisateur updateUser(Utilisateur user) {

		Objects.requireNonNull(user);
		Query query = new Query(Criteria.where(SequenceService.MONGO_ID_FIELD).is(user.getId()));

		Update update = constructUpdateForUser(user);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		return mongoOperations.findAndModify(query, update, options, Utilisateur.class);
	}
	
	private Update constructUpdateForUser(Utilisateur user) {
		Update update = new Update();
		
		if(stringValidation(user.getPrenom()))
			update.set("prenom", user.getPrenom());
		if(stringValidation(user.getNom()))
			update.set("nom", user.getNom());
		if(stringValidation(user.getEmail()))
			update.set("email", user.getEmail());
		if(stringValidation(user.getAdresse()))
			update.set("adresse", user.getAdresse());
		if(stringValidation(user.getPassword()))
			update.set("password", bCryptPasswordEncoder.encode(user.getPassword()));
		return update;
	}
	
	private boolean stringValidation(String string) {
		return Objects.nonNull(string) && !string.isEmpty();
	}

	@Override
	public boolean addFavoris(String id) {
		Objects.requireNonNull(annonceRepository.findById(id));
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur user = loadUserByUsername(username);
		boolean added = user.getFavoris().add(id);
		if(added)
			userRepository.save(user);
		return added;
	}
	
	@Override
	public boolean deleteFavoris(String id) {
		Objects.requireNonNull(annonceRepository.findById(id));
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur user = loadUserByUsername(username);
		boolean removed = user.getFavoris().remove(id);
		if(removed)
			userRepository.save(user);
		return removed;
	}

	@Override
	public boolean isFavoris(String id) {
		String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Utilisateur user = loadUserByUsername(username);
		return user.getFavoris().contains(id);
	}

}
