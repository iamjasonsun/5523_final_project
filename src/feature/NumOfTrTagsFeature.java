package feature;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NumOfTrTagsFeature implements Feature {

    @Override
    public int getFeatureValue(Document document) {
        final String tagName = "tr";
        return document.getElementsByTag(tagName).size();
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("testFile.html"), "UTF-8");
        Feature numOfTrTangs = new NumOfTrTagsFeature();
        System.out.println(numOfTrTangs.getFeatureValue(document));
    }
}
