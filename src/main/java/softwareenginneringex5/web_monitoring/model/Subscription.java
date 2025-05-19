package softwareenginneringex5.web_monitoring.model;

import softwareenginneringex5.web_monitoring.notifier.*;
import softwareenginneringex5.web_monitoring.observer.Observers;

public class Subscription implements Observers
{
    private String subscriptionId;
    private String frequency;
    private String channel;
    
    private boolean isActive = true;

    private Website website;
    private User user;
    private Notifier notifier;
        
    private static int idCounter = 0; //Count to generate auto-increment ID
    
    public Subscription(String frequency, String channel, boolean isActive, Website website, User user, Notifier notifier)
    {
        this.subscriptionId = generateSubscriptionId();
        this.frequency = frequency;
        this.channel = channel;
        this.isActive = isActive;
        this.website = website;
        this.user = user;
        this.notifier = notifier;
    }
    
    private String generateSubscriptionId() 
    {
        return "SUB" + (++idCounter);  //Create ID in the form "SUB1", "SUB2", ...
    }

    public String getSubscriptionId() 
    { 
        return subscriptionId; 
    }
    
    public void modify(String frequency, String channel) 
    {
        this.frequency = frequency;
        this.channel = channel;
    }
    
    public boolean isActive() 
    { 
        return isActive; 
    }
    public void cancel() 
    { 
        isActive = false; 
    }
    
    public Website getWebsite() 
    { 
        return website;
    }
    
    public Notifier getNotifier() 
    { 
        return notifier; 
    }
    
    public User getUser() 
    { 
        return user;
    }

    // Method from Observer pattern
    @Override
    public void update(Notification notification)
    {
        if (isActive) 
        {
            notifier.send(user, notification);
        }
    }
}
