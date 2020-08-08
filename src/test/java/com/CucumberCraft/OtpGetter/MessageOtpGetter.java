package com.CucumberCraft.OtpGetter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MessageOtpGetter {
    private MySmsMessageGetter messageGetter = new MySmsMessageGetter();
    private HardCodedOtpPatternRepository patternRepository = new HardCodedOtpPatternRepository();
    
    public MessageOtpGetter() {
       
    }
    
    public String getOtp(String sender, String receiver) {
        String pattern = patternRepository.getOtpPattern(sender);
        String message = messageGetter.getMessage(sender, receiver);
        return extractOtpByPattern(message, pattern);
    }

    private String extractOtpByPattern(String message, String pattern) {
        String result = "";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(message);
        if (m.find()) {
            result = m.group(0);
        }
        return result;
    }
}
