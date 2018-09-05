package app.model.services.impl;

import app.model.services.IUserService;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    @Test
    public void isStringCorrect() {
        IUserService userService = new UserServiceImpl();
        String REGEX_LOGIN = "^[A-Za-z0-9]{8,20}$";
        String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
        String [] wrongLogins = {"Marisa ads-a","L&623221"," Ldsdwd-dsdwe"};
        String [] rightLogins = {"Lad12345","Maria123","Ladladlad"};
        String [] rightPasswords = {"Maria123!","mA1","2!&#Lo"};
        String [] wrongPasswords = {"dskap121!","Lolo3 21!","ma1"};
        for(int i = 0; i < wrongLogins.length ; i ++ ){
            Assert.assertFalse("login is wrong", wrongLogins[i].matches(REGEX_LOGIN));
        }
        for(int i = 0; i < rightLogins.length ; i ++ ){
            Assert.assertTrue("login is right", rightLogins[i].matches(REGEX_LOGIN));
        }
        for(int i = 0; i < wrongPasswords.length ; i ++ ){
            Assert.assertFalse("password is wrong", wrongPasswords[i].matches(REGEX_PASSWORD));
        }
        for(int i = 0; i < rightPasswords.length ; i ++ ){
            Assert.assertTrue("password is right", rightPasswords[i].matches(REGEX_PASSWORD));
        }
    }
}