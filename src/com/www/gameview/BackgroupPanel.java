package com.www.gameview;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import javax.swing.JPanel;
	/**
	 * ¸÷´°Ìå´ø±³¾°µÄÈÝÆ÷
	 * @author wWw
	 *
	 */
@SuppressWarnings("serial")
public class BackgroupPanel extends JPanel {
	public static String backgroupImage="image/style1.jpg";
	public BackgroupPanel() {
		setLayout(null);
	}
	/**
	 * ÖØ»æÌî³ä±³¾°Í¼Æ¬
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Image image=Toolkit.getDefaultToolkit().getImage(backgroupImage);
		g.drawImage(image, 0, 0, this);
	}
}
