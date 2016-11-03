<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.util.*,java.io.BufferedReader,java.io.InputStream,java.io.InputStreamReader,
	java.net.HttpURLConnection,java.net.URL,org.json.JSONObject,org.json.JSONArray"
	pageEncoding="utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<!doctype html>
<html>
<head>
<base href="<%=basePath%>">
<meta name="viewport"
	content="width = device-width,initial-scale=1.0,maximum-scale=1.0">
<link rel="shortcut icon" href="未标题-1.ico">
<script src="modernizr-1.7.min.js"></script>
<style>
body, #main ul, #main li, h1 {
	margin: 0;
	padding: 0;
}

body {
	
}

#container {
	font-family: Arial;
	margin: 0 10px;
}

header, footer {
	display: block;
}

#main li {
	list-style: none;
	height: 65px;
	margin-bottom: 0.5em;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	border-radius: 15px;
}

.table1 {
	height: 70px;
	width: 320px;
	border-bottom: 1px solid #fb966e;
}

#main li a {
	text-decoration: none;
}

header {
	color: #003366;
}

img {
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
	border-radius: 15px;
}

.STYLE1 {
	font-family: "新宋体";
	font-weight: bold;
	font-size: 16px;
}

.STYLE3 {
	font-family: "新宋体";
	font-weight: bold;
	font-size: 16px;
	color: #000000;
}

a:link {
	color: #000000;
	text-decoration: none;
}

a:visited {
	text-decoration: none;
	color: #000000;
}

a:hover {
	text-decoration: none;
	color: #000000;
}

a:active {
	text-decoration: none;
	color: #000000;
}
</style>
<title>就业信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>

	<%
		String str = "http://localhost:8080/empforecast/api/rest/ssdut/empinfolist?page="
				+ request.getParameter("page");

		String l = "";

		try {

			URL url = new URL(str);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5 * 1000);
			if (conn.getResponseCode() != 200) // 从Internet获取网页,发送请求,将网页以流的形式读回来
			{
				throw new RuntimeException("请求url失败");
			}

			InputStream is = conn.getInputStream();
			InputStreamReader reader = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(reader);
			String s = null;
			while ((s = br.readLine()) != null) {
				if (s != null) {
					s.trim();
					l = l + s;
				}
			}

			br.close();
			reader.close();
			l.trim();
			conn.disconnect();

		} catch (Exception e) {

			l = "error";
		}

		JSONArray array = new JSONArray(l);
	%>
	<div id="container">

		<div style="width: 100%; height: 5px; background: #ee5800"></div>
		<div style="width: 100%; height: 3px; background: #FFFFFF"></div>
		<header align="center">
			<h1>就业信息公告</h1>
		</header>
		<div style="width: 100%; height: 3px; background: #FFFFFF"></div>
		<div style="width: 100%; height: 5px; background: #ee5800"></div>
		<nav id="main">

			<ul>
				<%
					int size = array.length();
					for (int i = 0; i < size; i++) {
						JSONObject p = array.getJSONObject(i);
				%>
				<li>
					<table class="table1">
						<tr>
							<td width="86"><div>
									<img src="./img/a.jpg" width="60" height="60"></img>
								</div></td>
							<td width="230" ALIGN="left"><span class="STYLE3"> 
							<a href=http://10.2.207.45:8080/empforecast/api/rest/ssdut/empinfodetail?id=<%=p.get("htmlurl").toString().substring(35, 39)%>> <%=p.get("title")%></a></span></td>
					</table>
				</li>

				<%
					}
				%>




				<li>
					<table class="table1">
						<tr>
							<td width="50%" align="center">
								<%
								
								    int paage=Integer.parseInt(request.getParameter("page"));
								
									if (1!=paage&&request.getParameter("page")!=null) {
										
										int s=paage-1;
												
										String uri="http://10.2.207.45:8080/empforecast/ssdutemp/ssdutempinfolist.jsp?page="+s;
										
										out.println("<a href="+uri+">上一页</a>");

									}
									
								%>

							</td>
							<td width="50%" align="center">
							
							<%								
								    //int paagea=Integer.parseInt(request.getParameter("page"));
								
									if (size==6&&request.getParameter("page")!=null) {
										
										int s=paage+1;
												
										String uri="http://10.2.207.45:8080/empforecast/ssdutemp/ssdutempinfolist.jsp?page="+s;
										
										out.println("<a href="+uri+">下一页</a>");

									}
									
								%>
							
							</td>
					</table>
				</li>
			</ul>
		</nav>

	</div>


</body>
</html>
