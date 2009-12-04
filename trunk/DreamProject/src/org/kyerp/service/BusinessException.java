/**
 * 
 */
package org.kyerp.service;

/**
 * @author y109 2009-11-24下午02:09:05
 */

public class BusinessException extends RuntimeException {

	private static final long	serialVersionUID	= -7501061903198638656L;

	public BusinessException() {
		super();
	}

	public BusinessException(String msg) {
		super(msg);
	}

	public BusinessException(String msg, Throwable e) {
		super(msg, e);
	}

	@Override
	public String toString() {
		if (this.getCause() != null) {
			return this.getCause().getMessage();
		}
		return this.getMessage();
	}

}
