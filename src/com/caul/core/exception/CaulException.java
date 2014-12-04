package com.caul.core.exception;

public class CaulException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final int errorCode;

	public CaulException(int errorCode, String s, Throwable throwable) {
		super(s, throwable);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public static class Builder {
		private int errorCode = 500;
		private String message;
		private Throwable throwable;

		public Builder errorCode(int errorCode) {
			this.errorCode = errorCode;
			return this;
		}

		public Builder message(String message) {
			this.message = message;
			return this;
		}

		public Builder throwable(Throwable throwable) {
			this.throwable = throwable;
			return this;
		}

		public CaulException builder() {
			return new CaulException(errorCode, message, throwable);
		}
	}

}
