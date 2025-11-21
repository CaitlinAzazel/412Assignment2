package com.example.assignment2

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import androidx.test.uiautomator.Until
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue

@RunWith(AndroidJUnit4::class)
class ExplicitLaunchUiAutomatorTest {

    private lateinit var device: UiDevice
    private val LAUNCH_TIMEOUT = 5000L
    private val APP_PACKAGE = "com.example.assignment2"

    @Before
    fun setUp() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        // Press home to start from the home screen
        device.pressHome()

        // Wait for launcher
        val launcherPackage = device.launcherPackageName
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT)

        // Launch the app
        val context = InstrumentationRegistry.getInstrumentation().context
        val intent = context.packageManager.getLaunchIntentForPackage(APP_PACKAGE)?.apply {
            addFlags(android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
        context.startActivity(intent)

        // Wait for the app to appear
        device.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), LAUNCH_TIMEOUT)
    }

    @Test
    fun testStartExplicitActivity() {
        // Click the "start activity explicitly" button
        val startButton = device.findObject(UiSelector().text("Start Activity Explicitly"))
        startButton.click()

        // Wait for second activity to appear
        device.wait(Until.hasObject(By.pkg(APP_PACKAGE).depth(0)), LAUNCH_TIMEOUT)

        // Check that the TextView contains one of the challenges
        val textView = device.findObject(UiSelector().resourceId("$APP_PACKAGE:id/textViewChallenges"))
        val challenges = listOf(
            "Battery consumption optimization",
            "Handling multiple screen sizes",
            "Ensuring app security",
            "Managing background tasks",
            "Network connectivity issues"
        )

        // Assert that at least one challenge is present in the TextView
        val text = textView.text
        val containsChallenge = challenges.any { text.contains(it) }
        assertTrue("Second activity does not contain any expected challenge", containsChallenge)
    }
}
