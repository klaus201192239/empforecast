package com.kaus.ssdutinfo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SsdutEmpSingle {

	private int classId;

	private int index;

	private String title;

	private String time;

	private String url;

	private int nextIndex;

	public String getTitle() {
		return title;
	}

	public String getTime() {
		return time;
	}

	public String getUrl() {
		return url;
	}

	public int getNextIndex() {
		return nextIndex;
	}

	public SsdutEmpSingle(int classId, int index) {

		this.classId = classId;

		this.index = index;

		this.url = "http://ssdut.dlut.edu.cn/info/" + classId + "/" + index + ".htm";

	}

	public int getClassId() {
		return classId;
	}


	public int getIndex() {
		return index;
	}


	public void excutor() throws Exception {

		Document doc = Jsoup.connect(this.url).get();

		Elements titles = doc.select("h1[align=\"center\"]");

		Elements times = doc.select("div[align=\"center\"]");

		Elements links = doc.select("p[align=\"right\"]");

		this.title = titles.get(0).html();
		
		//System.out.println(times.get(0).html());

		int x = times.get(0).html().indexOf(";点击");

		if (x != -1) {

			// System.out.println(times.get(0).html().substring(0,x-1));

			this.time = times.get(0).html().substring(0, x - 1);

		}

		for (int i = 0; i < links.size(); i++) {

			String str = links.get(i).html();
			
			//System.out.println(str);

			if (str.contains("下一条")) {

				int y = str.indexOf("下一条");

				// System.out.println(str);

				String str1 = str.substring(y);

				int z = str1.indexOf("htm");

				// System.out.println(str1.substring(13, z-1));

				this.nextIndex = Integer.parseInt(str1.substring(13, z - 1));

			}

		}
	}
	
}
