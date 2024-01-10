package io.sample.notes.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.sample.notes.demo.entity.Note;

public interface NoteRepository  extends JpaRepository<Note,Integer>{
	
	

}
