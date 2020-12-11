package net.eyde.personalblog.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.eyde.personalblog.service.PersonalBlogService;
import net.eyde.personalblog.struts.form.SearchForm;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public final class SearchAction extends BlogGeneralAction {

	private static Log log = LogFactory.getLog(SearchAction.class);
	
    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an ActionForward instance describing where and how
     * control should be forwarded, or null if the response has
     * already been completed.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward executeSub(ActionMapping mapping, ActionForm form,
        HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        HttpSession session = request.getSession();
        ActionErrors errors = new ActionErrors();

        // Get instance of PersonalBlog Service
        PersonalBlogService pblog = PersonalBlogService.getInstance();

        // Obtain all the request parameters from the NewPostForm
        SearchForm searchForm = (SearchForm) form;

        // Set Request Parameters
        request.setAttribute("posts",
            pblog.getPostsBySearch(searchForm.getSearch()));


        if (!errors.isEmpty()) {
            saveErrors(request, errors);
        }

        return (mapping.findForward("success"));
    }
}
