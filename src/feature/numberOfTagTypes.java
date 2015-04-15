package feature;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class numberOfTagTypes implements Feature {

    @Override
    public int getFeatureValue(Document doc) {
        Map<String, Integer> count = new HashMap<>(); 
    	Elements tags = doc.select("*");
    	for(Element e : tags){
    		if(!count.containsKey(e.tagName())){
    			count.put(e.tagName(),1);
    		}else{
    			count.put(e.tagName(),count.get(e.tagName())+1);
    		}
    	}
    	return count.size();
        
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("testFile.html"), "UTF-8");
        Feature numberOfTagTypes = new numberOfTagTypes();
        System.out.println(numberOfTagTypes.getFeatureValue(document));
    }

}
