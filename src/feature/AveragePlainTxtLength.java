package feature;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class AveragePlainTxtLength implements Feature{

	@Override
	public int getFeatureValue(Document document) {
		// TODO Auto-generated method stub
		int averageLength = 0;
		int tagNum = 0;
		Elements allElements = document.getAllElements();
		for(int i=0;i<allElements.size();i++){
			String tmpLength = allElements.get(i).ownText();
			if(tmpLength.contains("MIME-Version:")){
				continue;
			}
			averageLength+=tmpLength.length();
			tagNum++;
		}
		return averageLength/tagNum;
	}

}
