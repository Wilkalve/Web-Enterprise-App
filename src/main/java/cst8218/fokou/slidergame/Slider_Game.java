/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SingletonEjbClass.java to edit this template
 */
package cst8218.fokou.slidergame;

import cst8218.fokou.slidergame.entity.Slider;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.EJB;
import java.util.List;

@Startup
@Singleton
public class Slider_Game {

    private static final double CHANGE_RATE = 3.0; // updates per second

    @EJB
    private SliderFacade sliderFacade;

    @PostConstruct
    public void go() {
        new Thread(() -> {
            try {
                Thread.sleep(5000); // wait for container to stabilize
            } catch (InterruptedException ignored) {}

            while (true) {
                try {
                    List<Slider> sliders = sliderFacade.findAll();
                    for (Slider slider : sliders) {
                        slider.timeStep();
                        sliderFacade.edit(slider);
                    }

                    Thread.sleep((long)(1000 / CHANGE_RATE));
                } catch (Exception e) {
                    System.err.println("SliderGame: Unexpected error in game loop: " + e.getMessage());
                }
            }
        }).start();
    }
}