package featureGenerator;

import java.io.File;
import java.util.List;
import java.util.Map;

import classifier.WebpageClass;

public interface FeatureVectorGenerator {
    Map<List<Integer>, WebpageClass> getFeatureVectors(Map<File, WebpageClass> trainingFiles) throws Exception;
}
