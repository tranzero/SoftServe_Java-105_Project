/**
 * 
 */
package com.ita.edu.softserve.entity.impl;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.ita.edu.softserve.entity.Users;

/**
 * @author admin
 * 
 * Test class for UsersEntityClass 
 */
public class UserEntityImplTest {

	/**
     * Test when method return <code>true</code>.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMethodOfTrueEnterValueOfFirstPart() {
       	new Users(null, "mail@gmail.com", "12345");
    }

    
    /**
     * Test when method return <code>false</code>.
     */
    @Test
    public void testMethodOfFalseEnterValueOfFirstPart() {

    	 Users user = new Users();
         boolean actual = user.trueEnterValueOfFirstPart("", "mail@gmail.com", null);
         boolean expected = false;
         assertEquals(expected, actual);
         assertFalse(actual);
    }
    
    
    /**
     * Test when method return <code>true</code>.
     */
    @Test
    public void testMethodOfTrueEnterValueOfSecondPart() {

        Users user = new Users();
        boolean actual = user.trueEnterValueOfSecondPart("Ivan", "Popov");
        boolean expected = true;
        assertEquals(expected, actual);
        assertTrue(actual);
    }

    
    /**
     * Test when method return <code>false</code>.
     */
    @Test
    public void testMethodOfFalseEnterValueOfSecondPart() {

    	 Users user = new Users();
         boolean actual = user.trueEnterValueOfSecondPart("", "");
         boolean expected = false;
         assertEquals(expected, actual);
         assertFalse(actual);
    }

}
