package com.ddoongmause.persistence;

import org.springframework.data.repository.CrudRepository;

import com.ddoongmause.domain.WebBoard;

public interface CustomCrudRepository extends CrudRepository<WebBoard, Long>, CustomWebBoard {

}
