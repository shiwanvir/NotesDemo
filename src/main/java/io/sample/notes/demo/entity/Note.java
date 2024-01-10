package io.sample.notes.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "note")
public class Note {

	// declaring entity variables
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String title;
	private String description;

	// getters and setters
	public int getId() {
		return id;
	}

	// set id
	public void setId(int id) {
		this.id = id;
	}

	// get id
	public String getTitle() {
		return title;
	}

	// set title
	public void setTitle(String title) {
		this.title = title;
	}

	// get description
	public String getDescription() {
		return description;
	}

	// set description
	public void setDescription(String description) {
		this.description = description;
	}

}
