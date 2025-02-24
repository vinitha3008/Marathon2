package marathan;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tatacliq {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		ChromeDriver driver; 
		//Loading the URL -get
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		driver = new ChromeDriver(option);
		driver.get("https://www.tatacliq.com/");
		//Maximize the browser
	     driver.manage().window().maximize();
	     
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	    
	     WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
	     Actions a=new Actions(driver);
	    
	    WebElement brand = driver.findElement(By.xpath("(//div[@class='DesktopHeader__categoryAndBrand'])[2]"));
	    a.moveToElement(brand).perform();
	    Thread.sleep(2000);
	    
	    //mouse hover on watches and accessories
	    WebElement watchesMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='DesktopHeader__brandDetailsHolder']//div)[10]")));
        a.moveToElement(watchesMenu).perform();
        Thread.sleep(2000);
	    //Choose the first option from the 'Featured brands'
	    WebElement first_op = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[@class='DesktopHeader__subBrandsDetailsHolder']//div[2]//div)[2]")));
	    a.moveToElement(first_op).click().perform();
	    Thread.sleep(2000);
	    
	    //click on short by option
	    driver.findElement(By.xpath("//div[@class='Plp__sort']//div//div")).click();
	    WebElement newArrivals = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[contains(text(),'New Arrivals')]")));
        newArrivals.click();
        Thread.sleep(2000);
        
        //click on men categery
        WebElement men = driver.findElement(By.xpath("(//div[@class='FilterDesktop__subFilterDetails']//div[@class='FilterDesktop__newFilterBlock'])[2]//div[text()='Men']"));
        driver.executeScript("arguments[0].click();", men);
        Thread.sleep(2000);
        //to retrieve pricelist
        List<WebElement> pricelist = driver.findElements(By.xpath("(//div[@class='Grid__base']//div[@class='Grid__element']//div[@class='ProductDescription__priceHolder']//h3)"));
        System.out.println(pricelist.size());
        System.out.println("Watch Prices:");
        String firstprice=pricelist.get(0).getText();
        for(int i=0;i<pricelist.size();i++)
        {
        	System.out.println(pricelist.get(i).getText());
        }
        
        //click on the first resulting watch
        WebElement firstwatch = driver.findElement(By.xpath("//div[@class='ProductModule__content']//div"));
        driver.executeScript("arguments[0].click();", firstwatch);
        
        Set<String> windowHandles = driver.getWindowHandles();
		
		//set to list
		List<String> allWindows=new ArrayList<String>(windowHandles);
		
		driver.switchTo().window(allWindows.get(1));
        //get the price on clicked one
      
    		   String cost = driver.findElement(By.xpath("//div[@class='ProductDetailsMainCard__price']/h3")).getText();
    		   System.out.println("price of the clicked product: "+cost);
    		   String substring = cost.substring(5);
    		   if(firstprice.equals(substring))
    			   System.out.println("same product");
    		   else
    			   System.out.println("different product");
    		   //click on add to bag button
    		   WebElement add_cart = driver.findElement(By.xpath("(//div[@class='Button__base'])[3]"));
               driver.executeScript("arguments[0].click();", add_cart);
            //get the product count from cart icon
               String count = driver.findElement(By.xpath("//div[@class='DesktopHeader__myBagShow']//span")).getText();
               System.out.println("product count: "+count);
            //take a screenshot   
               File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
   			File destination=new File("./ScrnShot/productpage.png");
   			FileUtils.copyFile(screenshotAs, destination);
   			driver.close();
   			
   			driver.switchTo().window(allWindows.get(0));
   			driver.close();
   			
           
           
        
	}

}
