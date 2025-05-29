package softwareenginneringex5.web_monitoring;

import softwareenginneringex5.web_monitoring.model.Subscription;
import softwareenginneringex5.web_monitoring.model.User;
import softwareenginneringex5.web_monitoring.model.Website;

import softwareenginneringex5.web_monitoring.notifier.EmailNotifier;
import softwareenginneringex5.web_monitoring.notifier.SMSNotifier;

import softwareenginneringex5.web_monitoring.engine.MonitorEngine;

import softwareenginneringex5.web_monitoring.strategy.ComparisonStrategy;
import softwareenginneringex5.web_monitoring.strategy.ComparisonStrategyFactory;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        // 1. Create the website to monitor
        ComparisonStrategy textStrategy =  ComparisonStrategyFactory.getStrategy("size");
        Website w1 = new Website("https://youtube.com", textStrategy);
        
        ComparisonStrategy htmlStrategy =  ComparisonStrategyFactory.getStrategy("size");
        Website w2 = new Website("https://google.com", htmlStrategy);
        
        // Save website content to html file
        w1.saveContentToFile("google.html");
        
        w2.saveContentToFile("youtube.html");
//                
//        w3.saveContentToFile("spotify.html");

        // 2. Create a notifier (here we use EmailNotifier)
        EmailNotifier emailNotifier1 = new EmailNotifier();

        EmailNotifier emailNotifier2 = new EmailNotifier();      
        
        emailNotifier1.configureServer("smtp.example.com", 904);
        
        emailNotifier1.setFromAddress("noreply@example.com");
        
        emailNotifier2.configureServer("smtp.example.com", 604);
        
        emailNotifier2.setFromAddress("noreply@example.com");

        
        // 3. Create user
        User user1 = new User("U001", "Alice", "alice@example.com", "+9876543210");
        
        User user2 = new User("U002", "Vu", "vu@example.com", "+0123456789");
        
        // 4. User subscribes to website with email notification
        user1.subscribe(w1, "daily", "email", emailNotifier1);
        
        user2.subscribe(w2, "daily", "email", emailNotifier2);  

        // 5. Create a list of websites (according to the MonitorEngine code, you use the Website list)
        List<Website> websites = new ArrayList<>();
         
        websites.add(w1);
        websites.add(w2);

        // 6. Create MonitorEngine with list of websites
        MonitorEngine engine = new MonitorEngine(websites);

        for (int i = 1; i <= 3; i++) {
            System.out.println("\n===== Check #" + i + " =====");
            engine.checkWebsites();
            
           // Sleep 5 seconds before the next checking
            try 
            {
                Thread.sleep(5000);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }

        // 7. Print out the user's currently active subscriptions
        System.out.println("\nRemaining subscriptions: ");
        
        for (Subscription sub : user1.getSubscriptions()) 
        {
            System.out.println("- ID: " + sub.getSubscriptionId() + ", Website: " + sub.getWebsite().getUrl());
        }
        
        for (Subscription sub : user2.getSubscriptions()) 
        {
            System.out.println("- ID: " + sub.getSubscriptionId() + ", Website: " + sub.getWebsite().getUrl());
        }
        
    }
}
