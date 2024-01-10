package io.sample.notes.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import io.sample.notes.demo.entity.Note;
import io.sample.notes.demo.repository.NoteRepository;
import io.sample.notes.demo.util.CustomResponse;

@Service
public class NoteService {

	// injecting repository into Service
	@Autowired
	private NoteRepository noteRepository;

	// save single note in to database
	public CustomResponse<Note> saveNote(Note note) {

		Note savedNote = noteRepository.save(note);
		if (savedNote != null) {
			return new CustomResponse<>(savedNote, "Note saved sucessfully", HttpStatus.CREATED.value());
		} else {
			return new CustomResponse<>(null, "Faild to create note", HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	// get all note from database
	public CustomResponse<List<Note>> getAllNotes() {
		List<Note> allNotes = noteRepository.findAll();
		if (!allNotes.isEmpty()) {
			return new CustomResponse<>(allNotes, "Notes retrived sucessfully", HttpStatus.OK.value());
		} else {
			return new CustomResponse<>(null, "No notes found", HttpStatus.NOT_FOUND.value());
		}

	}

	// get one note

	public CustomResponse<Note> getNote(int id) {

		Note note = noteRepository.findById(id).orElse(null);
		if (note != null) {
			return new CustomResponse<>(note, "Note retrived sucessfully", HttpStatus.OK.value());
		} else {
			return new CustomResponse<>(null, "Note not found", HttpStatus.NOT_FOUND.value());
		}
	}

	// Update note

	public CustomResponse<Note> updateNote(Note note) {
		// check if note is available in the database
		if (noteRepository.existsById(note.getId())) {
			Note exsistingNote = noteRepository.findById(note.getId()).orElse(null);

			exsistingNote.setTitle(note.getTitle());
			exsistingNote.setDescription(note.getDescription());
			Note updatedNote = noteRepository.save(exsistingNote);

			return new CustomResponse<>(updatedNote, "Note updated sucessfully", HttpStatus.OK.value());

		} else {

			return new CustomResponse<>(null, "Note not found for updating", HttpStatus.NOT_FOUND.value());
		}

	}

	// Delete note

	public CustomResponse<String> deleteNote(int id) {
		if (noteRepository.existsById(id)) {
			noteRepository.deleteById(id);
			return new CustomResponse<>(null, ("Note with ID " + id + " deleted successfully."), HttpStatus.OK.value());
		} else {
			return new CustomResponse<>(null, "Note not found", HttpStatus.NOT_FOUND.value());
		}
	}

}
