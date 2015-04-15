package feature;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SizeOfWebpageFeature implements Feature {

    @Override
    public int getFeatureValue(Document document) {
        return document.toString().length();
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("testFile.html"), "UTF-8");
        Feature sizeOfWebpage = new SizeOfWebpageFeature();
        System.out.println(sizeOfWebpage.getFeatureValue(document));
    }

}
