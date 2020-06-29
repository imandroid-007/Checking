package com.example.realestate.OTPReceiver;

public interface OtpReceivedInterface {

    void onOtpReceived(String otp);
    void onOtpTimeout();
}
