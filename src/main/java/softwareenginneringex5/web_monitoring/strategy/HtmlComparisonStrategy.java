/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.strategy;

/**
 *
 * @author tranngocvu942004
 */
public class HtmlComparisonStrategy implements ComparisonStrategy {
    @Override
    public boolean isEqual(String oldContent, String newContent) 
    {
        return oldContent.equals(newContent);
    }
    
    @Override
    public void printComparisonDetails(String oldContent, String newContent) 
    {
        System.out.println("[HTML Strategy] Full HTML content changed:");
        
        System.out.println("Old content: ");
        
        System.out.println(oldContent);
        
        System.out.println("New content: ");
        
        System.out.println(newContent);
    }

}

