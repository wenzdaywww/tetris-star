package com.www.gameview;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JMenu;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import java.awt.event.KeyEvent;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import com.www.gamemodel.GridBlock;
import javax.swing.JRadioButtonMenuItem;
	/**
	 * 主窗体类
	 * @author wWw
	 *
	 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	/** @see 游戏菜单*/
	public JMenu gameMenu;	
	/** @see 帮助菜单*/
	public JMenu helpMenu;	
	/** @see 标签向上*/
	public JLabel labelUp;	
	/** @see 向左标签*/
	public JLabel labelLeft;	
	/** @see 向下标签*/
	public JLabel labelDown;	
	/** @see 自定义模式菜单项*/
	public JMenuItem setItem;	
	/** @see 菜单条*/
	private JMenuBar menuBar;	
	/** @see 向右标签*/
	public JLabel labelRight;	
	/** @see 超神榜菜单项*/
	public JMenuItem godItem;	
	/** @see 退出游戏菜单项*/
	public JMenuItem exitItem;	
	/** @see 玩家注册菜单项*/
	public JMenuItem registerItem;	
	/** @see 分数标签*/
	private JLabel labelScore; 	
	/** @see 当前模式标签*/
	private JLabel labelLevel;	
	/** @see 关于菜单项*/
	public JMenuItem aboutItem;	
	/** @see 自定义对话框*/
	public DiyDialog diyDialog;	
	/** @see 超神榜对话框*/
	public GodDialog godDialog;	
	/** @see 暂停/继续按钮*/
	public MyButtonUI btnPause;	
	/** @see 游戏说明菜单选项*/
	public JMenuItem explainItem;
	/** @see 游戏下落区域容器*/
	public BlockPanel blockPanel;
	/** @see 重新开始按钮*/
	public MyButtonUI btnRestart;
	/** @see 下落加速按钮*/
	public MyButtonUI btnSpeedUp;
	/** @see 下一个方块标签*/
	public JLabel labelNextShape;
	/** @see 左边容器*/
	private JPanel infomationPanel;
	/** @see 关于对话框*/
	public AboutDialog aboutDialog;
	/** @see 下落减速按钮*/
	public MyButtonUI btnSpeedDown;
	/** @see 主容器*/
	public BackgroupPanel mainPanel;
	/** @see 更换方块按钮*/
	public MyButtonUI btnChangeNext;
	/** @see 背景音乐单选框*/
	public JRadioButton radioBtnMusic;
	/** @see 玩家注册窗体*/
	public RegisterFrame registerFrame;
	/** @see 游戏说明对话框*/
	public ExplainDialog explainDialog;
	/** @see 消息提示对话框*/
	public MessageDialog messageDialog;
	/** @see 方块预览容器*/
	public NextShowPanel nextShowPanel;
	/** @see 按钮音效单选按钮*/
	public JRadioButton radioBtnSounds;
	/** @see 单人初级模式菜单项*/
	public JRadioButtonMenuItem singleSimpleRdbItem;
	/** @see 单人中级模式菜单项*/
	public JRadioButtonMenuItem singleMiddleRdbItem;
	/** @see 单人高级模式菜单项*/
	public JRadioButtonMenuItem singleSeniorRdbItem;
	/** @see 双人初级模式菜单项*/
	public JRadioButtonMenuItem doubleSimpleRdbItem;
	/** @see 双人中级模式菜单项*/
	public JRadioButtonMenuItem doubleMiddleRdbItem;
	/** @see 双人高级模式菜单项*/
	public JRadioButtonMenuItem doubleSeniorRdbItem;
	/** @see 所有文本字体颜色*/
	public static final Color FontColor=Color.blue;	
	/** @see 鼠标进入按钮时按钮的颜色*/
	private Color fouceColor=new Color(30, 121, 192, 255);
	/** @see 按钮正常显示的颜色*/
	private Color normalColor=new Color(30, 121, 192, 120);
	/**
	 * 初始化各组件、方块形状
	 */
	public MainFrame() {
		initComponent();
	}
	/**
	 * 设置当前得分
	 * @param score 当前得分
	 */
	public void setLabelScore(int score){
		labelScore.setText("得   分："+Integer.toString(score));
	}
	/**
	 * 设置当前等级
	 * @param level 当前等级
	 */
	public void setLabelLevel(String level){
		labelLevel.setText("当前模式："+level);
	}
	/**
	 * 主窗体组件初始化
	 */
	private void initComponent(){
		setFocusable(true);
		setVisible(false);
		setTitle("TetrisStar");
		setResizable(false);
		setSize(GridBlock.COL*GridBlock.SIZE+GridBlock.SIZE*8, GridBlock.ROW*GridBlock.SIZE+51);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);	//设置窗体关闭按钮无动作
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/tetris.png"));
		mainPanel=new BackgroupPanel();
		mainPanel.setLayout(null);
		setContentPane(mainPanel);
		//菜单栏初始化
		menuBar =new JMenuBar();
		menuBar.setBounds(0, 0, GridBlock.COL*GridBlock.SIZE+GridBlock.SIZE*8, 23);
		mainPanel.add(menuBar);
		gameMenu=new JMenu("游戏（G）");
		gameMenu.setMnemonic(KeyEvent.VK_G);
		gameMenu.setToolTipText("快捷键：Alt+G");
		menuBar.add(gameMenu);
		//单人菜单
		JMenu startSingleMenu=new JMenu("开始单人游戏");
		startSingleMenu.setIcon(new ImageIcon("image/start.png"));
		singleSimpleRdbItem=new JRadioButtonMenuItem("初级模式");
		singleSimpleRdbItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));//设置快捷键为Ctrl+S
		singleSimpleRdbItem.setToolTipText("该模式方块7中，下落速度500ms");
		singleMiddleRdbItem=new JRadioButtonMenuItem("中级模式");
		singleMiddleRdbItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.CTRL_MASK));//设置快捷键为Ctrl+M
		singleMiddleRdbItem.setToolTipText("该模式方块8中，下落速度300ms");
		singleSeniorRdbItem=new JRadioButtonMenuItem("高级模式");
		singleSeniorRdbItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));//设置快捷键为Ctrl+H
		singleSeniorRdbItem.setToolTipText("该模式方块9中，下落速度100ms");
		ButtonGroup buttonGroup=new ButtonGroup();
		buttonGroup.add(singleSimpleRdbItem);
		buttonGroup.add(singleMiddleRdbItem);
		buttonGroup.add(singleSeniorRdbItem);
		startSingleMenu.add(singleSimpleRdbItem);
		startSingleMenu.add(singleMiddleRdbItem);
		startSingleMenu.add(singleSeniorRdbItem);
		gameMenu.add(startSingleMenu);
		//双人菜单
		JMenu startDoubleMenu=new JMenu("开始双人游戏");
		startDoubleMenu.setIcon(new ImageIcon("image/start.png"));
		doubleSimpleRdbItem=new JRadioButtonMenuItem("初级模式");
		doubleSimpleRdbItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.SHIFT_MASK));//设置快捷键为Alt+S
		doubleSimpleRdbItem.setToolTipText("该模式方块7中，下落速度500ms");
		doubleMiddleRdbItem=new JRadioButtonMenuItem("中级模式");
		doubleMiddleRdbItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,ActionEvent.SHIFT_MASK));//设置快捷键为Alt+M
		doubleMiddleRdbItem.setToolTipText("该模式方块8中，下落速度300ms");
		doubleSeniorRdbItem=new JRadioButtonMenuItem("高级模式");
		doubleSeniorRdbItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.SHIFT_MASK));//设置快捷键为Alt+H
		doubleSeniorRdbItem.setToolTipText("该模式方块9中，下落速度100ms");
		ButtonGroup buttonDoubleGroup=new ButtonGroup();
		buttonDoubleGroup.add(doubleSimpleRdbItem);
		buttonDoubleGroup.add(doubleMiddleRdbItem);
		buttonDoubleGroup.add(doubleSeniorRdbItem);
		startDoubleMenu.add(doubleSimpleRdbItem);
		startDoubleMenu.add(doubleMiddleRdbItem);
		startDoubleMenu.add(doubleSeniorRdbItem);
		gameMenu.add(startDoubleMenu);
		setItem=new JMenuItem("自定义模式");
		setItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));//设置快捷键为Ctrl+D
		setItem.setIcon(new ImageIcon("image/set.png"));
		godItem=new JMenuItem("超神榜");
		godItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));//设置快捷键为Ctrl+G
		godItem.setIcon(new ImageIcon("image/god.png"));
		registerItem=new JMenuItem("玩家注册");
		registerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));//设置快捷键为Ctrl+U
		registerItem.setIcon(new ImageIcon("image/user.png"));
		exitItem=new JMenuItem("退出");
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));//设置快捷键为Ctrl+E
		exitItem.setIcon(new ImageIcon("image/exit.png"));
		gameMenu.add(godItem);
		gameMenu.add(registerItem);
		gameMenu.add(setItem);
		gameMenu.add(exitItem);
		helpMenu=new JMenu("帮助（H）");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		helpMenu.setToolTipText("快捷键：Alt+H");
		aboutItem=new JMenuItem("关于");
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));//设置快捷键为Ctrl+A
		aboutItem.setIcon(new ImageIcon("image/about.png"));
		explainItem=new JMenuItem("游戏说明");
		explainItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));//设置快捷键为Alt+E
		explainItem.setIcon(new ImageIcon("image/question.png"));
		menuBar.add(helpMenu);
		helpMenu.add(aboutItem);
		helpMenu.add(explainItem);
		//添加下落区域
		blockPanel=new BlockPanel();
		blockPanel.setLocation(0, 23);
		mainPanel.add(blockPanel);
		//显示信息面板
		infomationPanel=new JPanel();
		infomationPanel.setLayout(null);
		infomationPanel.setOpaque(false);
		infomationPanel.setSize(GridBlock.SIZE*9,GridBlock.SIZE*GridBlock.ROW);
		infomationPanel.setLocation(GridBlock.SIZE*GridBlock.COL, 23);
		nextShowPanel=new NextShowPanel();
		nextShowPanel.setLocation(50, 30);
		infomationPanel.add(nextShowPanel);
		mainPanel.add(infomationPanel);
		//创建对话框对象
		aboutDialog=new AboutDialog();
		diyDialog=new DiyDialog();
		godDialog=new GodDialog();
		registerFrame=new RegisterFrame();
		messageDialog=new MessageDialog();
		explainDialog=new ExplainDialog();
		//标签组件
		labelNextShape = new JLabel("下一块");
		labelNextShape.setFont(new Font("幼圆", Font.PLAIN, 13));
		labelNextShape.setForeground(FontColor);
		labelNextShape.setBounds(50, 10, 80, 15);
		infomationPanel.add(labelNextShape);
		labelScore = new JLabel("得   分：0");
		labelScore.setFont(new Font("幼圆", Font.PLAIN, 15));
		labelScore.setForeground(FontColor);
		labelScore.setBounds(30, 250, 130, 15);
		infomationPanel.add(labelScore);
		labelLevel = new JLabel("当前模式：");
		labelLevel.setFont(new Font("幼圆", Font.PLAIN, 15));
		labelLevel.setForeground(FontColor);
		labelLevel.setBounds(30, 280, 130, 15);
		infomationPanel.add(labelLevel);
		//更换方块按钮
		btnChangeNext = new MyButtonUI();
		btnChangeNext.setBtnText("更换方块（Q）");
		btnChangeNext.setNormalColor(Color.white, 14, 16, normalColor, 200);
		btnChangeNext.setFoucesdColor(fouceColor);
		btnChangeNext.setOpaque(false);
		btnChangeNext.setVisible(false);
		btnChangeNext.setBounds(25, 100, 110, 23);
		btnChangeNext.setFocusable(false);
		infomationPanel.add(btnChangeNext);
		//暂停/继续按钮
		btnPause = new MyButtonUI();
		btnPause.setBtnText("暂停（空格）");
		btnPause.setNormalColor(Color.white, 14, 16, normalColor, 200);
		btnPause.setFoucesdColor(fouceColor);
		btnPause.setOpaque(false);
		btnPause.setFocusable(false);
		btnPause.setVisible(false);
		btnPause.setBounds(25, 305, 110, 23);
		infomationPanel.add(btnPause);
		//重新开始按钮
		btnRestart = new MyButtonUI();
		btnRestart.setBtnText("重新开始（T）");
		btnRestart.setToolTipText("重新开始已设置的模式");
		btnRestart.setNormalColor(Color.white, 14, 16, normalColor, 200);
		btnRestart.setFoucesdColor(fouceColor);
		btnRestart.setOpaque(false);
		btnRestart.setVisible(false);
		btnRestart.setFocusable(false);
		btnRestart.setBounds(25, 338, 110, 23);
		infomationPanel.add(btnRestart);
		//下落加速按钮
		btnSpeedUp = new MyButtonUI();
		btnSpeedUp.setBtnText("下落加速（E）");
		btnSpeedUp.setNormalColor(Color.white, 14, 16, normalColor, 200);
		btnSpeedUp.setFoucesdColor(fouceColor);
		btnSpeedUp.setOpaque(false);
		btnSpeedUp.setVisible(false);
		btnSpeedUp.setFocusable(false);
		btnSpeedUp.setBounds(25, 371, 110, 23);
		infomationPanel.add(btnSpeedUp);
		//下落减速按钮
		btnSpeedDown = new MyButtonUI();
		btnSpeedDown.setBtnText("下落减速（R）");
		btnSpeedDown.setNormalColor(Color.white, 14, 16, normalColor, 200);
		btnSpeedDown.setFoucesdColor(fouceColor);
		btnSpeedDown.setOpaque(false);
		btnSpeedDown.setVisible(false);
		btnSpeedDown.setFocusable(false);
		btnSpeedDown.setBounds(25, 404, 110, 23);
		infomationPanel.add(btnSpeedDown);
		//音效单选按钮
		radioBtnSounds=new JRadioButton(new ImageIcon("image/soundson.png"),true);
		radioBtnSounds.setOpaque(false);
		radioBtnSounds.setFocusable(false);
		radioBtnSounds.setSelected(true);
		radioBtnSounds.setBounds(10, 210, 25, 20);
		infomationPanel.add(radioBtnSounds);
		//背景音乐单选按钮
		radioBtnMusic=new JRadioButton(new ImageIcon("image/musicoff.png"),true);
		radioBtnMusic.setOpaque(false);
		radioBtnMusic.setFocusable(false);
		radioBtnMusic.setSelected(false);
		radioBtnMusic.setBounds(40, 210, 25, 20);
		infomationPanel.add(radioBtnMusic);
		JPanel operationPanel = new JPanel();
		operationPanel.setOpaque(false);
		operationPanel.setLayout(new GridLayout(3, 3));
		operationPanel.setBounds(45, 133, 102, 102);
		//方向标签
		labelUp=new JLabel(new ImageIcon("image/up.png"));
		labelDown=new JLabel(new ImageIcon("image/down.png"));
		labelLeft=new JLabel(new ImageIcon("image/left.png"));
		labelRight=new JLabel(new ImageIcon("image/right.png"));
		JLabel label1=new JLabel();
		label1.setOpaque(false);
		JLabel label3=new JLabel();
		label3.setOpaque(false);
		JLabel label5=new JLabel();
		label5.setOpaque(false);
		JLabel label7=new JLabel();
		label7.setOpaque(false);
		JLabel label9=new JLabel();
		label9.setOpaque(false);
		operationPanel.add(label1);
		operationPanel.add(labelUp);
		operationPanel.add(label3);
		operationPanel.add(labelLeft);
		operationPanel.add(label5);
		operationPanel.add(labelRight);
		operationPanel.add(label7);
		operationPanel.add(labelDown);
		operationPanel.add(label9);
		infomationPanel.add(operationPanel);
	}
	/**
	 * 改变窗体大小和网格数
	 */
	public void changFrame(){
		setSize(GridBlock.COL*GridBlock.SIZE+GridBlock.SIZE*8, GridBlock.ROW*GridBlock.SIZE+51);
		blockPanel.setSize(GridBlock.COL*GridBlock.SIZE, GridBlock.ROW*GridBlock.SIZE);
		menuBar.setBounds(0, 0, GridBlock.COL*GridBlock.SIZE+GridBlock.SIZE*8, 23);
		infomationPanel.setLocation(GridBlock.SIZE*GridBlock.COL, 23);
		this.repaint();
	}
}
