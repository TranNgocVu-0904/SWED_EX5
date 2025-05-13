/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.notifier;

import softwareenginneringex5.web_monitoring.model.*;

public class EmailNotifier extends Notifier 
{
    private String smtpServer;
    private int port;
    private String fromAddress;

    public void configureServer(String smtpServer, int port) 
    {
        this.smtpServer = smtpServer;
        this.port = port;
    }

    public void setFromAddress(String fromAddress)
    {
        this.fromAddress = fromAddress;
    }

    @Override
    public void sendNotification(Notification notification, User user) 
    {
        System.out.println("Sending Email to " + user.getEmail() + ": " + notification.getMessage());
    }
}

