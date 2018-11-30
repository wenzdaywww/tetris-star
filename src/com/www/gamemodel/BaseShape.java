package com.www.gamemodel;

import com.www.gameview.BlockPanel;
	/**
	 * 方块基本类
	 * @author wWw
	 *
	 */
public class BaseShape{
	/**@see 方块处于哪一种形态*/
	protected int whichTime=0;		
	/**@see 游戏结束状态,true为游戏结束，false为游戏中*/
	public boolean gameOver;		
	/**@see true为炸弹，false为正常方块*/
	public boolean isBomb=false;	
	/**@see 方块填充的图片*/
	protected String shapeImage;	
	/**@see 长度为方块的个数，值为X坐标*/
	public int[] shapeX=new int[7];	
	/**@see 长度为方块的个数，值为Y坐标*/
	public int[] shapeY=new int[7];	
	/**@see 方块是否到底，false为到底*/
	protected boolean canMove=true;	
	/**@see 下落区域的对象*/
	protected BlockPanel blockPanel;
	/**
	 * 方块初始化
	 */
	public void init(){}	
	/**
	 * 方块左移
	 */
	public void left(){}	
	/**
	 * 方块右移
	 */
	public void right(){}
	/**
	 * 方块下落
	 */
	public void down(){}
	/**
	 * 方块旋转
	 */
	public void rotate(){}	
	/**
	 * 判断方块是否到底
	 * @return false为到底部，true为移动中
	 */
	public boolean isCanMove(){
		return canMove;
	}
	/**
	 * 设置到底标志
	 * @param canMove false为到底部，true为可移动
	 */
	public void setCanMove(boolean canMove){
		this.canMove=canMove;
	}
	/**
	 * 获取游戏状态
	 * @return 游戏状态，true为游戏结束，false为游戏中
	 */
	public boolean isGameOver(){
		return gameOver;
	}
	/**
	 * 游戏结束后设置重新
	 * @param gameOver	设置游戏状态，true为游戏结束，false为可开始新游戏
	 */
	public void setGameOverToRestart(boolean gameOver){
		this.gameOver=gameOver;
	}
	/**
	 * 设置方块图片
	 * @param shapeImage 图片的相对地址
	 */
	public void setImage(String shapeImage) {
		this.shapeImage=shapeImage;
	}
	/**
	 * x,y坐标转换成序号
	 * @param x	方块的X坐标
	 * @param y	方块的Y坐标
	 * @return	返回方块序号
	 */
	protected int shapeXYToInt(int x,int y){
		return GridBlock.shapeXYToInt(x, y);
	}
	/**
	 * 设置方块初始图片
	 */
	protected void setInitImage(){
		for (int i = 0; i < 4; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setImage(shapeImage);
		}
	}
	/**
	 * 隐藏方块不可见
	 */
	protected void notDraw(){
		for (int i = 0; i < 4; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setDraw(false);
		}
	}
	/**
	 * 设置方块可见
	 */
	protected void canDraw(){
		for (int i = 0; i < 4; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setDraw(true);
		}
	}
	/**
	 * 设置方块到底部为底部方块
	 */
	protected void bottomBlock() {
		for (int i = 0; i < 4; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setBottomBlock(true);;
		}
	}
	/**
	 * 判断每次方块出现游戏是否结束
	 * @return 判断是否游戏结束，true为游戏结束，false为游戏中 
	 */
	public boolean initGameOver(){
		if (GridBlock.xyList.get(shapeXYToInt(shapeX[0], shapeY[0])).isDraw()||	//方块初始出现的位置有方块阻挡则游戏结束
				GridBlock.xyList.get(shapeXYToInt(shapeX[1], shapeY[1])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[2], shapeY[2])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[3], shapeY[3])).isDraw()) {
			return true;
		}else {
			return false;
		}
	}
}
