package com.blazedemo.utils;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name="userData")
	public Object[][] getData(){
		return new Object[][] {
			{
				"John",
				"123 Main St",
				"New York",
				"NY",
				"12345",
				"Visa",
				"4111111111111111",
				"John Doe"
			},
			
			{
				"David",
				"Elm St",
				"Los Angeles",
				"LA",
				"34322",
				"American Express",
				"5500000000000004",
				"David Smith"
			}
		};
		
	}

}
