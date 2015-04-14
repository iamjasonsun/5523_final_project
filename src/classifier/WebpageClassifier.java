package classifier;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import trainingMethod.TrainingMethod;
import featureGenerator.FeatureVectorGenerator;

public class WebpageClassifier {
    private final FeatureVectorGenerator _featureVectorGenerator;
    private final TrainingMethod _trainingMethod;

    public WebpageClassifier(final FeatureVectorGenerator featureVectorGenerator, final TrainingMethod trainingMethod) {
        _featureVectorGenerator = featureVectorGenerator;
        _trainingMethod = trainingMethod;
    }

    public void execute(final String parentFolderPath) throws IOException {
        // get all files and the class it belongs to
        final Map<File, WebpageClass> courseFileData = getFileToWebpageClassMap(parentFolderPath, "course",
                WebpageClass.COURSE);
        final Map<File, WebpageClass> facultyFileData = getFileToWebpageClassMap(parentFolderPath, "faculty",
                WebpageClass.FACULTY);
        final Map<File, WebpageClass> studentFileData = getFileToWebpageClassMap(parentFolderPath, "student",
                WebpageClass.STUDENT);
        final Map<File, WebpageClass> fileDataToWebpageClassMap = new HashMap<File, WebpageClass>();
        fileDataToWebpageClassMap.putAll(courseFileData);
        fileDataToWebpageClassMap.putAll(facultyFileData);
        fileDataToWebpageClassMap.putAll(studentFileData);
        // get training data - get feature vector of each file and the class it
        // belongs to
        final Map<List<Integer>, WebpageClass> trainingData = new HashMap<List<Integer>, WebpageClass>();
        for (Map.Entry<File, WebpageClass> fileEntry : fileDataToWebpageClassMap.entrySet()) {
            Document document = Jsoup.parse(fileEntry.getKey(), "UTF-8");
            List<Integer> featureVector = _featureVectorGenerator.getFeatureVector(document);
            trainingData.put(featureVector, fileEntry.getValue());
        }
        // perform training
        _trainingMethod.train(trainingData);

        // TODO: get all testing files and their class

        final Map<List<Integer>, WebpageClass> testingData = new HashMap<List<Integer>, WebpageClass>();
        TestResult testResult = _trainingMethod.test(testingData);
        System.out.println(testResult.getAccuracy());
    }

    private Map<File, WebpageClass> getFileToWebpageClassMap(final String parentFolderPath, final String classFolder,
            final WebpageClass webpageClass) {
        final Map<File, WebpageClass> fileToWebpageClassMap = new HashMap<File, WebpageClass>();
        final File folder = new File(parentFolderPath + "/" + classFolder);
        for (final File fileEntry : folder.listFiles()) {
            if (!fileEntry.isDirectory()) {
                fileToWebpageClassMap.put(fileEntry, webpageClass);
            }
        }
        return fileToWebpageClassMap;
    }

    public static void main(String[] args) throws IOException {
        final String dataParentFolder = "";
        WebpageClassifierFactory webpageClassifierFactory = new WebpageClassifierFactory();
        WebpageClassifier webpageClassifierWithHumanChosenFeatureAndSVMTrainingMethod = webpageClassifierFactory
                .createClassifierWithHumanChosenFeatureAndSVMTrainingMethod();
        WebpageClassifier webpageClassifierWithKeywordsFeatureAndSVMTrainingMethod = webpageClassifierFactory
                .createClassifierWithKeywordsFeatureAndSVMTrainingMethod();
        webpageClassifierWithHumanChosenFeatureAndSVMTrainingMethod.execute(dataParentFolder);
        webpageClassifierWithKeywordsFeatureAndSVMTrainingMethod.equals(dataParentFolder);
    }
}
