package com.ecas.web.admin.bean;

import com.ecas.domain.User;
import com.ecas.security.SecurityUtil;
import com.ecas.service.ServiceRegistry;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.util.MessageFactory;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

public abstract class BaseBean implements Serializable {
    static Logger log = Logger.getLogger(BaseBean.class.getName());

    public static final String USER_CONTEXT_SESSION_KEY = "WORKER_CONTEXT_SESSION";
    public static final String DEFAULT_DATE_FORMAT = "dd/MM/yyyy";
    private static final long serialVersionUID = 20120423L;
    @ManagedProperty(value = "#{serviceRegistry}")
    private ServiceRegistry serviceRegistry;

    @ManagedProperty(value = "#{userDetailsService}")
    private UserDetailsService userDetailsService;

    public ServiceRegistry getServiceRegistry() {
        return serviceRegistry;
    }

    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }

    protected void addInfoMessage(String messageId, Object... objs) {
        FacesContext.getCurrentInstance()
                .addMessage(
                        "notification",
                        new FacesMessage(FacesMessage.SEVERITY_INFO, MessageFactory.getFormattedText(
                                FacesContext.getCurrentInstance().getApplication().getDefaultLocale(),
                                FacesContext.getCurrentInstance().getApplication()
                                        .getResourceBundle(FacesContext.getCurrentInstance(), "msg")
                                        .getString(messageId), objs), null));

    }

    protected String getStringMessage(String messageId, Object... objs) {
        return MessageFactory.getFormattedText(
                FacesContext.getCurrentInstance().getApplication().getDefaultLocale(),
                FacesContext.getCurrentInstance().getApplication()
                        .getResourceBundle(FacesContext.getCurrentInstance(), "msg").getString(messageId), objs);
    }

    protected void addWarnMessage(String messageId, Object... objs) {
        FacesContext.getCurrentInstance()
                .addMessage(
                        "notification",
                        new FacesMessage(FacesMessage.SEVERITY_WARN, MessageFactory.getFormattedText(
                                FacesContext.getCurrentInstance().getApplication().getDefaultLocale(),
                                FacesContext.getCurrentInstance().getApplication()
                                        .getResourceBundle(FacesContext.getCurrentInstance(), "msg")
                                        .getString(messageId), objs), null));
    }

    protected void redirect(String path) throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + path);
    }

    protected void hideDialog(String dialogId) {
        RequestContext.getCurrentInstance().execute("PF('" + dialogId + "').hide()");
    }

    protected void showDialog(String dialogId) {
        RequestContext.getCurrentInstance().execute(dialogId + ".show()");
    }

    public String getErrorMessage(final String clientId) {
        Iterator<FacesMessage> iter = FacesContext.getCurrentInstance().getMessages(clientId);
        if (iter.hasNext()) {
            return iter.next().getDetail(); // or getSummary()
        }
        return "";
    }

    protected void reloadWorkerContext() {
        getSession().setAttribute(USER_CONTEXT_SESSION_KEY,
                (User) userDetailsService.loadUserByUsername(SecurityUtil.getCurrentUser()));
    }

    protected Object getAttribute(ActionEvent actionEvent, Object key) {
        return actionEvent.getComponent().getAttributes().get(key);
    }

    public User getCurrentUser() {
        Object obj = getSession().getAttribute(USER_CONTEXT_SESSION_KEY);
        if (obj != null) {
            return (User) obj;
        }
        return null;
    }

    protected HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public boolean filterDateGte(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase(locale);
        if (StringUtils.isBlank(filterText)) {
            return true;
        }
        if (value == null) {
            return false;
        }
        try {
            String datavalue = (String) filterText;
            DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
            Date startDate = df.parse(datavalue);
            return startDate.compareTo((Date) value) <= 0;
        } catch (ParseException e) {
            log.warn("Parse to filter data fail", e);
            return false;
        }
    }

    public boolean filterDateLte(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase(locale);
        if (StringUtils.isBlank(filterText)) {
            return true;
        }
        if (value == null) {
            return false;
        }

        try {
            String datavalue = (String) filter;
            DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
            Date endDate = df.parse(datavalue);
            return endDate.compareTo((Date) value) >= 0;
        } catch (ParseException e) {
            log.warn("Parse to filter data fail", e);
            return false;
        }
    }

    public boolean filterSameDate(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim().toLowerCase(locale);
        if (StringUtils.isBlank(filterText)) {
            return true;
        }
        if (value == null) {
            return false;
        }

        try {
            String datavalue = (String) filter;
            DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
            Date filterDate = df.parse(datavalue);
            return DateUtils.isSameDay(filterDate, (Date) value);
        } catch (ParseException e) {
            log.warn("Parse to filter data fail", e);
            return false;
        }
    }

    public String getViewId() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String viewId = fc.getViewRoot().getViewId();
        String selectedComponent;
        if (viewId != null) {
            selectedComponent = viewId.substring(viewId.lastIndexOf("/") + 1, viewId.lastIndexOf("."));
        } else {
            selectedComponent = null;
        }

        return selectedComponent;
    }

    protected void reloadHeaderMessage() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        MessageReloader messageReloader = (MessageReloader) elContext.getELResolver().getValue(elContext, null,
                "userMessageBean");
        messageReloader.loadMessages();
    }

    protected void update(String updateClientId) {
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add(updateClientId);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
