package softwareenginneringex5.web_monitoring.model;

import softwareenginneringex5.web_monitoring.observer.*;

import java.util.*;

import java.net.URI;
import java.net.http.*;

import java.nio.file.*;

public class Website implements Subject 
{
    private String url;
    private String lastContent;
    private List<Observers> observers = new ArrayList<>();

    public Website(String url) 
    {
        this.url = url;
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

        if (!newContent.equals(this.lastContent)) {
            System.out.println("Website has changed!");

            System.out.println("Old content:");
            System.out.println(this.lastContent.substring(0, Math.min(300, this.lastContent.length())));

            System.out.println("New content:");
            System.out.println(newContent.substring(0, Math.min(300, newContent.length())));

            this.lastContent = newContent;

            notifyObservers("Website " + url + " has been updated.");

            return true;
        }

        System.out.println("Nothing change!!!");
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
