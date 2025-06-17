/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SingletonEjbClass.java to edit this template
 */
package cst8218.foko0002.slider.singleton;


import cst8218.foko0002.slider.entity.Slider;
import cst8218.foko0002.slider.entity.SliderFacade; 

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;

@Singleton
@Startup
public class SliderGame {

    private static final Logger LOGGER = Logger.getLogger(SliderGame.class.getName());
    private static final int CHANGE_RATE = 60; // Updates per second
    private volatile boolean running = true; // Flag for stopping safely

    // Thread-safe list
    private List<Slider> sliders = new CopyOnWriteArrayList<>(); 

    @Inject
    private SliderFacade sliderFacade; // Ensure proper EJB injection

    @PostConstruct
    public void go() {
        new Thread(() -> {
            LOGGER.info("SliderGame: Game loop starting...");

            while (running) {
                try {
                    // Fetch all sliders from DB and update them
                    sliders = sliderFacade.findAll();
                    for (Slider slider : sliders) {
                        slider.timeStep();
                        sliderFacade.edit(slider); // Persist changes
                    }

                    // Sleep to match animation frame rate
                    Thread.sleep((long) (1000.0 / CHANGE_RATE));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupt status
                    LOGGER.severe("SliderGame: Interrupted exception: " + e.getMessage());
                    break;
                } catch (Exception e) {
                    LOGGER.severe("SliderGame: Unexpected error in game loop: " + e.getMessage());
                }
            }
            LOGGER.info("SliderGame: Game loop stopped.");
        }).start();
    }

    public List<Slider> getSliders() {
        return new CopyOnWriteArrayList<>(sliders); // Return a thread-safe copy
    }

    public void stopGame() {
        running = false;
        LOGGER.info("SliderGame: Stopping game...");
    }
}
