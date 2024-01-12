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
		try {
			// check NoteisValid object
			if (isValidNoteObject(note)) {

				Note savedNote = noteRepository.save(note);
				// check saved status
				if (savedNote != null) {
					// if saving successful return the Response with saved object and HTTP status
					return new CustomResponse<>(savedNote, "Note saved sucessfully", HttpStatus.CREATED.value());
				} else {
					// if saving failed return the Response with saved object and HTTP status
					return new CustomResponse<>(null, "Faild to create note", HttpStatus.INTERNAL_SERVER_ERROR.value());
				}
			} else {
				return new CustomResponse<>(null, "Invalid Note object", HttpStatus.BAD_REQUEST.value());
			}
		} catch (Exception e) {
			// Handle any unexpected exceptions that might occur during the save process
			return new CustomResponse<>(null, "An error occurred while saveing the note " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	// get all note from database
	public CustomResponse<List<Note>> getAllNotes() {
		try {
			// get list of existing notes using JPA
			List<Note> allNotes = noteRepository.findAll();
			// check if note list is empty
			if (!allNotes.isEmpty()) {
				// if note list is not empty return list of notes with HTTP status
				return new CustomResponse<>(allNotes, "Notes retrived sucessfully", HttpStatus.OK.value());
			} else {
				// if list is empty return null with HTTP status
				return new CustomResponse<>(null, "No notes found", HttpStatus.NOT_FOUND.value());
			}
		} catch (Exception e) {
			// Handle any unexpected exceptions that might occur during the retrieving
			// process
			return new CustomResponse<>(null, "An error occurred while retiving the notes " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
	}

	// get one note from database

	public CustomResponse<Note> getNote(int id) {
		try {
			// check if note is available in the database
			Note note = noteRepository.findById(id).orElse(null);
			if (note != null) {
				// if available return note object with HTTP status
				return new CustomResponse<>(note, "Note retrived sucessfully", HttpStatus.OK.value());
			} else {
				// if not available return null with HTTP status
				return new CustomResponse<>(null, "Note not found", HttpStatus.NOT_FOUND.value());
			}
		} catch (Exception e) {
			// Handle any unexpected exceptions that might occur during the retrieving
			// process
			return new CustomResponse<>(null, "An error occurred while retiving the note " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR.value());

		}
	}

	// Update note from database

	public CustomResponse<Note> updateNote(Note note) {
		try {
			// check NoteisValid object
			if (isValidNoteObject(note)) {
				// check if note is available in the database
				if (noteRepository.existsById(note.getId())) {
					// if note is available assign it to note object
					Note exsistingNote = noteRepository.findById(note.getId()).orElse(null);

					exsistingNote.setTitle(note.getTitle());
					exsistingNote.setDescription(note.getDescription());
					// save updated object in to database
					Note updatedNote = noteRepository.save(exsistingNote);
					// return response with updated object and HTTP status
					return new CustomResponse<>(updatedNote, "Note updated sucessfully", HttpStatus.OK.value());

				} else {
					// if note is not found return empty object with HTTP status
					return new CustomResponse<>(null, "Note not found for updating", HttpStatus.NOT_FOUND.value());
				}
			} else {
				return new CustomResponse<>(null, "Invalid Note object", HttpStatus.BAD_REQUEST.value());
			}
		}

		catch (Exception e) {
			// Handle any unexpected exceptions that might occur during the update process
			return new CustomResponse<>(null, "An error occurred while updating the note" + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR.value());

		}
	}

	// Delete note

	public CustomResponse<String> deleteNote(int id) {
		try {
			// check note is available in the database by id
			if (noteRepository.existsById(id)) {
				// if note is available delete note
				noteRepository.deleteById(id);
				// return success message with HTTP status
				return new CustomResponse<>(null, ("Note with ID " + id + " deleted successfully."),
						HttpStatus.OK.value());
			} else {
				// if deletion valid return error response with HTTP status
				return new CustomResponse<>(null, "Note not found", HttpStatus.NOT_FOUND.value());
			}
		}

		catch (Exception e) {
			// Handle any unexpected exceptions that might occur during the deletion process
			return new CustomResponse<>(null, "An error occurred while updating the note " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR.value());

		}
	}

	// this will perform server side validation
	public boolean isValidNoteObject(Note note) {
		// check mandatory field values are empty
		if (note.getTitle().isEmpty() || note.getDescription().isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

}
