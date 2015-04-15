package feature;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class NumOfImgTags implements Feature {

    @Override
    public int getFeatureValue(Document document) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("testFile.html"), "UTF-8");
        Feature numOfImgTangs = new NumOfImgTags();
        System.out.println(numOfImgTangs.getFeatureValue(document));
    }

}
