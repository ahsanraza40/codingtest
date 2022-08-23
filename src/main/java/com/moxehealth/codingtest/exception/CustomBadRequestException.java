/**
 * 
 */
package com.moxehealth.codingtest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ahsan Raza
 *
 */

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomBadRequestException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomBadRequestException(String message) {
		super(message);
	}
}
