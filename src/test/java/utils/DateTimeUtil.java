package utils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class DateTimeUtil {
	
	public static LocalTime parseTime(String timeText)
	{
		
		String value = timeText.trim();
		
		try {
			
			return LocalTime.parse(value,DateTimeFormatter.ofPattern("HH:mm"));
		}catch(DateTimeParseException e)
		{
			System.out.println(e.getMessage());
		}
		
		try {
			
			return LocalTime.parse(value,DateTimeFormatter.ofPattern("h:mm a",Locale.ENGLISH));
		}
		catch(DateTimeParseException e)
		{
			throw new AssertionError("Invalid time format: " + timeText);
		}
	}

}
