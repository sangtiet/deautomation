package com.CucumberCraft.OtpGetter;

public class HardCodedOtpPatternRepository {
	public HardCodedOtpPatternRepository() {
		
	}
	
    public String getOtpPattern(String sender) {
        switch (sender) {
            case "BACABANK":
                return "(?<!\\d)\\d{6}(?!\\d)";
            case "Techcombank":
                return "(?<!\\d)\\d{8}(?!\\d)";
            case "Sacombank":
                return "(?<!\\d)\\d{3}-\\d{3}(?!\\d)";
            default:
                return "\\d{6}\\d*";
        }
    }
}
