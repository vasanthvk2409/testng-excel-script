package excel.vasanthvk;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;



public class Reg1 {
WebDriver driver;
@BeforeMethod
public void beforeMethod() {

String path="D:\\selinum\\chromedriver_win32\\chromedriver.exe";
System.setProperty("webdriver.chrome.driver", path);
driver=new ChromeDriver();
driver.get("file:///C:/Users/itctesting08/Downloads/Portfolio-Web_Application-Project-main/Portfolio-Web_Application-Project-main/Portfolio-WebApplication/Registration%20Form/index.html");
}
@Test(dataProvider="getData")
public void registration(String firstname,String lastname,String dob ,String email,String mobilenumber,String gender,String address,String state,String resume,String registrationdate,String password,String confirmpassword)
{
driver.findElement(By.id("firstname")).sendKeys(firstname);
driver.findElement(By.id("lastname")).sendKeys(lastname);
WebElement dateDob = driver.findElement(By.xpath("//input[@id=\"dob\"]"));
dateDob.sendKeys("20062022");
driver.findElement(By.id("email")).sendKeys(email);
driver.findElement(By.id("phonenumber")).sendKeys(mobilenumber);
Select drop=new Select(driver.findElement(By.id("gender")));
drop.selectByIndex(2);
driver.findElement(By.id("address")).sendKeys(address);
Select dropDown1=new Select(driver.findElement(By.id("state")));
dropDown1.selectByIndex(1);
WebElement uploadElement = driver.findElement(By.id("resume"));
uploadElement.sendKeys("D:\\resume\\vasanth.pdf");
driver.findElement(By.id("registrationdate")).sendKeys(registrationdate);
driver.findElement(By.id("password")).sendKeys(password);
driver.findElement(By.id("confirm-password")).sendKeys(confirmpassword);
WebElement checkBoxSelect=driver.findElement(By.id("agree"));
checkBoxSelect.click();

//Alert alert=driver.switchTo().alert();
//alert.accept();
driver.findElement(By.id("submit-btn")).click();
  }
@DataProvider
public String[][] getData() throws Exception {
File src=new File("C:\\Users\\itctesting08\\Documents\\reg1.xlsx");
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
@AfterMethod
public void afterMethod() {
driver.quit();
}
}

