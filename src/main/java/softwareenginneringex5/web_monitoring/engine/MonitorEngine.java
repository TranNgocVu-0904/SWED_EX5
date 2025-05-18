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
        notifications.clear();

        for (User user : users) {
            for (Subscription sub : user.getSubscriptions()) 
            {
                if (!sub.isActive()) continue;

                if (sub.getWebsite().hasChangedSinceLastCheck()) 
                {
                    String message = "Website updated: " + sub.getWebsite().getUrl();
                    
                    Notification notification = new Notification(message);
                    
                    notifications.add(notification);
                    
                    sub.getNotifier().send(user, notification);

                    System.out.println("[CHANGE DETECTED] " + message);
                }
            }
        }
    }
    
    public List<Notification> getNotifications() 
    {
        return notifications;
    }
}
