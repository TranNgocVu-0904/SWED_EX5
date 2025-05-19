package softwareenginneringex5.web_monitoring;

import softwareenginneringex5.web_monitoring.model.*;
import softwareenginneringex5.web_monitoring.notifier.*;
import softwareenginneringex5.web_monitoring.engine.*;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // 1. Create the website to monitor
        Website w1 = new Website("https://google.com");

        // Save website content to html file
        w1.saveContentToFile("google.html");

        // 2. Create a notifier (here we use EmailNotifier)
        EmailNotifier emailNotifier = new EmailNotifier();
        
        emailNotifier.configureServer("smtp.example.com", 587);
        
        emailNotifier.setFromAddress("noreply@example.com");

        // 3. Create user
        User user = new User("U001", "Alice", "alice@example.com", "+9876543210");

        // 4. User subscribes to website with email notification
        user.subscribe(w1, "daily", "email", emailNotifier);

        // 5. Create a list of websites (according to the MonitorEngine code, you use the Website list)
        List<Website> websites = new ArrayList<>();
         
        websites.add(w1);

        // 6. Tạo MonitorEngine với danh sách website
        MonitorEngine engine = new MonitorEngine(websites);

        for (int i = 1; i <= 3; i++) {
            System.out.println("\n===== Check #" + i + " =====");
            engine.checkWebsites();
            
           // Sleep 5 giây trước khi check tiếp
            try 
            {
                Thread.sleep(5000);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }

        // 7. In ra các subscription hiện còn active của user
        System.out.println("\nRemaining subscriptions: ");
        
        for (Subscription sub : user.getSubscriptions()) 
        {
            System.out.println("- ID: " + sub.getSubscriptionId() + ", Website: " + sub.getWebsite().getUrl());
        }
    }
}
