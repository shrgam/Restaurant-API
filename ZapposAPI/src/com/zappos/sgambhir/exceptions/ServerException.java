package com.zappos.sgambhir.exceptions;

import java.io.Serializable;

public class ServerException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

		public ServerException() {
			super();
		}

		public ServerException(String msg) {
			super(msg);
		}

		public ServerException(String msg, Exception e) {
			super(msg, e);
		}
}
