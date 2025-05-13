/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.model;

import softwareenginneringex5.web_monitoring.*;

import java.time.LocalDateTime;

public class Notification 
{
    private String message;
    private LocalDateTime timestamp;

    public Notification(String message) 
    {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage()
    {
        return message;
    }

    public LocalDateTime getTimestamp() 
    {
        return timestamp;
    }
}

