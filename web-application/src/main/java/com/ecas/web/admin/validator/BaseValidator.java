package com.ecas.web.admin.validator;

import com.ecas.domain.User;
import com.ecas.service.ServiceRegistry;
import com.ecas.web.admin.bean.BaseBean;
import com.sun.faces.component.visit.FullVisitContext;
import org.primefaces.util.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Keep common API for validator
 */
public abstract class BaseValidator {
    @Autowired
    private ServiceRegistry serviceRegistry;

    protected String getStringMessage(String messageId, Object... objs) {
        return MessageFactory.getFormattedText(FacesContext.getCurrentInstance().getApplication().getDefaultLocale(), FacesContext
                .getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString(messageId), objs);
    }

    public User getCurrentUser() {
        Object obj = getSession().getAttribute(BaseBean.USER_CONTEXT_SESSION_KEY);
        if (obj != null) {
            return (User) obj;
        }
        return null;
    }

    protected UIComponent findCompnent(final String id) {

        FacesContext context = FacesContext.getCurrentInstance();
        UIViewRoot root = context.getViewRoot();
        final UIComponent[] found = new UIComponent[1];

        root.visitTree(new FullVisitContext(context), new VisitCallback() {
            @Override
            public VisitResult visit(VisitContext context, UIComponent component) {
                if (component.getId().equals(id)) {
                    found[0] = component;
                    return VisitResult.COMPLETE;
                }
                return VisitResult.ACCEPT;
            }
        });
        return found[0];
    }

    protected HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }
}
