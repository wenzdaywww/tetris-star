package com.www.gamemodel;

	/**
	 * 炸弹方块类
	 * @author wWw
	 *
	 */
public class BombShape extends BaseShape {
	/**
	 * 炸弹的构造方法，获取下落区域对象
	 * @param blockPanel 下落区域对象
	 */
	public BombShape() {
	}
	/**
	 * 初始化炸弹出现的位置
	 */
	@Override
	public void init() {
		whichTime=0;	//设置炸弹初始形态
		canMove=true;	//设置可以移动
		isBomb=true;	//设置为炸弹标志
		shapeX[0]=8;shapeY[0]=1;	//初始炸弹出现的坐标
	}
	/**
	 * 炸弹的左移
	 */
	@Override
	public void left() {
		notDraw();	
		if (canMove&&(shapeX[0]!=1)&&(GridBlock.xyList.get(shapeXYToInt(shapeX[0]-1, shapeY[0])).isDraw()==false)) { //左移的条件
			shapeX[0]-=1;
		}
		setInitImage();	
		canDraw();	
	}
	/**
	 * 炸弹的右移
	 */
	@Override
	public void right() {
		notDraw();	
		if (canMove&&(shapeX[0]!=GridBlock.COL)&&(GridBlock.xyList.get(shapeXYToInt(shapeX[0]+1, shapeY[0])).isDraw()==false)) {	//右移的条件
			shapeX[0]+=1;
		}
		setInitImage();	
		canDraw();	
	}
	/**
	 * 炸弹下落
	 */
	@Override
	public void down() {
		notDraw();	
		if (canMove) {
			switch (whichTime) {
			case 0:	//从初始位置变化一个，这样才可以在第一行出现，否则在第二行出现
				whichTime=1;
				setInitImage();	
				canDraw();
				break;
			case 1:
				if ((shapeY[0]!=GridBlock.ROW)&&(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isDraw()==false)) {	//下落的条件
					shapeY[0]+=1;
					setInitImage();	
					canDraw();
				}else if ((shapeY[0]==GridBlock.ROW)||(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isBottomBlock())){ //到底的条件
					canMove=false;
					GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]))).setDraw(false);
					GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]))).setBottomBlock(false);
				}
				break;
			default:
				break;
			}
		}
	}
	/**
	 * 初始化炸弹的图片
	 */
	@Override
	protected void setInitImage() {
		GridBlock.xyList.get(shapeXYToInt(shapeX[0], shapeY[0])).setImage("image/bomb.png");
	}
	/**
	 * 炸弹移动前设置不可见
	 */
	@Override
	protected void notDraw() {
		GridBlock.xyList.get(shapeXYToInt(shapeX[0], shapeY[0])).setDraw(false);
	}
	/**
	 * 炸弹移动后设置可见
	 */
	@Override
	protected void canDraw() {
		GridBlock.xyList.get(shapeXYToInt(shapeX[0], shapeY[0])).setDraw(true);
	}
	/**
	 * 炸弹初始化不会判断游戏结束
	 */
	@Override
	public boolean initGameOver() {
		return false;
	}
}
