package feature;

import org.jsoup.nodes.Document;

public interface Feature {
    int getFeatureValue(Document document);
}
