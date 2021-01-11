package com.ddoongmause.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ddoongmause.persistence.MemberRepository;

import lombok.extern.java.Log;

@Service
@Log
public class DdoongmauseUsersService implements UserDetailsService {
	
	@Autowired
	MemberRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//User sampleUser = new User(username,"{noop}1111", Arrays.asList(new SimpleGrantedAuthority("ROLE_MANAGER")));
		//repo.findById(username).ifPresent(member -> log.info("" + member));
		return repo.findById(username)
					.filter(m -> m != null)
					.map(m -> new DdoongmauseSecurityUser(m)).get();
	}

}
