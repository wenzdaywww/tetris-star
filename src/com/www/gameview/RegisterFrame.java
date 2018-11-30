package com.www.gameview;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
	/**
	 * 玩家注册窗体类
	 * @author wWw
	 *
	 */
@SuppressWarnings("serial")
public class RegisterFrame extends JFrame{
	/** @see 取消按钮*/
	public MyButtonUI btnExit;	
	/** @see 注册按钮*/
	public MyButtonUI btnRegister;	
	/** @see 注册窗体的容器*/
	public BackgroupPanel loginPanel;	
	/** @see 输入框*/
	public JTextField gameUserTextField;	
	/** @see 鼠标进入按钮时按钮的颜色*/
	private Color fouceColor=new Color(27, 120, 255, 255);
	/** @see 按钮正常状态是的颜色*/
	private Color normalColor=new Color(27, 120, 255, 120);
	/**
	 * 各组件初始化
	 */
	public RegisterFrame() {
		setSize(300, 200);
		setTitle("玩家注册");
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);
		loginPanel=new BackgroupPanel();
		loginPanel.setVerifyInputWhenFocusTarget(false);
		setContentPane(loginPanel);
		JLabel lblNewLabel = new JLabel("俄罗斯方块玩家注册");
		lblNewLabel.setFont(new Font("幼圆", Font.PLAIN, 20));
		lblNewLabel.setForeground(MainFrame.FontColor);
		lblNewLabel.setBounds(60, 23, 198, 33);
		getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("玩家：");
		lblNewLabel_1.setFont(new Font("幼圆", Font.PLAIN, 15));
		lblNewLabel_1.setForeground(MainFrame.FontColor);
		lblNewLabel_1.setBounds(10, 80, 60, 33);
		getContentPane().add(lblNewLabel_1);
		gameUserTextField = new JTextField();
		gameUserTextField.setOpaque(false);
		gameUserTextField.setToolTipText("请输入小于5个长度的字符");
		gameUserTextField.setFont(new Font("幼圆", Font.PLAIN, 17));
		gameUserTextField.setBounds(60, 87, 185, 21);
		gameUserTextField.setForeground(MainFrame.FontColor);
		getContentPane().add(gameUserTextField);
		gameUserTextField.setColumns(10);
		btnRegister = new MyButtonUI();
		btnRegister.setBtnText("进入游戏");
		btnRegister.setNormalColor(Color.white , 25, 16, normalColor, 200);
		btnRegister.setFoucesdColor(fouceColor);
		btnRegister.setBounds(30, 150, 110, 25);
		btnRegister.setOpaque(false);
		getContentPane().add(btnRegister);
		btnExit = new MyButtonUI();
		btnExit.setBtnText("退出");
		btnExit.setNormalColor(Color.white , 40, 16, normalColor, 200);
		btnExit.setFoucesdColor(fouceColor);
		btnExit.setOpaque(false);
		btnExit.setBounds(177, 150, 110, 25);
		getContentPane().add(btnExit);
		JLabel lblNewLabel_2 = new JLabel("可以不注册，但无法上传分数");
		lblNewLabel_2.setForeground(MainFrame.FontColor);
		lblNewLabel_2.setFont(new Font("幼圆", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(59, 118, 185, 15);
		loginPanel.add(lblNewLabel_2);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/user.png"));
	}
}
