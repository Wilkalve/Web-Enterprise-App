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

/**
 *
 * @author wilfr
 */
@Entity
public class Slider implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

   // Constants
   public static final int INITIAL_SIZE = 10;
    public static final int TRAVEL_SPEED = 1;
    public static final int MAX_DIR_CHANGES = 5;
    public static final int DECREASE_RATE = 1;
    public static final int X_LIMIT = 500;
    public static final int Y_LIMIT = 500;
    public static final int SIZE_LIMIT = 1;
    public static final int MAX_TRAVEL_LIMIT = 50;
    
    // Position attributes with constraints
    @Min(0)
    @Max(X_LIMIT)
    private Integer x;
    
    @Min(0)
    @Max(Y_LIMIT)
    private Integer y;

    @Min(1)
    @Max(SIZE_LIMIT)
    private Integer size;
    
    // Movement attributes with constraints
    @Min(1)
    @Max(MAX_TRAVEL_LIMIT)
    private Integer maxTravel;
    
    // Travel attributes
    private int currentTravel = INITIAL_SIZE;
    private int  mvtDirection = 1;
    private int dirChangeCount = 0;
    
    // default constructor
    public Slider() {} 
    
    // overloaded Constructor
     public Slider(Integer x, Integer y, Integer size, Integer maxTravel) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.maxTravel = maxTravel;
    }
     
     // slider Movement logic 
    public void timeStep() {
        
        if (maxTravel == null || maxTravel <= 0) return;
        
        currentTravel += TRAVEL_SPEED * mvtDirection;

        if (Math.abs(currentTravel) >= maxTravel) {
            mvtDirection *= -1;
            dirChangeCount++;
            
            if (dirChangeCount >= MAX_DIR_CHANGES) {
                maxTravel = (maxTravel != null) ? Math.max(0, maxTravel - DECREASE_RATE) : null;
            }
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getters and Setters
    public Integer getX() { return x; }
    public void setX(Integer x) { this.x = x; }
    public Integer getY() { return y; }
    public void setY(Integer y) { this.y = y; }
    public Integer getSize() { return size; }
    public void setSize(Integer size) { this.size = size; }
    public Integer getMaxTravel() { return maxTravel; }
    public void setMaxTravel(Integer maxTravel) { this.maxTravel = maxTravel; }
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slider)) {
            return false;
        }
        Slider other = (Slider) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "cst8218.foko0002.slider.entity.Slider[ id=" + id + " ]";
    }
    
}
