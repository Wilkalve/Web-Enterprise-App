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
@ViewScoped
public class SliderBean implements Serializable {

    @EJB
    private SliderFacadeREST sliderService;

    private Slider selected = new Slider();
    private List<Slider> sliders;

    @PostConstruct
    public void init() {
        sliders = sliderService.findAll();
    }

    public String create() {
        if (selected != null) {
            sliderService.create(selected); // Save to DB
            selected = new Slider();        // Reset form
            sliders = sliderService.findAll(); // Refresh list
        }
        return "/contact/index.xhtml?faces-redirect=true"; // Adjust if path differs
    }

    public void refreshSliders() {
        sliders = sliderService.findAll();
    }

    public Slider getSelected() { return selected; }
    public void setSelected(Slider selected) { this.selected = selected; }

    public List<Slider> getSliders() { return sliders; }

    // Optional: expose limits
    public int getX_LIMIT() { return 800; }
    public int getY_LIMIT() { return 400; }
    public int getMIN_SIZE() { return 10; }
    public int getMAX_SIZE() { return 50; }
    public int getMIN_TRAVEL_LIMIT() { return 80; }
    public int getMAX_TRAVEL_LIMIT() { return 200; }
}
