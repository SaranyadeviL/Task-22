import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class PhpTravels {
    public static void main(String[] args) throws InterruptedException , IOException{
        //Disable Notifications and launch WebDriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        //Launch Web Driver
        WebDriver driver = new ChromeDriver(options);

        //Maximize the Window
        driver.manage().window().maximize();

        //Navigate webpage
        driver.navigate().to("https://phptravels.com/demo/");

        //Adding implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        //As per the requirement wait class used to fill the elements in the form
        WebElement nameInput = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Last Name']")));
        nameInput.sendKeys("Saranya");

        WebElement LastName = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='First Name']")));
        LastName.sendKeys("Devi");

        WebElement BusinessName = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Business Name']")));
        BusinessName.sendKeys("Php Travels");

        WebElement emailInput = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailInput.sendKeys("Saranya291295@gmail.com");

        //Solving captcha
        WebElement num1Element = driver.findElement(By.xpath("//span[@id='numb1']"));
        WebElement num2Element = driver.findElement(By.xpath("//span[@id='numb2']"));
        String num1Text = num1Element.getText();
        String num2Text = num2Element.getText();

        // Converting String into Integer
        int num1 = Integer.parseInt(num1Text);
        int num2 = Integer.parseInt(num2Text);
        int sum = num1 + num2;

        // Logic for sum verification
        WebElement Result =  new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='number']")));
        Result.sendKeys(String.valueOf(sum));

        //Implicit wait not working hence I tried with Thread.sleep---> to avoid element intercepted exception
        Thread.sleep(3000);

        // Submit the form
        WebElement submitButton = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='demo']")));
        submitButton.click();

        /// Verify form submission message
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[contains(text(),'Thank you!')]")));
        String message = successMessage.getText();
        if (message.contains("Thank you!"))
        {
            System.out.println("Form submitted successfully!: " + message);
        }
        else
        {
            System.out.println("Form submission failed.");
        }

        // Take screenshot
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

        // Save the screenshot to a specific location
        FileUtils.copyFile(screenshotFile, new File("screenshot.png"));
        System.out.println("Screenshot captured successfully and saved as screenshot.png");

        // Close the browser
        driver.quit();

    }
}
