package com.www.gameview;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JPanel;
import com.www.gamemodel.DrawArea;
import com.www.gamemodel.GridBlock;
/**
 * 方块预览区域类
 * @author wWw
 *
 */
@SuppressWarnings("serial")
public class NextShowPanel extends JPanel {
	/** @see 预览区域方块集合*/
	private HashMap<Integer, DrawArea> nextxyList=new HashMap<Integer, DrawArea>();	
	/**
	 * 初始化设置
	 */
	public NextShowPanel() {
		setBounds(GridBlock.SIZE*2, GridBlock.SIZE*2, GridBlock.SIZE*4,GridBlock.SIZE*3);
		setOpaque(false);
		setBackground(null);
		clearNextShowPanel();
	}
	public void showNextShape(int shapeNum,String level){
		switch (shapeNum) {
		case 0:
			showLeftLShape(GridBlock.shapeImage);
			break;
		case 1:
			showRightLShape(GridBlock.shapeImage);
			break;
		case 2:
			showTShape(GridBlock.shapeImage);
			break;
		case 3:
			showLeftZShape(GridBlock.shapeImage);
			break;
		case 4:
			showRightZShape(GridBlock.shapeImage);
			break;
		case 5:
			showSquareShape(GridBlock.shapeImage);
			break;
		case 6:
			showLineShape(GridBlock.shapeImage);
			break;
		case 7:
			if (level.equals("初级模式")) {
				showBombShape("image/bomb.png");
			}else {
				showConcaveShape(GridBlock.shapeImage);
			}
			break;
		case 8:
			if (level.equals("中级模式")) {
				showBombShape("image/bomb.png");
			}else {
				showCrossShape(GridBlock.shapeImage);
			}

			break;
		case 9:
			showBombShape("image/bomb.png");
			break;
		default:
			break;
		}
	}
	/**
	 * 清除预览区
	 */
	private void clearNextShowPanel() {
		clearNextShow();
		this.repaint();
	}
	/**
	 * 重置方块集合
	 */
	private void clearNextShow(){
		for (int i = 1; i <= 12; i++) {
			nextxyList.put(i, new DrawArea(intToShapeX(i), intToShapeY(i), false, "",false));
		}
	}
	/**
	 * 显示凹形方块
	 * @param shapeImage 方块图片
	 */
	private void showConcaveShape(String shapeImage){
		clearNextShow();
		nextxyList.get(shapeXYToInt(1, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 3)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 3)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(1, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(1, 3)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(3, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(3, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(3, 3)).setImage(shapeImage);
		this.repaint();
	}
	/**
	 * 显示十字形方块
	 * @param shapeImage 方块图片
	 */
	private void showCrossShape(String shapeImage){
		clearNextShow();
		nextxyList.get(shapeXYToInt(2, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 3)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(1, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 3)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(3, 2)).setImage(shapeImage);
		this.repaint();
	}
	/**
	 * 显示左L方块
	 * @param shapeImage 方块图片
	 */
	private void showLeftLShape(String shapeImage){
		clearNextShow();
		nextxyList.get(shapeXYToInt(1, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(1, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(3, 2)).setImage(shapeImage);
		this.repaint();
	}
	/**
	 * 显示左Z方块
	 * @param shapeImage 方块图片
	 */
	private void showLeftZShape(String shapeImage){
		clearNextShow();
		nextxyList.get(shapeXYToInt(1, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(3, 2)).setImage(shapeImage);
		this.repaint();
	}
	/**
	 * 显示直线形方块
	 * @param shapeImage 方块图片
	 */
	private void showLineShape(String shapeImage){
		clearNextShow();
		nextxyList.get(shapeXYToInt(1, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(4, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(3, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(4, 1)).setImage(shapeImage);
		this.repaint();
	}
	/**
	 * 显示右L方块
	 * @param shapeImage 方块图片
	 */
	private void showRightLShape(String shapeImage){
		clearNextShow();
		nextxyList.get(shapeXYToInt(3, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(1, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(3, 2)).setImage(shapeImage);
		this.repaint();
	}
	/**
	 * 显示右Z方块
	 * @param shapeImage 方块图片
	 */
	private void showRightZShape(String shapeImage){
		clearNextShow();
		nextxyList.get(shapeXYToInt(3, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(1, 2)).setImage(shapeImage);
		this.repaint();
	}
	/**
	 * 像是正方形方块
	 * @param shapeImage 方块图片
	 */
	private void showSquareShape(String shapeImage){
		clearNextShow();
		nextxyList.get(shapeXYToInt(1, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(1, 2)).setImage(shapeImage);
		this.repaint();
	}
	/**
	 * 显示T形方块
	 * @param shapeImage 方块图片
	 */
	private void showTShape(String shapeImage){
		clearNextShow();
		nextxyList.get(shapeXYToInt(2, 1)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(1, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(3, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 1)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(2, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(1, 2)).setImage(shapeImage);
		nextxyList.get(shapeXYToInt(3, 2)).setImage(shapeImage);
		this.repaint();
	}
	/**
	 * 显示炸弹
	 * @param shapeImage 方块图片
	 */
	private void showBombShape(String shapeImage){
		clearNextShow();
		nextxyList.get(shapeXYToInt(2, 2)).setDraw(true);
		nextxyList.get(shapeXYToInt(2, 2)).setImage(shapeImage);
		this.repaint();
	}
	/**
	 * 容器重绘
	 */
	@Override
	protected void paintComponent(Graphics g) {
		for (int i = 1; i <= nextxyList.size(); i++) {
			if (nextxyList.get(i).isDraw()) {
				Image image=Toolkit.getDefaultToolkit().getImage(nextxyList.get(i).getImage());
				g.drawImage(image, (nextxyList.get(i).getX()-1)*GridBlock.SIZE, (nextxyList.get(i).getY()-1)*GridBlock.SIZE, this);
			}
		}
	}
	/**
	 * 方块序号转换成X坐标
	 * @param i 方块X坐标
	 * @return 方块序号
	 */
	private int intToShapeX(int i){
		if ((i%4)==0) {
			return 4;
		}else {
			return i%4;
		}
	}
	/**
	 * 方块序号转换成Y坐标
	 * @param i 方块Y坐标
	 * @return 方块序号
	 */
	private int intToShapeY(int i){
		if ((i%4)==0) {
			return i/4;
		}else {
			return (i/4)+1;
		}
	}
	/**
	 * xy坐标转换成方块序号
	 * @param x 方块X坐标
	 * @param y 方块Y坐标
	 * @return 方块序号
	 */
	private int shapeXYToInt(int x,int y){
		return (x+4*(y-1));
	}
}
