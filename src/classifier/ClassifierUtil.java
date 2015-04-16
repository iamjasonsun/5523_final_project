package classifier;

import java.util.List;
import java.util.Map;

public class ClassifierUtil {
    private static final String FEATURE_CLASS_SEPARATOR = ":";
    private static final String FEATURE_SEPARATOR = ",";
    private static final String LINEBREAK = String.format("%n");

    private ClassifierUtil() {
        // prevent instantiation
    }

    public static String prettyPrintFeatureVectors(Map<List<Integer>, WebpageClass> featureVectors) {
        StringBuilder prettyPrintResult = new StringBuilder();
        for (Map.Entry<List<Integer>, WebpageClass> featureEntry : featureVectors.entrySet()) {
            List<Integer> featureVector = featureEntry.getKey();
            WebpageClass webpageClass = featureEntry.getValue();
            String prefix = "";
            for (Integer featureEle : featureVector) {
                prettyPrintResult.append(prefix);
                prettyPrintResult.append(featureEle.toString());
                prefix = FEATURE_SEPARATOR;
            }
            prettyPrintResult.append(FEATURE_CLASS_SEPARATOR);
            prettyPrintResult.append(webpageClass.ordinal());
            prettyPrintResult.append(LINEBREAK);
        }
        return prettyPrintResult.toString();
    }

}
