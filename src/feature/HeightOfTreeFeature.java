package feature;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

public class HeightOfTreeFeature implements Feature {

    @Override
    public int getFeatureValue(Document document) {
        return findHeight(document);
    }

    private int findHeight(Node node) {
        List<Node> children = node.childNodes();
        int max = 0;
        for (Node nodeEle : children) {
            int height = findHeight(nodeEle);
            if (height > max) {
                max = height;
            }
        }
        max++;
        return max;
    }

    public static void main(String[] args) throws IOException {
        Document document = Jsoup.parse(new File("testFile.html"), "UTF-8");
        Feature heightOfTreeFeature = new HeightOfTreeFeature();
        System.out.println(heightOfTreeFeature.getFeatureValue(document));
    }

}
