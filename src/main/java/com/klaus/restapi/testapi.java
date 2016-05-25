package com.klaus.restapi;


import java.io.File;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

@Path("/tteess")
public class testapi {

	@POST
	public String uploadExcel(@Context HttpServletRequest request) {

		try {

			String savePath = request.getServletContext().getRealPath("/WEB-INF/uploadFile");
			
			System.out.println(savePath);
			
			//String savePath ="E:/temp/";
			
			// 获取上传的文件集合
			Collection<Part> parts = request.getParts();			
			
			System.out.println("AAAAAAAAAAAAA"+parts.size());

			if (parts.size() == 1) {
				// Servlet3.0将multipart/form-data的POST请求封装成Part，通过Part对上传的文件进行操作。
			//	 Part part = parts[0];//从上传的文件集合中获取Part对象
				
				System.out.println("BBBBBBBBBBBBBBBB");
				
				Part part = request.getPart("file");// 通过表单file控件(<input
													// type="file"
													// name="file">)的名字直接获取Part对象
				
				System.out.println("CCCCCCCCCCCCCCCCc");
				
				// Servlet3没有提供直接获取文件名的方法,需要从请求头中解析出来
				// 获取请求头，请求头的格式：form-data; name="file";
				// filename="snmp4j--api.zip"
				String header = part.getHeader("content-disposition");
				
				System.out.println("DDDDDDDDDDDDDDDD");
				
				
				// 获取文件名
				String fileName = getFileName(header);
				
				
				System.out.println("EEEEEEEEEEEEEEEE");
				
				// 把文件写到指定路径
				part.write(savePath + File.separator + fileName);
				
				
				System.out.println("FFFFFFFFFFFFFFFFFFFF");
				
				//part.write(savePath + fileName);
			} else {
				
				
				System.out.println("GGGGGGGGGGGGGGGGGGGG");
				
				// 一次性上传多个文件
				for (Part part : parts) {// 循环处理上传的文件
					// 获取请求头，请求头的格式：form-data; name="file";
					// filename="snmp4j--api.zip"
					
					System.out.println("HHHHHHHHHHHHHHHHHHHHH");
					
					String header = part.getHeader("content-disposition");
					
					System.out.println("IIIIIIIIIIIIIIIIIIIIII");
					
					// 获取文件名
					String fileName = getFileName(header);
					
					System.out.println("JJJJJJJJJJJJJJJJJJJJJJJJ");
					
					// 把文件写到指定路径
					part.write(savePath + File.separator + fileName);
					//part.write(savePath + fileName);
					
					
					System.out.println("KKKKKKKKKKKKKKKKKKKKKKKKKK");
					
				}
			}

		} catch (Exception e) {
		}

		return "okklaus";

	}

	public String getFileName(String header) {
		/**
		 * String[] tempArr1 =
		 * header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
		 * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename=
		 * "snmp4j--api.zip"}
		 * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		/**
		 * 火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
		 * IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		// 获取文件名，兼容各种浏览器的写法
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

}
