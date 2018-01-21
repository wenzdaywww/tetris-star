package com.www.gameview;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JDialog;
	/**
	 * 消息提示框类
	 * @author wWw
	 *
	 */
@SuppressWarnings("serial")
public class MessageDialog extends JDialog {
	/** @see 确定按钮*/
	public MyButtonUI btnOk;	
	/** @see 消息标签*/
	public JLabel labelMessage;	
	/** @see 取消按钮*/
	public MyButtonUI btnCancel;	
	/** @see 鼠标进入按钮时按钮的颜色*/
	private Color fouceColor=new Color(75, 209, 238, 255);	
	/** @see 按钮正常状态是的颜色*/
	private Color normalColor=new Color(75, 209, 238, 120);	
	/**
	 * 初始化各组件
	 */
	public MessageDialog() {
		setSize(300, 120);
		setVisible(false);
		setModal(true);
		setTitle("退出提示");
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/message.png"));
		setLocationRelativeTo(null);
		BackgroupPanel uploadPanel=new BackgroupPanel();
		labelMessage=new JLabel();
		labelMessage.setFont(new Font("幼圆", Font.PLAIN, 15));
		labelMessage.setForeground(MainFrame.FontColor);
		labelMessage.setText("确认退出？？？");
		labelMessage.setBounds(15, 12, 250, 25);
		uploadPanel.add(labelMessage);
		btnOk=new MyButtonUI();
		btnOk.setBounds(90, 50, 80, 25);;
		btnOk.setBtnText("确定");
		btnOk.setOpaque(false);
		btnOk.setNormalColor(Color.white, 25, 16, normalColor, 100);
		btnOk.setFoucesdColor(fouceColor);
		uploadPanel.add(btnOk);
		btnCancel=new MyButtonUI();
		btnCancel.setBounds(190, 50, 80, 25);;
		btnCancel.setBtnText("取消");
		btnCancel.setOpaque(false);
		btnCancel.setNormalColor(Color.white, 25, 16, normalColor, 100);
		btnCancel.setFoucesdColor(fouceColor);
		uploadPanel.add(btnCancel);
		setContentPane(uploadPanel);
	}
}
