package excel.vasanthvk;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class log1 {
	WebDriver driver;
	@Test(dataProvider="getData")
	public void test(String name,String email,String subject,String message) {
	driver.findElement(By.id("name")).sendKeys(name);
	driver.findElement(By.id("email")).sendKeys(email);
	driver.findElement(By.id("subject")).sendKeys(subject);
	driver.findElement(By.id("message")).sendKeys(message);
	driver.findElement(By.id("submit")).click();
	}
  @BeforeMethod
  public void beforeMethod() {
	  String path="D:\\chromedriver.exe";
	  System.setProperty("webdriver.chrome.driver", path);
	  driver=new ChromeDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("file:///C:/Users/itctesting08/Downloads/Portfolio-Web_Application-Project-main/Portfolio-Web_Application-Project-main/Portfolio-WebApplication/Home%20Page/vasanthPortfolio/contact.html?name=vasanth&email=24vasanthakumarj%40gmail.com&subject=Send+your+Resume");
  }

  @AfterMethod
  public void afterMethod() {
	  driver.quit();
  }


  @DataProvider
  public String[][] getData() throws Exception {
  File src=new File("D:\\testdata\\contacts.xlsx");
  FileInputStream fis=new FileInputStream(src);
  XSSFWorkbook wb=new XSSFWorkbook(fis);
  XSSFSheet sheet=wb.getSheet("Sheet1");
  int Rows=sheet.getPhysicalNumberOfRows();
  int cols=sheet.getRow(0).getLastCellNum();

  String[][] data=new String[Rows-1][cols];
  for(int i=0;i<Rows-1;i++)
  {
  for(int j=0;j<cols;j++)
  {
  DataFormatter df=new DataFormatter();
  data[i][j]= df.formatCellValue(sheet.getRow(i+1).getCell(j));

  }
  System.out.println();
  }
  wb.close();
  fis.close();
  return data;
  }
  }