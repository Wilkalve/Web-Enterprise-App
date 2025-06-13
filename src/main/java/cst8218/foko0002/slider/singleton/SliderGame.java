/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SingletonEjbClass.java to edit this template
 */
package cst8218.foko0002.slider.singleton;

import cst8218.foko0002.slider.SliderFacade;
import cst8218.foko0002.slider.entity.Slider;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Startup;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 *
 * @author wilfr
 */
@Startup
@Singleton
@LocalBean
public class SliderGame {

    private static final Logger LOGGER = Logger.getLogger(SliderGame.class.getName());
    private ExecutorService executorService;
    private volatile boolean running = true; // Flag for stopping the loop safely
    private List<Slider> sliders;
    private static final int CHANGE_RATE = 60;
    
    private SliderFacade sliderFacade; // Assuming this is injected elsewhere

    @PostConstruct
    public void go() {
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            while (running) {
                try {
                    // Fetch all sliders and update them
                    sliders = sliderFacade.findAll();
                    for (Slider slider : sliders) {
                        slider.timeStep();
                        sliderFacade.edit(slider);
                    }

                    // Wait before processing the next frame
                    Thread.sleep((long) (1000.0 / CHANGE_RATE));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupt status
                    LOGGER.severe("Game loop interrupted: " + e.getMessage());
                } catch (Exception e) {
                    LOGGER.severe("Unexpected error in game loop: " + e.getMessage());
                }
            }
        });
    }

    // Method to safely stop the game loop
    public void stopGame() {
        running = false;
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
