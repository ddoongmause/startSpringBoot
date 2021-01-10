package com.ddoongmause.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.ddoongmause.domain.Member;
import com.ddoongmause.domain.MemberRole;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DdoongmauseSecurityUser extends User {
	
	private static final String ROLE_PREFIX = "ROLE_";
	
	private Member member;
	
	public DdoongmauseSecurityUser(Member member) {
		super(member.getUid(), "{noop}" + member.getUpw(), makeGrantedAuthority(member.getRoles()));
		this.member = member;
	}
	
	private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles){
		
		List<GrantedAuthority> list = new ArrayList<>();
		
		roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));
		
		return list;
	}
	
	
}
