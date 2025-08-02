/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cst8218.fokou.slidergame.entity;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author wilfr
 */
public class SliderTest {
    
    private Slider slider;
    
    public SliderTest() {
       
    }
    
    @BeforeClass
    public void setUpClass() {
      slider = new Slider();
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of timeStep method, of class Slider.
     */
    @Test
    public void testTimeStep() {
    System.out.println("timeStep");
    Slider instance = new Slider();
    instance.setCurrentTravel(0);
    instance.setMaxTravel(5);
    instance.setMvtDirection(true);

    instance.timeStep(); 
    assertEquals(Integer.valueOf(1), instance.getCurrentTravel());

    for (int i = 0; i < 5; i++) {
        instance.timeStep();
    }
    assertEquals(Integer.valueOf(0), instance.getCurrentTravel()); 
         System.out.println("Pass !!!");
    }

    /**
     * Test of getId method, of class Slider.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Slider instance = new Slider();
        Long expResult = 100L;
        Long result = instance.getId();
        assertEquals(expResult, result);
      System.out.println("Pass !!!");
    }

    /**
     * Test of setId method, of class Slider.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = 42L;
        Slider instance = new Slider();
        instance.setId(id);
        System.out.println("Pass !!!");
    }

    /**
     * Test of getX method, of class Slider.
     */
    @Test
    public void testGetX() {
        System.out.println("getX");
        Slider instance = new Slider();
        Integer expResult = 55;
        Integer result = instance.getX();
        assertEquals(expResult, result);
        System.out.println("Pass !!!");
    }

    /**
     * Test of setX method, of class Slider.
     */
    @Test
    public void testSetX() {
        System.out.println("setX");
        Integer x = 88;
        Slider instance = new Slider();
        instance.setX(x);
        System.out.println("Pass !!!");
    }

    /**
     * Test of getY method, of class Slider.
     */
    @Test
    public void testGetY() {
        System.out.println("getY");
        Slider instance = new Slider();
        Integer expResult = 20;
        Integer result = instance.getY();
        assertEquals(expResult, result);
        System.out.println("Pass !!!");
    }

    /**
     * Test of setY method, of class Slider.
     */
    @Test
    public void testSetY() {
        System.out.println("setY");
        Integer y = 56;
        Slider instance = new Slider();
        instance.setY(y);
        System.out.println("Pass !!!");
    }

    /**
     * Test of getSize method, of class Slider.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        Slider instance = new Slider();
        Integer expResult = 20;
        Integer result = instance.getSize();
        assertEquals(expResult, result);
       System.out.println("Pass !!!");
    }

    /**
     * Test of setSize method, of class Slider.
     */
    @Test
    public void testSetSize() {
        System.out.println("setSize");
        Integer size = 34;
        Slider instance = new Slider();
        instance.setSize(size);
        System.out.println("Pass !!!");
    }

    /**
     * Test of getMaxTravel method, of class Slider.
     */
    @Test
    public void testGetMaxTravel() {
        System.out.println("getMaxTravel");
        Slider instance = new Slider();
        Integer expResult = 20;
        Integer result = instance.getMaxTravel();
        assertEquals(expResult, result);
       System.out.println("Pass !!!");
    }

    /**
     * Test of setMaxTravel method, of class Slider.
     */
    @Test
    public void testSetMaxTravel() {
        System.out.println("setMaxTravel");
        Integer maxTravel = 15;
        Slider instance = new Slider();
        instance.setMaxTravel(maxTravel);
       System.out.println("Pass !!!");
    }

    /**
     * Test of setCurrentTravel method, of class Slider.
     */
    @Test
    public void testSetCurrentTravel() {
        System.out.println("setCurrentTravel");
        Integer currentTravel = 32;
        Slider instance = new Slider();
        instance.setCurrentTravel(currentTravel);
        System.out.println("Pass !!!");
    }

    /**
     * Test of setMvtDirection method, of class Slider.
     */
    @Test
    public void testSetMvtDirection() {
        System.out.println("setMvtDirection");
        Boolean mvtDirection = true;
        Slider instance = new Slider();
        instance.setMvtDirection(mvtDirection);
       System.out.println("Pass !!!");
    }

    /**
     * Test of getCurrentTravel method, of class Slider.
     */
    @Test
    public void testGetCurrentTravel() {
        System.out.println("getCurrentTravel");
        Slider instance = new Slider();
        Integer expResult = 6;
        Integer result = instance.getCurrentTravel();
        assertEquals(expResult, result);
        System.out.println("Pass !!!");
    }

    /**
     * Test of getMvtDirection method, of class Slider.
     */
    @Test
    public void testGetMvtDirection() {
        System.out.println("getMvtDirection");
        Slider instance = new Slider();
        Boolean expResult = false;
        Boolean result = instance.getMvtDirection();
        assertEquals(expResult, result);
        System.out.println("Pass !!!");
    }

    /**
     * Test of updateFrom method, of class Slider.
     */
    @Test
    public void testUpdateFrom() {
        System.out.println("updateFrom");
        Slider source = null;
        Slider instance = new Slider();
        instance.updateFrom(source);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Slider.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Slider instance = new Slider();
        int expResult = 13;
        int result = instance.hashCode();
        assertEquals(expResult, result);
       System.out.println("Pass !!!");
    }

    /**
     * Test of equals method, of class Slider.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Slider instance = new Slider();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Slider.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Slider instance = new Slider();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
