package softwareenginneringex5.web_monitoring.notifier;

import softwareenginneringex5.web_monitoring.model.User;
import softwareenginneringex5.web_monitoring.model.Notification;

public class SMSNotifier implements Notifier 
{
    private String smsGatewayUrl;
    private String apiKey;
    private String senderId;

    public void configureGateway(String gatewayUrl, String apiKey)
    {
        this.smsGatewayUrl = gatewayUrl;
        this.apiKey = apiKey;
        
        System.out.println("Configured SMS Gateway: " + gatewayUrl + " | API Key: " + apiKey);
    }

    public void setSenderId(String senderId)
    {
        this.senderId = senderId;
        
        System.out.println("Set SMS Sender ID: " + senderId);
    }

    @Override
    public void send(User user, Notification notification)
    {
        // Simulate sending SMS messages
        System.out.println("Sending SMS to " + user.getPhoneNumber());
        
        System.out.println("From: " + senderId);
        
        System.out.println("Message: " + notification.getMessage());
    }
}