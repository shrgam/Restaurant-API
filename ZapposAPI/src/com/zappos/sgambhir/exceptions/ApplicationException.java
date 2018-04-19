package com.zappos.sgambhir.exceptions;

import java.io.Serializable;

/**
 * @author Shriya
 *
 */
public class ApplicationException extends Exception implements Serializable {

	private static final long serialVersionUID = 1L;

		public ApplicationException() {
			super();
		}

		/**
		 * @param msg
		 */
		public ApplicationException(String msg) {
			super(msg);
		}

		/**
		 * @param msg
		 * @param e
		 */
		public ApplicationException(String msg, Exception e) {
			super(msg, e);
		}
}