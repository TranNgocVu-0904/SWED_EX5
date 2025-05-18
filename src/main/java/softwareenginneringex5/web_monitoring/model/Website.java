package softwareenginneringex5.web_monitoring.model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.nio.file.Files;
import java.nio.file.Paths;




public class Website {
    private String url;
    private String lastContent;

    public Website(String url) {
        this.url = url;
        this.lastContent = fetchContentFromUrl(); // tải nội dung ban đầu
    }

    public boolean hasChangedSinceLastCheck() {
        String newContent = fetchContentFromUrl(); // lấy nội dung mới

        if (!newContent.equals(this.lastContent)) 
        {
            System.out.println("Website đã thay đổi!");
            
            System.out.println("Nội dung cũ:");
            
            System.out.println(this.lastContent.substring(0, Math.min(300, this.lastContent.length()))); // chỉ in 300 ký tự đầu
            
            System.out.println("Nội dung mới:");
            
            System.out.println(newContent.substring(0, Math.min(300, newContent.length())));

            this.lastContent = newContent; // cập nhật lại
            return true;
        }

        System.out.println("Không có thay đổi.");
        return false;
    }


    public String fetchContentFromUrl() {
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

        } catch (Exception e) {
            return "Error fetching content: " + e.getMessage();
        }
    }
    
    public void saveContentToFile(String filePath) 
    {
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
