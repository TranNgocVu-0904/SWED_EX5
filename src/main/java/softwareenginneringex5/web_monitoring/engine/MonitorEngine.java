/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.engine;

import softwareenginneringex5.web_monitoring.model.*;

import java.util.*;

public class MonitorEngine 
{
    private List<Notification> notifications = new ArrayList<>();
    private List<User> users;

    public MonitorEngine(List<User> users)
    {
        this.users = users;
    }

    public void checkWebsites() 
    {
        System.out.println("Checking websites for updates...");

        //Loop through all users
        for (User user : users)
        {
            //Loop through all user subscriptions
            for (Subscription sub : user.getSubscriptions()) 
            {
                //Check if subscription is active and website has changed
                if (sub.isActive() && sub.getWebsite().hasChangedSinceLastCheck()) 
                {
                    String message = "Website updated: " + sub.getWebsite().getUrl();

                    Notification notification = new Notification(message);

                    notifications.add(notification);

                    //Send notification via subscription notifier
                    sub.getNotifier().sendNotification(notification, user);
                }
            }
        }
    }

    public List<Notification> getNotifications() 
    {
        return notifications;
    }
}
