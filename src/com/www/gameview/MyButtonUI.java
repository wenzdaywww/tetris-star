package com.www.gameview;

import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
	/**
	 * 按钮重绘类
	 * @author wWw
	 *
	 */
@SuppressWarnings("serial")
public class MyButtonUI extends JButton {
	/** @see 按钮文本显示的x左边*/
	private int X;
	/** @see 按钮文本显示的y左边*/
	private int Y;
	/** @see 按钮边角的弧度*/
	private int radian;
	/** @see 按钮的文本*/
	private String text;
	/** @see 设置按钮未选中时的颜色和透明度*/
	private Color norColor;
	/** @see 设置按钮选中时的颜色和透明度*/
	private Color fouColor;
	/** @see 设置按钮文本字体颜色*/
	private Color fontColor;
	/** @see 设置按钮文本字体*/
	private Font textFont=new Font("幼圆", Font.PLAIN, 14);
	/** @see 按钮的鼠标事件*/
	private String state = "normal";
	/**
	 * 给按钮添加鼠标监听
	 */
	public MyButtonUI() {
		addMouseListener(new MouseAdapter() {  
			public void mouseEntered(MouseEvent e) { //获取鼠标选中的状态
				state = "focused";  
				repaint();  //该函数是重画组件
			}  
			public void mouseExited(MouseEvent e) {  //获取鼠标未选中的状态
				state = "normal";  
				repaint();  
			}  
		});
	}
	/**
	 * 设置按钮文本
	 * @param text 按钮文本
	 */
	public void setBtnText(String text) {
		this.text=text;
		this.repaint();
	}
	/**
	 * 获取按钮的文本
	 * @return 按钮的文本
	 */
	public String getBtnText() {
		return text;
	}
	/**
	 * 设置文本坐标
	 * @param X 文本X坐标
	 * @param Y 文本Y坐标
	 */
	public void setBtnTextXY(int X,int Y){
		this.X=X;
		this.Y=Y;
		this.repaint();
	}
	/**
	 * 设置按钮弧度
	 * @param radian 按钮弧度
	 */
	public void setRadian(int radian) {
		this.radian = radian;
		this.repaint();
	}
	/**
	 * 按钮正常时的状态
	 * @param fontColor	文本字体颜色
	 * @param X	文本X坐标
	 * @param Y	文本Y坐标
	 * @param norColor 正常状态的颜色
	 * @param radian 按钮弧度
	 */
	public void setNormalColor(Color fontColor,int X,int Y,Color norColor,int radian) {
		this.fontColor=fontColor;
		this.X=X;
		this.Y=Y;
		this.norColor=norColor;
		this.radian=radian;
		this.repaint();
	}
	/**
	 * 设置按钮选中的显示的颜色
	 * @param fouColor 获取焦点的颜色
	 */
	public void setFoucesdColor(Color fouColor) {
		this.fouColor=fouColor;
		this.repaint();
	}
	/**
	 * 设置字体颜色
	 * @param fontColor 字体颜色
	 */
	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
		this.repaint();
	}
	/**
	 * 设置文本字体
	 * @param textFont 文本字体
	 */
	public void setTextFont(Font textFont) {
		this.textFont = textFont;
		this.repaint();
	}
	/**
	 * 按钮重绘
	 */
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//边框去锯齿
		if (state=="normal") {	//获取鼠标选中时的绘制
			g2d.setColor(norColor);
			g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radian, radian);	
		}
		else if (state=="focused") {	//鼠标未选中是的绘制
			g2d.setColor(fouColor);
			g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radian, radian);
		}
		g2d.setFont(textFont);//设置字体和大小
		g2d.setColor(fontColor);//设置字体颜色
		g2d.drawString(text, X, Y);//设置文本和显示的位置
	}
}
