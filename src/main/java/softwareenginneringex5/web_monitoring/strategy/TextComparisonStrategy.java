/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.strategy;

/**
 *
 * @author tranngocvu942004
 */
import org.jsoup.Jsoup;

public class TextComparisonStrategy implements ComparisonStrategy {
    @Override
    public boolean isEqual(String oldContent, String newContent) 
    {
        return Jsoup.parse(oldContent).text().equals(Jsoup.parse(newContent).text());
    }
    
    @Override
    public void printComparisonDetails(String oldContent, String newContent) 
    {
        System.out.println("[Text Strategy] Visible text content: ");
        
        System.out.println("Old text: ");
        
        System.out.println(org.jsoup.Jsoup.parse(oldContent).text());
        
        System.out.println("New text: ");
        
        System.out.println(org.jsoup.Jsoup.parse(newContent).text());
    }

}

