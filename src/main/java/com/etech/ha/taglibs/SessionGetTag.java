package com.etech.ha.taglibs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import com.etech.ha.constants.HaConstants;
import com.etech.system.bean.UserInfo;

public class SessionGetTag extends TagSupport {

	private static final long serialVersionUID = 6216185681100265999L;

	/**
     * The name of the scripting variable that will be exposed as a page
     * scope attribute.
     */
    protected String id;
    public String getId() {
        return id;
    }
    public void setId(String string) {
        id = string;
    }

    /**
     * The scope within which the newly defined bean will be creatd.
     */
    protected String toScope = null;
    public String getToScope() {
        return (this.toScope);
    }
    public void setToScope(String toScope) {
        this.toScope = toScope;
    }

    /**
     * subsystem,module,page,key are condition of get sessionvalue.
     */
    //protected String subsystem = "";
    //protected String module = "";
    //protected String page = "";
    protected String key;
    public String getKey() {
        return key;
    }
    public void setKey(String string) {
        key = string;
    }

    // --------------------------------------------------------- Public Methods
    /**
      * Retrieve the required property and expose it as a scripting variable.
      * @exception JspException if a JSP exception has occurred
      */
    public int doStartTag() throws JspException {

        if (id == null || key == null) {
            throw new JspException("toScope is error!");
        }

        // Expose this value as a scripting variable
        int inScope = PageContext.PAGE_SCOPE;
        if (this.toScope != null) {
        	if ("request".equals(toScope))
        		inScope = PageContext.REQUEST_SCOPE;
        }
        
		
        Object value = null;
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		value = ((UserInfo) request.getSession().getAttribute(HaConstants.SESSION_KEY_USER_INFO)).getSessionMap().get(key);
        if (value!=null){
            pageContext.setAttribute(id, value, inScope);
        }
        return SKIP_BODY;
    }
}
