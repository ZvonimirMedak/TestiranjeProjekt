import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
public class ApplicationTestClass{
    AndroidDriver driver;
    @BeforeClass()
    public void setUp() throws MalformedURLException{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("app", "C:\\Users\\zvoni\\Desktop\\recipe\\app-debug.apk");
        capabilities.setCapability("VERSION", "9.0");
        capabilities.setCapability("deviceName","emulator");
        capabilities.setCapability("platformName","Android");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(groups = "auth")
    public void testRegister() throws Exception {
        MobileElement registerButton = (MobileElement) driver.findElementById("com.example.recipehelper:id/register_text");
        registerButton.click();
        MobileElement registerUsername = (MobileElement) driver.findElementById("com.example.recipehelper:id/reg_username_edit");
        registerUsername.sendKeys("user");
        MobileElement registerPassword = (MobileElement) driver.findElementById("com.example.recipehelper:id/reg_password_edit");
        registerPassword.sendKeys("user");
        MobileElement registerConfirmPassword = (MobileElement) driver.findElementById("com.example.recipehelper:id/reg_confirmpass_edit");
        registerConfirmPassword.sendKeys("user");
        MobileElement doneButton = (MobileElement) driver.findElementById("com.example.recipehelper:id/done_button");
        doneButton.click();
    }

    @Test(dependsOnMethods = "testLoginBeforeRegistration", groups = "auth")
    public void testLoginAfterRegistration() throws Exception {
        login();
    }


    @Test(groups = "auth")
    public void testLoginBeforeRegistration() throws Exception {
        login();
    }

    @Test(dependsOnGroups = "auth")
    public  void testSearch() throws Exception {
        search("salad");
    }

    @Test(dependsOnMethods = "testSearch")
    public void testSearchResultsOpen() throws Exception {
        MobileElement firstElement = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[1]/android.widget.ImageView");
        firstElement.click();
    }

    @Test(dependsOnMethods = "testSearchResultsOpen")
    public void testSaveRecipe() throws Exception {
        MobileElement saveRecipeButton = (MobileElement) driver.findElementById("com.example.recipehelper:id/save_recipe_button");
        saveRecipeButton.click();
        MobileElement savedRecipesScreen = (MobileElement) driver.findElementByXPath("//androidx.appcompat.app.ActionBar.Tab[@content-desc=\"Saved\"]/android.widget.TextView");
        savedRecipesScreen.click();
    }

    @Test(dependsOnMethods = "testSaveRecipe")
    public void testOpenRecipeInBrowser() throws Exception {
        MobileElement internetButton = (MobileElement) driver.findElementByXPath("//androidx.appcompat.app.ActionBar.Tab[@content-desc=\"Internet\"]/android.widget.TextView");
        internetButton.click();
        MobileElement secondElement = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup/androidx.viewpager.widget.ViewPager/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.view.ViewGroup[2]/android.widget.ImageView");
        secondElement.click();
        MobileElement openInBrowserButton = (MobileElement) driver.findElementById("com.example.recipehelper:id/browser_opening_button");
        openInBrowserButton.click();
    }

    private void search(String item) {
        MobileElement searchField = (MobileElement) driver.findElementById("com.example.recipehelper:id/search_edit");
        searchField.sendKeys(item);
        MobileElement searchButton = (MobileElement) driver.findElementById("com.example.recipehelper:id/search_button");
        searchButton.click();
    }

    private void login() {
        MobileElement usernameField= (MobileElement)
                driver.findElement(By.id("com.example.recipehelper:id/username_edit"));
        usernameField.sendKeys("user");
        MobileElement passwordField= (MobileElement)
                driver.findElement(By.id("com.example.recipehelper:id/password_edit"));
        passwordField.sendKeys("user");
        MobileElement e13=(MobileElement)
                driver.findElement(By.id("com.example.recipehelper:id/log_in_button"));
        e13.click();
    }
    public void teardown(){
        driver.quit();
    }}
