package com.nsu.util;

import javax.swing.filechooser.FileSystemView;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NewFileUtils  {

	public static FileInputStream openInputStream(File file) throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException("File '" + file
						+ "' exists but is a directory");
			}
			if (file.canRead() == false) {
				throw new IOException("File '" + file + "' cannot be read");
			}
		} else {
			throw new FileNotFoundException("File '" + file
					+ "' does not exist");
		}
		return new FileInputStream(file);
	}

	public static FileOutputStream openOutputStream(File file)
			throws IOException {
		return openOutputStream(file, false);
	}

	public static FileOutputStream openOutputStream(File file, boolean append)
			throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException("File '" + file
						+ "' exists but is a directory");
			}
			if (file.canWrite() == false) {
				throw new IOException("File '" + file
						+ "' cannot be written to");
			}
		} else {
			File parent = file.getParentFile();
			if (parent != null) {
				if (!parent.mkdirs() && !parent.isDirectory()) {
					throw new IOException("Directory '" + parent
							+ "' could not be created");
				}
			}
		}
		return new FileOutputStream(file, append);
	}

	public static void closeQuietly(InputStream input) {
		closeQuietly((Closeable) input);
	}

	public static void closeQuietly(Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException ioe) {
			// ignore
		}
	}


	public static String getBasePath(){
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File com=fsv.getHomeDirectory();
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("mac")){

			return com.getPath()+File.separator + "Desktop" + File.separator;

		}if (os.toLowerCase().startsWith("linux")){

			return File.separator+"home"+ File.separator;

		}if (os.toLowerCase().startsWith("win")){
			return  com.getPath() + File.separator;
		}
		return null;
	}

	public static String getBasePath(String addPath){
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File com=fsv.getHomeDirectory();
		String baseUrl = "";
		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("mac")){

			baseUrl = com.getPath()+File.separator + "Desktop" + File.separator;

		}if (os.toLowerCase().startsWith("linux")){

			baseUrl =  File.separator+"home"+ File.separator;

		}if (os.toLowerCase().startsWith("win")){
			baseUrl =  com.getPath() + File.separator;
		}


		return baseUrl+addPath+File.separator;
	}

	public static String getProjectPath(String addPath){
		return  getClassPath()+addPath+File.separator;
	}



	public static String getClassPath(){
		String uri=NewFileUtils.class.getResource("").toString();
		String realPath =uri.substring(5, uri.indexOf("WEB-INF"));
		return realPath;
	}





}
