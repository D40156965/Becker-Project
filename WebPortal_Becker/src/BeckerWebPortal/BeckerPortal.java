package BeckerWebPortal;
               
               
               
   import org.testng.annotations.Test;
   import org.testng.AssertJUnit;


   import java.io.FileInputStream;
   import java.io.FileNotFoundException;
   import java.io.IOException;
   import java.util.concurrent.TimeUnit;
   
   import javax.xml.parsers.ParserConfigurationException;
   
   import org.openqa.selenium.By;
   import org.openqa.selenium.JavascriptExecutor;
   import org.openqa.selenium.WebDriver;
   import org.openqa.selenium.WebElement;
   import org.openqa.selenium.chrome.ChromeDriver;
   import org.openqa.selenium.edge.EdgeDriver;
   import org.openqa.selenium.firefox.FirefoxDriver;
   import org.openqa.selenium.support.ui.Select;
   import org.testng.TestNGException;
   import org.testng.annotations.AfterTest;
   import org.testng.annotations.BeforeTest;
   import org.testng.annotations.DataProvider;
   import org.testng.annotations.Parameters;

   import org.xml.sax.SAXException;
   import org.xml.sax.SAXParseException;

   import jxl.Sheet;
   import jxl.Workbook;
   import jxl.read.biff.BiffException;
               
               
               public class BeckerPortal{
               public WebDriver driver;
               
               
               
//Page Data
@DataProvider(name="becker",parallel=true)

               public Object[][] loginData() {
               Object[][] arrayObject = getExcelData("C:/Users/D40156965/OneDrive/MobileAutomation/Selenium/Becker.xls", "test");
               return arrayObject;
}
               public String[][] getExcelData(String fileName, String sheetName) {
               String[][] arrayExcelData = null;
               try {
                              FileInputStream fs = new FileInputStream("C:/Users/D40156965/OneDrive/MobileAutomation/Selenium/Becker.xls");
                              Workbook wb = Workbook.getWorkbook(fs);
                              Sheet sh = wb.getSheet("test");

                              int totalNoOfCols = sh.getColumns();
                              int totalNoOfRows = sh.getRows();
                              
                              arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];
                              
                              for (int i= 1 ; i < totalNoOfRows; i++) {

                                             for (int j=0; j < totalNoOfCols; j++) {
                                                            arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();
                                             }

                              }
               
               } catch (FileNotFoundException e) {
                              e.printStackTrace();
               } catch (IOException e) {
                              e.printStackTrace();
                              e.printStackTrace();
               } catch (BiffException e) {
                              e.printStackTrace();
               }
               return arrayExcelData;
}
               

//BeforeTest
               @BeforeTest
               @Parameters("browser")
               public void Openbrowser(String browserName) throws Exception,TestNGException,SAXParseException,ParserConfigurationException,SAXException{
           
               if(browserName.equalsIgnoreCase("fireFox")){
               System.setProperty("webdriver.gecko.driver", "C:/Users/D40156965/OneDrive/MobileAutomation/Selenium/drivers/geckodriver.exe");
               driver=new FirefoxDriver();
               }
               else if(browserName.equalsIgnoreCase("chrome")){ 
               System.setProperty("webdriver.chrome.driver", "C:/Users/D40156965/OneDrive/MobileAutomation/Selenium/drivers/chromedriver.exe");
               driver =new ChromeDriver();
               }
               else if(browserName.equalsIgnoreCase("IE")){
               System.setProperty("webdriver.edge.driver", "C:/Users/D40156965/OneDrive/MobileAutomation/Selenium/drivers/MicrosoftWebDriver.exe");
               driver = new EdgeDriver();
               }
               else{
            	   
            	   throw new Exception("Browser is not correct");
               }
               driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
               driver.manage().window().maximize();
               driver.get("https://cpa.qa.becker.com/");
               Thread.sleep(15000);
               System.out.println(driver.getTitle());
               System.out.println("-----------------------------------------------");
              
               
               /* SIGN IN
               Thread.sleep(15000);
               WebElement SignIn = driver.findElement(By.partialLinkText("Sign In"));
                              JavascriptExecutor signin = (JavascriptExecutor)driver;
                                             signin.executeScript("arguments[0].click();", SignIn);
               Thread.sleep(5000);
               
               driver.findElement(By.name("j_username")).sendKeys("D01655267");
               driver.findElement(By.name("j_password")).sendKeys("Nepal2042");
               
                   
               WebElement LogIn = driver.findElement(By.id("loginButton"));
                              JavascriptExecutor login = (JavascriptExecutor)driver;
                                             login.executeScript("arguments[0].click();", LogIn);
				*/
               }
               
               
//AfterTest
@AfterTest
	public void terminateBrowser() throws InterruptedException{
		Thread.sleep(6000);                    
  
	driver.quit();
   }   
               
                          
             
//Testing
			
//Home
			@Test(priority=1,enabled=true)
					public void HomePage() throws InterruptedException 
				{
					String expectedTitle = "Login";
					String actualTitle = "";
			   
// get the actual value of the title and verifying it
					actualTitle = driver.getTitle();
					AssertJUnit.assertEquals(actualTitle,expectedTitle);  
				        
				Thread.sleep(6000); 	
				}           
			
//Sign In Selection
			@Test(priority=2,enabled=true)
					public void CPA() throws InterruptedException 
				{
				WebElement SignIn = driver.findElement(By.linkText("Sign In"));
					JavascriptExecutor signin = (JavascriptExecutor)driver;
						signin.executeScript("arguments[0].click();", SignIn);
						
				Thread.sleep(6000);
				}  
			               
			               
//CPA Create Account Selection
			@Test(priority=3,enabled=true,dataProvider="becker")
					public void CreateAccount(String UserName, String Password, String ConfirmPassword, String FirstName, String LastName, String Phone, String AltPhone,
							String Address, String AptUnitSuite, String City, String PostalCode, String UserID) 
									throws InterruptedException
			   {
				WebElement Account = driver.findElement(By.xpath("//*[@class='button  green with-arrow ']"));
					JavascriptExecutor account = (JavascriptExecutor)driver;
						account.executeScript("arguments[0].click();", Account);
			                              
				Thread.sleep(6000);                          
/*##################Feeding data in Create Account section#########################*/
			   
	//Account			
				driver.findElement(By.name("rep:userId")).clear(); 
					driver.findElement(By.name("rep:userId")).sendKeys(UserName);               
			   
				driver.findElement(By.name("rep:password")).clear(); 
					driver.findElement(By.name("rep:password")).sendKeys(Password);
			   		
				driver.findElement(By.name("rep:password_confirm")).clear(); 
					driver.findElement(By.name("rep:password_confirm")).sendKeys(ConfirmPassword);	
			   		
				WebElement Continue = driver.findElement(By.xpath("//*[@class='form-submit blue with-arrow']"));
					JavascriptExecutor cont = (JavascriptExecutor)driver;
						cont.executeScript("arguments[0].click();", Continue);	
			
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//Contact			
				WebElement SelectTitile = driver.findElement(By.xpath("//*[@id='UserContact_Title_']")); 
					Select myTitle= new Select(SelectTitile);
						myTitle.selectByValue("Mrs");		
					
				driver.findElement(By.id("UserContact_FirstName_")).clear(); 
					driver.findElement(By.id("UserContact_FirstName_")).sendKeys(FirstName);
			  		
				driver.findElement(By.id("UserContact_LastName_")).clear(); 
					driver.findElement(By.id("UserContact_LastName_")).sendKeys(LastName);
			   		
				driver.findElement(By.id("UserContact_dayPhone_")).clear(); 
					driver.findElement(By.id("UserContact_dayPhone_")).sendKeys(Phone); 		
			   		
				driver.findElement(By.id("UserContact_eveningPhone_")).clear(); 
					driver.findElement(By.id("UserContact_eveningPhone_")).sendKeys(AltPhone); 		
			   
				WebElement Continue1 = driver.findElement(By.xpath("//*[@class='form-submit blue with-arrow']"));
					JavascriptExecutor cont1 = (JavascriptExecutor)driver;
						cont1.executeScript("arguments[0].click();", Continue1);
					
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//Address 	  		
				driver.findElement(By.name("AddressLine1_")).clear(); 
					driver.findElement(By.name("AddressLine1_")).sendKeys(Address);     		
			       		
				driver.findElement(By.name("AddressLine2_")).clear(); 
					driver.findElement(By.name("AddressLine2_")).sendKeys(AptUnitSuite); 
					
				WebElement SelectCountry = driver.findElement(By.name("Country_")); 
					Select myCountry= new Select(SelectCountry);
						myCountry.selectByValue("US");
					
				driver.findElement(By.name("City_")).clear(); 
					driver.findElement(By.name("City_")).sendKeys(City);
			
				WebElement SelectState = driver.findElement(By.name("State_")); 
					Select myState= new Select(SelectState);
						myState.selectByValue("IL");
					
				driver.findElement(By.name("PostalCode_")).clear(); 
					driver.findElement(By.name("PostalCode_")).sendKeys(PostalCode); 
					
				WebElement Continue2 = driver.findElement(By.xpath("//*[@class='form-submit blue with-arrow']"));
					JavascriptExecutor cont2 = (JavascriptExecutor)driver;
						cont2.executeScript("arguments[0].click();", Continue2);
						
				driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				
	//Affiliation			
			
				WebElement SelectUnderGrad = driver.findElement(By.name("undergraduateuniversities")); 
					Select myUndergrad= new Select(SelectUnderGrad);
						myUndergrad.selectByValue("634917");
						
				WebElement SelectUnderGradMonth = driver.findElement(By.name("undergraduateuniversities_date_month")); 
					Select myUndergradMonth= new Select(SelectUnderGradMonth);
						myUndergradMonth.selectByValue("12");
			
				WebElement SelectUnderGradYear = driver.findElement(By.name("undergraduateuniversities_date_year")); 
					Select myUndergradyear= new Select(SelectUnderGradYear);
					myUndergradyear.selectByValue("2009");
					
				WebElement SelectGrad = driver.findElement(By.name("postgraduateuniversities")); 
					Select mygrad= new Select(SelectGrad);
						mygrad.selectByValue("634917");
						
				WebElement SelectGradMonth = driver.findElement(By.name("postgraduateuniversities_date_month")); 
					Select mygradMonth= new Select(SelectGradMonth);
						mygradMonth.selectByValue("12");
			
				WebElement SelectGradYear = driver.findElement(By.name("postgraduateuniversities_date_year")); 
					Select mygradyear= new Select(SelectGradYear);
					mygradyear.selectByValue("2013");		
			       
				WebElement Military = driver.findElement(By.name("militaryveterans"));
					JavascriptExecutor milt = (JavascriptExecutor)driver;
						milt.executeScript("arguments[0].click();", Military);	
				
				WebElement CurrentEmployer = driver.findElement(By.name("employers")); 
					Select current= new Select(CurrentEmployer);
						current.selectByValue("218056");
						
				WebElement Society = driver.findElement(By.name("societies")); 
					Select society= new Select(Society);
						society.selectByValue("4006468");
						
				if(driver.findElements(By.xpath("//*[@class='form-submit blue with-arrow']")).size() != 0){
					System.out.println("Element is Present");
					}else{
					System.out.println("Element is Absent");
					}		
				
				WebElement Submit = driver.findElement(By.xpath("//*[@class='form-submit blue with-arrow']"));
					JavascriptExecutor submit = (JavascriptExecutor)driver;
						submit.executeScript("arguments[0].click();", Submit);
				
				System.out.println("###########################################");	
						
				if(driver.findElements(By.id("user_account")).size() != 0){
					System.out.println("Element is Present   -   "+UserID);
					}else{
					System.out.println("Element is Absent   -   "+UserID);
					}		
				
				WebElement Logout = driver.findElement(By.xpath("//*[@id='logout']"));
					JavascriptExecutor logout = (JavascriptExecutor)driver;
						logout.executeScript("arguments[0].click();", Logout);
				
				System.out.println(driver.getTitle());	
						
						
			   }
			               
 /*SIGN IN and SIGN OUT
			@Test(priority=4,enabled=true,dataProvider="becker")
			public void SignIn(String UserName, String Password, String ConfirmPassword, String FirstName, String LastName, String Phone, String AltPhone,
					String Address, String AptUnitSuite, String City, String PostalCode, String UserID) 
							throws InterruptedException
			{ 				
				driver.findElement(By.name("j_username")).clear(); 
					driver.findElement(By.name("j_username")).sendKeys("QA2Test2@mail.com"); 
				System.out.println();	
				
				driver.findElement(By.name("j_password")).clear(); 
					driver.findElement(By.name("j_password")).sendKeys("Devry123"); 
				System.out.println();
				
				WebElement SignIn = driver.findElement(By.xpath("//*[@class='form-submit blue with-arrow']"));
					JavascriptExecutor signin = (JavascriptExecutor)driver;
						signin.executeScript("arguments[0].click();", SignIn);
				
				System.out.println("###########################################");	
				
				if(driver.findElements(By.id("user_account")).size() != 0){
					System.out.println("Element is Present   -"+UserID  );
					}else{
					System.out.println("Element is Absent    -"+UserID);
					}
				
				WebElement Logout = driver.findElement(By.xpath("//*[@id='logout']"));
					JavascriptExecutor logout = (JavascriptExecutor)driver;
						logout.executeScript("arguments[0].click();", Logout);
						
            }      
   */            
               
               }