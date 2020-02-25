package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WordsTranslatorTest {

	private static final String WEB_CHROME_DRIVER = "webdriver.chrome.driver";
	private static final String CHROME_DRIVER_PATH = "driver/chromedriver.exe";
	private static final String TARGET_URL = "https://translate.google.com/#view=home&op=translate&sl=en&tl=it";

	@Test
	public void translatedTextTest() {
		final String TEST_TEXT = "The pizza margherita is awesome";
		assertEquals(true, seleniumTranslatedTextTest(TEST_TEXT));
	}

	public boolean seleniumTranslatedTextTest(String targetText) {
		System.setProperty(WEB_CHROME_DRIVER, CHROME_DRIVER_PATH);
		WebDriver driver = new ChromeDriver();
		driver.get(TARGET_URL);
		driver.findElement(By.id("source")).sendKeys(targetText);
		driver.findElement(By.id("source")).submit();

		WebElement translationContainer = driver.findElement(By.className("tlid-translation translation"));

		String translatedText = translationContainer.findElement(By.name("span")).getText();
		String[] wordsArray = translatedText.trim().split(" ");
		driver.close();
		if (wordsArray.length == 5) {
			return true;
		}
		return false;
	}
}
