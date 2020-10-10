package com.ddoongmause.persistence;


import org.springframework.data.repository.CrudRepository;

import com.ddoongmause.domain.WebBoard;

public interface WebBoardRepository extends CrudRepository<WebBoard, Long>{

}
