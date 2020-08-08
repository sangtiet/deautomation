package com.CucumberCraft.OtpGetter;

public class TestOTP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MessageOtpGetter otpGetter = new MessageOtpGetter();
		String sender = "ZaloPay";
		String receiver = "0367260747";
		String otp = otpGetter.getOtp(sender, receiver);
		System.out.println("OTP = " + otp);
	}

}
