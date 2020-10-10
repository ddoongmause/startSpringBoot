package com.ddoongmause.persistence;

import org.springframework.data.repository.CrudRepository;

import com.ddoongmause.domain.Profile;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

}
