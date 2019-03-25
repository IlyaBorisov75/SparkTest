package models;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Driver {
	public String id;
	public String driverName;
	public String address;
	public String email;
	
	public static Driver fromString(String s) {
		String[] driverFields;
		driverFields = s.split(",");
		Driver driver = Driver.builder()
				.id(driverFields[0])
				.driverName(driverFields[1])
				.address(driverFields[2])
				.email(driverFields[3])
				.build();
		return driver;
	}
}
