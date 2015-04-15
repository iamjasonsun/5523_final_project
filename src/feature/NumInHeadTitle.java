package feature;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NumInHeadTitle implements Feature{

	@Override
	public int getFeatureValue(Document document) {
		// TODO Auto-generated method stub
		Elements titleElements = document.getElementsByTag("title");
		int number = 0;
		for(int i=0;i<titleElements.size();i++){
			String title = titleElements.get(i).html();
			System.out.println(title);
			for(int j = 0;j<title.length();j++){
				if(title.charAt(j)>='0'&&title.charAt(j)<='9'){
					number++;
				}
			}
		}
		return number;
	}

}
