package featureGenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import classifier.WebpageClass;
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
    public Map<List<Integer>, WebpageClass> getFeatureVectors(Map<File, WebpageClass> trainingFiles) throws IOException {
        final Map<List<Integer>, WebpageClass> featureVectors = new HashMap<List<Integer>, WebpageClass>();
        for (Map.Entry<File, WebpageClass> fileEntry : trainingFiles.entrySet()) {
            Document document = Jsoup.parse(fileEntry.getKey(), "UTF-8");
            List<Integer> featureVector = getFeatureVector(document);
            featureVectors.put(featureVector, fileEntry.getValue());
        }
        return featureVectors;
    }

    private List<Integer> getFeatureVector(final Document document) {
        final List<Integer> featureVector = new ArrayList<Integer>();
        for (final Feature feature : _featureList) {
            featureVector.add(feature.getFeatureValue(document));
        }
        return featureVector;
    }

}
