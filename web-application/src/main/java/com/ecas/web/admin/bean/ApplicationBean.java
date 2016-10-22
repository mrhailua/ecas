package com.ecas.web.admin.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ManagedBean
@ApplicationScoped
public class ApplicationBean extends BaseBean implements Serializable {
    private static final long serialVersionUID = -6255646714469511339L;
    private Map<String, String> commontTexts = new ConcurrentHashMap<String, String>();

    @PostConstruct
    public void init() {
    }

    /**
     * @return the commontText
     */
    public Map<String, String> getCommontTexts() {
        return commontTexts;
    }

    /**
     * @param commontText the commontText to set
     */
    public void setCommontTexts(Map<String, String> commontText) {
        this.commontTexts = commontText;
    }
}
