/*
 * $$Id: QuoteMessageAction.java,v 1.3 2005/06/07 12:31:54 bel70 Exp $$
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
 *              Dmitry Belov <bel@jresearch.org>
 *
 * ***** END LICENSE BLOCK ***** */
/*
 * Created on Sep 25, 2003
 *
 */
package org.jresearch.gossip.actions.message;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.util.MessageResources;
import org.jresearch.gossip.IConst;
import org.jresearch.gossip.beans.forum.Message;
import org.jresearch.gossip.configuration.Configurator;
import org.jresearch.gossip.exception.ConfiguratorException;
import org.jresearch.gossip.forms.MessageForm;
import org.jresearch.gossip.forms.ProcessMessageForm;

/**
 * DOCUMENT ME!
 * 
 * @author Bel
 */
public class QuoteMessageAction extends GetMessageAction {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.jresearch.gossip.actions.message.GetMessageAction#fillMessageForm(org.jresearch.gossip.forms.MessageForm,
	 *      org.jresearch.gossip.beans.forum.Message)
	 */
	protected String fillMessageForm(MessageForm messageForm, Message mess,
			ProcessMessageForm form, HttpServletRequest request)
			throws ConfiguratorException {
		MessageResources messages = getResources(request);
		messageForm.setTitle(messages.getMessage(Configurator.getInstance()
				.getLocale(IConst.CONFIG.DEFAULT_LOCALE), "messages.RE")
				+ mess.getHeading());

		StringBuffer text = new StringBuffer(mess.getSender());
		text.append(",\n");
		text.append("[QUOTE]");
		text.append(mess.getCentents());
		text.append("[/QUOTE]");
		messageForm.setText(text.toString());
		messageForm.setFid(form.getFid());
		messageForm.setTid(form.getTid());
		messageForm.setBlock(form.getBlock());

		return "addMessageForm";
	}
}