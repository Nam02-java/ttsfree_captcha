package com.example.Selenium.Package01;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.*;
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

    private static WebElement Element_inputText;

    private static String windowHandle;

    private static List<WebElement> element_solve;

    protected static String user_name = "nam02test";
    protected static String user_password = "IUtrangmaimai02";

    private static String string;

    private static WebDriverWait wait;

    /**
     * cd C:\Program Files\Google\Chrome\Application
     * chrome.exe --remote-debugging-port=9222 --user-data-dir="F:\CongViecHocTap\ChromeData"
     * chrome://settings/content/popups
     */

    @GetMapping("/testElenmentInputCaptcha")
    public ResponseEntity<?> testElenmentInputCaptcha(@RequestParam Map<String, String> params) throws InterruptedException, IOException, AWTException {
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

        driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

        Thread.sleep(5000);
        js = (JavascriptExecutor) driver;
        Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
        js.executeScript("arguments[0].scrollIntoView();", Element);

        js = (JavascriptExecutor) driver;

        Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
        js.executeScript("arguments[0].scrollIntoView();", Element);

        return ResponseEntity.ok(new String("END GAME"));

    }

    @GetMapping("/testSwap")
    public ResponseEntity<?> testSwap(@RequestParam Map<String, String> params) throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "F:\\CongViecHocTap\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("debuggerAddress", "localhost:9222");
        options.addArguments("disable-infobars");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys("test");

        ((JavascriptExecutor) driver).executeScript("window.open('https://imagevietnam.vn/','_blank');");
        Thread.sleep(3000);
        windowHandle = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(windowHandle);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"header_fixed_2\"]/div[2]/ul/li[1]/a/span")).click();
        Thread.sleep(1000);


        ((JavascriptExecutor) driver).executeScript("window.open('http://tratu.soha.vn/dict/en_vn/Image','_blank');");
        Thread.sleep(3000);
        windowHandle = driver.getWindowHandles().toArray()[2].toString();
        driver.switchTo().window(windowHandle);
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"header\"]/div[1]/a")).click();
        Thread.sleep(1000);

        driver.close();

        Thread.sleep(5000);
        windowHandle = driver.getWindowHandles().toArray()[1].toString();
        driver.switchTo().window(windowHandle);
        driver.close();

        Thread.sleep(5000);
        windowHandle = driver.getWindowHandles().toArray()[0].toString();
        driver.switchTo().window(windowHandle);
        driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();


        return ResponseEntity.ok(new String("END GAME"));

    }

    @GetMapping("/CaptchaMonster_ImageToText")
    public ResponseEntity<?> captChaMonster_ImageToText_API(@RequestParam Map<String, String> params) throws InterruptedException, IOException, AWTException {
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


        if (driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/div")).isDisplayed()) {
            System.out.println("Captcha displayed");

            Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
            js.executeScript("arguments[0].scrollIntoView();", Element);
            Thread.sleep(3000);

            Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
            js.executeScript("arguments[0].scrollIntoView();", Element);
            Thread.sleep(3000);

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
            Thread.sleep(10000);

            WebElement image = driver.findElement(By.id("resultImage"));
            String imageUrl = image.getAttribute("src");
            String base64Image = imageUrl.split(",")[1];
            byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
            FileOutputStream fos = new FileOutputStream(new File("F:\\CongViecHocTap\\CaptchaMonster\\CaptchaMonster.jpg"));
            fos.write(decodedBytes);
            fos.close();

            Thread.sleep(2000);

            ((JavascriptExecutor) driver).executeScript("window.open('https://www.imagetotext.info/','_blank');");
            windowHandle = driver.getWindowHandles().toArray()[2].toString();
            driver.switchTo().window(windowHandle);

            Thread.sleep(2000);
            Element = driver.findElement(By.xpath("/html/body/section[1]/div/div/div[1]/div[1]/div[1]/div[1]/div/label[2]/span"));
            js.executeScript("arguments[0].scrollIntoView();", Element);

            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"file\"]")).sendKeys("F:\\CongViecHocTap\\CaptchaMonster\\CaptchaMonster.jpg");
            Thread.sleep(2000);

            driver.findElement(By.id("jsShadowRoot")).click();
            Thread.sleep(10000);

            String string = String.valueOf(driver.findElement(By.id("imagetotext_result0")).getAttribute("value"));
            string = string.replaceAll("\\s+", ""); // Loại bỏ khoảng trắng
            string = string.replaceAll("[^a-zA-Z0-9]", ""); // Loại bỏ các kí tự không phải là chữ cái hoặc số
            System.out.println(string); // Kết quả: HelloWorld
            int count = string.length();
            System.out.println("Số kí tự trong chuỗi là: " + count);
            driver.close();

            Thread.sleep(2000);
            windowHandle = driver.getWindowHandles().toArray()[1].toString();
            driver.switchTo().window(windowHandle);
            driver.close();

            Thread.sleep(2000);
            windowHandle = driver.getWindowHandles().toArray()[0].toString();
            driver.switchTo().window(windowHandle);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"captcha_input\"]")).clear();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"captcha_input\"]")).sendKeys(string);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/div/a[2]")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/div/a[1]/i")).click();
        }
        return ResponseEntity.ok(new String("END GAME"));
    }

    @GetMapping("/ttsfree_captcha")
    public ResponseEntity<?> ttsfree_captcha(@RequestParam Map<String, String> params) throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "F:\\CongViecHocTap\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false); // disable chrome running as automation
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // disable chrome running as automation

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();

        driver.get(URL_WEBSITE);

        element_solve = driver.findElements(By.xpath("(//a[@class='link mr-20 color-heading ml-10'])[1]"));
        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
            System.out.println("login displayed");
            driver.findElement(By.xpath("(//a[@class='link mr-20 color-heading ml-10'])[1]")).click();
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
            checkElemenetESC(1);
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

            checkElemenetESC(3);

            js = (JavascriptExecutor) driver; // work

            Element_inputText = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
            js.executeScript("arguments[0].scrollIntoView();", Element_inputText);

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

            driver.findElement(By.xpath("//*[@id=\"input_text\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys(text);

            if (driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).getText().equals(xpath_vietnameseToText)) {
            } else {
                driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).click();
                driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Vietnamese);
                driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
            }

            // new ad displays 1
            element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("new ad displays after choose sex");
                driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
            }

            if (j % 2 == 0) {
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(male_voice))).click();
            } else {
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(female_voice))).click();
            }

            driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

            // new ad displays 2
            element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("new ad displays after captcha image is not displaed");
                driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
            }

            try {
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a")));
                element_solve = driver.findElements(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a"));
                if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                    checkElemenetESC(3);
                    js.executeScript("arguments[0].scrollIntoView();", Element_inputText);
                    driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a")).click();
                }
            } catch (Exception exception) {
                System.out.println("download button not displays");

                element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
                if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                    System.out.println("new ad displays after captcha image is not displaed");
                    driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
                }

                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("captcha_image")));
                    element_solve = driver.findElements(By.id("captcha_image"));
                } catch (Exception exception1) {
                    System.out.println("exception captcha is not displays");
                }
                if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                    while (true) {
                        System.out.println("Captcha displayed");

                        js.executeScript("arguments[0].scrollIntoView();", Element_inputText);

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

                        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                        try {
                            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultImage")));
                            WebElement image = driver.findElement(By.id("resultImage"));
                            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                                driver.findElement(By.xpath("/html/body/div[1]/div[1]/small")).click();
                                String imageUrl = image.getAttribute("src");
                                String base64Image = imageUrl.split(",")[1];
                                byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
                                FileOutputStream fos = new FileOutputStream(new File("F:\\CongViecHocTap\\CaptchaMonster\\CaptchaMonster.jpg"));
                                fos.write(decodedBytes);
                                fos.close();
                            }
                        } catch (Exception exception2) {
                            System.out.println("resultImage error : " + exception);
                            break;
                        }

                        ((JavascriptExecutor) driver).executeScript("window.open('https://www.imagetotext.info/','_blank');");
                        windowHandle = driver.getWindowHandles().toArray()[2].toString();
                        driver.switchTo().window(windowHandle);

                        js.executeScript("arguments[0].scrollIntoView();", Element_inputText);

                        driver.findElement(By.xpath("//*[@id=\"file\"]")).sendKeys("F:\\CongViecHocTap\\CaptchaMonster\\CaptchaMonster.jpg");
                        driver.findElement(By.id("jsShadowRoot")).click();

                        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                        try {
                            WebElement element = driver.findElement(By.id("imagetotext_result0"));
                            wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
                            string = String.valueOf(element.getAttribute("value"));
                            string = string.replaceAll("\\s+", "");
                            string = string.replaceAll("[^a-zA-Z0-9]", "");
                            System.out.println(string);
                            int count = string.length();
                            System.out.println("Số kí tự trong chuỗi là: " + count);
                            driver.close();
                        } catch (Exception exception3) {
                            System.out.println("Value in input text is not displays");
                            break;
                        }

                        windowHandle = driver.getWindowHandles().toArray()[1].toString();
                        driver.switchTo().window(windowHandle);
                        driver.close();

                        windowHandle = driver.getWindowHandles().toArray()[0].toString();
                        driver.switchTo().window(windowHandle);
                        driver.findElement(By.xpath("//*[@id=\"captcha_input\"]")).clear();
                        driver.findElement(By.xpath("//*[@id=\"captcha_input\"]")).sendKeys(string);
                        driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/div/a[2]")).click();

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
            }

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

            getLastModified("E:\\Downloads\\");
            Files.move(Paths.get(String.valueOf(chosenFile)), Paths.get("F:\\CongViecHocTap\\TestDowloadMP3\\" + fileName + ".mp3"), StandardCopyOption.REPLACE_EXISTING);
        }
        driver.quit();
        return ResponseEntity.ok(new String("END GAME"));
    }

    @GetMapping("/ttsfree_captcha_noForLoop")
    public ResponseEntity<?> ttsfree_captcha_noForLoop(@RequestParam Map<String, String> params) throws InterruptedException, IOException {
        //div[@class='swal2-container swal2-center swal2-backdrop-show']
        //span[@class='ns-kcq03-e-16']
        WebDriverWait wait;
        List<WebElement> element_solve;
        String URL_WEBSITE = "https://ttsfree.com/vn";
        String user_name = "nam02test";
        String user_password = "IUtrangmaimai02";
        String male_voice = "//*[@id=\"voice_name_bin\"]/div[2]/label";
        String female_voice = "//*[@id=\"voice_name_bin\"]/div[1]/label";
        String fileName;
        String Vietnamese = "138";
        String xpath_vietnameseToText = "138. Vietnamese (Vietnam) - VN";
        JavascriptExecutor js;
        WebElement Element_inputText;
        String windowHandle;
        String string;
        File chosenFile = null;

        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "F:\\CongViecHocTap\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false); // disable chrome running as automation
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // disable chrome running as automation

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.manage().window().maximize();

        driver.get(URL_WEBSITE);

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            System.out.println("wait ESC 1");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/small")));
            element_solve = driver.findElements(By.xpath("/html/body/div[1]/div[1]/small"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                driver.findElement(By.xpath("/html/body/div[1]/div[1]/small")).click();
            }
        } catch (Exception exception) {
            System.out.println("ESC not displays 1");
        }

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            System.out.println("wait update display 1");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-modal='true']")));
            element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
            }
        } catch (Exception exception) {
            System.out.println("AD UPDATE NOT DISPLAYS 1");
        }

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            System.out.println("AD display 1");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-modal='true']")));
            element_solve = driver.findElements(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("AD 1 displays");
                WebElement frame1 = driver.findElement(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
                driver.switchTo().frame(frame1);
                List<WebElement> list = driver.findElements(By.id("dismiss-button"));
                if (list.size() > 0) {
                    driver.findElement(By.id("dismiss-button")).click();
                }
                driver.switchTo().defaultContent(); // return default content
            }
        } catch (Exception exception) {
            System.out.println("AD 1  NOT DISPLAYS 1");
        }

        driver.findElement(By.xpath("(//a[@class='link mr-20 color-heading ml-10'])[1]")).click(); // click login

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            System.out.println("wait ESC 1");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/small")));
            element_solve = driver.findElements(By.xpath("/html/body/div[1]/div[1]/small"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                driver.findElement(By.xpath("/html/body/div[1]/div[1]/small")).click();
            }
        } catch (Exception exception) {
            System.out.println("ESC not displays 1");
        }

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            System.out.println("wait update display 1");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-modal='true']")));
            element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
            }
        } catch (Exception exception) {
            System.out.println("AD UPDATE NOT DISPLAYS 1");
        }

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            System.out.println("AD display 1");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-modal='true']")));
            element_solve = driver.findElements(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("AD 1 displays");
                WebElement frame1 = driver.findElement(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
                driver.switchTo().frame(frame1);
                List<WebElement> list = driver.findElements(By.id("dismiss-button"));
                if (list.size() > 0) {
                    driver.findElement(By.id("dismiss-button")).click();
                }
                driver.switchTo().defaultContent(); // return default content
            }
        } catch (Exception exception) {
            System.out.println("AD 1  NOT DISPLAYS 1");
        }

        driver.findElement(By.xpath("//input[@name='txt_username']")).sendKeys(user_name);
        driver.findElement(By.xpath("//input[@name='txt_password']")).sendKeys(user_password);
        driver.findElement(By.xpath("//ins[@class='iCheck-helper']")).click();
        driver.findElement(By.xpath("//input[@id='btnLogin']")).click();

        System.out.println("---------------------------------------------------------------------------------------");
        String text = params.get("Text");
        String voice = params.get("Voice");
        fileName = params.get("FileName");
        System.out.println(text + " " + voice + " " + fileName);

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

        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/small")));
            element_solve = driver.findElements(By.xpath("/html/body/div[1]/div[1]/small"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                driver.findElement(By.xpath("/html/body/div[1]/div[1]/small")).click();
            }
        } catch (Exception exception) {
            System.out.println("ESC not displays");
        }


        js = (JavascriptExecutor) driver; // work

        Element_inputText = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
        js.executeScript("arguments[0].scrollIntoView();", Element_inputText);

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

        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys(text);

        if (driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).getText().equals(xpath_vietnameseToText)) {
        } else {
            driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).click();
            driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Vietnamese);
            driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
        }

        // new ad displays 1
        element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
            System.out.println("new ad displays after choose sex");
            driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
        }

        if (voice.equals(male_voice)) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(male_voice))).click();
        } else {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(female_voice))).click();
        }

        driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

        // new ad displays 2
//        element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
//        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
//            driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
//        }
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            System.out.println("wait update display 1");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-modal='true']")));
            element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("new ad displays after captcha image is not displaed");
                driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
            }
        } catch (Exception exception) {
            System.out.println("AD UPDATE NOT DISPLAYS 1");
        }

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a")));
            element_solve = driver.findElements(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {

                wait = new WebDriverWait(driver, Duration.ofSeconds(3));
                try {
                    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/small")));
                    element_solve = driver.findElements(By.xpath("/html/body/div[1]/div[1]/small"));
                    if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                        driver.findElement(By.xpath("/html/body/div[1]/div[1]/small")).click();
                    }
                } catch (Exception exception) {
                    System.out.println("ESC not displays");
                }

                /**
                 * new update 18/10 at 10:43AM
                 */
                wait = new WebDriverWait(driver, Duration.ofSeconds(5));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a")));
                js.executeScript("arguments[0].scrollIntoView();", Element_inputText);
                driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a")).click();
            }
        } catch (Exception exception) {
            System.out.println("download button not displays");

            element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("new ad displays after captcha image is not displaed");
                driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
            }

            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("captcha_image")));
                element_solve = driver.findElements(By.id("captcha_image"));
            } catch (Exception exception1) {
                System.out.println("exception captcha is not displays");
            }
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                while (true) {
                    System.out.println("Captcha displayed");

                    js.executeScript("arguments[0].scrollIntoView();", Element_inputText);

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

                    wait = new WebDriverWait(driver, Duration.ofSeconds(20));
                    try {
                        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("resultImage")));
                        WebElement image = driver.findElement(By.id("resultImage"));
                        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                            driver.findElement(By.xpath("/html/body/div[1]/div[1]/small")).click();
                            String imageUrl = image.getAttribute("src");
                            String base64Image = imageUrl.split(",")[1];
                            byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
                            FileOutputStream fos = new FileOutputStream(new File("F:\\CongViecHocTap\\CaptchaMonster\\CaptchaMonster.jpg"));
                            fos.write(decodedBytes);
                            fos.close();
                        }
                    } catch (Exception exception2) {
                        System.out.println("resultImage error : " + exception);
                        break;
                    }

                    ((JavascriptExecutor) driver).executeScript("window.open('https://www.imagetotext.info/','_blank');");
                    windowHandle = driver.getWindowHandles().toArray()[2].toString();
                    driver.switchTo().window(windowHandle);

                    js.executeScript("arguments[0].scrollIntoView();", Element_inputText);

                    driver.findElement(By.xpath("//*[@id=\"file\"]")).sendKeys("F:\\CongViecHocTap\\CaptchaMonster\\CaptchaMonster.jpg");
                    driver.findElement(By.id("jsShadowRoot")).click();

                    wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                    try {
                        WebElement element = driver.findElement(By.id("imagetotext_result0"));
                        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, "value"));
                        string = String.valueOf(element.getAttribute("value"));
                        string = string.replaceAll("\\s+", "");
                        string = string.replaceAll("[^a-zA-Z0-9]", "");
                        System.out.println(string);
                        int count = string.length();
                        System.out.println("Số kí tự trong chuỗi là: " + count);
                        driver.close();
                    } catch (Exception exception3) {
                        System.out.println("Value in input text is not displays");
                        break;
                    }

                    windowHandle = driver.getWindowHandles().toArray()[1].toString();
                    driver.switchTo().window(windowHandle);
                    driver.close();

                    windowHandle = driver.getWindowHandles().toArray()[0].toString();
                    driver.switchTo().window(windowHandle);
                    driver.findElement(By.xpath("//*[@id=\"captcha_input\"]")).clear();
                    driver.findElement(By.xpath("//*[@id=\"captcha_input\"]")).sendKeys(string);
                    driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/div/a[2]")).click();

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
        }

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(4));
            System.out.println("AD display 1");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@aria-modal='true']")));
            element_solve = driver.findElements(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("AD 1 displays");
                WebElement frame1 = driver.findElement(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
                driver.switchTo().frame(frame1);
                List<WebElement> list = driver.findElements(By.id("dismiss-button"));
                if (list.size() > 0) {
                    driver.findElement(By.id("dismiss-button")).click();
                }
                driver.switchTo().defaultContent(); // return default content
            }
        } catch (Exception exception) {
            System.out.println("AD 1  NOT DISPLAYS 1");
        }

        // getLastModified("E:\\Downloads\\");

        File directory_download = new File("E:\\Downloads\\");
        File[] file_download = directory_download.listFiles(File::isFile);

        long lastModifiedTime = Long.MIN_VALUE;
        chosenFile = null;

        if (file_download != null) {
            for (File file : file_download) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        System.out.println(chosenFile);


        Files.move(Paths.get(String.valueOf(chosenFile)), Paths.get("F:\\CongViecHocTap\\TestDowloadMP3\\" + fileName + ".mp3"), StandardCopyOption.REPLACE_EXISTING);
        //driver.close();
        return ResponseEntity.ok(new String("END GAME"));
    }


    @GetMapping("/tssfree_test")
    public ResponseEntity<?> tssfree_test(@RequestParam Map<String, String> params) throws InterruptedException, IOException, AWTException {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        System.setProperty("webdriver.chrome.driver", "F:\\CongViecHocTap\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false); // disable chrome running as automation
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation")); // disable chrome running as automation

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get(URL_WEBSITE);

        driver.close();

        return ResponseEntity.ok(new String("END GAME"));
    }


    public void checkElemenetESC(int seconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[1]/small")));
            element_solve = driver.findElements(By.xpath("/html/body/div[1]/div[1]/small"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                driver.findElement(By.xpath("/html/body/div[1]/div[1]/small")).click();
            }
        } catch (Exception exception) {
            System.out.println("ESC not displays");
        }
    }

    public static File getLastModified(String directoryFilePath) throws InterruptedException {
        //   File chosenFile = null;

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

    @GetMapping("/todo")
    public String aaa() {
        System.out.println("hihihi");
        return "hihihihi";
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println("ok");
    }
}
