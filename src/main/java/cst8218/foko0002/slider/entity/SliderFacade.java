/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package cst8218.foko0002.slider.entity;

import cst8218.foko0002.slider.AbstractFacade;
import cst8218.foko0002.slider.entity.Slider;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author wilfr
 */
@Stateless
@LocalBean
public class SliderFacade extends AbstractFacade<Slider> {
    @PersistenceContext(unitName = "my_persistence_unit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SliderFacade() {
        super(Slider.class);
    }

    public List<Slider> findAll() {
        return em.createQuery("SELECT s FROM Slider s", Slider.class).getResultList();
    }
}


