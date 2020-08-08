package com.CucumberCraft.OtpGetter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.CucumberCraft.JsonModel.MySmsMessage;

import javassist.NotFoundException;

public class MySmsMessageGetter {
    private static final int MAX_RETRY_COUNT = 90;
    private static final int RETRY_INTERVAL = 2000;
    private long lastMessageTimestamp;
    private final Map<String, Boolean> history;
    private final MySmsRestfulClient mySmsClient = new MySmsRestfulClient();


    public MySmsMessageGetter() {        

        this.lastMessageTimestamp = System.currentTimeMillis();
        this.history = new HashMap<>();
    }

    public String getMessage(String sender, String receiver) {
        String result = "<no-message>";
        try {
            MySmsMessage message = getSmsMessage(sender, receiver);
            result = message.getMessage();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        } finally {
            lastMessageTimestamp = System.currentTimeMillis();
            rememberMessage(sender, receiver, result);
        }
    }

    private MySmsMessage getSmsMessage(String sender, String receiver) throws Exception {
        List<MySmsMessage> messages = mySmsClient.getMessages(sender, receiver, 0, 1);
        int tryCount = 0;
        while (tryCount < MAX_RETRY_COUNT) {
            if (validResult(sender, receiver, messages)) {
                return messages.get(0);
            }
            Thread.sleep(RETRY_INTERVAL);
            messages = mySmsClient.getMessages(sender, receiver, 0, 1);
            tryCount++;
        }
        throw new NotFoundException("No valid message found");
    }

    private boolean validResult(String sender, String receiver, List<MySmsMessage> result) {
        if (result == null || result.size() == 0) {
            return false;
        }

        MySmsMessage message = result.get(0);
        return message.getDateSent() >= lastMessageTimestamp
                && !receivedBefore(sender, receiver, message.getMessage());
    }

    private boolean receivedBefore(String sender, String receiver, String message) {
        return history.containsKey(composeMapKey(sender, receiver, message));
    }

    private void rememberMessage(String sender, String receiver, String message) {
        history.put(composeMapKey(sender, receiver, message), true);
    }

    private String composeMapKey(String sender, String receiver, String message) {
        return String.format("%s - %s - %s", sender, receiver, message);
    }
}
