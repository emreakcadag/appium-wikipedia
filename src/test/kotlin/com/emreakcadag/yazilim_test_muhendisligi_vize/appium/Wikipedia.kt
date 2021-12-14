package com.emreakcadag.yazilim_test_muhendisligi_vize.appium

import com.emreakcadag.yazilim_test_muhendisligi_vize.appium.DriverType.ANDROID
import com.emreakcadag.yazilim_test_muhendisligi_vize.appium.DriverType.IOS
import io.appium.java_client.MobileDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import java.net.URL
import java.util.concurrent.TimeUnit

/**
 * Created By Emre Akçadağ
 * Appium Test
 */
class Wikipedia {
    companion object {
        @Volatile
        private var INSTANCE: Wikipedia? = null

        var instance: Wikipedia
            get() = if (INSTANCE == null) {
                INSTANCE = Wikipedia()
                INSTANCE!!
            } else {
                INSTANCE!!
            }
            private set(_) {}
    }

    val config = WikipediaConfig()
    var driverType: DriverType? = null

    fun getDriver(driverType: DriverType): MobileDriver = when (driverType) {
        IOS -> IOSDriver<MobileElement>(URL(config.serverUrl), config.getCapabilitiesIOS())
        ANDROID -> AndroidDriver<MobileElement>(URL(config.serverUrl), config.getCapabilitiesAndroid())
    }.also {
        it.manage().timeouts().implicitlyWait(15000, TimeUnit.MILLISECONDS)
        this.driverType = driverType
    }
}