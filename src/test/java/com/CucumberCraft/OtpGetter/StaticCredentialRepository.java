package com.CucumberCraft.OtpGetter;

import com.CucumberCraft.JsonModel.MySmsCredential;
import com.CucumberCraft.supportLibraries.GsonUtils;
import com.google.common.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class StaticCredentialRepository {
    private static final String MYSMS_AUTH_FILE = "src/test/resources/mysms/mysms-auth.json";

    public MySmsCredential getForPhoneNumber(String phoneNumber) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(MYSMS_AUTH_FILE));
            //TODO we dont really need type token
            Type empMapType = new TypeToken<Map<String, MySmsCredential>>() {}.getType();
            Map<String, MySmsCredential> credentialMap = GsonUtils.fromJsonReader(reader, empMapType);
            return credentialMap.getOrDefault(phoneNumber, null);
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
