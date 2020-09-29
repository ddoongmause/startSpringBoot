package com.ddoongmause.persistence;

import org.springframework.data.repository.CrudRepository;

import com.ddoongmause.domain.Member;

public interface MemberRepository extends CrudRepository<Member, String> {

}
