package org.sid.securite;

import org.sid.dao.UtilisateurRepository;
import org.sid.entite.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UtilisateurRepository utilisateurRepo;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) {
		Utilisateur appUser = utilisateurRepo.findUtilisateurByUsername(username);
		if(appUser == null) {
			Utilisateur newUser = new Utilisateur();
			newUser.setUsername(username);
			newUser.setEtat(true);
			newUser.setPassword(bCryptPasswordEncoder.encode(username));
			utilisateurRepo.save(newUser);
			appUser = utilisateurRepo.findUtilisateurByUsername(username);
			appUser.setPassword(bCryptPasswordEncoder.encode(username));
		}
		appUser.setPassword(bCryptPasswordEncoder.encode(username));
		if (appUser == null || !appUser.isEtat())
			throw new UsernameNotFoundException("invalid user");
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		appUser.getRoles().forEach(r -> {
			authorities.add(new SimpleGrantedAuthority(r));
		});
		return new User(appUser.getUsername(), appUser.getPassword(), authorities);
	}
}
