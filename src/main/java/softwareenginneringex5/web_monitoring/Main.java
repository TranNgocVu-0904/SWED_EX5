/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring;

import softwareenginneringex5.web_monitoring.model.*;
import softwareenginneringex5.web_monitoring.notifier.*;
import softwareenginneringex5.web_monitoring.engine.*;

import java.util.*;

public class Main {
    public static void main(String[] args) 
    {

        //Initialize object Website 
        Website w1 = new Website("https://example.com");

        //Simulate user and notification methods       
        EmailNotifier emailNotifier = new EmailNotifier();

        emailNotifier.configureServer("smtp.example.com", 587);

        emailNotifier.setFromAddress("noreply@example.com");

        User user = new User("U001", "Alice", "alice@example.com", "+9876543210");
        
        user.subscribe(w1, "daily", "email", emailNotifier);

        List<User> users = new ArrayList<>();

        users.add(user);

        MonitorEngine engine = new MonitorEngine(users);

        //Simulate periodic check
        engine.checkWebsites();

        // =============================
        //Simulate website change testing
        System.out.println("\n--- Manual Website Change Simulation ---");
        
        for (int i = 0; i < 5; i++) 
        {
            boolean changed = w1.hasChangedSinceLastCheck();

            System.out.println("Check #" + (i + 1) + " | Changed: " + changed + " | Content: " + w1.getLatestContent());
        }

        // =============================
        //Simulate Subscription deletion
        System.out.println("\n--- Deleting Subscription ---");

        //Assuming that subscriptionId is "SUB1" (or whatever id you want)
        user.deleteSubscription("SUB1");

        // In ra thông tin các subscription còn lại sau khi xóa
        System.out.println("\nRemaining subscriptions: ");

        for (Subscription sub : user.getSubscriptions())
        {
            System.out.println("Subscription ID: " + sub.getSubscriptionId());
        }
    }
}

