package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Trip {
	public String id;
	public String city;
	public Integer length;
	public String tripData;
	
	public static Trip fromString(String s) throws NumberFormatException, ParseException {
		String[] tripFields;
		Trip trip;
		tripFields = s.split(" ");
		if (checkLength(tripFields[2])) {
			trip = Trip.builder()
				.id(tripFields[0])
				.city(tripFields[1] + " " + tripFields[2])
				.length(Integer.valueOf(tripFields[3]))
				.tripData(tripFields[4]+ " " + tripFields[5] + " " + tripFields[6] + " " + tripFields[7] + tripFields[8] + " " + tripFields[9])
				.build();
		}else {
			trip = Trip.builder()
				.id(tripFields[0])
				.city(tripFields[1])
				.length(Integer.valueOf(tripFields[2]))
				.tripData(tripFields[3]+ " " + tripFields[4] + " " + tripFields[5] + " " + tripFields[6] + tripFields[7] + " " + tripFields[8])
				.build();
		}
		
		return trip;
	}
	
	private static boolean checkLength(String word) {
		Pattern p = Pattern.compile("^*\\D*$");  
        Matcher m = p.matcher(word);  
        return m.matches();  
	}
}
