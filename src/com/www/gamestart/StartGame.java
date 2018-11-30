package com.www.gamestart;

import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import com.www.gameview.MainFrame;
import com.www.gamemodel.GridBlock;
import com.www.gamemodel.SoundModel;
import com.www.gamecontrol.KeyControl;
import com.www.gamecontrol.TimerControl;
import com.www.gamecontrol.MouseControl;
import javax.swing.UnsupportedLookAndFeelException;
	/**
	 * 主程序对象创建类
	 * @author wWw
	 *
	 */
public class StartGame {
	/**
	 * 创建窗体对象、事件处理对象、定时器对象
	 */
	public StartGame() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {	//设置界面风格
			JOptionPane.showConfirmDialog(null, "获取风格失败！", "提示", JOptionPane.CLOSED_OPTION);
		}
		MainFrame mainFrame =new MainFrame();	//视图对象
		SoundModel soundModel=new SoundModel();	//音乐模型对象
		GridBlock gridModel=new GridBlock();	//网格方块对象
		TimerControl timerCtrl=new TimerControl(mainFrame,soundModel,gridModel);	//定时器控制对象
		new KeyControl(mainFrame,timerCtrl,soundModel,gridModel);	//键盘监听对象
		new MouseControl(mainFrame, timerCtrl,soundModel,gridModel);	//鼠标点击监听对象
	}
	/**
	 * main方法
	 * @param args
	 */
	public static void main(String[] args) {
		//创建swingUI线程
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new StartGame();
			}
		});
	}
}
