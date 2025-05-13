/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.notifier;

import softwareenginneringex5.web_monitoring.model.*;

public class SMSNotifier extends Notifier 
{
    private String apiKey;
    private String senderNumber;

    public void setSender(String number)
    {
        this.senderNumber = number;
    }

    @Override
    public void sendNotification(Notification notification, User user) 
    {
        System.out.println("Sending SMS to " + user.getPhoneNumber() + ": " + notification.getMessage());
    }
}

