/*
 * $Id: Ban.java,v 1.3 2005/06/07 12:32:26 bel70 Exp $
 *
 * ***** BEGIN LICENSE BLOCK *****
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in 
 * compliance with the License. You may obtain a copy of the License 
 * at http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and 
 * limitations under the License.
 *
 * The Original Code is JGossip forum code.
 *
 * The Initial Developer of the Original Code is the JResearch, Org. 
 * Portions created by the Initial Developer are Copyright (C) 2004 
 * the Initial Developer. All Rights Reserved. 
 * 
 * Contributor(s): 
 *              Dmitriy Belov <bel@jresearch.org>
 *               .
 * * ***** END LICENSE BLOCK ***** */
/*
 * Created on 29.08.2004
 *
 */
package org.jresearch.gossip.beans;

/**
 * @author Dmitry Belov
 * 
 */
public class Ban {

	private int type;

	private String mask;

	/**
	 * 
	 */
	public Ban() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param type
	 * @param mask
	 */
	public Ban(int type, String mask) {
		super();
		if (type <= 0 || null == mask) {
			throw new IllegalArgumentException(
					"incorrect values of type and/or mask");
		}
		this.type = type;
		this.mask = mask;
	}

	/**
	 * @return Returns the mask.
	 */
	public String getMask() {
		return mask;
	}

	/**
	 * @param mask
	 *            The mask to set.
	 */
	public void setMask(String mask) {
		this.mask = mask;
	}

	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(int type) {
		this.type = type;
	}
}