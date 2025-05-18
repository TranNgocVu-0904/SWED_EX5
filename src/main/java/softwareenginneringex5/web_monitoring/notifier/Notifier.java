/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.notifier;

import softwareenginneringex5.web_monitoring.model.*;

public interface Notifier 
{
    void send(User user, Notification notification);
}


