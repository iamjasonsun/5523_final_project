package feature;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NumOfEmailAddress implements Feature {

    @Override
    public int getFeatureValue(Document doc) {
        int count = 0;
        Queue<String> plainText = new LinkedList<>();
        Elements tags = doc.select("*");
        for (Element element : tags) {
            String temp = element.ownText();
            if (!temp.equals("")) {
                plainText.add(temp);
            }
        }
        for (String s : plainText) {
            count = count + countNumberEmailAddress(s);
        }
        return count;
    }

    private static int countNumberEmailAddress(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (temp == '@') {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("testFile.html"), "UTF-8");
        Feature numOfPlainText = new NumOfEmailAddress();
        System.out.println(numOfPlainText.getFeatureValue(document));
    }

}
