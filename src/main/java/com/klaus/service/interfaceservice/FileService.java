package com.klaus.service.interfaceservice;

import java.io.File;
import java.util.List;

public interface FileService {
	
	public void saveFile(File file,int fileKind);
	public void deleteFile(String filePath);
	public List<String> getFileList(int fileKind);
	
}
