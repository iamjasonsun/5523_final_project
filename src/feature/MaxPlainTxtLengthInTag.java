package feature;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MaxPlainTxtLengthInTag implements Feature {

    @Override
    public int getFeatureValue(Document document) {
        int maxLength = 0;
        Elements allElements = document.getAllElements();
        for (int i = 0; i < allElements.size(); i++) {
            String tmpLength = allElements.get(i).ownText();
            if (tmpLength.contains("MIME-Version:")) {
                continue;
            }
            if (tmpLength.length() > maxLength) {
                maxLength = tmpLength.length();
            }
        }
        return maxLength;
    }
}
