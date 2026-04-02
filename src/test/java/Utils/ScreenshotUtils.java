package Utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtils {
	public static String getScreenshot(WebDriver driver, String name) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		File src= ts.getScreenshotAs(OutputType.FILE);
		String folderpath = System.getProperty("user.dir") + "/BStackDemoScreenshots/";
		File folder= new File(folderpath);
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		String path = folderpath + name + System.currentTimeMillis() + ".png";
		File dest = new File(path);
		
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}

}
