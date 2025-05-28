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
}

