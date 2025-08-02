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
public class AppUserTest {
    private AppUser appuser;
    public AppUserTest() {
    }
    
    @BeforeClass
    public void setUpClass() {
        appuser = new AppUser();
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
     * Test of getId method, of class AppUser.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AppUser instance = new AppUser();
        Long expResult = 43L;
        Long result = instance.getId();
        assertEquals(expResult, result);
        System.out.println("Pass !!!");
        
    }

    /**
     * Test of setId method, of class AppUser.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = 12L;
        AppUser instance = new AppUser();
        instance.setId(id);
      System.out.println("Pass !!!");
    }

    /**
     * Test of getUserid method, of class AppUser.
     */
    @Test
    public void testGetUserid() {
        System.out.println("getUserid");
        AppUser instance = new AppUser();
        String expResult = "Kalve";
        String result = instance.getUserid();
        assertEquals(expResult, result);
        System.out.println("Pass !!!");
    }

    /**
     * Test of setUserid method, of class AppUser.
     */
    @Test
    public void testSetUserid() {
        System.out.println("setUserid");
        String userid = " Alfred";
        AppUser instance = new AppUser();
        instance.setUserid(userid);
        System.out.println("Pass !!!");
    }

    /**
     * Test of getPassword method, of class AppUser.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        AppUser instance = new AppUser();
        String expResult = "admin12$";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        System.out.println("Pass !!!");
    }

    /**
     * Test of setPassword method, of class AppUser.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String rawPassword = "fred$12";
        AppUser instance = new AppUser();
        instance.setPassword(rawPassword);
        System.out.println("Pass !!!");
    }

    /**
     * Test of getPasswordHash method, of class AppUser.
     */
    @Test
    public void testGetPasswordHash() {
        System.out.println("getPasswordHash");
        AppUser instance = new AppUser();
        String expResult = "dhfdhjfjdhdfddhfhd";
        String result = instance.getPasswordHash();
        assertEquals(expResult, result);
        System.out.println("Pass !!!");
    }

    /**
     * Test of getGroupname method, of class AppUser.
     */
    @Test
    public void testGetGroupname() {
        System.out.println("getGroupname");
        AppUser instance = new AppUser();
        String expResult = "Admin";
        String result = instance.getGroupname();
        assertEquals(expResult, result);
       System.out.println("Pass !!!");
    }

    /**
     * Test of setGroupname method, of class AppUser.
     */
    @Test
    public void testSetGroupname() {
        System.out.println("setGroupname");
        String groupname = "WEBGroup";
        AppUser instance = new AppUser();
        instance.setGroupname(groupname);
        System.out.println("Pass !!!");
    }

    /**
     * Test of toString method, of class AppUser.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AppUser instance = new AppUser();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
