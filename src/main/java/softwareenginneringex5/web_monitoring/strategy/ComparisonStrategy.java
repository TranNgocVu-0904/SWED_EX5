/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.strategy;

/**
 *
 * @author tranngocvu942004
 */
public interface ComparisonStrategy 
{
    boolean isEqual(String oldContent, String newContent);
    
    void printComparisonDetails(String oldContent, String newContent);
}

