package com.ecas.web.helper;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * CAS is a system that help us control authorization
 * Created by mrhailua on 11/18/16.
 */
@ManagedBean(name = "colorHelper")
public class AppColorHelper implements Serializable {
    private static List<String> styles = new ArrayList<String>();

    static {
        styles.add("green-sharp");
        styles.add("blue-sharp");
        styles.add("purple-sharp");
        styles.add("green-haze");
        styles.add("red-haze");
        styles.add("purple-studio");
        styles.add("green-soft");
        styles.add("blue-soft");
        styles.add("red-soft");
        styles.add("purple-soft");
        styles.add("green-meadow");
        styles.add("blue-chambray");
        styles.add("grey-steel");
        styles.add("red-pink");
        styles.add("purple-wisteria");
        styles.add("green-seagreen");
        styles.add("blue-ebonyclay");
        styles.add("grey-cararra");
        styles.add("red-sunglo");
        styles.add("yellow-gold");
        styles.add("yellow-gold");
        styles.add("blue-hoki");
        styles.add("grey-gallery");
        styles.add("red-intense");
        styles.add("yellow-casablanca");
        styles.add("purple-seance");
        styles.add("green-turquoise");
        styles.add("blue-steel");
        styles.add("grey-cascade");
        styles.add("red-thunderbird");
        styles.add("yellow-crusta");
        styles.add("green-jungle");
        styles.add("blue-dark");
        styles.add("grey-silver");
        styles.add("yellow-lemon");
        styles.add("grey-salsa");
        styles.add("red-flamingo");
        styles.add("yellow-saffron");
        styles.add("grey-salt");
        styles.add("purple-plum");
        styles.add("grey-mint");
        styles.add("purple-medium");
    }

    public String getStyle(Integer index) {
        return styles.get(index % styles.size());
    }
}
