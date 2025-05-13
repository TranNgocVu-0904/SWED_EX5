/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.model;

import softwareenginneringex5.web_monitoring.*;

import java.util.Random;

public class Website {
    private String url;
    private String lastContent;
    private Random random = new Random(); //Using Random for creating random version of the website

    public Website(String url) 
    {
        this.url = url;
        this.lastContent = getLatestContent(); //Initializing beginning content
    }

    public String getLatestContent() 
    {
        int version = random.nextInt(3); //Create random number from 0 to 2
        return "Mock content version " + version;
    }

    public boolean hasChangedSinceLastCheck()
    {
        String currentContent = getLatestContent();
        
        boolean changed = !currentContent.equals(lastContent);

        if (changed) 
        {
            lastContent = currentContent; //Update the content
        }
        return changed;
    }

    public String getUrl() 
    {
        return url;
    }
}

