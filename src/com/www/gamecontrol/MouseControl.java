package com.www.gamecontrol;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import com.www.gamemodel.RWScore;
import java.awt.event.ActionEvent;

import com.www.gameview.BackgroupPanel;
import com.www.gameview.MainFrame;
import java.awt.event.WindowEvent;
import com.www.gamemodel.GridBlock;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;
import com.www.gamemodel.SoundModel;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeListener;
import java.awt.event.MouseMotionAdapter;
/**
 * 鼠标监听事件处理类
 * @author wWw
 *
 */
public class MouseControl{
	/**@see 获取鼠标拖拽之前的X坐标*/
	private int mx=0;
	/**@see 获取鼠标拖拽之前的Y坐标*/
	private int my=0;				
	/**@see 注册窗体的X坐标*/
	private int jfx=0;				
	/**@see 注册窗体的Y坐标*/
	private int jfy=0;			
	/**@see 分数保存文本对象*/
	private RWScore rwScore;	
	/**@see 网格方块对象*/
	private GridBlock gridBlock;
	/**@see 主窗体对象*/
	private MainFrame mainFrame;	
	/**@see 声音对象*/
	private SoundModel soundModel;	
	/**@see 鼠标进入组件声音对象*/
	private MouseSound mouseSound;	
	/**@see 定时器对象*/
	private TimerControl timerCtrl;	
	/**
	 * 创建鼠标控制的有参构造方法，传递对象
	 * @param mainFrame	主窗体对象
	 * @param timerCtrl 定时器对象
	 * @param soundModel 声音对象
	 * @param gridBlock 网格方块对象
	 */ 
	public MouseControl(MainFrame mainFrame,TimerControl timerCtrl,SoundModel soundModel,GridBlock gridBlock) {
		this.mainFrame=mainFrame;
		this.gridBlock=gridBlock;
		this.timerCtrl=timerCtrl;
		this.soundModel=soundModel;
		mouseSound=new MouseSound();	//创建鼠标进入组件声音对象
		rwScore=new RWScore();			//创建分数文本对象
		rwScore.scoreRank();			//排序分数文本中的分数
		clickCtrl();					//点击事件处理
	}
	/**
	 * 所有窗体、对话框的所有鼠标事件处理
	 **/
	private void clickCtrl(){
		itemClick();			//主窗体菜单栏的点击处理
		messageClick();			//提示对话框点击处理
		diyDialogClick();		//自定义模式对框点击处理
		componentSound();		//鼠标进入组件音效
		loginFrameClick();		//登入对话框点击处理
		mainFrameBtnClick();	//主窗体按钮标签点击处理
	}
	/**
	 * 所有提示对话框的点击处理。如退出时的提示、游戏结束时上传分数的提示、
	 * 分数上传成功的提示。
	 **/
	private void messageClick(){
		//==========确定按钮的点击处理========
		mainFrame.messageDialog.btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.messageDialog.getTitle().equals("退出提示")) {		//点击退出按钮后提示框的确定处理
					System.exit(0);		//退出游戏
				}else if (mainFrame.messageDialog.getTitle().equals("上传分数")) {	//游戏结束后上传分数提醒的确定处理
					mainFrame.messageDialog.setVisible(false);	//隐藏提示框
					//上传玩家名、模式、分数到文本
					rwScore.writeScore("User:"+mainFrame.registerFrame.gameUserTextField.getText()+"Model:"+
							gridBlock.level+"Score:"+Integer.toString(gridBlock.socre));
					rwScore.scoreRank();	//排序文本的分数，便于下次分数的读取
					uploadSuccessDialog();	//弹出上传成功提示框
				}
			}
		});
		//=========取消按钮的点击处理=========
		mainFrame.messageDialog.btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.messageDialog.setVisible(false);	//隐藏提示框
			}
		});
	}
	/**
	 * 玩家注册窗体的点击处理
	 **/
	private void loginFrameClick(){
		//==========注册窗体标题栏已被隐藏，添加容器的移动方法=========
		mainFrame.registerFrame.loginPanel.addMouseMotionListener(new MouseMotionAdapter() {	//添加鼠标运动侦听器，便于移动窗体
			@Override
			public void mouseDragged(MouseEvent e) {	//鼠标拖动事件处理，移动窗体
				mainFrame.registerFrame.setLocation(jfx+(e.getXOnScreen()-mx), jfy+(e.getYOnScreen()-my));	//窗体移动的的位置算法
			}
		});
		//==========获取容器的鼠标坐标==========
		mainFrame.registerFrame.loginPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {	//获取鼠标的坐标
				mx=e.getXOnScreen();				//获取鼠标拖拽之前的X坐标
				my=e.getYOnScreen();				//获取鼠标拖拽之前的Y坐标
				jfx=mainFrame.registerFrame.getX();	//获取窗体的X坐标
				jfy=mainFrame.registerFrame.getY();	//获取窗体的Y坐标
			}
		});
		//==========注册窗体的退出/取消按钮事件==========
		mainFrame.registerFrame.btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.registerFrame.btnExit.getBtnText().equals("取消")) {	//从主窗体选择注册玩家窗体则是返回到主窗体
					mainFrame.registerFrame.gameUserTextField.setText("");		//取消注册则清除输入框数据
					mainFrame.registerFrame.setVisible(false);		//隐藏登入界面
				}else {		//程序一开始时是“退出”
					System.exit(0);
				}
			}
		});
		//==========注册按钮事件=========
		mainFrame.registerFrame.btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.registerFrame.btnRegister.getBtnText().equals("进入游戏")) {	//程序一开始时是“进入游戏”
					mainFrame.setVisible(true);		//显示主窗体
					mainFrame.registerFrame.setVisible(false);	//隐藏注册窗体
				}else {	//从主窗体选择注册窗体输入完玩家名后直接隐藏注册窗体
					mainFrame.registerFrame.setVisible(false);
				}
			}
		});
		//=========设置玩家名输入长度小于5个字符========
		mainFrame.registerFrame.gameUserTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (mainFrame.registerFrame.gameUserTextField.getText().length()<5) {
				}else {
					e.consume();	//API：使用此事件，以便不会按照默认的方式由产生此事件的源代码来处理此事件。 
				}
			}
		});
	}
	/**
	 * 自定义对话框点击控制
	 **/
	private void diyDialogClick(){
		//==========自定义对话框的确定===========
		mainFrame.diyDialog.btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.rdbtnSimple.isSelected()||mainFrame.diyDialog.rdbtnMiddle.isSelected()||
						mainFrame.diyDialog.rdbtnSenior.isSelected()) {
					if (mainFrame.diyDialog.rdbtnSimple.isSelected()) {	//单选框选中初级模式
						gridBlock.level="初级模式";	//设置为初级模式
					}else if (mainFrame.diyDialog.rdbtnMiddle.isSelected()) {	//单选框选中初级模式
						gridBlock.level="中级模式";	//设置为中级模式
					}else if (mainFrame.diyDialog.rdbtnSenior.isSelected()) {
						gridBlock.level="高级模式";	//设置为高级模式
					}
					mainFrame.btnPause.setVisible(true);	//显示“暂停/继续”按钮
					mainFrame.btnRestart.setVisible(true);	//显示“重新开始”按钮
					mainFrame.setLabelLevel("自定义");	//显示当前游戏为自定义模式
					timerCtrl.starNewLoopTime(mainFrame.diyDialog.slider.getValue());	//获取滑条的速度数值并反射到定时器
				}
				timerCtrl.setRestartGame(true);	//设置未重新开始游戏
				timerCtrl.setRunningOrPause(true);	//状态设为true不是暂停
				mainFrame.diyDialog.setVisible(false);	//隐藏自定义对话框
			}
		});
		//==========方块形状有选中才显示开始游戏按钮==========
		mainFrame.diyDialog.rdbtnSimple.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.rdbtnSimple.isSelected()) {
					mainFrame.diyDialog.btnOk.setVisible(true);
				}
			}
		});
		//==========方块形状有选中才显示开始游戏按钮==========
		mainFrame.diyDialog.rdbtnMiddle.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.rdbtnMiddle.isSelected()) {
					mainFrame.diyDialog.btnOk.setVisible(true);
				}
			}
		});
		//==========方块形状有选中才显示开始游戏按钮==========
		mainFrame.diyDialog.rdbtnSenior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.rdbtnSenior.isSelected()) {
					mainFrame.diyDialog.btnOk.setVisible(true);
				}
			}
		});
		//==========自定义对话框的取消===========
		mainFrame.diyDialog.btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.diyDialog.setVisible(false);
			}
		});
		//==========辅助线选择==========
		mainFrame.diyDialog.chckbxHlepLine.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.chckbxHlepLine.isSelected()) {	//选择辅助线复选框则显示下落区域的网格线
					GridBlock.helpLine=true;
				}else {		//取消辅助线复选框则显示下落区域的网格线
					GridBlock.helpLine=false;
				}
				mainFrame.blockPanel.repaint();
			}
		});
		//==========选择是否显示预览区域==========
		mainFrame.diyDialog.chckbxShowNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.chckbxShowNext.isSelected()) {	//“方块提示”选中则显示方块预览
					mainFrame.nextShowPanel.setVisible(true);
					mainFrame.labelNextShape.setVisible(true);
					mainFrame.diyDialog.chckbxChangeNext.setEnabled(true);
				}else {		//“方块提示”取消则显示方块预览
					mainFrame.nextShowPanel.setVisible(false);
					mainFrame.labelNextShape.setVisible(false);
					mainFrame.diyDialog.chckbxChangeNext.setEnabled(false);
				}
			}
		});
		//==========是否可以更换下一个方块的按钮==========
		mainFrame.diyDialog.chckbxChangeNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.chckbxChangeNext.isSelected()) {	//“更换方块”选中则显示“更换方块”按钮
					mainFrame.btnChangeNext.setVisible(true);
				}else {		//“更换方块”取消则显示“更换方块”按钮
					mainFrame.btnChangeNext.setVisible(false);
				}
			}
		});
		//==========控制方块自由下落速度加减的按钮显示==========
		mainFrame.diyDialog.chckbxCtrlSpeed.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.chckbxCtrlSpeed.isSelected()) {		//“控制下落速度”选中则显示速度按钮
					mainFrame.btnSpeedUp.setVisible(true);
					mainFrame.btnSpeedDown.setVisible(true);
				}else {		//“控制下落速度”取消则显示速度按钮
					mainFrame.btnSpeedUp.setVisible(false);
					mainFrame.btnSpeedDown.setVisible(false);
				}
			}
		});
		//==========修改游戏区域宽度==========
		mainFrame.diyDialog.checkBoxChangeCol.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.checkBoxChangeCol.isSelected()) {
					GridBlock.COL=(int) mainFrame.diyDialog.spinnerPanelCol.getValue();	//获取列数
					mainFrame.changFrame();	//改变窗体
					timerCtrl.setRestartGame(true);	//设置为重新开始
					timerCtrl.setRunningOrPause(true);	//设置为不是暂停
					mainFrame.btnPause.setVisible(true); //显示“暂停/继续”按钮
					mainFrame.btnRestart.setVisible(true);	//显示“重新开始”按钮
					mainFrame.diyDialog.setVisible(false);
					mainFrame.btnPause.setBtnText("暂停（空格）"); //显示“暂停/继续”按钮的文本
				}
			}
		});
		//==========主题一单选按钮==========
		mainFrame.diyDialog.rdbtnStyle1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.rdbtnStyle1.isSelected()) {
					BackgroupPanel.backgroupImage="image/style1.jpg";
					mainFrame.repaint();
					mainFrame.diyDialog.repaint();
				}
			}
		});
		//==========主题二单选按钮==========
		mainFrame.diyDialog.rdbtnStyle2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.rdbtnStyle2.isSelected()) {
					BackgroupPanel.backgroupImage="image/style2.jpg";
					mainFrame.repaint();
					mainFrame.diyDialog.repaint();
				}
			}
		});
		//==========主题三单选按钮==========
		mainFrame.diyDialog.rdbtnStyle3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (mainFrame.diyDialog.rdbtnStyle3.isSelected()) {
					BackgroupPanel.backgroupImage="image/style3.jpg";
					mainFrame.repaint();
					mainFrame.diyDialog.repaint();
				}
			}
		});
	}
	/**
	 * 主窗体按钮和标签点击事件处理
	 **/
	private void mainFrameBtnClick(){
		//===========标签图片方向上的点击==========
		mainFrame.labelUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timerCtrl.getRunningOrPause()) {	//游戏未暂停方可旋转
					gridBlock.player1Rotate();
					mainFrame.blockPanel.repaint();
				}
			}
		});
		//==========标签图片方向下的点击===========
		mainFrame.labelDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timerCtrl.getRunningOrPause()) {	//游戏未暂停方可下落
					gridBlock.player1Down();
					mainFrame.blockPanel.repaint();
				}
			}
		});
		//===========标签图片方向左的点击==========
		mainFrame.labelLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timerCtrl.getRunningOrPause()) {	//游戏未暂停方可左移
					gridBlock.player1Left();
					mainFrame.blockPanel.repaint();
				}
			}
		});
		//==========标签图片方向右的点击============
		mainFrame.labelRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timerCtrl.getRunningOrPause()) {	//游戏未暂停方可右移
					gridBlock.player1Right();
					mainFrame.blockPanel.repaint();
				}
			}
		});
		//==========主窗体的关闭按钮============
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				showExitDialog();	//弹出退出提示
			}
		});
		//===========按钮更换下一个方块=============
		mainFrame.btnChangeNext.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gridBlock.randomShape(mainFrame.diyDialog.chckbxBoom.isSelected());	//随机选择下一个方块
				mainFrame.nextShowPanel.showNextShape(gridBlock.shapeNum, gridBlock.level);//方块预览区显示下一个方块
			}
		});
		//===========暂停/继续=========
		mainFrame.btnPause.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timerCtrl.getRunningOrPause()==true) {	
					timerCtrl.setRunningOrPause(false);
					mainFrame.btnPause.setBtnText("继续（空格）");
				}else if (timerCtrl.getRunningOrPause()==false) {
					timerCtrl.setRunningOrPause(true); 
					mainFrame.btnPause.setBtnText("暂停（空格）");
				}
			}
		});
		//==========音效单选框控制===========
		mainFrame.radioBtnSounds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon musicIcoOn=new ImageIcon("image/soundson.png");
				ImageIcon musicIcoOff=new ImageIcon("image/soundsoff.png");
				if (mainFrame.radioBtnSounds.isSelected()) {
					mainFrame.radioBtnSounds.setIcon(musicIcoOn);	//显示声音开的图片
				}else {
					mainFrame.radioBtnSounds.setIcon(musicIcoOff);	//显示声音关的图片
				}
			}
		});
		//===========背景音乐控制==========
		mainFrame.radioBtnMusic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ImageIcon musicIcoOn=new ImageIcon("image/musicon.png");
				ImageIcon musicIcoOff=new ImageIcon("image/musicoff.png");
				if (mainFrame.radioBtnMusic.isSelected()) {
					soundModel.backgroupMusic.loop();
					mainFrame.radioBtnMusic.setIcon(musicIcoOn);	//显示背景音乐开的图片
				}else {
					soundModel.backgroupMusic.stop();
					mainFrame.radioBtnMusic.setIcon(musicIcoOff);	//显示背景音乐开的图片
				}
			}
		});
		//==========重新开始===========
		mainFrame.btnRestart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				timerCtrl.setRestartGame(true);	//设置为重新开始
				timerCtrl.setRunningOrPause(true);	//设置为不是暂停
				mainFrame.btnPause.setVisible(true); //显示“暂停/继续”按钮
				mainFrame.btnPause.setBtnText("暂停（空格）"); //显示“暂停/继续”按钮的文本
			}
		});
		//==========方块自由下落速度加速=========
		mainFrame.btnSpeedUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timerCtrl.getRunningOrPause()) {
					if (timerCtrl.getLoopTime()>50) {	//下落速度最快只能是50ms
						timerCtrl.starNewLoopTime(timerCtrl.getLoopTime()-50);	//当前速度-50ms
					}
				}
			}
		});
		//==========方块自由下落速度减速==========
		mainFrame.btnSpeedDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (timerCtrl.getRunningOrPause()) {
					if (timerCtrl.getLoopTime()<1000) {	//下落速度最慢只能是1000ms
						timerCtrl.starNewLoopTime(timerCtrl.getLoopTime()+50);	//当前速度+50ms
					}
				}
			}
		});
		//==========标签实时显示滑条的值===========
		mainFrame.diyDialog.slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				mainFrame.diyDialog.showLoopTime(mainFrame.diyDialog.slider.getValue());	//设置标签的文本
			}
		});
	}
	/**
	 * 主窗体菜单栏点击事件处理
	 **/
	private void itemClick() {
		//===========单人菜单初级模式选择开始=============
		mainFrame.singleSimpleRdbItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GridBlock.COL=15;				//设置游戏区域列数
				mainFrame.changFrame();			//改变窗体大小
				timerCtrl.setRestartGame(true);	//设置重新开始
				gridBlock.level="初级模式";	//设置为初级模式
				timerCtrl.starNewLoopTime(500);	//设置新的定时器执行周期为500ms
				mainFrame.setLabelLevel("初级");	//标签显示当前模式
				mainFrame.btnPause.setVisible(true);	//显示“暂停/继续”按钮
				mainFrame.btnRestart.setVisible(true);	//显示“重新开始”按钮
				mainFrame.btnPause.setBtnText("暂停（空格）"); 
				mainFrame.diyDialog.chckbxShowNext.setSelected(true);	//自定义对话框的方块预览选中
				mainFrame.diyDialog.checkBoxChangeCol.setEnabled(true);
			}
		});
		//==========单人菜单中级模式选择开始===========
		mainFrame.singleMiddleRdbItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GridBlock.COL=15;				//设置游戏区域列数
				mainFrame.changFrame();			//改变窗体大小
				timerCtrl.setRestartGame(true);	//设置重新开始
				timerCtrl.starNewLoopTime(300);	//设置新的定时器执行周期为300ms
				gridBlock.level="中级模式";	//设置为中级模式
				mainFrame.setLabelLevel("中级");	//标签显示当前模式
				mainFrame.btnPause.setVisible(true);	//显示“暂停/继续”按钮
				mainFrame.btnRestart.setVisible(true);	//显示“重新开始”按钮
				mainFrame.btnPause.setBtnText("暂停（空格）"); 
				mainFrame.diyDialog.chckbxShowNext.setSelected(true);	//自定义对话框的方块预览选中
				mainFrame.diyDialog.checkBoxChangeCol.setEnabled(true);
			}
		});
		//===========单人菜单高级模式选择开始===========
		mainFrame.singleSeniorRdbItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GridBlock.COL=15;				//设置游戏区域列数
				mainFrame.changFrame();			//改变窗体大小
				timerCtrl.setRestartGame(true);	//设置重新开始
				timerCtrl.starNewLoopTime(100);	//设置新的定时器执行周期为100ms
				gridBlock.level="高级模式";	//设置为高级模式
				mainFrame.setLabelLevel("高级");	//标签显示当前模式
				mainFrame.btnPause.setVisible(true);	//显示“暂停/继续”按钮
				mainFrame.btnRestart.setVisible(true);	//显示“重新开始”按钮
				mainFrame.btnPause.setBtnText("暂停（空格）"); 
				mainFrame.diyDialog.chckbxShowNext.setSelected(true);	//自定义对话框的方块预览选中
				mainFrame.diyDialog.checkBoxChangeCol.setEnabled(true);
			}
		});
		//===========双人菜单初级模式选择开始=============
		mainFrame.doubleSimpleRdbItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GridBlock.COL=25;				//设置游戏区域列数，只有在双人游戏时才是25
				mainFrame.changFrame();			//改变窗体大小
				timerCtrl.setRestartGame(true);	//设置重新开始
				gridBlock.level="初级模式";	//设置为初级模式
				timerCtrl.starNewLoopTime(500);	//设置新的定时器执行周期为500ms
				mainFrame.setLabelLevel("初级");	//标签显示当前模式
				mainFrame.btnPause.setVisible(true);	//显示“暂停/继续”按钮
				mainFrame.btnRestart.setVisible(true);	//显示“重新开始”按钮
				mainFrame.btnPause.setBtnText("暂停（空格）"); 
				mainFrame.diyDialog.chckbxShowNext.setSelected(true);	//自定义对话框的方块预览选中
				mainFrame.diyDialog.checkBoxChangeCol.setEnabled(false);
			}
		});
		//==========双人菜单中级模式选择开始===========
		mainFrame.doubleMiddleRdbItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GridBlock.COL=25;				//设置游戏区域列数，只有在双人游戏时才是25
				mainFrame.changFrame();			//改变窗体大小
				timerCtrl.setRestartGame(true);	//设置重新开始
				timerCtrl.starNewLoopTime(300);	//设置新的定时器执行周期为300ms
				gridBlock.level="中级模式";	//设置为中级模式
				mainFrame.setLabelLevel("中级");	//标签显示当前模式
				mainFrame.btnPause.setVisible(true);	//显示“暂停/继续”按钮
				mainFrame.btnRestart.setVisible(true);	//显示“重新开始”按钮
				mainFrame.btnPause.setBtnText("暂停（空格）"); 
				mainFrame.diyDialog.chckbxShowNext.setSelected(true);	//自定义对话框的方块预览选中
				mainFrame.diyDialog.checkBoxChangeCol.setEnabled(false);
			}
		});
		//===========双人菜单高级模式选择开始===========
		mainFrame.doubleSeniorRdbItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GridBlock.COL=25;				//设置游戏区域列数，只有在双人游戏时才是25
				mainFrame.changFrame();			//改变窗体大小
				timerCtrl.setRestartGame(true);	//设置重新开始
				timerCtrl.starNewLoopTime(100);	//设置新的定时器执行周期为100ms
				gridBlock.level="高级模式";	//设置为高级模式
				mainFrame.setLabelLevel("高级");	//标签显示当前模式
				mainFrame.btnPause.setVisible(true);	//显示“暂停/继续”按钮
				mainFrame.btnRestart.setVisible(true);	//显示“重新开始”按钮
				mainFrame.btnPause.setBtnText("暂停（空格）"); 
				mainFrame.diyDialog.chckbxShowNext.setSelected(true);	//自定义对话框的方块预览选中
				mainFrame.diyDialog.checkBoxChangeCol.setEnabled(false);
			}
		});
		//===========菜单超神榜===========
		mainFrame.godItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rwScore.readerScore();	//从分数文本中获取数据
				showSimpleScore();	//显示初级榜的玩家
				showMiddleScore();	//显示中级榜的玩家
				showSeniorScore();	//显示高级榜的玩家
				showNonhumanScore();	//显示变态榜的玩家
				mainFrame.godDialog.setVisible(true);	//显示超神榜对话框
			}
		});
		//==========菜单自定义模式============
		mainFrame.setItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.diyDialog.setVisible(true);	//显示自定义模式对话框
			}
		});
		//=========玩家注册菜单=========
		mainFrame.registerItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.registerFrame.btnRegister.setBtnText("注册");	//修改注册的注册按钮文本为“注册”
				mainFrame.registerFrame.btnRegister.setBtnTextXY(40, 16);//修改文本的显示位置为居中
				mainFrame.registerFrame.btnExit.setBtnText("取消");	//修改注册的按钮取消文本为“注册”
				mainFrame.registerFrame.setVisible(true);	//显示注册窗体
			}
		});
		//=========退出游戏菜单=========
		mainFrame.exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showExitDialog();	//弹出退出提示框
			}
		});
		//=========关于菜单=========
		mainFrame.aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.aboutDialog.setVisible(true);	//显示关于对话框
			}
		});
		//=========游戏说明菜单=======
		mainFrame.explainItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.explainDialog.setVisible(true);	//显示游戏说明对话框
			}
		});
	}
	/**
	 * 鼠标进入组件的音效
	 **/
	private void componentSound(){
		//暂停按钮音效
		mainFrame.btnPause.addMouseListener(mouseSound);
		//下落加速按钮音效
		mainFrame.btnSpeedUp.addMouseListener(mouseSound);
		//重新开始按钮音效
		mainFrame.btnRestart.addMouseListener(mouseSound);
		//下落减速按钮音效
		mainFrame.btnSpeedDown.addMouseListener(mouseSound);
		//更换方块按钮音效
		mainFrame.btnChangeNext.addMouseListener(mouseSound);
		//自定义对话框的开始游戏按钮音效
		mainFrame.diyDialog.btnOk.addMouseListener(mouseSound);
		//自定义对话框的返回按钮音效
		mainFrame.diyDialog.btnCancel.addMouseListener(mouseSound);
		//注册窗体的注册按钮音效
		mainFrame.registerFrame.btnRegister.addMouseListener(mouseSound);
		//注册窗体的取消按钮音效
		mainFrame.registerFrame.btnExit.addMouseListener(mouseSound);
		//提示框的确定按钮音效
		mainFrame.messageDialog.btnOk.addMouseListener(mouseSound);
		//提示框的取消按钮音效
		mainFrame.messageDialog.btnCancel.addMouseListener(mouseSound);
		//标签图标方向上声音
		mainFrame.labelUp.addMouseListener(mouseSound);
		//标签图标方向下声音
		mainFrame.labelDown.addMouseListener(mouseSound);
		//标签图标方向左声音
		mainFrame.labelLeft.addMouseListener(mouseSound);
		//标签图标方向右声音
		mainFrame.labelRight.addMouseListener(mouseSound);
		//音乐单选框开关音效
		mainFrame.radioBtnMusic.addMouseListener(mouseSound);
		//声音开关音效
		mainFrame.radioBtnSounds.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				soundModel.buttonSound.play();	//鼠标进入的音效
			}
			@Override
			public void mousePressed(MouseEvent e) {
				soundModel.rotateSound.play();	//鼠标点击的音效
			}
		});
	}
	/**
	 * 上传分数成功提示对话框的创建
	 */
	private void uploadSuccessDialog(){
		mainFrame.messageDialog.setTitle("上传提示");		//修改标题
		mainFrame.messageDialog.labelMessage.setText("玩家："+mainFrame.registerFrame.gameUserTextField.getText()+"  分数上传成功！");//提示内容
		mainFrame.messageDialog.btnCancel.setBtnText("返回");	//修改取消按钮文本
		mainFrame.messageDialog.btnOk.setVisible(false);	//隐藏确定按钮
		mainFrame.messageDialog.setVisible(true);	//显示对话框
	}
	/**
	 * 游戏退出提示对话框的创建
	 */
	private void showExitDialog(){
		mainFrame.messageDialog.setTitle("退出提示");	//修改标题
		mainFrame.messageDialog.labelMessage.setText("确定退出游戏吗？？？");	//提示内容
		mainFrame.messageDialog.btnOk.setBtnText("确定");	//修改确定按钮文本
		mainFrame.messageDialog.btnCancel.setBtnText("取消");	//修改取消按钮文本
		mainFrame.messageDialog.btnOk.setVisible(true);	//显示确定按钮
		mainFrame.messageDialog.setVisible(true);	//显示对话框
	}
	/**
	 * 初级模式分数榜的刷新
	 */
	private void showSimpleScore(){
		mainFrame.godDialog.simpleTextArea.setText(null);	//清除文本区域
		if (rwScore.simpleScoreList.size()!=0) {	//玩家信息刷新到文本区域
			for (int i = 0; i < rwScore.simpleScoreList.size(); i++) {
				mainFrame.godDialog.simpleTextArea.append("玩家："+
						rwScore.simpleScoreList.get(i).substring(rwScore.simpleScoreList.get(i).indexOf("User:")+5, 
								rwScore.simpleScoreList.get(i).indexOf("Model:"))+"\t分数："+
								rwScore.simpleScoreList.get(i).substring(rwScore.simpleScoreList.get(i).indexOf("Score:")+6, 
										rwScore.simpleScoreList.get(i).length())+"\n");
			}
		}
		switch (rwScore.simpleScoreList.size()) {	//根据玩家的个数显示第一、第二、第三和最后一名
		case 0:	//玩家的个数0个
			mainFrame.godDialog.simpleFirstUser.setText("");
			mainFrame.godDialog.simpleSecondUser.setText("");
			mainFrame.godDialog.simpleThirdUser.setText("");
			mainFrame.godDialog.simpleLastUser.setText("");
			break;
		case 1:	//玩家的个数只有1个
			mainFrame.godDialog.simpleFirstUser.setText(rwScore.simpleScoreList.get(0).
					substring(rwScore.simpleScoreList.get(0).indexOf("User:")+5, rwScore.simpleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.simpleSecondUser.setText("");
			mainFrame.godDialog.simpleThirdUser.setText("");
			mainFrame.godDialog.simpleLastUser.setText("");
			break;
		case 2:	//玩家的个数只有2个
			mainFrame.godDialog.simpleFirstUser.setText(rwScore.simpleScoreList.get(0).
					substring(rwScore.simpleScoreList.get(0).indexOf("User:")+5, rwScore.simpleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.simpleSecondUser.setText(rwScore.simpleScoreList.get(0).
					substring(rwScore.simpleScoreList.get(0).indexOf("User:")+5, rwScore.simpleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.simpleThirdUser.setText("");
			mainFrame.godDialog.simpleLastUser.setText("");
			break;
		case 3:	//玩家的个数只有3个
			mainFrame.godDialog.simpleFirstUser.setText(rwScore.simpleScoreList.get(0).
					substring(rwScore.simpleScoreList.get(0).indexOf("User:")+5, rwScore.simpleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.simpleSecondUser.setText(rwScore.simpleScoreList.get(1).
					substring(rwScore.simpleScoreList.get(1).indexOf("User:")+5, rwScore.simpleScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.simpleThirdUser.setText(rwScore.simpleScoreList.get(2).
					substring(rwScore.simpleScoreList.get(2).indexOf("User:")+5, rwScore.simpleScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.simpleLastUser.setText("");
			break;
		case 4:	//玩家的个数只有4个
			mainFrame.godDialog.simpleFirstUser.setText(rwScore.simpleScoreList.get(0).
					substring(rwScore.simpleScoreList.get(0).indexOf("User:")+5, rwScore.simpleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.simpleSecondUser.setText(rwScore.simpleScoreList.get(1).
					substring(rwScore.simpleScoreList.get(1).indexOf("User:")+5, rwScore.simpleScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.simpleThirdUser.setText(rwScore.simpleScoreList.get(2).
					substring(rwScore.simpleScoreList.get(2).indexOf("User:")+5, rwScore.simpleScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.simpleLastUser.setText(rwScore.simpleScoreList.get(3).
					substring(rwScore.simpleScoreList.get(3).indexOf("User:")+5, rwScore.simpleScoreList.get(3).indexOf("Model:")));
			break;
		default:	//玩家的个数大于4个
			mainFrame.godDialog.simpleFirstUser.setText(rwScore.simpleScoreList.get(0).
					substring(rwScore.simpleScoreList.get(0).indexOf("User:")+5, rwScore.simpleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.simpleSecondUser.setText(rwScore.simpleScoreList.get(1).
					substring(rwScore.simpleScoreList.get(1).indexOf("User:")+5, rwScore.simpleScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.simpleThirdUser.setText(rwScore.simpleScoreList.get(2).
					substring(rwScore.simpleScoreList.get(2).indexOf("User:")+5, rwScore.simpleScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.simpleLastUser.setText(rwScore.simpleScoreList.get(rwScore.simpleScoreList.size()-1).
					substring(rwScore.simpleScoreList.get(rwScore.simpleScoreList.size()-1).indexOf("User:")+5, 
							rwScore.simpleScoreList.get(rwScore.simpleScoreList.size()-1).indexOf("Model:")));
			break;
		}
	}
	/**
	 * 中级模式分数榜的刷新
	 */
	private void showMiddleScore(){
		mainFrame.godDialog.middleTextArea.setText(null);	//清除文本区域
		if (rwScore.middleScoreList.size()!=0) {	//玩家信息刷新到文本区域
			for (int i = 0; i < rwScore.middleScoreList.size(); i++) {
				mainFrame.godDialog.middleTextArea.append("玩家："+
						rwScore.middleScoreList.get(i).substring(rwScore.middleScoreList.get(i).indexOf("User:")+5, 
								rwScore.middleScoreList.get(i).indexOf("Model:"))+"\t分数："+
								rwScore.middleScoreList.get(i).substring(rwScore.middleScoreList.get(i).indexOf("Score:")+6, 
										rwScore.middleScoreList.get(i).length())+"\n");
			}
		}
		switch (rwScore.middleScoreList.size()) {	//根据玩家的个数显示第一、第二、第三和最后一名
		case 0:	//玩家的个数0个
			mainFrame.godDialog.middleFirstUser.setText("");
			mainFrame.godDialog.middleSecondUser.setText("");
			mainFrame.godDialog.middleThirdUser.setText("");
			mainFrame.godDialog.middleLastUser.setText("");
			break;
		case 1:	//玩家的个数只有1个
			mainFrame.godDialog.middleFirstUser.setText(rwScore.middleScoreList.get(0).
					substring(rwScore.middleScoreList.get(0).indexOf("User:")+5, rwScore.middleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.middleSecondUser.setText("");
			mainFrame.godDialog.middleThirdUser.setText("");
			mainFrame.godDialog.middleLastUser.setText("");
			break;
		case 2:	//玩家的个数只有2个
			mainFrame.godDialog.middleFirstUser.setText(rwScore.middleScoreList.get(0).
					substring(rwScore.middleScoreList.get(0).indexOf("User:")+5, rwScore.middleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.middleSecondUser.setText(rwScore.middleScoreList.get(0).
					substring(rwScore.middleScoreList.get(0).indexOf("User:")+5, rwScore.middleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.middleThirdUser.setText("");
			mainFrame.godDialog.middleLastUser.setText("");
			break;
		case 3:	//玩家的个数只有3个
			mainFrame.godDialog.middleFirstUser.setText(rwScore.middleScoreList.get(0).
					substring(rwScore.middleScoreList.get(0).indexOf("User:")+5, rwScore.middleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.middleSecondUser.setText(rwScore.middleScoreList.get(1).
					substring(rwScore.middleScoreList.get(1).indexOf("User:")+5, rwScore.middleScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.middleThirdUser.setText(rwScore.middleScoreList.get(2).
					substring(rwScore.middleScoreList.get(2).indexOf("User:")+5, rwScore.middleScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.middleLastUser.setText("");
			break;
		case 4:	//玩家的个数只有4个
			mainFrame.godDialog.middleFirstUser.setText(rwScore.middleScoreList.get(0).
					substring(rwScore.middleScoreList.get(0).indexOf("User:")+5, rwScore.middleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.middleSecondUser.setText(rwScore.middleScoreList.get(1).
					substring(rwScore.middleScoreList.get(1).indexOf("User:")+5, rwScore.middleScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.middleThirdUser.setText(rwScore.middleScoreList.get(2).
					substring(rwScore.middleScoreList.get(2).indexOf("User:")+5, rwScore.middleScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.middleLastUser.setText(rwScore.middleScoreList.get(3).
					substring(rwScore.middleScoreList.get(3).indexOf("User:")+5, rwScore.middleScoreList.get(3).indexOf("Model:")));
			break;
		default:	//玩家的个数大于4个
			mainFrame.godDialog.middleFirstUser.setText(rwScore.middleScoreList.get(0).
					substring(rwScore.middleScoreList.get(0).indexOf("User:")+5, rwScore.middleScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.middleSecondUser.setText(rwScore.middleScoreList.get(1).
					substring(rwScore.middleScoreList.get(1).indexOf("User:")+5, rwScore.middleScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.middleThirdUser.setText(rwScore.middleScoreList.get(2).
					substring(rwScore.middleScoreList.get(2).indexOf("User:")+5, rwScore.middleScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.middleLastUser.setText(rwScore.middleScoreList.get(rwScore.middleScoreList.size()-1).
					substring(rwScore.middleScoreList.get(rwScore.middleScoreList.size()-1).indexOf("User:")+5, 
							rwScore.middleScoreList.get(rwScore.middleScoreList.size()-1).indexOf("Model:")));
			break;
		}
	}
	/**
	 * 高级模式分数榜的刷新
	 */
	private void showSeniorScore(){
		mainFrame.godDialog.seniorTextArea.setText(null);	//清除文本区域
		if (rwScore.seniorScoreList.size()!=0) {	//玩家信息刷新到文本区域
			for (int i = 0; i < rwScore.seniorScoreList.size(); i++) {
				mainFrame.godDialog.seniorTextArea.append("玩家："+
						rwScore.seniorScoreList.get(i).substring(rwScore.seniorScoreList.get(i).indexOf("User:")+5, 
								rwScore.seniorScoreList.get(i).indexOf("Model:"))+"\t分数："+
								rwScore.seniorScoreList.get(i).substring(rwScore.seniorScoreList.get(i).indexOf("Score:")+6, 
										rwScore.seniorScoreList.get(i).length())+"\n");
			}
		}
		switch (rwScore.seniorScoreList.size()) {	//根据玩家的个数显示第一、第二、第三和最后一名
		case 0:	//玩家的个数0个
			mainFrame.godDialog.seniorFirstUser.setText("");
			mainFrame.godDialog.seniorSecondUser.setText("");
			mainFrame.godDialog.seniorThirdUser.setText("");
			mainFrame.godDialog.seniorLastUser.setText("");
			break;
		case 1:	//玩家的个数只有1个
			mainFrame.godDialog.seniorFirstUser.setText(rwScore.seniorScoreList.get(0).
					substring(rwScore.seniorScoreList.get(0).indexOf("User:")+5, rwScore.seniorScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.seniorSecondUser.setText("");
			mainFrame.godDialog.seniorThirdUser.setText("");
			mainFrame.godDialog.seniorLastUser.setText("");
			break;
		case 2:	//玩家的个数只有2个
			mainFrame.godDialog.seniorFirstUser.setText(rwScore.seniorScoreList.get(0).
					substring(rwScore.seniorScoreList.get(0).indexOf("User:")+5, rwScore.seniorScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.seniorSecondUser.setText(rwScore.seniorScoreList.get(0).
					substring(rwScore.seniorScoreList.get(0).indexOf("User:")+5, rwScore.seniorScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.seniorThirdUser.setText("");
			mainFrame.godDialog.seniorLastUser.setText("");
			break;
		case 3:	//玩家的个数只有3个
			mainFrame.godDialog.seniorFirstUser.setText(rwScore.seniorScoreList.get(0).
					substring(rwScore.seniorScoreList.get(0).indexOf("User:")+5, rwScore.seniorScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.seniorSecondUser.setText(rwScore.seniorScoreList.get(1).
					substring(rwScore.seniorScoreList.get(1).indexOf("User:")+5, rwScore.seniorScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.seniorThirdUser.setText(rwScore.seniorScoreList.get(2).
					substring(rwScore.seniorScoreList.get(2).indexOf("User:")+5, rwScore.seniorScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.seniorLastUser.setText("");
			break;
		case 4:	//玩家的个数只有4个
			mainFrame.godDialog.seniorFirstUser.setText(rwScore.seniorScoreList.get(0).
					substring(rwScore.seniorScoreList.get(0).indexOf("User:")+5, rwScore.seniorScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.seniorSecondUser.setText(rwScore.seniorScoreList.get(1).
					substring(rwScore.seniorScoreList.get(1).indexOf("User:")+5, rwScore.seniorScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.seniorThirdUser.setText(rwScore.seniorScoreList.get(2).
					substring(rwScore.seniorScoreList.get(2).indexOf("User:")+5, rwScore.seniorScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.seniorLastUser.setText(rwScore.seniorScoreList.get(3).
					substring(rwScore.seniorScoreList.get(3).indexOf("User:")+5, rwScore.seniorScoreList.get(3).indexOf("Model:")));
			break;
		default:	//玩家的个数大于4个
			mainFrame.godDialog.seniorFirstUser.setText(rwScore.seniorScoreList.get(0).
					substring(rwScore.seniorScoreList.get(0).indexOf("User:")+5, rwScore.seniorScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.seniorSecondUser.setText(rwScore.seniorScoreList.get(1).
					substring(rwScore.seniorScoreList.get(1).indexOf("User:")+5, rwScore.seniorScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.seniorThirdUser.setText(rwScore.seniorScoreList.get(2).
					substring(rwScore.seniorScoreList.get(2).indexOf("User:")+5, rwScore.seniorScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.seniorLastUser.setText(rwScore.seniorScoreList.get(rwScore.seniorScoreList.size()-1).
					substring(rwScore.seniorScoreList.get(rwScore.seniorScoreList.size()-1).indexOf("User:")+5, 
							rwScore.seniorScoreList.get(rwScore.seniorScoreList.size()-1).indexOf("Model:")));
			break;
		}
	}
	/**
	 * 变态模式分数榜的刷新
	 */
	private void showNonhumanScore(){
		mainFrame.godDialog.nonhumanTextArea.setText(null);	//清除文本区域
		if (rwScore.nonhumanScoreList.size()!=0) {	//玩家信息刷新到文本区域
			for (int i = 0; i < rwScore.nonhumanScoreList.size(); i++) {
				mainFrame.godDialog.nonhumanTextArea.append("玩家："+
						rwScore.nonhumanScoreList.get(i).substring(rwScore.nonhumanScoreList.get(i).indexOf("User:")+5, 
								rwScore.nonhumanScoreList.get(i).indexOf("Model:"))+"\t分数："+
								rwScore.nonhumanScoreList.get(i).substring(rwScore.nonhumanScoreList.get(i).indexOf("Score:")+6, 
										rwScore.nonhumanScoreList.get(i).length())+"\n");
			}
		}
		switch (rwScore.nonhumanScoreList.size()) {	//根据玩家的个数显示第一、第二、第三和最后一名
		case 0:	//玩家的个数0个
			mainFrame.godDialog.nonhumanFirstUser.setText("");
			mainFrame.godDialog.nonhumanSecondUser.setText("");
			mainFrame.godDialog.nonhumanThirdUser.setText("");
			mainFrame.godDialog.nonhumanLastUser.setText("");
			break;
		case 1:	//玩家的个数只有1个
			mainFrame.godDialog.nonhumanFirstUser.setText(rwScore.nonhumanScoreList.get(0).
					substring(rwScore.nonhumanScoreList.get(0).indexOf("User:")+5, rwScore.nonhumanScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.nonhumanSecondUser.setText("");
			mainFrame.godDialog.nonhumanThirdUser.setText("");
			mainFrame.godDialog.nonhumanLastUser.setText("");
			break;
		case 2:	//玩家的个数只有2个
			mainFrame.godDialog.nonhumanFirstUser.setText(rwScore.nonhumanScoreList.get(0).
					substring(rwScore.nonhumanScoreList.get(0).indexOf("User:")+5, rwScore.nonhumanScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.nonhumanSecondUser.setText(rwScore.nonhumanScoreList.get(0).
					substring(rwScore.nonhumanScoreList.get(0).indexOf("User:")+5, rwScore.nonhumanScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.nonhumanThirdUser.setText("");
			mainFrame.godDialog.nonhumanLastUser.setText("");
			break;
		case 3:	//玩家的个数只有3个
			mainFrame.godDialog.nonhumanFirstUser.setText(rwScore.nonhumanScoreList.get(0).
					substring(rwScore.nonhumanScoreList.get(0).indexOf("User:")+5, rwScore.nonhumanScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.nonhumanSecondUser.setText(rwScore.nonhumanScoreList.get(1).
					substring(rwScore.nonhumanScoreList.get(1).indexOf("User:")+5, rwScore.nonhumanScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.nonhumanThirdUser.setText(rwScore.nonhumanScoreList.get(2).
					substring(rwScore.nonhumanScoreList.get(2).indexOf("User:")+5, rwScore.nonhumanScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.nonhumanLastUser.setText("");
			break;
		case 4:	//玩家的个数只有4个
			mainFrame.godDialog.nonhumanFirstUser.setText(rwScore.nonhumanScoreList.get(0).
					substring(rwScore.nonhumanScoreList.get(0).indexOf("User:")+5, rwScore.nonhumanScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.nonhumanSecondUser.setText(rwScore.nonhumanScoreList.get(1).
					substring(rwScore.nonhumanScoreList.get(1).indexOf("User:")+5, rwScore.nonhumanScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.nonhumanThirdUser.setText(rwScore.nonhumanScoreList.get(2).
					substring(rwScore.nonhumanScoreList.get(2).indexOf("User:")+5, rwScore.nonhumanScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.nonhumanLastUser.setText(rwScore.nonhumanScoreList.get(3).
					substring(rwScore.nonhumanScoreList.get(3).indexOf("User:")+5, rwScore.nonhumanScoreList.get(3).indexOf("Model:")));
			break;
		default:	//玩家的个数大于4个
			mainFrame.godDialog.nonhumanFirstUser.setText(rwScore.nonhumanScoreList.get(0).
					substring(rwScore.nonhumanScoreList.get(0).indexOf("User:")+5, rwScore.nonhumanScoreList.get(0).indexOf("Model:")));
			mainFrame.godDialog.nonhumanSecondUser.setText(rwScore.nonhumanScoreList.get(1).
					substring(rwScore.nonhumanScoreList.get(1).indexOf("User:")+5, rwScore.nonhumanScoreList.get(1).indexOf("Model:")));
			mainFrame.godDialog.nonhumanThirdUser.setText(rwScore.nonhumanScoreList.get(2).
					substring(rwScore.nonhumanScoreList.get(2).indexOf("User:")+5, rwScore.nonhumanScoreList.get(2).indexOf("Model:")));
			mainFrame.godDialog.nonhumanLastUser.setText(rwScore.nonhumanScoreList.get(rwScore.nonhumanScoreList.size()-1).
					substring(rwScore.nonhumanScoreList.get(rwScore.nonhumanScoreList.size()-1).indexOf("User:")+5, 
							rwScore.nonhumanScoreList.get(rwScore.nonhumanScoreList.size()-1).indexOf("Model:")));
			break;
		}
	}
	/**
	 * 鼠标事件内部类
	 */
	private class MouseSound extends MouseAdapter{
		/**
		 * 鼠标进入组件的监听
		 */
		@Override
		public void mouseEntered(MouseEvent e) {
			if (mainFrame.radioBtnSounds.isSelected()) {	//音效开关打开则播放音效
				soundModel.buttonSound.play();	//鼠标进入的音效
			}
		}
		/**
		 * 鼠标点击组件的监听
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			if (mainFrame.radioBtnSounds.isSelected()) {	//音效开关打开则播放音效
				soundModel.rotateSound.play();	//鼠标点击的音效
			}
		}
	}
}