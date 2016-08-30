package com.klaus.urpscore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

public class ScorePaser {

	private String cookie = "";

	public ScorePaser(String userName, String password) {

		String url = "http://zhjw.dlut.edu.cn/loginAction.do";
		Map<String, String> map = new HashMap<String, String>();
		map.put("zjh", userName);
		map.put("mm", password);

		try {

			send(url, map, "utf-8");

		} catch (Exception e) {

		}

	}

	public List<Map<String, String>> getScoreAfterPater() throws Exception {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		String htmlResult = getScoreHtml();

		Document doc = Jsoup.parse(htmlResult);

		Elements links = doc.select("tr[class=\"odd\"]");
		
		
		for(Element src:links){
			
			List<Node> nodes = src.childNodes();

			Map<String, String> map = new HashMap<String, String>(2);

			for (int i = 0; i < nodes.size(); i++) {

				if (i == 1) {

					Node node = nodes.get(i);

					String str = node.toString();

					map.put("courseid", str.substring(20).replace("</td>", "").trim());


				}

				if (i == 13) {

					Node node = nodes.get(i);

					String str = node.toString();

					map.put("grade",str.substring(20).replace("<p align=\"center\">", "").replace("&nbsp;</p> </td>", "").trim());

				}
				
			}
			
			
			list.add(map);
			
		}

		return list;

	}

	public void getScoreSaveAsHtmlFile(String filePath) throws Exception {

		String result = getScoreHtml();

		File file = new File(filePath);

		if (!file.exists()) {

			file.createNewFile();

		}

		FileWriter fw = new FileWriter(file, true);
		fw.write(result);
		fw.close();

	}

	public String getScoreHtml() throws Exception {

		String result = "";

		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet("http://zhjw.dlut.edu.cn/gradeLnAllAction.do?type=ln&oper=sxinfo&lnsxdm=001");

		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");

		httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");

		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8");

		httpGet.setHeader("Connection", "keep-alive");

		httpGet.setHeader("Cookie", cookie);

		httpGet.setHeader("Host", "zhjw.dlut.edu.cn");

		httpGet.setHeader("Referer", "http://zhjw.dlut.edu.cn/gradeLnAllAction.do?type=ln&oper=sx");

		httpGet.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.154 Safari/537.36 LBBROWSER");

		HttpResponse response = null;

		try {

			response = httpClient.execute(httpGet);

		} catch (ClientProtocolException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		if (response != null) {

			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

				result = EntityUtils.toString(response.getEntity());

				// FileWriter fw = new FileWriter("F:\\bs.html", false);
				// fw.write(result);
				// fw.close();

			}
		}

		httpClient.close();

		return result;

	}

	private void send(String url, Map<String, String> map, String encoding) throws Exception {

		// String body = "";

		CloseableHttpClient client = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost(url);

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (map != null) {
			for (Entry<String, String> entry : map.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}

		httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));

		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.setHeader("Accept-Encoding", "gzip, deflate");
		httpPost.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpPost.setHeader("Cache-Control", "max-age=0");
		httpPost.setHeader("Connection", "keep-alive");
		// httpPost.setHeader("Content-Length", "23");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.setHeader("Host", "zhjw.dlut.edu.cn");
		httpPost.setHeader("Origin", "null");
		httpPost.setHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.154 Safari/537.36 LBBROWSER");

		// 执行请求操作，并拿到结果（同步阻塞）
		CloseableHttpResponse response = client.execute(httpPost);

		Header headers[] = response.getAllHeaders();

		int i = 0;

		while (i < headers.length) {

			if (headers[i].getName().equals("Set-Cookie")) {

				cookie = cookie + headers[i].getValue().replace("path=/", "");

			}

			i++;
		}

		// HttpEntity entity = response.getEntity();
		// if (entity != null) {
		// body = EntityUtils.toString(entity, encoding);
		// }
		// EntityUtils.consume(entity);
		// 释放链接
		response.close();

		// return body;
	}

}
