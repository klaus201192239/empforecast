package com.kaus.ssdutinfo;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SsdutEmpList {

	private int classId;

	private int maxIndex;

	public SsdutEmpList(int classId) {

		this.classId = classId;

		this.maxIndex=0;
		
	}

	public int getClassId() {
		return classId;
	}

	public int getMaxIndex() {
		return maxIndex;
	}

	public void excutor() throws IOException {

		String url = null;

		switch (this.classId) {
		
		case 1040:

			url="http://ssdut.dlut.edu.cn/bkspy/bksgl/zsjy/sxjy/jyzd.htm";
			
			break;

		case 1041:
			url="http://ssdut.dlut.edu.cn/bkspy/bksgl/zsjy/sxjy/sxxx.htm";
			break;

		case 1042:

			url="http://ssdut.dlut.edu.cn/bkspy/bksgl/zsjy/sxjy/jyxx.htm";
			
			break;
		}
		
		if(null==url){
			
			return ;
		}
		
		Document doc = Jsoup.connect(url).get();

		Elements indexes = doc.select("span[class=\"fl\"]");

		for(int i=0;i<indexes.size();i++){
			
			//System.out.println(indexes.get(i).html());
			
			//System.out.println(indexes.get(i).select("a").get(0).attr("href").substring(22).replace(".htm", "").trim());
			
			
			int x=Integer.parseInt(indexes.get(i).select("a").get(0).attr("href").substring(22).replace(".htm", "").trim());
			
			
			if(x>this.maxIndex){
				
				this.maxIndex=x;
				
			}
			
		}
		
	}

}
