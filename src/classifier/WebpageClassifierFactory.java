package classifier;

import trainingMethod.SVMTrainingMethod;
import feature.AveragePlainTxtLength;
import feature.HeightOfTreeFeature;
import feature.MaxPlainTxtLengthInTag;
import feature.NumInHeadTitle;
import feature.NumOfBranchesFeature;
import feature.NumOfEmTagsFeature;
import feature.NumOfEmailAddress;
import feature.NumOfHyperLink;
import feature.NumOfImgTagsFeature;
import feature.NumOfPlainText;
import feature.NumOfTrTagsFeature;
import feature.SizeOfWebpageFeature;
import feature.numOfDigits;
import feature.numberOfTagTypes;
import feature.numberOfTags;
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
        humanChosenFeature.addFeature(new AveragePlainTxtLength());
        humanChosenFeature.addFeature(new HeightOfTreeFeature());
        humanChosenFeature.addFeature(new MaxPlainTxtLengthInTag());
        humanChosenFeature.addFeature(new numberOfTags());
        humanChosenFeature.addFeature(new numberOfTagTypes());
        humanChosenFeature.addFeature(new NumInHeadTitle());
        humanChosenFeature.addFeature(new NumOfBranchesFeature());
        humanChosenFeature.addFeature(new numOfDigits());
        humanChosenFeature.addFeature(new NumOfEmailAddress());
        humanChosenFeature.addFeature(new NumOfEmTagsFeature());
        humanChosenFeature.addFeature(new NumOfHyperLink());
        humanChosenFeature.addFeature(new NumOfImgTagsFeature());
        humanChosenFeature.addFeature(new NumOfPlainText());
        humanChosenFeature.addFeature(new NumOfTrTagsFeature());
        humanChosenFeature.addFeature(new SizeOfWebpageFeature());
        return humanChosenFeature;
    }

    private KeywordsFeatureVectorGenerator getKeywordsFeatureVectorGenerator() {
        return new KeywordsFeatureVectorGenerator();
    }

    private SVMTrainingMethod getSVMTrainingMethod() {
        return new SVMTrainingMethod();
    }
}
