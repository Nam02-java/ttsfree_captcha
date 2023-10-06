package com.example.Selenium.Package01;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.*;
import java.util.List;

@RestController
@RequestMapping("/api/web")
public class Class01 {

    private static String URL_WEBSITE = "https://ttsfree.com/vn";
    private static String male_voice = "//*[@id=\"voice_name_bin\"]/div[2]/label";
    private static String female_voice = "//*[@id=\"voice_name_bin\"]/div[1]/label";
    private static File chosenFile = null;
    private static String fileName;
    private static WebDriver driver;

    private static String Vietnamese = "138";

    private static String xpath_vietnameseToText = "138. Vietnamese (Vietnam) - VN";

    private static JavascriptExecutor js;

    private static WebElement Element;

    private static String windowHandle;

    private static List<WebElement> element_solve;

    protected static String user_name = "test02";
    protected static String user_password = "PASSword123";

    /**
     * cd C:\Program Files\Google\Chrome\Application
     * chrome.exe --remote-debugging-port=9222 --user-data-dir="F:\CongViecHocTap\ChromeData"
     * chrome://settings/content/popups
     */
    @GetMapping("/ttsfree_captcha")
    public ResponseEntity<?> ttsfree_captcha(@RequestParam Map<String, String> params) throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "F:\\CongViecHocTap\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

//        System.setProperty("java.awt.headless", "false"); // false = active robot class
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("disable-infobars");
//        options.addArguments("--start-maximized");
//        options.addArguments("--disable-extensions");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false); // disable chrome running as automation
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // disable chrome running as automation

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get(URL_WEBSITE);

//        Robot robot = new Robot();
//        int x = 1512; // tọa độ x của vị trí cần click
//        int y = 100; // tọa độ y của vị trí cần click
//        robot.mouseMove(x, y); // di chuyển con trỏ chuột đến vị trí cần click
//        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK); // nhấn nút trái chuột
//        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK); // thả nút trái chuột
//        Current mouse position: (1512, 98)

        element_solve = driver.findElements(By.xpath("(//a[@class='link mr-20 color-heading ml-10'])[1]"));
        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
            System.out.println("login displayed");
            driver.findElement(By.xpath("(//a[@class='link mr-20 color-heading ml-10'])[1]")).click();
            Thread.sleep(1000);
            element_solve = driver.findElements(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("AD displays");
                WebElement frame1 = driver.findElement(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
                driver.switchTo().frame(frame1);
                List<WebElement> list = driver.findElements(By.id("dismiss-button"));
                if (list.size() > 0) {
                    driver.findElement(By.id("dismiss-button")).click();
                }
                driver.switchTo().defaultContent(); // return default content
            }
            checkElenmentESC();
            driver.findElement(By.xpath("//input[@name='txt_username']")).sendKeys(user_name);
            driver.findElement(By.xpath("//input[@name='txt_password']")).sendKeys(user_password);
            driver.findElement(By.xpath("//ins[@class='iCheck-helper']")).click();
            driver.findElement(By.xpath("//input[@id='btnLogin']")).click();
        }

        for (int j = 1; j <= 100; j++) {
            System.out.println("---------------------------------------------------------------------------------------");
            System.out.println("NUMBER :  " + j);
            String text = params.get("Text");
            String voice = params.get("Voice");
            fileName = params.get("FileName") + j;
            System.out.println(fileName);

            File directory = new File("F:\\CongViecHocTap\\TestDowloadMP3\\");
            File[] files = directory.listFiles(File::isFile);
            for (int i = 0; i < files.length; i++) {
                String name = files[i].getName();
                String target = name.copyValueOf(".mp3".toCharArray());
                name = name.replace(target, "");
                if (name.equals(fileName)) {
                    return ResponseEntity.ok(new String("The file name is duplicate , please change the name"));
                }
            }

            Thread.sleep(2000);
            checkElenmentESC();

            js = (JavascriptExecutor) driver;
            Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
            js.executeScript("arguments[0].scrollIntoView();", Element);

            Thread.sleep(2000);

            element_solve = driver.findElements(By.xpath("//ins[@data-anchor-status='displayed']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("ad 1 displayed");
                driver.findElement(By.xpath("(//div[@class='grippy-host'])[1]")).click();
            }

            element_solve = driver.findElements(By.xpath("(//img[@title='Ad.Plus Advertising'])[1]"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("ad 2 displayed");
                driver.findElement(By.xpath("(//img[@title='Ad.Plus Advertising'])[1]")).click();
            }

            checkElenmentESC();

            driver.findElement(By.xpath("//*[@id=\"input_text\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys(text);

            checkElenmentESC();

            if (driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).getText().equals(xpath_vietnameseToText)) {
            } else {
                driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).click();
                waitForElementToSendKeys(10, "/html/body/span/span/span[1]/input", Vietnamese);
                driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
            }

            checkElenmentESC();

            // new ad displays 1
            Thread.sleep(1000);
            element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("new ad displays after choose sex");
                driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();

            }

            if (j % 2 == 0) {
                waitForElementToClick(10, male_voice);
            } else {
                waitForElementToClick(10, female_voice);
            }
            checkElenmentESC();

            driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

            Thread.sleep(2000);
            js = (JavascriptExecutor) driver;
            Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
            js.executeScript("arguments[0].scrollIntoView();", Element);

            checkElenmentESC();

            element_solve = driver.findElements(By.id("captcha_image"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                while (true) {
                    System.out.println("Captcha displayed");

                    js = (JavascriptExecutor) driver;
                    Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
                    js.executeScript("arguments[0].scrollIntoView();", Element);

                    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    try {
                        BufferedImage fullScreen = ImageIO.read(screenshot);
                        BufferedImage capture = fullScreen.getSubimage(892, 615, 190, 55);
                        ImageIO.write(capture, "png", new File("F:\\CongViecHocTap\\Captcha\\Captcha.png"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ((JavascriptExecutor) driver).executeScript("window.open('https://capmonster.cloud/en/Demo/','_blank');");
                    windowHandle = driver.getWindowHandles().toArray()[1].toString();
                    driver.switchTo().window(windowHandle);
                    driver.findElement(By.xpath("//*[@id=\"uploadImageInput\"]")).sendKeys("F:\\CongViecHocTap\\Captcha\\Captcha.png"); //copy input tag
                    Thread.sleep(10000); // loading image

                    WebElement image = driver.findElement(By.id("resultImage"));
                    String imageUrl = image.getAttribute("src");
                    String base64Image = imageUrl.split(",")[1];
                    byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
                    FileOutputStream fos = new FileOutputStream(new File("F:\\CongViecHocTap\\CaptchaMonster\\CaptchaMonster.jpg"));
                    fos.write(decodedBytes);
                    fos.close();

                    ((JavascriptExecutor) driver).executeScript("window.open('https://www.imagetotext.info/','_blank');");
                    windowHandle = driver.getWindowHandles().toArray()[2].toString();
                    driver.switchTo().window(windowHandle);

                    Thread.sleep(1000);

                    js = (JavascriptExecutor) driver;
                    Element = driver.findElement(By.xpath("/html/body/section[1]/div/div/div[1]/div[1]/div[1]/div[1]/div/label[2]/span"));
                    js.executeScript("arguments[0].scrollIntoView();", Element);

                    driver.findElement(By.xpath("//*[@id=\"file\"]")).sendKeys("F:\\CongViecHocTap\\CaptchaMonster\\CaptchaMonster.jpg");
                    Thread.sleep(2000); // load ảnh
                    driver.findElement(By.id("jsShadowRoot")).click();
                    Thread.sleep(10000); // load text

                    String string = String.valueOf(driver.findElement(By.id("imagetotext_result0")).getAttribute("value"));
                    string = string.replaceAll("\\s+", "");
                    string = string.replaceAll("[^a-zA-Z0-9]", "");
                    System.out.println(string);
                    int count = string.length();
                    System.out.println("Số kí tự trong chuỗi là: " + count);
                    driver.close();

                    windowHandle = driver.getWindowHandles().toArray()[1].toString();
                    driver.switchTo().window(windowHandle);
                    driver.close();

                    windowHandle = driver.getWindowHandles().toArray()[0].toString();
                    driver.switchTo().window(windowHandle);
                    driver.findElement(By.xpath("//*[@id=\"captcha_input\"]")).clear();
                    driver.findElement(By.xpath("//*[@id=\"captcha_input\"]")).sendKeys(string);
                    driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/div/a[2]")).click();
                    Thread.sleep(2000);
                    if (driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a")).isDisplayed()) {
                        // nếu nút download hiển thị thì break khỏi vòng lặp while
                        break;
                    }
                    // nếu nút download không hiển thị thì tiếp tục công việc với captcha đến khi được thì thôi
                    driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/div/a[1]/i")).click();
                }
            } else {
                System.out.println("Captcha image is not displayed");
            }

            // new ad displays 2
            Thread.sleep(1000);
            element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("new ad displays after captcha image is not displaed");
                driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();

            }

            waitForElementUnstable(5, 30, "//*[@id=\"progessResults\"]/div[2]/center[1]/div/a");
            getLastModified("E:\\Downloads\\");
            Files.move(Paths.get(String.valueOf(chosenFile)), Paths.get("F:\\CongViecHocTap\\TestDowloadMP3\\" + fileName + ".mp3"), StandardCopyOption.REPLACE_EXISTING);
        }
        driver.quit();
        return ResponseEntity.ok(new String("END GAME"));
    }

    public void checkElenmentESC() throws InterruptedException {
        Thread.sleep(1000);
        element_solve = driver.findElements(By.xpath("/html/body/div[1]/div[1]/small"));
        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/small")).click();
        }
    }

    public void waitForElementToSendKeys(int seconds, String waitConditionLocator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).sendKeys(text);
    }

    public void waitForElementUnstable(int timeOut, int pollingEvery, String xpath) throws InterruptedException, IOException {

        Thread.sleep(3000);

        checkElenmentESC();

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofSeconds(pollingEvery)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();

        Thread.sleep(1500);
        element_solve = driver.findElements(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
            System.out.println("AD displays");
            WebElement frame1 = driver.findElement(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
            driver.switchTo().frame(frame1);
            List<WebElement> list = driver.findElements(By.id("dismiss-button"));
            if (list.size() > 0) {
                driver.findElement(By.id("dismiss-button")).click();
            }
            driver.switchTo().defaultContent(); // return default content
        }
    }

    public void waitForElementToClick(int seconds, String waitConditionLocator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).click();
    }

    public static File getLastModified(String directoryFilePath) throws InterruptedException {
        Thread.sleep(2000);
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);

        long lastModifiedTime = Long.MIN_VALUE;
        chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        System.out.println(chosenFile);
        return chosenFile;
    }
}

class test {
    public static void main(String[] args) throws AWTException, InterruptedException {
        while (true) {
            Thread.sleep(2000);
            Point mousePosition = MouseInfo.getPointerInfo().getLocation();
            int x = (int) mousePosition.getX();
            int y = (int) mousePosition.getY();
            System.out.println("Current mouse position: (" + x + ", " + y + ")");
        }
    }
}

