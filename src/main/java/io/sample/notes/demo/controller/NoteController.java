package io.sample.notes.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.sample.notes.demo.entity.Note;
import io.sample.notes.demo.service.NoteService;
import io.sample.notes.demo.util.CustomResponse;

@RestController

//allow all clients to communicate with REST application just for development purposes
@CrossOrigin(origins = "*")
public class NoteController {
	// injecting noteService in to controller
	@Autowired
	private NoteService noteService;

	// save note into database
	@PostMapping("/notes")
	public ResponseEntity<CustomResponse<Note>> addNote(@RequestBody Note note) {
		// crating a customResponse using return value from noteServe
		CustomResponse<Note> response = noteService.saveNote(note);

		// return Response with response object and HTTP status
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));

	}

	// get All Notes at once
	@GetMapping("/notes")
	public ResponseEntity<CustomResponse<List<Note>>> getAllNotes() {
		// crating a customResponse using return value from noteServe with list of notes
		// objects
		CustomResponse<List<Note>> response = noteService.getAllNotes();

		// return Response with response object and HTTP status
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
	}

	// find note by id
	@GetMapping("/notes/{id}")
	public ResponseEntity<CustomResponse<Note>> getNoteById(@PathVariable int id) {
		// crating a customResponse using return value from noteServe
		CustomResponse<Note> response = noteService.getNote(id);

		// return Response with response object and HTTP status
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
	}

	// update note
	@PutMapping("/update")
	public ResponseEntity<CustomResponse<Note>> updateNote(@RequestBody Note note) {
		// crating a customResponse using return value from noteServe
		CustomResponse<Note> response = noteService.updateNote(note);

		// return Response with response object and HTTP status
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
	}

	// delete note using id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CustomResponse<String>> deleteNote(@PathVariable int id) {
		// crating a customResponse using return value from noteServe
		CustomResponse<String> response = noteService.deleteNote(id);
		// return Response with response object and HTTP status
		return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
	}

}
