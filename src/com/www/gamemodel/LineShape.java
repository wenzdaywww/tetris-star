package com.www.gamemodel;

	/**
	 * 直线形方块类
	 * @author wWw
	 *
	 */
public class LineShape extends BaseShape {
	/**
	 * 直线形方块构造方法，传递下落区域对象
	 * @param GridBlock
	 */
	public LineShape() {
	}
	/**
	 * 直线方块的初始化
	 */
	@Override
	public void init() {
		whichTime=0;	//直线方块的形态0,形态2和0一样
		canMove=true;	//设置可以移动
		shapeX[0]=6;shapeY[0]=1;	//直线方块的坐标
		shapeX[1]=7;shapeY[1]=1;
		shapeX[2]=8;shapeY[2]=1;
		shapeX[3]=9;shapeY[3]=1;
	}
	/**
	 * 直线方块的左移
	 */
	@Override
	public void left() {
		notDraw();
		if (canMove) {
			switch (whichTime) {
			case 0:	//形态0的左移条件和移动
				if ((shapeX[0]!=1)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)) {
					shapeX[0]-=1;shapeX[1]-=1;shapeX[2]-=1;shapeX[3]-=1;
				}
				break;
			case 1:	//形态1的左移条件和移动
				if ((shapeX[2]!=1) &&(GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[1]-1), shapeY[1])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[2]-1), shapeY[2])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[3]-1), shapeY[3])).isDraw()==false)) {
					shapeX[0]-=1;shapeX[1]-=1;shapeX[2]-=1;shapeX[3]-=1;
				}
				break;
			case 2:	//形态2的左移条件和移动
				if ((shapeX[0]!=1)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)) {
					shapeX[0]-=1;shapeX[1]-=1;shapeX[2]-=1;shapeX[3]-=1;
				}
				break;
			default:
				break;
			}
		}
		setInitImage();
		canDraw();
	}
	/**
	 * 直线方块的右移
	 */
	@Override
	public void right() {
		notDraw();
		if (canMove) {
			switch (whichTime) {
			case 0:	//形态0的右移条件和移动
				if ((shapeX[3]!=GridBlock.COL)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[3]+1), shapeY[0])).isDraw()==false)) {
					shapeX[0]+=1;shapeX[1]+=1;shapeX[2]+=1;shapeX[3]+=1;
				}
				break;
			case 1:	//形态1的右移条件和移动
				if ((shapeX[2]!=GridBlock.COL) &&(GridBlock.xyList.get(shapeXYToInt((shapeX[0]+1), shapeY[0])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[1]+1), shapeY[1])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[2]+1), shapeY[2])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[3]+1), shapeY[3])).isDraw()==false)) {
					shapeX[0]+=1;shapeX[1]+=1;shapeX[2]+=1;shapeX[3]+=1;
				}
				break;
			case 2:	//形态2的右移条件和移动
				if ((shapeX[3]!=GridBlock.COL)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[3]+1), shapeY[0])).isDraw()==false)) {
					shapeX[0]+=1;shapeX[1]+=1;shapeX[2]+=1;shapeX[3]+=1;
				}
				break;
			default:
				break;
			}
		}
		setInitImage();
		canDraw();
	}
	/**
	 * 直线方块的下落
	 */
	@Override
	public void down() {
		notDraw();
		if (canMove) {
			switch (whichTime) {
			case 0:	//使初始位置在第一行
				whichTime=2;
				canDraw();
				break;
			case 1:
				if ((shapeY[3]!=GridBlock.ROW) &&(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isDraw()==false)) {	//形态1的下落条件和移动
					shapeY[0]+=1;shapeY[1]+=1;shapeY[2]+=1;shapeY[3]+=1;
				}else if ((shapeY[3]==GridBlock.ROW) ||(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isBottomBlock())){//形态1的到底条件
					canMove=false;
					bottomBlock();
				}
				break;
			case 2:
				if ((shapeY[2]!=GridBlock.ROW)&&(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isDraw()==false)) {	//形态2的下落条件和移动
					shapeY[0]+=1;shapeY[1]+=1;shapeY[2]+=1;shapeY[3]+=1;
				}else if ((shapeY[2]==GridBlock.ROW)||(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isBottomBlock())){	//形态2的到底条件
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
	/**
	 * 直线方块的旋转
	 */
	@Override
	public void rotate() {
		notDraw();
		if (canMove) {
			switch (whichTime) {
			case 0:	//形态0旋转到形态1
				twoToOne();
				break;
			case 1:	//形态1旋转到形态2
				oneToTwo();
				break;
			case 2:	//形态2旋转到形态1
				twoToOne();
				break;
			default:
				break;
			}
		}
		setInitImage();
		canDraw();
	}
	/**
	 * 直线方块形态1
	 */
	private void twoToOne(){
		if ((shapeY[1]!=1)&&(shapeY[1]!=GridBlock.ROW)&&(shapeY[1]!=GridBlock.ROW-1)&&
				(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]-1))).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]-1))).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+2))).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+1))).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+2))).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+2))).isDraw()==false)) {	//旋转条件
			whichTime=1;
			shapeX[0]+=1;shapeY[0]-=1;
			shapeX[2]-=1;shapeY[2]+=1;
			shapeX[3]-=2;shapeY[3]+=2;
		}
	}
	/**
	 * 直线方块形态2
	 */
	private void oneToTwo(){
		if ((shapeX[1]!=1)&&(shapeX[1]!=GridBlock.COL)&&(shapeX[1]!=GridBlock.COL-1)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[1]-1), shapeY[1])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[1]+1), shapeY[1])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[1]+2), shapeY[1])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[2]+1), shapeY[2])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[2]+2), shapeY[2])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[3]+1), shapeY[3])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[3]+2), shapeY[3])).isDraw()==false)) {	//旋转条件
			whichTime=2;
			shapeX[0]-=1;shapeY[0]+=1;
			shapeX[2]+=1;shapeY[2]-=1;
			shapeX[3]+=2;shapeY[3]-=2;
		}
	}
}
