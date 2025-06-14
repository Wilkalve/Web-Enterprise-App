/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.foko0002.slider;

import cst8218.foko0002.slider.entity.Slider;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author wilfr
 */

@Named("sliderBean")
// Keeps the data available while interacting with the page
@ViewScoped 
public class SliderBean implements Serializable {

    @EJB
    private SliderFacadeREST sliderService;

    private List<Slider> sliders;

    @PostConstruct
    public void init() {
        sliders = sliderService.findAll();
        System.out.println("SliderBean initialized with sliders: " + sliders);
    }

    public List<Slider> getSliders() {
        return sliders;
    }

    // Method to manually refresh sliders in JSF
    public void refreshSliders() {
        sliders = sliderService.findAll();
        System.out.println("Sliders refreshed: " + sliders);
    }
}
