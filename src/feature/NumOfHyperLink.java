package feature;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NumOfHyperLink implements Feature {

    @Override
    public int getFeatureValue(Document doc) {
    	Elements links = doc.select("a[href]");
    	
        return links.size();
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("testFile.html"), "UTF-8");
        Feature numOfHyperLink = new NumOfHyperLink();
        System.out.println(numOfHyperLink.getFeatureValue(document));
    }

}
