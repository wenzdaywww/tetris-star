package com.www.gamemodel;

	/**
	 * 十字形方块类
	 * @author wWw
	 *
	 */
public class CrossShape extends BaseShape {
	/**
	 * 十字形方块的构造方法，传递下落区域对象
	 * @param GridBlock 下落区域对象
	 */
	public CrossShape() {
	}
	/**
	 * 十字形方块的初始化
	 */
	@Override
	public void init() {
		whichTime=0;	//十字形方块形态0,形态1和0一样
		canMove=true;	//设置可以移动
		shapeX[0]=8;shapeY[0]=1;	//设置十字形方块的坐标
		shapeX[1]=7;shapeY[1]=2;
		shapeX[2]=8;shapeY[2]=2;
		shapeX[3]=9;shapeY[3]=2;
		shapeX[4]=8;shapeY[4]=3;
	}
	/**
	 * 十字形方块的左移
	 */
	@Override
	public void left() {
		notDraw();
		if (canMove&&(shapeX[1]!=1)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)&&	
				(GridBlock.xyList.get(shapeXYToInt((shapeX[1]-1), shapeY[1])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[4]-1), shapeY[4])).isDraw()==false)) {	//左移的条件
			shapeX[0]-=1;shapeX[1]-=1;shapeX[2]-=1;shapeX[3]-=1;shapeX[4]-=1;
		} 
		setInitImage();
		canDraw();
	}
	/**
	 * 十字形方块的右移
	 */
	@Override
	public void right() {
		notDraw();
		if (canMove&&(shapeX[3]!=GridBlock.COL)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[0]+1), shapeY[0])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[3]+1), shapeY[3])).isDraw()==false)&&
				(GridBlock.xyList.get(shapeXYToInt((shapeX[4]+1), shapeY[4])).isDraw()==false)) {	//右移的条件
			shapeX[0]+=1;shapeX[1]+=1;shapeX[2]+=1;shapeX[3]+=1;shapeX[4]+=1;
		} 
		setInitImage();
		canDraw();
	}
	/**
	 * 十字形方块的下落
	 */
	@Override
	public void down() {
		notDraw();
		if (canMove) {
			switch (whichTime) {
			case 0:	//使初始位置在第一行
				whichTime=1;
				canDraw();
				break;
			case 1:	
				if ((shapeY[4]!=GridBlock.ROW)&&(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[4], (shapeY[4]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[4], (shapeY[4]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isDraw()==false)) {	//形态1的下落条件和移动
					shapeY[0]+=1;shapeY[1]+=1;shapeY[2]+=1;shapeY[3]+=1;shapeY[4]+=1;
				}else if ((shapeY[4]==GridBlock.ROW)||(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[4], (shapeY[4]+1))).isBottomBlock())||
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
	/**
	 * 十字形方块的图片初始化
	 */
	@Override
	protected void setInitImage() {
		for (int i = 0; i < 5; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setImage(shapeImage);
		}
	}
	/**
	 * 十字形方块移动前不可见
	 */
	@Override
	protected void notDraw() {
		for (int i = 0; i < 5; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setDraw(false);
		}
	}
	/**
	 * 十字形方块移动后可见
	 */
	@Override
	protected void canDraw() {
		for (int i = 0; i < 5; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setDraw(true);
		}
	}
	/**
	 * 十字形方块到底后设为底部方块
	 */
	@Override
	protected void bottomBlock() {
		for (int i = 0; i < 5; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setBottomBlock(true);
		}
	}
	/**
	 * 十字形方块每次出现判断游戏是否结束
	 */
	@Override
	public boolean initGameOver() {
		if (GridBlock.xyList.get(shapeXYToInt(shapeX[0], shapeY[0])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[1], shapeY[1])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[2], shapeY[2])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[3], shapeY[3])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[4], shapeY[4])).isDraw()) {
			return true;
		}else {
			return false;
		}
	}
}
