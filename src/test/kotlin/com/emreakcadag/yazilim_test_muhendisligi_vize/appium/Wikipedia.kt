package com.emreakcadag.yazilim_test_muhendisligi_vize.appium

import io.appium.java_client.MobileElement
import io.appium.java_client.ios.IOSDriver
import java.net.URL
import java.util.concurrent.TimeUnit

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
    val driver = IOSDriver<MobileElement>(URL(config.serverUrl), config.getCapabilitiesIOS()).also {
        it.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS)
    }
}