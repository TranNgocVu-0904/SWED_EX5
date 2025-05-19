package softwareenginneringex5.web_monitoring.observer;

public interface Subject
{
    void registerObserver(Observers observer);
    
    void removeObserver(Observers observer);
    
    void notifyObservers(String message);
}
