package com.ddoongmause.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ddoongmause.domain.WebBoard;
import com.ddoongmause.domain.WebReply;

public interface WebReplyRepository extends CrudRepository<WebReply, Long>{

	@Query("SELECT r FROM WebReply r WHERE r.board = ?1 " + " AND r.rno > 0 ORDER BY r.rno ASC")
	public List<WebReply> getRepliesOfBoard(WebBoard board);
}
