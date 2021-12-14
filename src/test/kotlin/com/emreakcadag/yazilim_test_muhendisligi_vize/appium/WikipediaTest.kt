package com.emreakcadag.yazilim_test_muhendisligi_vize.appium

import com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.openqa.selenium.By
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

/**
 * Created By Emre Akçadağ
 * Appium Test
 */
class WikipediaTest {
    private val page = Wikipedia.instance
    private var driver = Wikipedia.instance.getDriver(DriverType.IOS)

    @BeforeEach
    fun setUp() {
        // Wikipedia.org sayfası için mobil driver oluşturulur.
        driver.get(page.config.baseUrl)
    }

    @Test
    fun openLoginPage() {
        goToLoginPage()

        assertEquals(driver.findElement(By.id("section_0")).text, "Log in")
    }

    @Test
    fun loginWithoutUsernameAndPassword() {
        goToLoginPage()
        loginByUsernameAndPassword("", "")

        Assertions.assertEquals(driver.findElement(By.id("wpLoginAttempt")).isEnabled, true)
    }

    @Test
    fun loginWithoutPassword() {
        goToLoginPage()
        loginByUsernameAndPassword(page.config.username, "")

        Assertions.assertEquals(driver.findElement(By.id("wpLoginAttempt")).isEnabled, true)
    }

    @Test
    fun loginWithoutUsername() {
        goToLoginPage()
        loginByUsernameAndPassword("", page.config.password)

        Assertions.assertEquals(driver.findElement(By.id("wpLoginAttempt")).isEnabled, true)
    }

    @Test
    fun loginWithWrongPassword() {
        goToLoginPage()
        loginByUsernameAndPassword(page.config.username, "wrongPassword")

        Assertions.assertEquals(driver.findElement(By.id("wpLoginAttempt")).isEnabled, true)
    }

    @Test
    fun login() {
        openLoginPage()

        loginByUsernameAndPassword(page.config.username, page.config.password)

        // Login butonu ekrandan kaybolana kadar Thread'i bekletir.
        WebDriverWait(driver, 35).until(ExpectedConditions.invisibilityOfElementLocated(By.id("wpLoginAttempt")))

        // Giriş işleminin başarılı olup olmadığı bu aşamada doğrulanır.
        assertEquals(driver.findElement(By.id("section_0")).text, "Welcome, ${page.config.username}!")
    }

    private fun goToLoginPage() {
        // English butonunu bulma ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("js-link-box-en")).click()

        // Giriş butonunu bulma ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("mw-mf-main-menu-button")).click()

        // Giriş butonunu bulma ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.className("menu__item--login")).click()

        // Login butonu ekrandan kaybolana kadar Thread'i bekletir.
        WebDriverWait(driver, 35).until(ExpectedConditions.invisibilityOfElementLocated(By.id("menu__item--login")))
    }

    private fun loginByUsernameAndPassword(username: String?, password: String?) {
        // Giriş sayfasındaki kullanıcı adı ve şifre alanları bulunur.
        val etUsername = driver.findElement(By.id("wpName1"))
        val etPassword = driver.findElement(By.id("wpPassword1"))

        // Kullanıcı adı ve şifre alanlarını doldurulur.
        etUsername.sendKeys(username)
        etPassword.sendKeys(password)

        if (page.driverType == DriverType.ANDROID) {
            driver.hideKeyboard()
        }

        // Giriş butonu tıklanır ve tıklama işlemi gerçekleştirilir.
        driver.findElement(By.id("wpLoginAttempt")).click()

        if (page.driverType == DriverType.ANDROID) {
            driver.hideKeyboard()
        }
    }
}