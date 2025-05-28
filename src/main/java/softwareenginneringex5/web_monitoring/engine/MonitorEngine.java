package softwareenginneringex5.web_monitoring.engine;

import softwareenginneringex5.web_monitoring.model.Website;

import java.util.List;

public class MonitorEngine
{
    private List<Website> websites;
    int fileIndex = 0;

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
                
                site.saveContentToFile("updated_" +  fileIndex + ".html");
                
                fileIndex++;
                
            } 
            else 
            {
                System.out.println("Website " + site.getUrl() + " has no changes.");
            }
        }
    }   
}
