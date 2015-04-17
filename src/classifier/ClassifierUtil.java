package classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class ClassifierUtil {
    // private static final String FEATURE_CLASS_SEPARATOR = ":";
    private static final String FEATURE_SEPARATOR = ",";

    // private static final String LINEBREAK = String.format("%n");

    private ClassifierUtil() {
        // prevent instantiation
    }

    public static void prettyPrintFeatureVectors(Map<List<Integer>, WebpageClass> featureVectors)
            throws FileNotFoundException {
        PrintWriter pwCourse = new PrintWriter(new File("result_Course"));
        PrintWriter pwFaculty = new PrintWriter(new File("result_Faculty"));
        PrintWriter pwStudent = new PrintWriter(new File("result_Student"));

        for (Map.Entry<List<Integer>, WebpageClass> featureEntry : featureVectors.entrySet()) {
            StringBuilder prettyPrintResult = new StringBuilder();
            List<Integer> featureVector = featureEntry.getKey();
            WebpageClass webpageClass = featureEntry.getValue();
            String prefix = "";
            for (Integer featureEle : featureVector) {
                prettyPrintResult.append(prefix);
                prettyPrintResult.append(featureEle.toString());
                prefix = FEATURE_SEPARATOR;
            }
            String featureVectorForOne = prettyPrintResult.toString();
            if (webpageClass == WebpageClass.COURSE) {
                pwCourse.println(featureVectorForOne);
            } else if (webpageClass == WebpageClass.FACULTY) {
                pwFaculty.println(featureVectorForOne);
            } else {
                pwStudent.println(featureVectorForOne);
            }
        }

        pwCourse.close();
        pwFaculty.close();
        pwStudent.close();
    }

}
