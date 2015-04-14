package classifier;

public class TestResult {
    private int _totalNumOfData;
    private int _correctNumOfData;

    public void setTotalNumOfData(final int totalNumOfData) {
        _totalNumOfData = totalNumOfData;
    }

    public void setCorrectNumOfData(final int correctNumOfData) {
        _correctNumOfData = correctNumOfData;
    }

    public int getTotalNumOfData() {
        return _totalNumOfData;
    }

    public int getCorrectNumOfData() {
        return _correctNumOfData;
    }

    public double getAccuracy() {
        return (double) _correctNumOfData / (double) _totalNumOfData;
    }
}
