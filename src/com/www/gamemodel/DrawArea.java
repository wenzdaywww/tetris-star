package com.www.gamemodel;
	/**
	 * 容器填充位置类
	 * @author wWw
	 *
	 */
public class DrawArea {
	/**@see 下落区域网格的x坐标*/
	private int x;			
	/**@see 下落区域网格的y坐标*/
	private int y;			
	/**@see 下落区域要显示的图片相对地址*/
	private String image;	
	/**@see 下落区域是否有方块显示*/
	private boolean isDraw;	
	/**@see 下落区域已到达底部的方块，true为底部方块*/
	private boolean isBottomBlock;	
	/**
	 * @param x	要填充的x坐标
	 * @param y	要填充的y坐标
	 * @param isDraw	是否有方块，true为有方块，false为没有方块
	 * @param image		要填充的图片
	 * @param isBottomBlock 下落区域中的方块 ，true为底部方块，false为移动中的方块 
	 */
	public DrawArea(int x,int y,boolean isDraw,String image,boolean isBottomBlock){
		this.x=x;
		this.y=y;
		this.image=image;
		this.isDraw=isDraw;
		this.isBottomBlock=isBottomBlock;
	}
	/**
	 * 设置X坐标
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * 设置Y坐标
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 获取X坐标
	 * @return X坐标
	 */
	public int getX() {
		return x;
	}
	/**
	 * 获取Y坐标
	 * @return Y坐标
	 */
	public int getY() {
		return y;
	}
	/**
	 * 获取是否有方块
	 * @return true为有方块，false为没有方块
	 */
	public boolean isDraw() {
		return isDraw;
	}
	/**
	 * 设置是否设置有无方块
	 * @param isDraw true为有方块，false为没有方块
	 */
	public void setDraw(boolean isDraw) {
		this.isDraw = isDraw;
	}
	/**
	 * 设置填充的图片
	 * @param image	图片的相对地址
	 */
	public void setImage(String image){
		this.image=image;
	}
	/**
	 * 获取图片的相对地址
	 */
	public String getImage(){
		return image;
	}
	/**
	 * 获取是不是底部方块
	 */
	public boolean isBottomBlock() {
		return isBottomBlock;
	}
	/**
	 * 获取设置方块是不是底部
	 */
	public void setBottomBlock(boolean isBottomBlock) {
		this.isBottomBlock = isBottomBlock;
	}
	
}
