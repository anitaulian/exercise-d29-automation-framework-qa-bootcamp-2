package dibimbing.tests;

import dibimbing.core.BaseTest;
import dibimbing.core.DriverManager;
import dibimbing.core.TestUtils;
import dibimbing.pages.LoginPage;
import dibimbing.pages.ProductPage;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Driver;

public class LoginTest extends BaseTest {
    // ======Test Runner: TestNG vs JUnit======
    // [Test runner] kalau run yang groupingnya "regression" maka akan run yang regression
    // [Test runner] kalau run yang groupingnya "smoke" maka akan run yang smoke
    @Test(groups = {"regression"})
    public void testLogin() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToSauceDemo();
        loginPage.login("standard_user", "secret_sauce");
    }

//    //[Assertions] -- Data Provider untuk wrongLoginData -- bisa run banyak data tp skenario test sama
//    @DataProvider(name = "wrongLoginData")
//    public Object[][] wrongLoginData() {
//        return new Object[][] {
//                {"asdasdasd", "qwerty"},
//                {"uiop", "zxcvb"}
//        };
//    }
//
// =====kalau mau di uncomment yang test utils excelnya yang dicomment yaa=====
    //[Assertions] -- Data Provider (skenario test : gimana kalau kita isi password salah)
//    @Test(groups = {"regression", "negative"}, dataProvider = "wrongLoginData")
//    public void testFailedLogin(String username, String password) {
//        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
//        loginPage.navigateToSauceDemo();
//        loginPage.login(username, password);
//        loginPage.assertLoginAlert(); //panggil method login alert
//    }

    //untuk TestUtils by Excel
    @DataProvider(name = "wrongLoginData")
    public Object[][] wrongLoginData() {
        return TestUtils.getTestData("src/test/resources/testdata/testdataAnita.xlsx", "wrong-login-data");
    }

    @Test(groups = {"regression", "negative"}, dataProvider = "wrongLoginData")
    public void testFailedLogin(String username, String password) {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToSauceDemo();
        loginPage.login(username, password);
        loginPage.assertLoginAlert(); //panggil method login alert
    }

    //Test Runner
    @Test(groups = {"regression"})
    public void testLogin1() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToSauceDemo();
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(groups = {"smoke", "p1"})
    public void testLogin2() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToSauceDemo();
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test(groups = {"smoke", "p2"})
    public void testLogin3() {
        LoginPage loginPage = new LoginPage(DriverManager.getDriver());
        loginPage.navigateToSauceDemo();
        loginPage.login("standard_user", "secret_sauce");
    }

    //======Assertions======
    //[Assertion] -- ini untuk skenario test ProductPage
    @Test(groups = {"smoke", "p1"})
    public void testLogin4() {
        WebDriver driver = DriverManager.getDriver(); //buat lagi karna menghidari duplikasi di LoginPage (baris 40)
        LoginPage loginPage = new LoginPage(driver); //jadi tinggal panggil driver aja
        ProductPage productPage = new ProductPage(driver); //jadi tinggal panggil driver aja
        loginPage.navigateToSauceDemo();
        loginPage.login("standard_user", "secret_sauce");
        productPage.assertProductPage(); //verify manggil assert test productPage
    }
}