package softwareenginneringex5.web_monitoring.notifier;

import softwareenginneringex5.web_monitoring.model.*;

public class EmailNotifier implements Notifier 
{
    private String smtpServer;
    private int port;
    private String fromAddress;

    public void configureServer(String smtpServer, int port)
    {
        this.smtpServer = smtpServer;
        this.port = port;
        
        System.out.println("Configured SMTP server: " + smtpServer + ":" + port);
    }

    public void setFromAddress(String fromAddress) 
    {
        this.fromAddress = fromAddress;
        
        System.out.println("Set from address: " + fromAddress);
    }

    @Override
    public void send(User user, Notification notification)
    {
        // Simulate sending email
        System.out.println("Sending EMAIL to " + user.getEmail());
        
        System.out.println("From: " + fromAddress);
        
        System.out.println("Message: " + notification.getMessage());
    }
}
