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
        driver.manage().window().maximize();        element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
            System.out.println("new ad displays after captcha image is not displaed");
            driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
        }
        checkElemenetESC(10);
        Thread.sleep(5000);
        element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
            System.out.println("new ad displays after captcha image is not displaed");
            driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

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

            js = (JavascriptExecutor) driver;
            Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
            js.executeScript("arguments[0].scrollIntoView();", Element);

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

            checkElemenetESC(1);

            driver.findElement(By.xpath("//*[@id=\"input_text\"]")).clear();
            driver.findElement(By.xpath("//*[@id=\"input_text\"]")).sendKeys(text);

            checkElemenetESC(1);

            if (driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).getText().equals(xpath_vietnameseToText)) {
            } else {
                driver.findElement(By.xpath("//*[@id=\"select2-select_lang_bin-container\"]")).click();
                driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Vietnamese);
                driver.findElement(By.xpath("/html/body/span/span/span[1]/input")).sendKeys(Keys.ENTER);
            }

            checkElemenetESC(1);

            // new ad displays 1
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
            checkElemenetESC(1);

            driver.findElement(By.xpath("//*[@id=\"frm_tts\"]/div[2]/div[2]/div[1]/a")).click();

            js = (JavascriptExecutor) driver;
            Element = driver.findElement(By.xpath("//*[@id=\"input_text\"]"));
            js.executeScript("arguments[0].scrollIntoView();", Element);

            checkElemenetESC(1);



            wait = new WebDriverWait(driver, Duration.ofSeconds(9));
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("captcha_image")));
                element_solve = driver.findElements(By.id("captcha_image"));
            } catch (Exception exception) {
                System.out.println("captcha not displays 0 ");
            }

            System.out.println("ok1");
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
                    } catch (Exception exception) {
                        System.out.println("resultImage error : " + exception);
                        break;
                    }

                    ((JavascriptExecutor) driver).executeScript("window.open('https://www.imagetotext.info/','_blank');");
                    windowHandle = driver.getWindowHandles().toArray()[2].toString();
                    driver.switchTo().window(windowHandle);

                    js = (JavascriptExecutor) driver;
                    Element = driver.findElement(By.xpath("/html/body/section[1]/div/div/div[1]/div[1]/div[1]/div[1]/div/label[2]/span"));
                    js.executeScript("arguments[0].scrollIntoView();", Element);

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
                    } catch (Exception exception) {
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
            System.out.println("ok2");

            // new ad displays 2
            element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("new ad displays after captcha image is not displaed");
                driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
            }

            // download
            checkElemenetESC(4);

            element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
            if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                System.out.println("new ad displays after captcha image is not displaed");
                driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a")));
                element_solve = driver.findElements(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a"));
                if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
                    driver.findElement(By.xpath("//*[@id=\"progessResults\"]/div[2]/center[1]/div/a")).click();
                }
            } catch (Exception exception) {
                System.out.println("download button not displays");
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


//    public void waitForElementToSendKeys(int seconds, String waitConditionLocator, String text) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(waitConditionLocator))).sendKeys(text);
//    }
//
//    public void waitForElementUnstable(int timeOut, int pollingEvery, String xpath) throws InterruptedException, IOException {
//
//        checkElemenetESC(4);
//
//        element_solve = driver.findElements(By.xpath("//div[@aria-modal='true']"));
//        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
//            System.out.println("new ad displays after captcha image is not displaed");
//            driver.findElement(By.xpath("//button[@aria-label='Close this dialog']")).click();
//        }
//
//        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofSeconds(pollingEvery)).ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
//        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath))).click();
//
//        element_solve = driver.findElements(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
//        if (element_solve.size() > 0 && element_solve.get(0).isDisplayed()) {
//            System.out.println("AD displays");
//            WebElement frame1 = driver.findElement(By.xpath("//iframe[contains(@style,'width: 100vw')]"));
//            driver.switchTo().frame(frame1);
//            List<WebElement> list = driver.findElements(By.id("dismiss-button"));
//            if (list.size() > 0) {
//                driver.findElement(By.id("dismiss-button")).click();
//            }
//            driver.switchTo().defaultContent(); // return default content
//        }
//    }

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

