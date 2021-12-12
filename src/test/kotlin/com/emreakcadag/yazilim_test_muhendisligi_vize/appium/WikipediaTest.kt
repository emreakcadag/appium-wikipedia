package com.emreakcadag.yazilim_test_muhendisligi_vize.appium

import com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By

/**
 * Created By Emre Akçadağ
 * Appium Test
 */
class WikipediaTest {
    private val page = Wikipedia.instance
    private var driver = Wikipedia.instance.driver

    @BeforeEach
    fun setUp() {
        // Wikipedia.org sayfası için mobil safari driver oluşturulur.
        driver.get(page.config.baseUrl)
    }

    @Test
    fun openLoginPage() {
        // English butonunu bulma ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("js-link-box-en")).click()

        // Giriş butonunu bulma ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("mw-mf-main-menu-button")).click()

        // Giriş butonunu bulma ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.className("menu__item--login")).click()

        Thread.sleep(2000)

        assertEquals(driver.findElement(By.id("section_0")).text, "Log in")
    }

    @Test
    fun login() {
        openLoginPage()

        // Giriş sayfasındaki kullanıcı adı ve şifre alanları bulunur.
        val etUsername = driver.findElement(By.id("wpName1"))
        val etPassword = driver.findElement(By.id("wpPassword1"))

        // Kullanıcı adı ve şifre alanlarını doldurulur.
        etUsername.sendKeys(page.config.username)
        etPassword.sendKeys(page.config.password)

        // Giriş butonu tıklanır ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("wpLoginAttempt")).click()

        Thread.sleep(5000)

        // Giriş işleminin başarılı olup olmadığı bu aşamada doğrulanır.
        assertEquals(driver.findElement(By.id("section_0")).text, "Welcome, ${page.config.username}!")
    }
}