package io.sample.notes.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.sample.notes.demo.entity.Note;

//this interface extended by JPA repository to perform CRUD operations with Mysql database
public interface NoteRepository  extends JpaRepository<Note,Integer>{
	
	

}
