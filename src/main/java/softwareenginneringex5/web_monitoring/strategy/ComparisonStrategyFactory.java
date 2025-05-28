/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package softwareenginneringex5.web_monitoring.strategy;

/**
 *
 * @author tranngocvu942004
 */
public class ComparisonStrategyFactory {
    public static ComparisonStrategy getStrategy(String type)
    {
        switch (type.toLowerCase()) 
        {
            case "text":
                
                return new TextComparisonStrategy();
                
            case "html":
                
                return new HtmlComparisonStrategy();
                
            case "size":
                
                return new SizeComparisonStrategy();
                
            default:
                
                throw new IllegalArgumentException("Unknown strategy: " + type);
        }
    }
}

