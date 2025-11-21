package com.example.assignment2

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val TIMEOUT = 5000L
private const val APP_PACKAGE = "com.example.assignment2"

@RunWith(AndroidJUnit4::class)
class ExplicitLaunchTest {

    private lateinit var device: UiDevice

    @Before
    fun setUp() {
        val instrumentation = InstrumentationRegistry.getInstrumentation()
        device = UiDevice.getInstance(instrumentation)

        device.pressHome()

        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), TIMEOUT)
    }

    @Test
    fun testStartExplicitActivity() {

        // 1. Find and tap your app icon
        val appIcon = device.findObject(By.descContains("Assignment2App"))
            ?: device.findObject(By.textContains("Assignment2App"))


        assertTrue("App icon not found!", appIcon != null)
        appIcon!!.click()

        // Wait for MainActivity
        device.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), TIMEOUT)

        // 2. Click the button with resource ID btnExplicit
        val explicitButton = device.wait(
            Until.findObject(By.res(APP_PACKAGE, "btnExplicit")),
            TIMEOUT
        )

        assertTrue("Explicit button not found!", explicitButton != null)
        explicitButton!!.click()

        // 3. Verify text "Challenges" appears in SecondActivity
        val foundChallengesText = device.wait(
            Until.hasObject(By.textContains("Challenges")),
            TIMEOUT
        )

        assertTrue(
            "Expected 'Challenges' text not found in SecondActivity.",
            foundChallengesText
        )
    }
}
