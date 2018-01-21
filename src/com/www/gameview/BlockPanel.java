package com.www.gameview;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import javax.swing.JPanel;
import com.www.gamemodel.GridBlock;
/**
 * 方块下落区域类
 * @author wWw
 *
 */
@SuppressWarnings("serial")
public class BlockPanel extends JPanel{
	/**
	 *初始化下落区域
	 */
	public BlockPanel() {
		setSize(GridBlock.COL*GridBlock.SIZE, GridBlock.ROW*GridBlock.SIZE);
		setOpaque(false);
	}
	/**
	 * 方块下落区域重绘
	 */
	@Override
	protected void paintComponent(Graphics gb) {
		for (int i = 1; i <= GridBlock.xyList.size(); i++) {
			if (GridBlock.xyList.get(i).isDraw()) {
				Image image=Toolkit.getDefaultToolkit().getImage(GridBlock.xyList.get(i).getImage());
				gb.drawImage(image, (GridBlock.xyList.get(i).getX()-1)*GridBlock.SIZE, (GridBlock.xyList.get(i).getY()-1)*GridBlock.SIZE, this);
			}
		}
		if (GridBlock.helpLine) {	//绘制网格线
			gb.setColor(new Color(255, 255, 255, 150));
			gb.drawLine(GridBlock.COL*GridBlock.SIZE-1, 0, GridBlock.COL*GridBlock.SIZE-1, GridBlock.ROW*GridBlock.SIZE);
			gb.drawRect(0, 0, GridBlock.COL*GridBlock.SIZE-1, GridBlock.ROW*GridBlock.SIZE-1);
			for (int x = 0; x < GridBlock.COL; x++) {
				gb.drawLine(x*GridBlock.SIZE, 0, x*GridBlock.SIZE, GridBlock.ROW*GridBlock.SIZE);
			}
			for (int y = 0; y < GridBlock.ROW; y++) {
				gb.drawLine(0, y*GridBlock.SIZE, GridBlock.COL*GridBlock.SIZE, y*GridBlock.SIZE);
			}
		}
	}
}
