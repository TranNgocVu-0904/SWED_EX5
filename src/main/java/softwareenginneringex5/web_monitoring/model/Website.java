package softwareenginneringex5.web_monitoring.model;

import java.util.List;
import java.util.ArrayList;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.file.Files;
import java.nio.file.Paths;

import softwareenginneringex5.web_monitoring.observer.Observers;
import softwareenginneringex5.web_monitoring.observer.Subject;

import softwareenginneringex5.web_monitoring.strategy.ComparisonStrategy;
import softwareenginneringex5.web_monitoring.strategy.HtmlComparisonStrategy;
import softwareenginneringex5.web_monitoring.strategy.SizeComparisonStrategy;
import softwareenginneringex5.web_monitoring.strategy.TextComparisonStrategy;

public class Website implements Subject 
{
    private String url;
    private String lastContent;
    private List<Observers> observers = new ArrayList<>();
    private ComparisonStrategy comparisonStrategy;

    public Website(String url, ComparisonStrategy strategy) 
    {
        this.url = url;
        this.comparisonStrategy = strategy;
        this.lastContent = fetchContentFromUrl();
    }


    @Override
    public void registerObserver(Observers observer)
    {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observers observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) 
    {
        for (Observers obj : observers) {
            obj.update(new Notification(message));
        }
    }
    
    
    public boolean hasChangedSinceLastCheck() 
    {
        String newContent = fetchContentFromUrl();

        boolean changed = !comparisonStrategy.isEqual(this.lastContent, newContent);

        // Always show comparison details via strategy
        comparisonStrategy.printComparisonDetails(this.lastContent, newContent);

        if (changed) 
        {
            System.out.println("Website has changed!");

            this.lastContent = newContent;
            notifyObservers("Website " + url + " has been updated.");
            return true;
        }

        System.out.println("Nothing changed!!!");
        return false;
    }


    public String fetchContentFromUrl() 
    {
        try {
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.body();

        } 
        catch (Exception e)
        {
            return "Error fetching content: " + e.getMessage();
        }
    }

    public void saveContentToFile(String filePath) {
        try 
        {
            String content = fetchContentFromUrl();
            
            Files.write(Paths.get(filePath), content.getBytes());
            
            System.out.println("Website content saved to " + filePath);
        } 
        catch (Exception e) 
        {
            System.out.println("Failed to save content: " + e.getMessage());
        }
    }

    public String getUrl()
    {
        return url;
    }

    public String getLastContent() 
    {
        return lastContent;
    }

    public void setLastContent(String lastContent) 
    {
        this.lastContent = lastContent;
    }
}
