package io.sample.notes.demo.util;

/*
 * this generic class is created to handle custom response and  this class do not contain any setter functions
 *  in order to maintain stability of response objects through out the project
 */

public class CustomResponse<T> {
	//allows pass generic type
	private T data;
	//response message
	private String message;
	//response code
	private int statusCode;
	
	//custom response constructor
	public CustomResponse(T data, String message, int statusCode) {
		this.data = data;
		this.message = message;
		this.statusCode = statusCode;
	}
	 

	public T getData() {
		return data;
	}

	public String getMessage() {
		return message;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

}
