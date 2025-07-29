/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.fokou.slidergame.controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Locale;

/**
 *
 * @author wilfr
 */
@Named("localeBean")
@SessionScoped
public class LocaleBean implements Serializable {

    private String language = "en"; // default

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        FacesContext.getCurrentInstance()
                    .getViewRoot()
                    .setLocale(new Locale(language));
    }

    public Locale getLocale() {
        return new Locale(language);
    }
    
    public void applyLanguage() {
    FacesContext.getCurrentInstance()
                .getViewRoot()
                .setLocale(new Locale(language));
}

}
