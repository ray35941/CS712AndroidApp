package com.example.csci712assignment2bykaiding;

import static org.junit.Assert.assertNotNull;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.Until;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class UITesting {

    private static final int TIMEOUT = 7000;
    private UiDevice device;


    @Test
    public void testcases() {
        UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        device.pressHome();
        UiObject2 checkIcon = device.wait(
                Until.findObject(By.text("CSCI 712 Assignment 2 By Kai Ding")),
                TIMEOUT // the test should wait for up to a certain amount of time, use TIMEOUT to handle it.
        );
        assertNotNull("App icon not found on the home screen", checkIcon);
        checkIcon.clickAndWait(Until.newWindow(), TIMEOUT);


        UiObject2 CheckButton = device.wait(
                Until.findObject(By.text("Start Activity Explicitly")),
                TIMEOUT
        );
        assertNotNull("Start Activity Explicitly button not found", CheckButton);
        CheckButton.clickAndWait(Until.newWindow(), TIMEOUT);


        UiObject2 VerifyContent = device.wait(
                Until.findObject(By.textContains("Device fragmentation")),
                TIMEOUT
        );
        assertNotNull("Expected challenge text not found in second activity", VerifyContent);
    }
}
