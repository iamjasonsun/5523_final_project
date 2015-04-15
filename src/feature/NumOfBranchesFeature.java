package feature;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NumOfBranchesFeature implements Feature {

    @Override
    public int getFeatureValue(Document document) {
        int max = 0;
        Elements elements = document.select("*");
        for (Element ele : elements) {
            int size = ele.children().size();
            if (size > max) {
                max = size;
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("testFile.html"), "UTF-8");
        Feature numOfBranchesFeature = new NumOfBranchesFeature();
        System.out.println(numOfBranchesFeature.getFeatureValue(document));
    }

}
