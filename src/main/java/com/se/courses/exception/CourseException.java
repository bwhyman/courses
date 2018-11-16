package com.se.courses.exception;

public class CourseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CourseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public CourseException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public CourseException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public CourseException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public CourseException() {
		// TODO Auto-generated constructor stub
	}

}
