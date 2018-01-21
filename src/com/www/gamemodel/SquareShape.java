package com.www.gamemodel;

	/**
	 * 正方形方块类
	 * @author wWw
	 *
	 */
public class SquareShape extends BaseShape {
	/**
	 * 正方形方块构造方法，传递下落区域对象
	 * @param GridBlock
	 */
	public SquareShape() {
	}
	/**
	 * 正方形方块的初始化
	 */
	@Override
	public void init() {
		whichTime=0;	//正方形方块形态0,形态1和0一样
		canMove=true;	//设置可以移动
		shapeX[0]=7;shapeY[0]=1;	//设置初始坐标
		shapeX[1]=8;shapeY[1]=1;
		shapeX[2]=7;shapeY[2]=2;
		shapeX[3]=8;shapeY[3]=2;
	}
	/**
	 * 正方形方块的左移
	 */
	@Override
	public void left(){
		notDraw();
		if (canMove&&(shapeX[0]!=1)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[2]-1), shapeY[2])).isDraw()==false)) {	//左移条件
			shapeX[0]-=1;shapeX[1]-=1;shapeX[2]-=1;shapeX[3]-=1;
		} 
		setInitImage();
		canDraw();
	}
	/**
	 * 正方形方块的右移
	 */
	@Override
	public void right(){
		notDraw();
		if (canMove&&(shapeX[1]!=GridBlock.COL)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[1]+1), shapeY[1])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[3]+1), shapeY[3])).isDraw()==false)) {	//右移条件
			shapeX[0]+=1;shapeX[1]+=1;shapeX[2]+=1;shapeX[3]+=1;
		} 
		setInitImage();
		canDraw();
	}
	/**
	 * 正方形方块的下落
	 */
	@Override
	public void down(){
		notDraw();
		if (canMove) {
			switch (whichTime) {
			case 0:	//使初始位置在第一行
				whichTime=1;
				canDraw();
				break;
			case 1:
				if ((shapeY[2]!=GridBlock.ROW)&&(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isDraw()==false)) {	//形态1的下落条件和移动
					shapeY[0]+=1;shapeY[1]+=1;shapeY[2]+=1;shapeY[3]+=1;
				}else 	if ((shapeY[2]==GridBlock.ROW)||(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isBottomBlock())){	//形态1的到底条件
					canMove=false;
					bottomBlock();
				}
				break;
			default:
				break;
			}
		}
		setInitImage();
		canDraw();
	}
}
