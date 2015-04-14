package trainingMethod;

import java.util.List;
import java.util.Map;

import classifier.TestResult;
import classifier.WebpageClass;

public interface TrainingMethod {
    /**
     * Given training data, perform training.
     * 
     * @param trainingFeatureMatrixAndClass
     *            training data and its class
     */
    void train(Map<List<Integer>, WebpageClass> trainingFeatureMatrixAndClass);

    /**
     * Given testing data, perform testing.
     * 
     * @param testingFeatureMatrixAndClass
     *            testing data and its class
     * @return TestResult object
     */
    TestResult test(Map<List<Integer>, WebpageClass> testingFeatureMatrixAndClass);
}
