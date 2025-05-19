package softwareenginneringex5.web_monitoring.engine;

import softwareenginneringex5.web_monitoring.model.*;

import java.util.*;

public class MonitorEngine
{
    private List<Website> websites;

    public MonitorEngine(List<Website> websites) 
    {
        this.websites = websites;
    }

    public void checkWebsites()
    {
        System.out.println("Checking websites for updates...");

        for (Website site : websites) 
        {            
            if (site.hasChangedSinceLastCheck()) // If changed, Website will automatically notifyObservers()
            {
                System.out.println("Website " + site.getUrl() + " has changed!");
                
                site.saveContentToFile("updated_" +  site.getUrl() + ".html");
            } 
            else 
            {
                System.out.println("Website " + site.getUrl() + " has no changes.");
            }
        }
    }   
}
