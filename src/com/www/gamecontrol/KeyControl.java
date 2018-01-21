package com.www.gamecontrol;

import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import com.www.gameview.MainFrame;
import com.www.gamemodel.GridBlock;
import com.www.gamemodel.SoundModel;
/**
 * 键盘监听处理类
 * @author wWw
 *
 */
public class KeyControl extends KeyAdapter {
	/**@see 网格方块对象*/
	private GridBlock gridBlock;
	/**@see 主窗体对象*/
	private MainFrame mainFrame;
	/**@see 声音对象*/
	private SoundModel soundModel;	
	/**@see 定时器对象*/
	private TimerControl timerCtrl;
	/**
	 * 创建键盘有参构造方法，传递对象
	 * @param mainFrame	主窗体对象
	 * @param timerControl	声音对象
	 * @param soundModel	定时器对象
	 * @param gridBlock 网格方块对象
	 */
	public KeyControl(MainFrame mainFrame,TimerControl timerControl,SoundModel soundModel,GridBlock gridBlock) {
		this.gridBlock=gridBlock;
		this.mainFrame=mainFrame;
		this.soundModel=soundModel;
		this.timerCtrl=timerControl;
		this.mainFrame.addKeyListener(this); //给主窗体添加键盘监听
	}
	/**
	 * 键盘监听处理事件
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:	//玩家1方块的旋转
			if (timerCtrl.getRunningOrPause()) { //判断是否暂停，false为暂停
				gridBlock.player1Rotate();    //方块的旋转
				if (mainFrame.radioBtnSounds.isSelected()) {	//主窗体音效单选框选中才有方块旋转音效
					soundModel.rotateSound.play();	//方块旋转音效
				}
			}
			break;
		case KeyEvent.VK_DOWN:	//玩家1方块手动下落加速
			if (timerCtrl.getRunningOrPause()) {	//判断是否暂停，false为暂停
				gridBlock.player1Down();	//方块下落
			}
			break;
		case KeyEvent.VK_LEFT:	//玩家1方块左移
			if (timerCtrl.getRunningOrPause()) {	//判断是否暂停，false为暂停
				gridBlock.player1Left();	//方块左移
			}
			break;
		case KeyEvent.VK_RIGHT:	//玩家1方块右移
			if (timerCtrl.getRunningOrPause()) {	//判断是否暂停，false为暂停
				gridBlock.player1Right();	//方块右移
			}
			break;
		case KeyEvent.VK_W:	//玩家2方块的旋转
			if (GridBlock.COL==25) {
				if (timerCtrl.getRunningOrPause()) { //判断是否暂停，false为暂停
					gridBlock.player2Rotate();    //方块的旋转
					if (mainFrame.radioBtnSounds.isSelected()) {	//主窗体音效单选框选中才有方块旋转音效
						soundModel.rotateSound.play();	//方块旋转音效
					}
				}
			}
			break;
		case KeyEvent.VK_S:	//玩家2方块手动下落加速
			if (GridBlock.COL==25) {
				if (timerCtrl.getRunningOrPause()) {	//判断是否暂停，false为暂停
					gridBlock.player2Down();	//方块下落
				}
			}
			break;
		case KeyEvent.VK_A:	//玩家2方块左移
			if (GridBlock.COL==25) {
				if (timerCtrl.getRunningOrPause()) {	//判断是否暂停，false为暂停
					gridBlock.player2Left();	//方块左移
				}
			}
			break;
		case KeyEvent.VK_D:	//玩家2方块右移
			if (GridBlock.COL==25) {
				if (timerCtrl.getRunningOrPause()) {	//判断是否暂停，false为暂停
					gridBlock.player2Right();	//方块右移
				}
			}
			break;
		case KeyEvent.VK_SPACE:	//游戏暂停/继续的控制
			if (timerCtrl.getRunningOrPause()==true) {	
				timerCtrl.setRunningOrPause(false);	//游戏暂停
				mainFrame.btnPause.setBtnText("继续（空格）");	//设置暂停按钮的文本
			}else if (timerCtrl.getRunningOrPause()==false) {
				timerCtrl.setRunningOrPause(true);		//继续游戏
				mainFrame.btnPause.setBtnText("暂停（空格）");	//设置暂停按钮的文本
			}
			break;
		case KeyEvent.VK_Q:	//更换预览区下一个方块
			if (mainFrame.diyDialog.chckbxChangeNext.isSelected()) {	//自定义模式中“更换方块”选中才允许更换
				gridBlock.randomShape(mainFrame.diyDialog.chckbxBoom.isSelected());	//随机下一个方块
				mainFrame.nextShowPanel.showNextShape(gridBlock.shapeNum, gridBlock.level);
			}
			break;
		case KeyEvent.VK_E:	//方块自由下落加速
			if (mainFrame.diyDialog.chckbxCtrlSpeed.isSelected()&&timerCtrl.getRunningOrPause()) {
				if (timerCtrl.getLoopTime()>50) {	//下落速度最快只能是50ms
					timerCtrl.starNewLoopTime(timerCtrl.getLoopTime()-50);	//当前速度-50ms
				}
			}
			break;
		case KeyEvent.VK_R:	//方块自由下落减速
			if (mainFrame.diyDialog.chckbxCtrlSpeed.isSelected()&&timerCtrl.getRunningOrPause()) {
				if (timerCtrl.getLoopTime()<1000) {	//下落速度最慢只能是1000ms
					timerCtrl.starNewLoopTime(timerCtrl.getLoopTime()+50);	//当前速度+50ms
				}
			}
			break;
		case KeyEvent.VK_T://重新开始
			timerCtrl.setRestartGame(true);	//设置重新开始
			timerCtrl.setRunningOrPause(true);	//设置为游戏可以继续
			mainFrame.btnPause.setVisible(true); //显示“暂停/继续”按钮
			mainFrame.btnPause.setBtnText("暂停（空格）");//设置“暂停/继续”按钮文本
			break;
		default:
			break;
		}
		mainFrame.blockPanel.repaint();
	}
}
