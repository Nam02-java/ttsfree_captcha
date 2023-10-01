package com.example.Selenium.Package01;

import com.github.dockerjava.transport.DockerHttpClient;
import com.google.common.net.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.http.HttpEntity;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.http.HttpResponse;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Dimension;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Security;
import java.time.Duration;
import java.util.*;
import java.awt.Rectangle;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/web")
public class Class01 {


    private static String URL_WEBSITE = "https://ttsfree.com/#google_vignette";
    private static String male_voice = "//*[@id=\"voice_name_bin\"]/div[2]/label";
    private static String female_voice = "//*[@id=\"voice_name_bin\"]/div[1]/label";
    private static File chosenFile = null;
    private static String fileName;
    private static WebDriver driver;

    private static String Vietnamese = "138";

    private static String xpath_vietnameseToText = "138. Vietnamese (Vietnam) - VN";


    private static Boolean check;

    private static int j;

    private static JavascriptExecutor js;

    private static WebElement Element;

    private static String windowHandle;

    private static Boolean flag;

    /**
     * cd C:\Program Files\Google\Chrome\Application
     * chrome.exe --remote-debugging-port=9222 --user-data-dir="F:\CongViecHocTap\ChromeData"
     * chrome://settings/content/popups
     */



      @GetMapping("/ttsfree_captcha")
    public ResponseEntity<?> ttsfree_captcha(@RequestParam Map<String, String> params) throws InterruptedException, IOException, AWTException {
        for (int j = 1; j <= 100; j++) {
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

            System.setProperty("webdriver.http.factory", "jdk-http-client");
            System.setProperty("webdriver.chrome.driver", "F:\\CongViecHocTap\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("debuggerAddress", "localhost:9222");
            options.addArguments("disable-infobars");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-extensions");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

            js = (JavascriptExecutor) driver;
            Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
            js.executeScript("arguments[0].scrollIntoView();", Element);


            driver.findElement(By.xpath("//*[@id=\"input_text\"]")).clear();

            driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys(text);

            if (driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).getText().equals(xpath_vietnameseToText)) {
            } else {
                driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).click();
                waitForElementToSendKeys(10, "/html/body/span/span/span[1]/input", Vietnamese);
                driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
            }

            if (j % 2 == 0) {
                waitForElementToClick(10, male_voice);
            } else {
                waitForElementToClick(10, female_voice);
            }

            driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

            waitForElementDisplay("/html/body/footer/div/div[1]/div[1]/a");

            List<WebElement> captchaImages = driver.findElements(By.id("captcha_image"));
            if (captchaImages.size() > 0 && captchaImages.get(0).isDisplayed()) {
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
            waitForElementUnstable(5, 30, "//*[@id=\"progessResults\"]/div[2]/center[1]/div/a");
            getLastModified("E:\\Downloads\\");
            Files.move(Paths.get(String.valueOf(chosenFile)), Paths.get("F:\\CongViecHocTap\\TestDowloadMP3\\" + fileName + ".mp3"), StandardCopyOption.REPLACE_EXISTING);
        }
        return ResponseEntity.ok(new String("END GAME"));
    }



    public void waitForElementToSendKeys(int seconds, String waitConditionLocator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).sendKeys(text);
    }

    public void waitForElementUnstable(int timeOut, int pollingEvery, String xpath) throws InterruptedException, IOException {


        Thread.sleep(1000);

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofSeconds(pollingEvery)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
    }

    public void waitForElementDisplay(String element_location) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(100));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element_location)));

        Thread.sleep(3000);

        js = (JavascriptExecutor) driver;
        Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
        js.executeScript("arguments[0].scrollIntoView();", Element);
    }

    public void waitForElementToClick(int seconds, String waitConditionLocator) {
        check = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).click();
        check = true;
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


//https://www.imagetotext.info/
//Element position: (713, 1095)
//Element size: (160, 45)
/**
 * BufferedImage capture = fullScreen.getSubimage(892, 615, 190, 55);
 * bba6a309545447103aa496d679a9f5a0
 */
