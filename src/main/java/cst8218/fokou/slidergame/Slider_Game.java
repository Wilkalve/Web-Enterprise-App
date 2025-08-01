/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SingletonEjbClass.java to edit this template
 */
package cst8218.fokou.slidergame;

import cst8218.fokou.slidergame.entity.Slider;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.ejb.EJB;
import jakarta.enterprise.concurrent.ManagedExecutorService;
import java.util.List;

@Startup
@Singleton
public class Slider_Game {

    private static final double CHANGE_RATE = 3.0; // updates per second

    @EJB
    private SliderFacade sliderFacade;

    @Resource
    private ManagedExecutorService executor;

    @PostConstruct
    public void go() {
        executor.submit(() -> {
            try {
                Thread.sleep(5000); // wait for container to stabilize
                while (true) {
                    try {
                        List<Slider> sliders = sliderFacade.findAll();
                        for (Slider slider : sliders) {
                            slider.timeStep();
                            sliderFacade.edit(slider);
                        }
                        Thread.sleep((long)(1000 / CHANGE_RATE));
                    } catch (Exception e) {
                        System.err.println("SliderGame loop error: " + e.getMessage());
                    }
                }
            } catch (InterruptedException ignored) {}
        });
    }
}