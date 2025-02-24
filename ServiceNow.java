package marathan;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.sukgu.Shadow;

public class ServiceNow {

	public static void main(String[] args) throws IOException {
		ChromeDriver driver; 
		//Loading the URL -get
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
		driver.get("https://dev186929.service-now.com/");
		
		//Maximize the browser
	    driver.manage().window().maximize();
	     
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    
	    WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	    Actions a=new Actions(driver);
	    //Enter the username
	    driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
	    //Enter the password
	    driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("2AqjN!lC2!Vy");
	    //Click on login
	    driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
	    
	    Shadow dom = new Shadow(driver);
	    dom.setImplicitWait(10);
        //  Click on "All" Button
        WebElement allButton = dom.findElementByXPath("//div[@class='sn-polaris-navigation polaris-header-menu']//div");
        allButton.click();

        //  Click on "Service Catalog"
        WebElement serviceCatalog = dom.findElementByXPath("//span[text()='Service Catalog']");
        serviceCatalog.click();
        WebElement frame1 = dom.findElementByXPath("//iframe[@id='gsft_main']");
        driver.switchTo().frame(frame1);
        //Click on  mobiles
        driver.findElement(By.xpath("(//div[@class='drag_section']//table//tbody)[16]//td[2]//a//h2")).click();
	    //click on iphone13pro
       
        driver.findElement(By.xpath("(//tbody)[3]//a//h2")).click();
        
        //get the name of the product
        String product=driver.findElement(By.xpath("//tbody//tr//td[@class='guide_tray']")).getText();
        //click on yes radio button
        driver.findElement(By.xpath("//div[@class='row sc-row']//span//label")).click();
        //send phone number
        driver.findElement(By.xpath("(//div[@class='col-xs-6  form-field input_controls sc-form-field '])[2]//input[2]")).sendKeys("9876");
        //click on data allowance dropdown
        driver.findElement(By.xpath("(//div[@class='col-xs-6  form-field input_controls sc-form-field '])[3]")).click();
        //click on unlimited data
        driver.findElement(By.xpath("(//div[@class='col-xs-6  form-field input_controls sc-form-field '])[3]//select//option[3]")).click();
        //click on color
        driver.findElement(By.xpath("(//div[@class='col-xs-6  form-field input_controls sc-form-field '])[4]//span[3]//label")).click();
        //click on 512GB storage radio button
        driver.findElement(By.xpath("(//div[@class='col-xs-6  form-field input_controls sc-form-field '])[5]//span[3]//label")).click();
        //click on order now button
        driver.findElement(By.id("oi_order_now_button")).click();
        //get the product name from description
        String ordered_product = driver.findElement(By.xpath("//table[@class='request_table cart_edit table']//tbody//tr//td//a")).getText();
        if(product.equalsIgnoreCase(ordered_product))
        	System.out.println("Same Product");
        	else
        		System.out.println("different");
        //get the request number
        String req_num = driver.findElement(By.xpath("(//dl[@class='dl-horizontal sc-dl-horizontal']//dd)[2]//a")).getText();
        System.out.println("Request number is: "+req_num);
        //screenshot
        File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
			File destination=new File("./ScrnShot/mbl_descriptipon.png");
			FileUtils.copyFile(screenshotAs, destination);
			driver.close();
        		
	}

}
