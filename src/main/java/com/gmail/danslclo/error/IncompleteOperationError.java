package com.gmail.danslclo.error;

public class IncompleteOperationError extends Error {
	private static final long serialVersionUID = 1848768847036674538L;

	public IncompleteOperationError(String message) {
		super(message);
	}
}
