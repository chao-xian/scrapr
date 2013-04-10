package controllers;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;

import play.mvc.*;

import models.*;

public class Application extends Controller {

    public static void index(String url) {
        render();
        
    }
    
    public static void instacolour(String url) {
        
        ArrayList<Colours> colours = new ArrayList<Colours>();
        
        try {
            Document instaPage = Jsoup.connect(url).get();
            Element instaImg = instaPage.select("img").get(0);
            
            String instaImgSrc = instaImg.attr("src");
            
            Connection connection = HttpConnection.connect("http://colorsuckr.com/?img="+instaImgSrc);
                                    
            Document instaColoursPage = connection.userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/534.21 (KHTML, like Gecko) Chrome/11.0.682.0 Safari/534.21").get();

            Elements instaColours = instaColoursPage.select("p.colours");
            
            
            for (Element colour : instaColours) {
                //System.out.println(colour);
                String colourString = colour.ownText();
                System.out.println(colourString);
                
                String hexString = colourString.substring(6, 12);
                String rgbString = colourString.substring(13, colourString.length());                
                
                Colours colrs = new Colours(hexString, rgbString);
                
                //System.out.println(colrs.hex + " " + colrs.rgb);
                
                colours.add(colrs);
                
            }
            
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
                
        renderJSON(colours);
        
    }
    
    public static void bathtimetable(String course) {
        String url = "http://timetables.bath.ac.uk:3090/reporting/individual?identifier=CM10227-s1&weeks=1-15&submit2=View+Timetable&idtype=name&objectclass=modules&periods=1-11&days=1-5&width=100&height=0";
        try {
            Document timeTablePage = Jsoup.connect(url).get();
            
            Element table = timeTablePage.select("table").get(0);
            
            System.out.println(table);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        
        render();
    }

}