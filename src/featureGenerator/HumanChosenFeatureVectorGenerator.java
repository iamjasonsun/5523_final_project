package featureGenerator;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;

import feature.Feature;

public class HumanChosenFeatureVectorGenerator implements FeatureVectorGenerator {
    private final List<Feature> _featureList;

    public HumanChosenFeatureVectorGenerator() {
        _featureList = new ArrayList<Feature>();
    }

    public void addFeature(final Feature feature) {
        _featureList.add(feature);
    }

    @Override
    public List<Integer> getFeatureVector(final Document document) {
        final List<Integer> featureVector = new ArrayList<Integer>();
        for (final Feature feature : _featureList) {
            featureVector.add(feature.getFeatureValue(document));
        }
        return featureVector;
    }

}
