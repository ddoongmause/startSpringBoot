package com.ddoongmause;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.ddoongmause.domain.PDSBoard;
import com.ddoongmause.domain.PDSFile;
import com.ddoongmause.persistence.PDSBoardRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class PDSBoardTests {
	@Autowired
	PDSBoardRepository repo;
	
	@Test
	public void testInsertPDS() {
		PDSBoard pds = new PDSBoard();
		pds.setPname("Document");
		
		PDSFile file1 = new PDSFile();
		file1.setPdsfile("file1.doc");
		
		PDSFile file2 = new PDSFile();
		file2.setPdsfile("file2.doc");
		
		pds.setFiles(Arrays.asList(file1, file2));
		
		log.info("try to save pds");
		repo.save(pds);
	}
	
	@Transactional
	@Test
	public void testUpdateFileName1() {
		Long fno = 2L;
		String newName = "updatedFile2.doc";
		
		int count = repo.updatePDSFile(fno, newName);
		//@Log 설정된 이후 사용 가능
		log.info("update count: " + count);
	}
	
	@Transactional
	@Test
	public void testupdateFileName2() {
		String newName = "updateddFile2.doc";
		//반드시 번호가 존재하는지 확인할 것
		Optional<PDSBoard> result = repo.findById(2L);
		result.ifPresent(pds -> {
			log.info("데이터가 존재하므로 update 시도");
			PDSFile target = new PDSFile();
			target.setFno(2L);
			target.setPdsfile(newName);
			
			int idx = pds.getFiles().indexOf(target);
			if(idx > -1) {
				List<PDSFile> list = pds.getFiles();
				list.remove(idx);
				list.add(target);
				pds.setFiles(list);
			}
			
			repo.save(pds);
		});
	}
	
	@Transactional
	@Test
	public void deletePDSFile() {
		//첨부 파일 번호
		Long fno = 2L;
		int count = repo.deletePDSFile(fno);
		log.info("DELETE PDSFILE: " + count);
	}
	
	@Test
	public void insertDummies() {
		
		List<PDSBoard> list = new ArrayList<>();
		IntStream.range(1, 100).forEach(i->{
			PDSBoard pds = new PDSBoard();
			pds.setPname("자료" + i);
			
			PDSFile file1 = new PDSFile();
			file1.setPdsfile("file1.doc");
			
			PDSFile file2 = new PDSFile();
			file2.setPdsfile("file2.doc");
			
			pds.setFiles(Arrays.asList(file1, file2));
			
			log.info("try to save pds");
			list.add(pds);
		});
	repo.saveAll(list);
	}
	
	@Test
	public void viewSummary() {
		repo.getSummary().forEach(arr -> log.info(Arrays.toString(arr)));
	}
}
