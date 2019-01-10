package com.nsu.util;

import java.util.List;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;


public class ZipFile {
	
	//将文件添加到压缩包中
	public static void zipFile(List<File> files,ZipOutputStream outputStream)
			           throws IOException,ServletException{
	
		int size = files.size();
		for(int i = 0 ; i<size ; i++){
	
			File file =  files.get(i);
			zipFile(file, outputStream);
 		}
	}

	private static void zipFile(File inputFile, ZipOutputStream outputStream) throws IOException {
		
		if (inputFile.exists()) {
			if (inputFile.isFile()) {
				FileInputStream inputStream = new FileInputStream(inputFile);
				BufferedInputStream bInputStream = new BufferedInputStream(inputStream);
				ZipEntry entry = new ZipEntry(inputFile.getName());
				outputStream.putNextEntry(entry);
				
				final int MAX_BYTE = 2*1024*1024;	// 最大的流为2M
				long streamTotal = 0; //接受流的
				int streamNum = 0;  //流需要分开的数量
				int leaveByte = 0;  //文件剩下的字符数
				byte[] inOutbyte;  //byte数组接受文件的数据
				
				// 通过available方法取得流的最大字符数
				streamTotal = bInputStream.available();
				// 取得流文件需要分开的数量
				streamNum = (int) Math.floor(streamTotal/MAX_BYTE);
				// 分开文件之后,剩余的数量
				leaveByte = (int) streamTotal % MAX_BYTE; 
				
				if (streamNum > 0) {
					for(int i =0 ; i < streamNum ; i++){
						inOutbyte = new byte[MAX_BYTE];
						//读入流，保存在byte中
						bInputStream.read(inOutbyte,0,MAX_BYTE);
						//写入流
						outputStream.write(inOutbyte,0,MAX_BYTE);
					}
				}
				//写出剩下的数据流
				inOutbyte = new byte[leaveByte];
				bInputStream.read(inOutbyte,0,leaveByte);
				outputStream.write(inOutbyte);
				
				//关闭
				outputStream.closeEntry();
				bInputStream.close();
				inputStream.close();
			}
		}
	}
	
	

	
}
