package softwareenginneringex5.web_monitoring.model;

import softwareenginneringex5.web_monitoring.notifier.*;

import java.util.*;

public class User 
{
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
    private List<Subscription> subscriptions = new ArrayList<>();

    public User(String userId, String name, String email, String phoneNumber) 
    {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void subscribe(Website website, String frequency, String channel, Notifier notifier) 
    {
        Subscription sub = new Subscription(frequency, channel, true, website, this, notifier);
        
        subscriptions.add(sub);

        // Register this subscription as an Observer
        website.registerObserver(sub);
    }

    public void deleteSubscription(String subscriptionId)
    {
        Iterator<Subscription> iterator = subscriptions.iterator();
        
        while (iterator.hasNext())
        {
            Subscription sub = iterator.next();
            
            if (sub.getSubscriptionId().equals(subscriptionId)) 
            {
                // Unregister this subscription from the website
                sub.getWebsite().removeObserver(sub);
                
                iterator.remove();
                
                System.out.println("Subscription with ID: " + subscriptionId + " has been deleted.");
                return;
            }
        }
        System.out.println("Subscription with ID: " + subscriptionId + " not found.");
    }

    public String getEmail()
    {
        return email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public List<Subscription> getSubscriptions()
    {
        return subscriptions;
    }

    // Optional method, not directly needed if Observer Pattern works through Subscription
    public void notifyUser(String message) 
    {
        for (Subscription sub : subscriptions) 
        {
            if (sub.isActive()) 
            {
                Notification notification = new Notification(message);
                
                sub.getNotifier().send(sub.getUser(), notification);
            }
        }
    }
}
