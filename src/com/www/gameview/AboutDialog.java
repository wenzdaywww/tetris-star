package com.www.gameview;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JDialog;
	/**
	 * 关于对话框类
	 * @author wWw
	 */
@SuppressWarnings("serial")
public class AboutDialog extends JDialog {
	/**
	 * 关于对话框组件的初始化
	 */
	public AboutDialog() {
		setTitle("关于");
		setSize(320, 180);
		setVisible(false);
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/about.png"));
		setLocationRelativeTo(null);
		AboutPanel aboutPane=new AboutPanel();
		setContentPane(aboutPane);
		getContentPane().setLayout(null);
		JLabel lblNewLabel = new JLabel("俄罗斯星星");
		lblNewLabel.setFont(new Font("幼圆", Font.PLAIN, 13));
		lblNewLabel.setForeground(MainFrame.FontColor);
		lblNewLabel.setBounds(158, 10, 116, 15);
		getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("版本:  V1.0");
		lblNewLabel_1.setFont(new Font("幼圆", Font.PLAIN, 13));
		lblNewLabel_1.setForeground(MainFrame.FontColor);
		lblNewLabel_1.setBounds(158, 40, 116, 15);
		getContentPane().add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("版权所有:  2016 WWW");
		lblNewLabel_2.setFont(new Font("幼圆", Font.PLAIN, 13));
		lblNewLabel_2.setForeground(MainFrame.FontColor);
		lblNewLabel_2.setBounds(158, 70, 130, 15);
		getContentPane().add(lblNewLabel_2);
		JLabel lblNewLabel_3 = new JLabel("作者:  吴伟文");
		lblNewLabel_3.setFont(new Font("幼圆", Font.PLAIN, 13));
		lblNewLabel_3.setForeground(MainFrame.FontColor);
		lblNewLabel_3.setBounds(158, 100, 116, 15);
		getContentPane().add(lblNewLabel_3);
	}
	/**
	 * 关于对话框中图片的载入
	 * @author wWw
	 *
	 */
	private class AboutPanel extends BackgroupPanel{
		
		public AboutPanel() {
			setLayout(null);
		}
		/**
		 * 重绘容器图片
		 */
		@Override
		protected void paintComponent(Graphics g) {
			Image image=Toolkit.getDefaultToolkit().getImage(BackgroupPanel.backgroupImage);
			g.drawImage(image, 0, 0, this);
			Image image1=Toolkit.getDefaultToolkit().getImage("image/www.png");
			g.drawImage(image1, 0, 20, this);
		}
	}
}
