package com.kaus.ssdutinfo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class SsdutEmpDetails {
	
	private String filePath="C:/file/";
	
	
	public static void main(String[] args) throws Exception {
		
		String url="http://ssdut.dlut.edu.cn/info/1042/7799.htm";
		int id=7799;
		
		String title="果加智能2017校园招聘";
		
		SsdutEmpDetails  s=new SsdutEmpDetails ();
		
		String content=s.getContent(url);
		
		s.writeFile(id, content, title);
		
	}
	
	public void buildHtml(String url,int id,String title) throws Exception{
		
		String content=getContent(url);
		
		writeFile(id,content,title) ;
		
	}

	private void writeFile(int id,String content,String title) {
		
		try{
			
			StringBuffer sb= new StringBuffer("");
			
			String encoding = "utf-8";
			
			File file = new File(filePath+"demo.html");
			
			if(file.isFile() && file.exists()){ //判断文件是否存在
				
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
				
				BufferedReader bufferedReader = new BufferedReader(read);
				
				String lineTxt = null;
				
				while((lineTxt = bufferedReader.readLine()) != null){
					
					if("<h1>damytitle</h1>".equals(lineTxt)){
						
						
						String str=lineTxt.replace("<h1>damytitle</h1>", title);
						sb.append(str);

						
					}
					
					else if("damycontent".equals(lineTxt)){
						
						
						String str=lineTxt.replace( "damycontent",content);
						sb.append(str);

					}
					else{
						sb.append(lineTxt);
					}
					
					
				}
				
				read.close();
				
				
				FileWriter writer = new FileWriter(filePath+id+".html");
	            BufferedWriter bw = new BufferedWriter(writer);
	            bw.write(sb.toString());	           
	            bw.close();
	            writer.close();
				
			}
			
		}catch(Exception e){

			e.printStackTrace();
		}

	}

	private String getContent(String url) throws Exception {

		//System.out.println(url);
		
		Document doc = Jsoup.connect(url).get();

		Elements body = doc.select("form[name=\"_newscontent_fromname\"]");

		String str = body.get(0).html();

		int s = str.indexOf("hr");
		int e = str.indexOf("上一条");
		
		if(e==-1){
			
			e=str.indexOf("下一条");
			
		}
			

		str = str.substring(s, e);

		Document doca = Jsoup.parse(str);

		Elements scripts = doca.select("div");

		return scripts.get(0).html();

	}

}
