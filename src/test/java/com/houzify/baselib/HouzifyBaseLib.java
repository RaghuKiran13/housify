/***********************************************************************
* @author 			:		LAKSHMI BS
* @description		: 		Generic library with reusable methods that can be used only for QuickRide app.
* @method			:		setobjects()
* @method			:		setUp()
* @method			:		tearDown()
* @method 			:		
* @method 
*/
package com.houzify.baselib;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import io.appium.java_client.android.AndroidDriver;

public class HouzifyBaseLib 
{	  
	public AndroidDriver driver;
	static public String sDirPath=System.getProperty("user.dir");
	static public String sConfigFile=sDirPath+"\\Capabilities.Properties";
	//static public String sAPKPath = sDirPath+"\\..\\HouzifyAPK\\";
	static public String sAPKPath = sDirPath+"\\..\\Houzify Android Dev Build\\Housify\\app\\build\\outputs\\apk\\";
	File file = new File(sAPKPath);
	public DesiredCapabilities capabilities=null;
	GenericLib genericLib;
	
	@BeforeTest
	public void setobjects()
	{		
		genericLib = new GenericLib();
	}
	@BeforeMethod
	public void setUp() throws MalformedURLException, InterruptedException{
		File[] apk = file.listFiles();
		File app = new File(sAPKPath+"app-production-release.apk");
		//File app = new File(sAPKPath+apk[1].getName().toString());
		
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("automationName",genericLib.getCongigValue(sConfigFile, "AUTOMATIONNAME") );
		capabilities.setCapability("platformName",genericLib.getCongigValue(sConfigFile, "PLATFORMNAME") );
		capabilities.setCapability("platformVersion",genericLib.getCongigValue(sConfigFile, "PLATFORMVERSION"));
		capabilities.setCapability("deviceName",genericLib.getCongigValue(sConfigFile, "DEVICENAME") );
		capabilities.setCapability("app",app.getAbsolutePath());
		capabilities.setCapability("apppackage", genericLib.getCongigValue(sConfigFile, "APPPACKAGE") );
		capabilities.setCapability("appactivity", genericLib.getCongigValue(sConfigFile, "APPACTIVITY") );
	    
		driver=new AndroidDriver(new URL(genericLib.getCongigValue(sConfigFile, "URL")), capabilities);
		Thread.sleep(12000);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}

	@AfterMethod
	public void tearDown()
	{
		
		driver.quit();
		
	}
}
