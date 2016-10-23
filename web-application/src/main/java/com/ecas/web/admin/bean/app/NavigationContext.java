package com.ecas.web.admin.bean.app;

import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

@ManagedBean
@ApplicationScoped
public class NavigationContext implements Serializable {

    private static final long serialVersionUID = 20111020L;
    private static HashMap<String, Set<String>> headerNavigationTree = new HashMap<String, Set<String>>();

    static {
        headerNavigationTree.put("dashboard", ImmutableSet.of("dashboard"));
        headerNavigationTree.put("content", ImmutableSet.of("contents","handle-content","images","categories"));
        headerNavigationTree.put("page", ImmutableSet.of("page-design", "pages","resources"));
        headerNavigationTree.put("admin", ImmutableSet.of("admin"));
        headerNavigationTree.put("general", ImmutableSet.of("general", "location", "translation"));
        headerNavigationTree.put("saasService", ImmutableSet.of("organization", "order", "appText", "saasService"));
        headerNavigationTree.put("profile", ImmutableSet.of("profile", "notification"));
    }

    public String getActiveClass(final String page) {
        final String viewId = getViewId();
        if (viewId != null && viewId.equals(page)) {
            return "active";
        }

        return StringUtils.EMPTY;
    }

    public String getActiveHeaderClass(final String page) {
        Set<String> posibleChildPages = headerNavigationTree.get(page);
        if (posibleChildPages != null && posibleChildPages.contains(getViewId())) {
            return "active-header";
        }
        return StringUtils.EMPTY;
    }

    private String getViewId() {
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
}