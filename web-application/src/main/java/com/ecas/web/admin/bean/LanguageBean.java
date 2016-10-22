package com.ecas.web.admin.bean;

import com.ecas.utils.LocaleHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.Locale;

@ManagedBean(name = "languageBean")
@SessionScoped
public class LanguageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String DEFAULT_LANG = "en";
    public static final String LANGE_PARAM = "lang";
    static Logger log = Logger.getLogger(LanguageBean.class.getName());
    private String localeCode;

    @PostConstruct
    public void init() {
        try {
            localeCode = StringUtils.isNotEmpty(LocaleHolder.getParamLocale()) ? LocaleHolder
                    .getParamLocale() : LocaleHolder.getLocale();
            if (StringUtils.isBlank(localeCode)) {
                localeCode = DEFAULT_LANG;
            }
        } catch (Exception ex) {
            localeCode = "en";
            log.error("No Locale found", ex);
        }
    }

    public String getLocaleCode() {
        if (StringUtils.isNotEmpty(LocaleHolder.getParamLocale())) {
            localeCode = LocaleHolder.getParamLocale();
            return localeCode;
        }
        return StringUtils.EMPTY;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public void determineLocale(ActionEvent actionEvent) {
        localeCode = (String) actionEvent.getComponent().getAttributes().get("countryCode");
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(localeCode));

    }

}
