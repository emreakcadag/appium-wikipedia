package com.emreakcadag.yazilim_test_muhendisligi_vize.appium

import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.DesiredCapabilities

data class WikipediaConfig(
        val baseUrl: String = "https://www.wikipedia.org/",
        val username: String = "Ahmetyeseviodev",
        val password: String = "112233emre",
        val serverUrl: String = "http://127.0.0.1:4723/wd/hub",
) {
    fun getCapabilitiesIOS() = DesiredCapabilities().apply {
        setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS")
        setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.5")
        setCapability(MobileCapabilityType.UDID, "D88020EF-D994-4290-934C-8653AE424377")
        setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest")
        setCapability(MobileCapabilityType.BROWSER_NAME, "safari")
        setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 12 Pro Max")
        setCapability("safariAllowPopups", true)
    }
}