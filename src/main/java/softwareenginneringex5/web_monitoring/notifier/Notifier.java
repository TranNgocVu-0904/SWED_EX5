package softwareenginneringex5.web_monitoring.notifier;

import softwareenginneringex5.web_monitoring.model.*;

public interface Notifier 
{
    void send(User user, Notification notification);
}


