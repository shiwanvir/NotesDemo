package io.sample.notes.demo.util;

public class CustomResponse<T> {

	private T data;
	private String message;
	private int statusCode;

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
