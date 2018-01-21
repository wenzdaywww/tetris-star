package com.www.gamemodel;

	/**
	 * 凹形方块类
	 * @author wWw
	 *
	 */
public class ConcaveShape extends BaseShape {
	/**
	 * 凹形方块构造方法，传递下落区域 对象
	 * @param GridBlock 下落区域 对象
	 */
	public ConcaveShape() {
	}
	/**
	 * 初始化凹形方块位置
	 */
	@Override
	public void init() {
		whichTime=0;	//设置凹形方块的初始形态标志，形态0和形态4一样
		canMove=true;	//设置可以移动
		shapeX[0]=9;shapeY[0]=3;	//设置凹形初始位置
		shapeX[1]=7;shapeY[1]=3;
		shapeX[2]=9;shapeY[2]=2;
		shapeX[3]=7;shapeY[3]=2;
		shapeX[4]=9;shapeY[4]=1;
		shapeX[5]=8;shapeY[5]=1;
		shapeX[6]=7;shapeY[6]=1;
	}
	/**
	 * 凹形方块左移
	 */
	@Override
	public void left() {
		notDraw();
		if (canMove) {
			switch (whichTime) {
			case 0:	//初始位置的左移条件和移动
				if ((shapeX[1]!=1)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[1]-1), shapeY[1])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[3]-1), shapeY[3])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[2]-1), shapeY[2])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[6]-1), shapeY[6])).isDraw()==false)) {
					shapeX[0]-=1;shapeX[1]-=1;shapeX[2]-=1;shapeX[3]-=1;shapeX[4]-=1;shapeX[5]-=1;shapeX[6]-=1;
				}
				break;
			case 1:	//形态1的左移条件和移动
				if ((shapeX[1]!=1) &&(GridBlock.xyList.get(shapeXYToInt((shapeX[1]-1), shapeY[1])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[5]-1), shapeY[5])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)) {
					shapeX[0]-=1;shapeX[1]-=1;shapeX[2]-=1;shapeX[3]-=1;shapeX[4]-=1;shapeX[5]-=1;shapeX[6]-=1;
				}
				break;
			case 2:	//形态2的左移条件和移动
				if ((shapeX[0]!=1)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[2]-1), shapeY[2])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[1]-1), shapeY[1])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[3]-1), shapeY[3])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[4]-1), shapeY[4])).isDraw()==false)) {
					shapeX[0]-=1;shapeX[1]-=1;shapeX[2]-=1;shapeX[3]-=1;shapeX[4]-=1;shapeX[5]-=1;shapeX[6]-=1;
				}
				break;
			case 3:	//形态3的左移条件和移动
				if ((shapeX[4]!=1)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[4]-1), shapeY[4])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[5]-1), shapeY[5])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[6]-1), shapeY[6])).isDraw()==false)) {
					shapeX[0]-=1;shapeX[1]-=1;shapeX[2]-=1;shapeX[3]-=1;shapeX[4]-=1;shapeX[5]-=1;shapeX[6]-=1;
				}
				break;
			case 4:	//形态4的左移条件和移动
				if ((shapeX[1]!=1)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[1]-1), shapeY[1])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[3]-1), shapeY[3])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[2]-1), shapeY[2])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[6]-1), shapeY[6])).isDraw()==false)) {
					shapeX[0]-=1;shapeX[1]-=1;shapeX[2]-=1;shapeX[3]-=1;shapeX[4]-=1;shapeX[5]-=1;shapeX[6]-=1;
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
	 * 凹形方块的右移
	 */
	@Override
	public void right() {
		notDraw();
		if (canMove) {
			switch (whichTime) {
			case 0:	//形态0的右移条件和移动
				if ((shapeX[0]!=GridBlock.COL)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[1]+1), shapeY[1])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[3]+1), shapeY[3])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[0]+1), shapeY[0])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[2]+1), shapeY[2])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[4]+1), shapeY[4])).isDraw()==false)) {
					shapeX[0]+=1;shapeX[1]+=1;shapeX[2]+=1;shapeX[3]+=1;shapeX[4]+=1;shapeX[5]+=1;shapeX[6]+=1;
				}
				break;
			case 1:	//形态1的右移条件和移动
				if ((shapeX[5]!=GridBlock.COL) &&(GridBlock.xyList.get(shapeXYToInt((shapeX[4]+1), shapeY[4])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[5]+1), shapeY[5])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[6]+1), shapeY[6])).isDraw()==false)) {
					shapeX[0]+=1;shapeX[1]+=1;shapeX[2]+=1;shapeX[3]+=1;shapeX[4]+=1;shapeX[5]+=1;shapeX[6]+=1;
				}
				break;
			case 2:	//形态2的右移条件和移动
				if ((shapeX[1]!=GridBlock.COL)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[1]+1), shapeY[1])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[3]+1), shapeY[3])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[6]+1), shapeY[6])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[2]+1), shapeY[2])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[0]+1), shapeY[0])).isDraw()==false)) {
					shapeX[0]+=1;shapeX[1]+=1;shapeX[2]+=1;shapeX[3]+=1;shapeX[4]+=1;shapeX[5]+=1;shapeX[6]+=1;
				}
				break;	
			case 3:	//形态3的右移条件和移动
				if ((shapeX[0]!=GridBlock.COL)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[0]+1), shapeY[0])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[5]+1), shapeY[5])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[1]+1), shapeY[1])).isDraw()==false)) {
					shapeX[0]+=1;shapeX[1]+=1;shapeX[2]+=1;shapeX[3]+=1;shapeX[4]+=1;shapeX[5]+=1;shapeX[6]+=1;
				}
				break;
			case 4:	//形态4的右移条件和移动
				if ((shapeX[0]!=GridBlock.COL)&&(GridBlock.xyList.get(shapeXYToInt((shapeX[1]+1), shapeY[1])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[3]+1), shapeY[3])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[0]+1), shapeY[0])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[2]+1), shapeY[2])).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt((shapeX[4]+1), shapeY[4])).isDraw()==false)) {
					shapeX[0]+=1;shapeX[1]+=1;shapeX[2]+=1;shapeX[3]+=1;shapeX[4]+=1;shapeX[5]+=1;shapeX[6]+=1;
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
	 * 凹形方块的下落
	 */
	@Override
	public void down() {
		notDraw();
		if (canMove) {
			switch (whichTime) {
			case 0:	//使初始位置在第一行
				whichTime=4;
				canDraw();
				break;
			case 1:	
				if ((shapeY[2]!=GridBlock.ROW)&&(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[4], (shapeY[4]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[4], (shapeY[4]+1))).isDraw()==false)) {	//形态1的下落条件和移动
					shapeY[0]+=1;shapeY[1]+=1;shapeY[2]+=1;shapeY[3]+=1;shapeY[4]+=1;shapeY[5]+=1;shapeY[6]+=1;
				}else if ((shapeY[2]==GridBlock.ROW)||(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[2], (shapeY[2]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[4], (shapeY[4]+1))).isBottomBlock())){	//形态1的到底的条件
					canMove=false;
					bottomBlock();
				}
				break;
			case 2:
				if ((shapeY[5]!=GridBlock.ROW)&&(GridBlock.xyList.get(shapeXYToInt(shapeX[4], (shapeY[4]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[4], (shapeY[4]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[5], (shapeY[5]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[5], (shapeY[5]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[6], (shapeY[6]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[6], (shapeY[6]+1))).isDraw()==false)) {	//形态2的下落条件和移动
					shapeY[0]+=1;shapeY[1]+=1;shapeY[2]+=1;shapeY[3]+=1;shapeY[4]+=1;shapeY[5]+=1;shapeY[6]+=1;
				}else if ((shapeY[5]==GridBlock.ROW)||(GridBlock.xyList.get(shapeXYToInt(shapeX[4], (shapeY[4]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[5], (shapeY[5]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[6], (shapeY[6]+1))).isBottomBlock())) {	//形态2的到底条件
					canMove=false;
					bottomBlock();
				}
				break;
			case 3:	
				if ((shapeY[3]!=GridBlock.ROW)&&(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[6], (shapeY[6]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[6], (shapeY[6]+1))).isDraw()==false)) {	//形态3的下落条件和移动
					shapeY[0]+=1;shapeY[1]+=1;shapeY[2]+=1;shapeY[3]+=1;shapeY[4]+=1;shapeY[5]+=1;shapeY[6]+=1;
				}else if ((shapeY[3]==GridBlock.ROW)||(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[3], (shapeY[3]+1))).isBottomBlock())||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[6], (shapeY[6]+1))).isBottomBlock())) {	//形态3的到底条件
					canMove=false;
					bottomBlock();
				}
				break;
			case 4:
				if ((shapeY[1]!=GridBlock.ROW)&&(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[5], (shapeY[5]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[5], (shapeY[5]+1))).isDraw()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isBottomBlock()==false)&&
						(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isDraw()==false)) {	//形态4的下落条件和移动
					shapeY[0]+=1;shapeY[1]+=1;shapeY[2]+=1;shapeY[3]+=1;shapeY[4]+=1;shapeY[5]+=1;shapeY[6]+=1;
				}else if ((shapeY[1]==GridBlock.ROW)||(GridBlock.xyList.get(shapeXYToInt(shapeX[1], (shapeY[1]+1))).isBottomBlock()==false)||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[5], (shapeY[5]+1))).isBottomBlock()==false)||
						(GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isBottomBlock()==false)){	//形态4的到底条件
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
	 * 凹形方块的旋转
	 */
	@Override
	public void rotate() {
		notDraw();
		if (canMove) {
			switch (whichTime) {
			case 0:	//形态0/4旋转到形态1
				fourToOne();
				break;
			case 1:	//形态1旋转到形态2
				oneToTwo();
				break;
			case 2:	//形态2旋转到形态3
				twoToThree();
				break;
			case 3:	//形态3旋转到形态4
				threeToFour();
				break;
			case 4:	//形态4旋转到形态1
				fourToOne();
				break;
			default:
				break;
			}
		}
		setInitImage();
		canDraw();
	}
	/**
	 * 凹方块形态1
	 */
	private void fourToOne(){
		if ((GridBlock.xyList.get(shapeXYToInt((shapeX[0]-1), shapeY[0])).isDraw()==false)) {	//旋转条件
			whichTime=1;
			shapeX[0]-=2;
			shapeY[1]-=2;
			shapeX[2]-=1;shapeY[2]+=1;
			shapeX[3]+=1;shapeY[3]-=1;
			shapeY[4]+=2;
			shapeX[5]+=1;shapeY[5]+=1;
			shapeX[6]+=2;
		}
	}
	/**
	 * 凹方块形态2
	 */
	private void oneToTwo(){
		if ((GridBlock.xyList.get(shapeXYToInt(shapeX[0], shapeY[0]-1)).isDraw()==false)) {	//旋转条件
			whichTime=2;
			shapeY[0]-=2;
			shapeX[1]+=2;
			shapeX[2]-=1;shapeY[2]-=1;
			shapeX[3]+=1;shapeY[3]+=1;
			shapeX[4]-=2;
			shapeX[5]-=1;shapeY[5]+=1;
			shapeY[6]+=2;
		}
	}
	/**
	 * 凹方块形态3
	 */
	private void twoToThree(){
		if ((GridBlock.xyList.get(shapeXYToInt((shapeX[0]+1), shapeY[0])).isDraw()==false)) {	//旋转条件
			whichTime=3;
			shapeX[0]+=2;
			shapeY[1]+=2;
			shapeX[2]+=1;shapeY[2]-=1;
			shapeX[3]-=1;shapeY[3]+=1;
			shapeY[4]-=2;
			shapeX[5]-=1;shapeY[5]-=1;
			shapeX[6]-=2;
		}
	}
	/**
	 * 凹方块形态4
	 */
	private void threeToFour(){
		if ((GridBlock.xyList.get(shapeXYToInt(shapeX[0], (shapeY[0]+1))).isDraw()==false)) {	//旋转条件
			whichTime=4;
			shapeY[0]+=2;
			shapeX[1]-=2;
			shapeX[2]+=1;shapeY[2]+=1;
			shapeX[3]-=1;shapeY[3]-=1;
			shapeX[4]+=2;
			shapeX[5]+=1;shapeY[5]-=1;
			shapeY[6]-=2;
		}
	}
	/**
	 * 初始化凹形方块的图片
	 */
	@Override
	protected void setInitImage() {
		for (int i = 0; i < 7; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setImage(shapeImage);
		}
	}
	/**
	 * 凹形方块移动前不可见
	 */
	@Override
	protected void notDraw() {
		for (int i = 0; i < 7; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setDraw(false);
		}
	}
	/**
	 * 凹形方块移动后可见
	 */
	@Override
	protected void canDraw() {
		for (int i = 0; i < 7; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setDraw(true);
		}
	}
	/**
	 * 凹形方块到底后设为底部方块
	 */
	@Override
	protected void bottomBlock() {
		for (int i = 0; i < 7; i++) {
			GridBlock.xyList.get(shapeXYToInt(shapeX[i], shapeY[i])).setBottomBlock(true);
		}
	}
	/**
	 * 凹形方块每次出现判断游戏是否结束
	 */
	@Override
	public boolean initGameOver() {
		if (GridBlock.xyList.get(shapeXYToInt(shapeX[0], shapeY[0])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[1], shapeY[1])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[2], shapeY[2])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[3], shapeY[3])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[4], shapeY[4])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[5], shapeY[5])).isDraw()||
				GridBlock.xyList.get(shapeXYToInt(shapeX[6], shapeY[6])).isDraw()) {
			return true;
		}else {
			return false;
		}
	}
}
