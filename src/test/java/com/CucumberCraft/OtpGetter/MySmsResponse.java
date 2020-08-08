package com.CucumberCraft.OtpGetter;

import java.util.List;

import com.CucumberCraft.JsonModel.MySmsMessage;

public class MySmsResponse {
    public int errorCode;
    public List<MySmsMessage> messages;
}
