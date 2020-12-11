/*
jboard is a java bulletin board.
version $Name:  $
http://sourceforge.net/projects/jboard/
Copyright (C) 2003 Charles GAY
This file is part of jboard.
jboard is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

jboard is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with jboard; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/
package net.sf.jboard.fwk.exception;

/**
 * Exception Class thrown when an Access event failed.
 *
 * @author <a href="mailto:diabolo512@users.sourceforge.net ">Charles Gay</a>
 * @version $Revision: 1.1 $
 */
public final class DAOException extends Exception {

	/**
	 * Constructor for DAOException.
	 */
	public DAOException() {
		super();

	}

	/**
	 * Constructor for DAOException with error message.
	 *
	 * @param message
	 *            The custom error message
	 */
	public DAOException(String message) {
		super(message);

	}

	/**
	 * Constructor for DAOException with error message and root
	 * {@link Exception}.
	 *
	 * @param message
	 *            The custom error message
	 * @param cause
	 *            The root exception
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);

	}

	/**
	 * Constructor for DAOException with the root {@link Exception}.
	 *
	 * @param cause
	 *            The root exception
	 */
	public DAOException(Throwable cause) {
		super(cause);

	}

}
