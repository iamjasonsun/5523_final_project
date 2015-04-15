package classifier;

import trainingMethod.SVMTrainingMethod;
import feature.NumOfImgTagsFeature;
import feature.NumOfTrTagsFeature;
import featureGenerator.HumanChosenFeatureVectorGenerator;
import featureGenerator.KeywordsFeatureVectorGenerator;

public class WebpageClassifierFactory {

    public WebpageClassifier createClassifierWithHumanChosenFeatureAndSVMTrainingMethod() {
        return new WebpageClassifier(getHumanChosenFeatureVectorGenerator(), getSVMTrainingMethod());
    }

    public WebpageClassifier createClassifierWithKeywordsFeatureAndSVMTrainingMethod() {
        return new WebpageClassifier(getKeywordsFeatureVectorGenerator(), getSVMTrainingMethod());
    }

    private HumanChosenFeatureVectorGenerator getHumanChosenFeatureVectorGenerator() {
        final HumanChosenFeatureVectorGenerator humanChosenFeature = new HumanChosenFeatureVectorGenerator();
        humanChosenFeature.addFeature(new NumOfImgTagsFeature());
        humanChosenFeature.addFeature(new NumOfTrTagsFeature());
        // TODO: add more features
        return humanChosenFeature;
    }

    private KeywordsFeatureVectorGenerator getKeywordsFeatureVectorGenerator() {
        return new KeywordsFeatureVectorGenerator();
    }

    private SVMTrainingMethod getSVMTrainingMethod() {
        return new SVMTrainingMethod();
    }
}
