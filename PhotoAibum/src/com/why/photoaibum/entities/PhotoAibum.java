package com.why.photoaibum.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PhotoAibum implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;   //相册名字
	private int count = 0; //数量
	private int  bitmap;  // 相册第一张图片
	public int checkCount = 0;
	private List<PhotoItem> bitList = new ArrayList<PhotoItem>();
	
	public PhotoAibum() {
	}
	
	
	public PhotoAibum(String name, int count, int bitmap) {
		super();
		this.name = name;
		this.count = count;
		this.bitmap = bitmap;
	}


	public List<PhotoItem> getBitList() {
		return bitList;
	}


	public void setBitList(List<PhotoItem> bitList) {
		this.bitList = bitList;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getBitmap() {
		return bitmap;
	}
	public void setBitmap(int bitmap) {
		this.bitmap = bitmap;
	}
	@Override
	public String toString() {
		return "PhotoAibum [name=" + name + ", count=" + count + ", bitmap="
				+ bitmap + ", bitList=" + bitList + "]";
	}
}
