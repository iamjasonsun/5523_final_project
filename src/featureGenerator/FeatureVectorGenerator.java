package featureGenerator;

import java.util.List;

import org.jsoup.nodes.Document;

public interface FeatureVectorGenerator {
    List<Integer> getFeatureVector(Document document);
}
