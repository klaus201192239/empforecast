package com.klaus.apiserviceimpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.klaus.apiservice.ScoreService;

public class ScoreServiceImpl implements ScoreService {

	public String uploadExcel(InputStream ioBodyStream) {
		try {

			File f = new File("E:/temp", "asas.xls");
			FileOutputStream fos = new FileOutputStream(f);
			byte[] b = new byte[1024];
			int n = 0;
			while ((n = ioBodyStream.read(b)) != -1) {
				fos.write(b, 0, n);
			}
			fos.close();
			ioBodyStream.close();

		} catch (Exception ex) {			
			return "error";			
		}
		
		return "ok";
	}

}
