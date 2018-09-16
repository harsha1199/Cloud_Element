package com.element.cloud.bean;

public class FileBean {

	private String fileName;
	private boolean isFile;
	private long fileSize;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public boolean isFile() {
		return isFile;
	}
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
}
