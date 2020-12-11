/*
 * $Id: BanListAction.java,v 1.3 2005/06/07 12:32:27 bel70 Exp $
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
 * Created on 31.08.2004
 *
 */
package org.jresearch.gossip.actions.admin.user.ban;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.actions.BaseAction;
import org.jresearch.gossip.am.ban.BanGuard;
import org.jresearch.gossip.am.ban.BanMap;
import org.jresearch.gossip.exception.JGossipException;

/**
 * @author Dmitry Belov
 * 
 */
public class BanListAction extends BaseAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.actions.BaseAction#process(org.apache.struts.action.ActionMapping,
	 *      org.apache.struts.action.ActionForm,
	 *      javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ActionForward process(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws JGossipException {
		BanMap bm = BanGuard.getInstance().getBanMap();
		request.setAttribute(IConst.REQUEST.RECORDS_DATA, bm.getMap());
		return mapping.findForward(IConst.TOKEN.PAGE);
	}

}