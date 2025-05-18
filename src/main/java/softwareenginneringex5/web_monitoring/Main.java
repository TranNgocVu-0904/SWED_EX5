package softwareenginneringex5.web_monitoring;

import softwareenginneringex5.web_monitoring.model.*;
import softwareenginneringex5.web_monitoring.notifier.*;
import softwareenginneringex5.web_monitoring.engine.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // Initialize a real website to monitor
        Website w1 = new Website("https://google.com");


        // Create an EmailNotifier
        EmailNotifier emailNotifier = new EmailNotifier();
        emailNotifier.configureServer("smtp.example.com", 587);  // dummy SMTP server
        emailNotifier.setFromAddress("noreply@example.com");

        // Create a user
        User user = new User("U001", "Alice", "alice@example.com", "+9876543210");

        // User subscribes to the website with email notification
        user.subscribe(w1, "daily", "email", emailNotifier);

        // Add user to list
        List<User> users = new ArrayList<>();
        users.add(user);
        
        w1.saveContentToFile("google.html");

        // Create engine
        MonitorEngine engine = new MonitorEngine(users);

        // Simulate multiple periodic checks
        for (int i = 1; i <= 3; i++) {
            System.out.println("\n===== Check #" + i + " =====");
            engine.checkWebsites();

            List<Notification> sentNotifications = engine.getNotifications();
            System.out.println("Number of notifications sent: " + sentNotifications.size());

            // In nội dung các thông báo đã gửi
            for (Notification n : sentNotifications) {
                System.out.println("Notification: " + n.getMessage());
            }

            // Sleep for a few seconds before the next check
            try {
                Thread.sleep(5000);  // 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // In ra subscription còn lại
        System.out.println("Remaining subscriptions:");
        
        for (Subscription sub : user.getSubscriptions()) 
        {
            System.out.println("- ID: " + sub.getSubscriptionId() + ", Website: " + sub.getWebsite().getUrl());
        }      
    }
}
