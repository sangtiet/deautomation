package com.CucumberCraft.supportLibraries;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Class to encapsulate utility functions of the framework
 * 
 */
public class Util {

	private Util() {
		// To prevent external instantiation of this class
	}

	/**
	 * Function to get the separator string to be used for directories and files
	 * based on the current OS
	 * 
	 * @return The file separator string
	 */
	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}

	public static String getAbsolutePath() {
		String relativePath = new File(System.getProperty("user.dir"))
				.getAbsolutePath();
		return relativePath;
	}

	public static String getResultsPath() {

		File path = new File(Util.getAbsolutePath() + Util.getFileSeparator()
				+ "target" + Util.getFileSeparator() + "Results");

		if (!path.isDirectory()) {
			path.mkdirs();
		}

		return path.toString();
	}

	public static String getOldResultPath() {

		File path = new File(Util.getAbsolutePath() + Util.getFileSeparator()
				+ "ResultsOld");

		if (!path.isDirectory()) {
			path.mkdirs();
		}

		return path.toString();
	}

	public static String getTargetPath() {

		File targetPath = new File(Util.getAbsolutePath()
				+ Util.getFileSeparator() + "target" + Util.getFileSeparator()
				+ "cucumber-report");

		return targetPath.toString();
	}

	public static byte[] takeScreenshot(WebDriver driver) {
		if (driver == null) {
			throw new RuntimeException("Report.driver is not initialized!");
		}

		if (driver.getClass().getSimpleName().equals("HtmlUnitDriver")
				|| driver
						.getClass()
						.getGenericSuperclass()
						.toString()
						.equals("class org.openqa.selenium.htmlunit.HtmlUnitDriver")) {
			return null; // Screenshots not supported in headless mode
		}

		if (driver.getClass().getSimpleName().equals("RemoteWebDriver")) {
			Capabilities capabilities = ((RemoteWebDriver) driver)
					.getCapabilities();
			if (capabilities.getBrowserName().equals("htmlunit")) {
				return null; // Screenshots not supported in headless mode
			}
			WebDriver augmentedDriver = new Augmenter().augment(driver);
			return ((TakesScreenshot) augmentedDriver)
					.getScreenshotAs(OutputType.BYTES);
		} else {
			return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		}
	}

	/**
	 * Function to return the current time
	 * 
	 * @return The current time
	 * @see #getCurrentFormattedTime(String)
	 */
	public static Date getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * Function to return the current time, formatted as per the
	 * DateFormatString setting
	 * 
	 * @param dateFormatString
	 *            The date format string to be applied
	 * @return The current time, formatted as per the date format string
	 *         specified
	 * @see #getCurrentTime()
	 * @see #getFormattedTime(Date, String)
	 */
	public static String getCurrentFormattedTime(String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * Function to format the given time variable as specified by the
	 * DateFormatString setting
	 * 
	 * @param time
	 *            The date/time variable to be formatted
	 * @param dateFormatString
	 *            The date format string to be applied
	 * @return The specified date/time, formatted as per the date format string
	 *         specified
	 * @see #getCurrentFormattedTime(String)
	 */
	public static String getFormattedTime(Date time, String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		return dateFormat.format(time);
	}

	/**
	 * Function to get the time difference between 2 {@link Date} variables in
	 * minutes/seconds format
	 * 
	 * @param startTime
	 *            The start time
	 * @param endTime
	 *            The end time
	 * @return The time difference in terms of hours, minutes and seconds
	 */
	public static String getTimeDifference(Date startTime, Date endTime) {
		long timeDifferenceSeconds = (endTime.getTime() - startTime.getTime()) / 1000; // to
																						// convert
																						// from
																						// milliseconds
																						// to
																						// seconds
		long timeDifferenceMinutes = timeDifferenceSeconds / 60;

		String timeDifferenceDetailed;
		if (timeDifferenceMinutes >= 60) {
			long timeDifferenceHours = timeDifferenceMinutes / 60;

			timeDifferenceDetailed = Long.toString(timeDifferenceHours)
					+ " hour(s), " + Long.toString(timeDifferenceMinutes % 60)
					+ " minute(s), "
					+ Long.toString(timeDifferenceSeconds % 60) + " second(s)";
		} else {
			timeDifferenceDetailed = Long.toString(timeDifferenceMinutes)
					+ " minute(s), "
					+ Long.toString(timeDifferenceSeconds % 60) + " second(s)";
		}

		return timeDifferenceDetailed;
	}
}