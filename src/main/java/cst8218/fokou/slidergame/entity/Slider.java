/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cst8218.fokou.slidergame.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.io.Serializable;

/**
 *
 * @author wilfr
 */
@Entity
public class Slider implements Serializable {

    private static final long serialVersionUID = 1L;

    // Constants for game behavior 
    public static final int INITIAL_SIZE = 10;
    public static final int INITIAL_TRAVEL = 10;
    public static final int TRAVEL_SPEED = 1;
    public static final int CHANGE_RATE = 60;
    public static final int MAX_DIR_CHANGES = 10;
    public static final int DECREASE_RATE = 1;
    public static final int SIZE_LIMIT = 100;
    public static final int X_LIMIT = 500;
    public static final int Y_LIMIT = 500;
    public static final int MAX_TRAVEL_LIMIT = 200;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Game-specific properties 
    @Min(0)
    @Max(X_LIMIT)
    private Integer x;

    @Min(0)
    @Max(Y_LIMIT)
    private Integer y;

    @Min(1)
    @Max(SIZE_LIMIT)
    private Integer size = INITIAL_SIZE;

    @Min(1)
    @Max(MAX_TRAVEL_LIMIT)
    private Integer maxTravel;

    @Transient
    private Integer currentTravel = INITIAL_TRAVEL;

    @Transient
    private Boolean mvtDirection = true; // true = right, false = left

    @Transient
    private int dirChangeCount = 0;

    // Behavior method  for object travel
    public void timeStep() {
        if (maxTravel == null || maxTravel <= 0) return;

        if (Boolean.TRUE.equals(mvtDirection)) {
            currentTravel += TRAVEL_SPEED;
            if (currentTravel >= maxTravel) {
                mvtDirection = false;
                processDirectionChange();
            }
        } else {
            currentTravel -= TRAVEL_SPEED;
            if (currentTravel <= -maxTravel) {
                mvtDirection = true;
                processDirectionChange();
            }
        }
    }

    private void processDirectionChange() {
        dirChangeCount++;
        if (dirChangeCount >= MAX_DIR_CHANGES) {
            maxTravel = Math.max(0, maxTravel - DECREASE_RATE);
            dirChangeCount = 0;
        }
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getX() { return x; }
    public void setX(Integer x) { this.x = x; }
    public Integer getY() { return y; }
    public void setY(Integer y) { this.y = y; }
    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }
    public Integer getMaxTravel() { return maxTravel; }
    public void setMaxTravel(Integer maxTravel) { this.maxTravel = maxTravel; }
    public void setCurrentTravel(Integer currentTravel) {
    this.currentTravel = currentTravel;
}
public void setMvtDirection(Boolean mvtDirection) {
    this.mvtDirection = mvtDirection;
}

    // Optionally expose currentTravel and mvtDirection 
    public Integer getCurrentTravel() { return currentTravel; }
    public Boolean getMvtDirection() { return mvtDirection; }

    
  /**
 * Updates this Slider's properties using non-null values from another Slider instance.
 * Preserves existing values when new ones are null.
 */
public void updateFrom(Slider source) {
    if (source.getX() != null) this.setX(source.getX());
    if (source.getY() != null) this.setY(source.getY());
    if (source.getSize() != null) this.setSize(source.getSize());
    if (source.getMaxTravel() != null) this.setMaxTravel(source.getMaxTravel());

    // These are @Transient and won't be persisted, but include them if needed for runtime state
    if (source.getCurrentTravel() != null) this.currentTravel = source.getCurrentTravel();
    if (source.getMvtDirection() != null) this.mvtDirection = source.getMvtDirection();
}

    
    //@Overrides 
    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Slider)) return false;
        Slider other = (Slider) object;
        return (this.id != null) && this.id.equals(other.id);
    }
    
    @Override
    public String toString() {
        return "cst8218.fokou.slidergame.slider.Slider[ id=" + id + " ]";
    }
    
}
