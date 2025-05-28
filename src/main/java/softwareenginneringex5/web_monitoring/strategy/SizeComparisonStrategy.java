/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.strategy;

/**
 *
 * @author tranngocvu942004
 */
public class SizeComparisonStrategy implements ComparisonStrategy {
    
    @Override
    public boolean isEqual(String oldContent, String newContent) 
    {
        return oldContent.length() == newContent.length();
    }
    
    @Override
    public void printComparisonDetails(String oldContent, String newContent) 
    {
        System.out.println("[Size Strategy] Size changed:");
        
        System.out.println("Old size: " + oldContent.length() + " characters");
        
        System.out.println("New size: " + newContent.length() + " characters");
    }

}
