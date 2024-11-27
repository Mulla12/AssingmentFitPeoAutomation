package fitpeoAssingment;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

	public class FitPeoAutomation {

		public static void main(String[] args) {
	        // Set up WebDriver
	       
	        WebDriver driver = new ChromeDriver();

	        try {
	            // Maximize browser window
	            driver.manage().window().maximize();

	            // Navigate to FitPeo Homepage
	            driver.get("https://fitpeo.com");

	            // Wait for the page to load completely
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	            // Navigate to Revenue Calculator Page
	            WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.linkText("Revenue Calculator")
	            ));
	            revenueCalculatorLink.click();

	            // Scroll down to the slider section
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            WebElement sliderSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.id("slider-section") // Replace with the actual ID or class of the slider section
	            ));
	            js.executeScript("arguments[0].scrollIntoView(true);", sliderSection);

	            // Adjust the slider to 820
	            WebElement slider = driver.findElement(By.id("revenue-slider")); // Replace with actual slider element
	            js.executeScript("arguments[0].value = arguments[1];", slider, 820);

	            // Verify the bottom text field is updated to 820
	            WebElement sliderTextField = driver.findElement(By.id("slider-text-field")); // Replace with the actual ID
	            String sliderValue = sliderTextField.getAttribute("value");
	            if (!"820".equals(sliderValue)) {
	                throw new AssertionError("Slider value did not update to 820.");
	            }

	            // Update the text field to 560
	            sliderTextField.clear();
	            sliderTextField.sendKeys("560");

	            // Validate the slider position reflects the value 560
	            String updatedSliderValue = slider.getAttribute("value");
	            if (!"560".equals(updatedSliderValue)) {
	                throw new AssertionError("Slider did not update to 560 when text field was changed.");
	            }

	            // Scroll down to CPT Codes
	            WebElement cptSection = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.id("cpt-section") // Replace with the actual ID or class of the CPT codes section
	            ));
	            js.executeScript("arguments[0].scrollIntoView(true);", cptSection);

	            // Select CPT codes
	            String[] cptCodes = {"CPT-99091", "CPT-99453", "CPT-99454", "CPT-99474"};
	            for (String code : cptCodes) {
	                WebElement checkbox = driver.findElement(By.id(code)); // Replace with actual IDs
	                if (!checkbox.isSelected()) {
	                    checkbox.click();
	                }
	            }

	            // Validate Total Recurring Reimbursement
	            WebElement totalReimbursementHeader = driver.findElement(By.id("total-reimbursement-header")); // Replace with actual ID
	            String totalReimbursement = totalReimbursementHeader.getText();
	            if (!"$110700".equals(totalReimbursement)) {
	                throw new AssertionError("Total Reimbursement value is incorrect.");
	            }

	            System.out.println("All test cases passed successfully!");

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the browser
	            driver.quit();
	        }
	    }
	}
