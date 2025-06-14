/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.foko0002.slider.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.io.Serializable;

@Entity
public class Slider implements Serializable {

    private static final long serialVersionUID = -825634229676522580L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Constants variables value for the slider properties
    public static final int TRAVEL_SPEED = 5; 
    public static final int MAX_DIR_CHANGES = 5;
    public static final int DECREASE_RATE = 5; 
    public static final int X_LIMIT = 750; 
    public static final int Y_LIMIT = 350; 
    public static final int MIN_SIZE = 10;
    public static final int MAX_SIZE = 50; 
    public static final int MIN_TRAVEL_LIMIT = 80; 
    public static final int MAX_TRAVEL_LIMIT = 200; 

    // Position attributes with constraints
    @Min(0)
    @Max(X_LIMIT)
    private Integer x;

    @Min(0)
    @Max(Y_LIMIT)
    private Integer y;

    @Min(MIN_SIZE) 
    @Max(MAX_SIZE) 
    private Integer size;

    // Movement attributes with constraints
    @Min(MIN_TRAVEL_LIMIT) 
    @Max(MAX_TRAVEL_LIMIT) 
    private Integer maxTravel;

    // Travel attributes
    private int currentTravel; 
    private int mvtDirection; 
    private int dirChangeCount; 

    // Default constructor
    public Slider() {
        this.currentTravel = 0; 
        this.mvtDirection = 1;  
        this.dirChangeCount = 0;
    }

    // Overloaded Constructor
    public Slider(Integer x, Integer y, Integer size, Integer maxTravel) {
        this(); // Call default constructor 
        this.x = x;
        this.y = y;
        this.size = size;
        this.maxTravel = maxTravel;
    }

    // Slider Movement logic
    public void timeStep() {
        // Ensure maxTravel is valid before attempting to move
        if (maxTravel == null || maxTravel <= 0) {
            System.out.println("Slider ID: " + id + " - Cannot move, maxTravel is null or non-positive.");
            return;
        }

        // Update currentTravel based on direction and speed
        currentTravel += TRAVEL_SPEED * mvtDirection;

        // Check for boundary conditions and reverse direction
        if (currentTravel >= maxTravel) {
            currentTravel = maxTravel; 
            mvtDirection = -1;        
            dirChangeCount++;
        } else if (currentTravel <= 0) {
            currentTravel = 0;         
            mvtDirection = 1;          
            dirChangeCount++;
        }

        // Apply logic for reducing maxTravel after multiple direction changes
        if (dirChangeCount >= MAX_DIR_CHANGES) {
            dirChangeCount = 0; 
            
            // Ensure maxTravel doesn't go below MIN_TRAVEL_LIMIT
            maxTravel = Math.max(MIN_TRAVEL_LIMIT, maxTravel - DECREASE_RATE); 
            System.out.println("Slider ID: " + id + " - MaxTravel reduced to: " + maxTravel);
        }
    }


    // Method to update slider properties from another Slider object
    public void updateFrom(Slider newSlider) {
        if (newSlider.x != null) this.x = newSlider.x;
        if (newSlider.y != null) this.y = newSlider.y;
        if (newSlider.size != null) this.size = newSlider.size;
        if (newSlider.maxTravel != null) this.maxTravel = newSlider.maxTravel;
        
        
        this.currentTravel = newSlider.currentTravel; 
        this.mvtDirection = newSlider.mvtDirection; 
        this.dirChangeCount = newSlider.dirChangeCount; 
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Slider)) {
            return false;
        }
        Slider other = (Slider) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    // Getters and Setters for all fields
    public Integer getX() { return x; }
    public void setX(Integer x) { this.x = x; }
    public Integer getY() { return y; }
    public void setY(Integer y) { this.y = y; }
    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }
    public Integer getMaxTravel() { return maxTravel; }
    public void setMaxTravel(Integer maxTravel) { this.maxTravel = maxTravel; }
    public int getCurrentTravel() { return currentTravel; }
    public void setCurrentTravel(int currentTravel) { this.currentTravel = currentTravel; }
    public int getMvtDirection() { return mvtDirection; }
    public void setMvtDirection(int mvtDirection) { this.mvtDirection = mvtDirection; }
    public int getDirChangeCount() { return dirChangeCount; }
    public void setDirChangeCount(int dirChangeCount) { this.dirChangeCount = dirChangeCount; }

    @Override
    public String toString() {
        return "Slider{" + "id=" + id + ", x=" + x + ", y=" + y +
               ", size=" + size +
               ", maxTravel=" + maxTravel +
               ", currentTravel=" + currentTravel +
               ", mvtDirection=" + mvtDirection +
               ", dirChangeCount=" + dirChangeCount +
               '}';
    }
}
