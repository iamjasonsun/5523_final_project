package feature;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class numberOfTags implements Feature {

    @Override
    public int getFeatureValue(Document doc) {
        Elements tags = doc.select("*");
        return tags.size();
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("testFile.html"), "UTF-8");
        Feature numOfTags = new numberOfTags();
        System.out.println(numOfTags.getFeatureValue(document));
    }

}
