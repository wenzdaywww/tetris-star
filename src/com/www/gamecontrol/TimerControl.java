package com.www.gamecontrol;

import java.util.Timer;
import java.util.TimerTask;
import java.lang.reflect.Field;
import com.www.gameview.MainFrame;
import com.www.gamemodel.GridBlock;
import com.www.gamemodel.SoundModel;
/**
 * 定时器事件处理类
 * @author wWw
 *
 */
public class TimerControl extends TimerTask {
	/**@see 定时器执行周期*/
	private long loopTime=500;		
	/**@see 网格方块对象*/
	private GridBlock gridBlock;
	/**@see 主窗体对象*/
	private MainFrame mainFrame;	
	/**@see 声音对象*/
	private SoundModel soundModel;	
	/**@see 重新开始游戏状态，true为重新开始游戏*/
	private boolean restartGame=false;
	/**@see 游戏状态，true为游戏中*/ 
	private boolean runningOrPause=false;
	/**
	 * 创建定时器的有参构造方法，传递对象
	 * @param mainFrame 主窗体对象
	 * @param soundModel 声音对象
	 * @param gridBlock 网格方块对象
	 */
	public TimerControl(MainFrame mainFrame,SoundModel soundModel,GridBlock gridBlock) {
		this.mainFrame=mainFrame;
		this.gridBlock=gridBlock;
		this.soundModel=soundModel;
		this.gridBlock.player1CreateShape(this.mainFrame.diyDialog.chckbxBoom.isSelected());	//程序一开始时就随机产生一个方块
		startTimer();	//开始定时器
	} 
	/**
	 * 创建定时器对象和开始定时器任务
	 */
	private void startTimer(){ 
		Timer timer=new Timer();	//创建定时器对象
		timer.schedule(this, 0, loopTime);	//开始定时器的任务
	}
	/**
	 * 通过反射修改定时器的执行周期
	 */
	private void restartLevel() {  
		setDeclaredField(TimerTask.class, this, "period", loopTime);  
	}  
	/**
	 * 通过反射来修改定时器的执行周期
	 * @param clazz	反射的类
	 * @param obj	反射的对象
	 * @param name	指定的对象
	 * @param value	修改的值
	 * @return	true为反射成功，false为反射异常
	 */
	private boolean setDeclaredField(Class<?> clazz, Object obj,  
			String name, Object value) {  
		try {  
			Field field = clazz.getDeclaredField(name);  //获取类中反射的对象
			//将此对象的 accessible 标志设置为指示的布尔值。值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
			//值为 false 则指示反射的对象应该实施 Java 语言访问检查。
			field.setAccessible(true); 
			//将指定对象变量上此 Field 对象表示的字段设置为指定的新值。如果底层字段的类型为基本类型，则对新值进行自动解包。 
			field.set(obj, value);  
			return true;  
		} catch (Exception ex) {  
			return false;  
		}  
	} 
	/**
	 * 定时器的任务
	 */
	@Override
	public void run() {
		autoLevelUp();	//游戏模式是否自动升级
		if (mainFrame.godDialog.isVisible()||mainFrame.registerFrame.isVisible()||mainFrame.diyDialog.isVisible()||
				mainFrame.aboutDialog.isVisible()||mainFrame.explainDialog.isVisible()||mainFrame.messageDialog.isVisible()||
				mainFrame.gameMenu.isPopupMenuVisible()||mainFrame.helpMenu.isPopupMenuVisible()) {	//打开对话框或者菜单则暂停
			setRunningOrPause(false);
			mainFrame.btnPause.setBtnText("继续（空格）");
		}
		if (GridBlock.COL==25) {	//双人模式
			doubleGame();
		}else {	//单人模式
			singleGame();
		}
	}
	/**
	 * 单人游戏中的各种判断和执行操作
	 */
	private void singleGame(){
		if (runningOrPause) {	//用于控制游戏暂停和继续，true为游戏中
			if (restartGame) {	//用于控制重新开始游戏，true为重新开始游戏
				restartGame=false;	
				gridBlock.player1CreateShape(mainFrame.diyDialog.chckbxBoom.isSelected());	//重新开始游戏后重新生成一个方块
				mainFrame.nextShowPanel.showNextShape(gridBlock.shapeNum, gridBlock.level);
				gridBlock.socre=0;//重新开始，清除分数
				GridBlock.clearBlockPanel();	//清除方块下路区域
				gridBlock.player1SetGameOverToRestart();	//清除方块下落时游戏结束的标志
			}else {
				if (gridBlock.player1IsGameOver()) {	//方块到顶游戏结束，true为游戏结束
					runningOrPause=false;	//游戏暂停
					gridBlock.showGameOver();	//显示游戏结束字样
					mainFrame.blockPanel.repaint();
					soundModel.gameOverSound.play();	//游戏结束声音播放
					mainFrame.btnPause.setVisible(false);	//隐藏“暂停/继续”按钮
					mainFrame.btnPause.setBtnText("暂停（空格）");	//修改“暂停/继续”按钮文本
					showDialog();	//显示游戏结束时的提示框
				}else {
					if (gridBlock.player1IsCanMove()==false) {	//方块到底不能移动则下落下一个方块，false为方块到底
						if (gridBlock.blockDisappear()&&mainFrame.radioBtnSounds.isSelected()) {	//判断消行和是否播放消行音效
							soundModel.blockDisappearSound.play();	//方块消行音效
						}
						if (gridBlock.player1IsBomb()&&mainFrame.radioBtnSounds.isSelected()) {	//判断是否出现炸弹和是佛播放炸弹音效
							soundModel.bombSound.play();	//炸弹音效
						}
						mainFrame.setLabelScore(gridBlock.socre);	//获取游戏得分，并显示
						if (mainFrame.radioBtnSounds.isSelected()) {	//是否播放方块到底音效
							soundModel.cantMoveSound.play();	//方块到底音效
						}
						gridBlock.player1CreateShape(mainFrame.diyDialog.chckbxBoom.isSelected());	//产生一个方块
						mainFrame.nextShowPanel.showNextShape(gridBlock.shapeNum, gridBlock.level);
						if (gridBlock.blockAutoUp(mainFrame.diyDialog.chckbxAutoUp.isSelected(),(int)mainFrame.diyDialog.spinnerAutoUpY.getValue())) {
							mainFrame.diyDialog.spinnerAutoUpY.setValue((int)mainFrame.diyDialog.spinnerAutoUpY.getValue()-1);
						}//方块是否自动上涨
						if (gridBlock.blockRandomOpen(mainFrame.diyDialog.chckbxRandomOpen.isSelected(),(int)mainFrame.diyDialog.spinnerRandomTime.getValue())) {
							mainFrame.diyDialog.spinnerRandomTime.setValue((int)mainFrame.diyDialog.spinnerRandomTime.getValue()-1);
							
						}//方块随机出现在游戏区域中
						mainFrame.diyDialog.slider.setValue((int) loopTime);	//同步游戏速度到滑条当前值
					}else {
						gridBlock.player1Down();	//方块下落
					}
				}
			}
		}
		mainFrame.blockPanel.repaint();
	}
	/**
	 * 双人游戏中的各种判断和执行操作
	 */
	private void doubleGame(){
		if (runningOrPause) {	//用于控制游戏暂停和继续，true为游戏中
			if (restartGame) {	//用于控制重新开始游戏，true为重新开始游戏
				restartGame=false;	
				gridBlock.player1CreateShape(mainFrame.diyDialog.chckbxBoom.isSelected());	//玩家1重新开始游戏后重新随机生成一个方块
				gridBlock.player2CreateShape(mainFrame.diyDialog.chckbxBoom.isSelected());	//玩家2重新开始游戏后重新随机生成一个方块
				mainFrame.nextShowPanel.showNextShape(gridBlock.shapeNum, gridBlock.level);
				gridBlock.socre=0;//重新开始，清除分数
				GridBlock.clearBlockPanel();	//清除方块下路区域
				gridBlock.player1SetGameOverToRestart();	//清除方块下落时游戏结束的标志
				gridBlock.player2SetGameOverToRestart();	//清除方块下落时游戏结束的标志
			}else {	//判断2个玩家是否有一个游戏结束
				if (gridBlock.player1IsGameOver()||gridBlock.player2IsGameOver()) {	//方块到顶游戏结束，true为游戏结束
					runningOrPause=false;	//游戏暂停
					gridBlock.showGameOver();	//显示游戏结束字样
					mainFrame.blockPanel.repaint();
					soundModel.gameOverSound.play();	//游戏结束声音播放
					mainFrame.btnPause.setVisible(false);	//隐藏“暂停/继续”按钮
					mainFrame.btnPause.setBtnText("暂停（空格）");	//修改“暂停/继续”按钮文本
					showDialog();	//显示游戏结束时的提示框
				}else{	//两个玩家都没有游戏结束，则各自判断到底情况
					//玩家1或玩家2方块到底判断是否消行
					if ((gridBlock.player1IsCanMove()==false)||(gridBlock.player2IsCanMove()==false)) {	
						if (gridBlock.blockDisappear()&&mainFrame.radioBtnSounds.isSelected()) {	//判断消行和是否播放消行音效
							soundModel.blockDisappearSound.play();	//方块消行音效
						}
						if (gridBlock.blockAutoUp(mainFrame.diyDialog.chckbxAutoUp.isSelected(),(int)mainFrame.diyDialog.spinnerAutoUpY.getValue())) {
							mainFrame.diyDialog.spinnerAutoUpY.setValue((int)mainFrame.diyDialog.spinnerAutoUpY.getValue()-1);
						}//方块是否自动上涨
						if (gridBlock.blockRandomOpen(mainFrame.diyDialog.chckbxRandomOpen.isSelected(),(int)mainFrame.diyDialog.spinnerRandomTime.getValue())) {
							mainFrame.diyDialog.spinnerRandomTime.setValue((int)mainFrame.diyDialog.spinnerRandomTime.getValue()-1);
							
						}//方块随机出现在游戏区域中
						if (mainFrame.radioBtnSounds.isSelected()) {	//是否播放方块到底音效
							soundModel.cantMoveSound.play();	//方块到底音效
						}
						mainFrame.diyDialog.slider.setValue((int) loopTime);	//同步游戏速度到滑条当前值
					}
					//玩家1方块的下落，判断是否出现炸弹和是否播放炸弹音效
					if (gridBlock.player1IsCanMove()==false) {
						if (gridBlock.player1IsBomb()&&mainFrame.radioBtnSounds.isSelected()) {	
							soundModel.bombSound.play();	//玩家1炸弹音效
						}
						gridBlock.player1CreateShape(mainFrame.diyDialog.chckbxBoom.isSelected());
						mainFrame.nextShowPanel.showNextShape(gridBlock.shapeNum, gridBlock.level);
					}else {
						gridBlock.player1Down();
					}
					//玩家2方块的下落，判断是否出现炸弹和是否播放炸弹音效
					if (gridBlock.player2IsCanMove()==false) {
						if (gridBlock.player2IsBomb()&&mainFrame.radioBtnSounds.isSelected()) {	
							soundModel.bombSound.play();	//玩家2炸弹音效
						}
						gridBlock.player2CreateShape(mainFrame.diyDialog.chckbxBoom.isSelected());
						mainFrame.nextShowPanel.showNextShape(gridBlock.shapeNum, gridBlock.level);
					}else {
						gridBlock.player2Down();
					}
					mainFrame.setLabelScore(gridBlock.socre);	//获取游戏得分，并显示
				}
			}
		}
		mainFrame.blockPanel.repaint();
	}
	/**
	 * 单/双人游戏的模式自动升级，选中自动升级模式后，将从初级开始玩起，分数每增加200分则上升一个等级
	 */
	private void autoLevelUp(){
		if (mainFrame.diyDialog.chckbxAutoLevel.isSelected()) {	//自动升级模式选中
			if (gridBlock.socre<200) {	//分数小于200分则处于初级模式
				loopTime=500;	//设置初级模式下落速度为500ms
				restartLevel();	//重新开始定时器新的执行周期
				gridBlock.level="初级模式";	//设置模式为初级模式
				mainFrame.setLabelLevel("初级");	//标签显示当前处于初级模式
			}else if ((gridBlock.socre>=200)&&(gridBlock.socre<400)) {	//分数在200~400分之间处于中级模式
				loopTime=300;	//设置中级模式下落速度为300ms
				restartLevel();	//重新开始定时器新的执行周期
				gridBlock.level="中级模式";	//设置模式为中级模式
				mainFrame.setLabelLevel("中级");	//标签显示当前处于中级模式
			}else if ((gridBlock.socre>=400)&&(gridBlock.socre<600)) {	//分数在400~600分之间处于高级模式
				loopTime=100;	//设置高级模式的下落速度为100ms
				restartLevel();	//重新开始定时器新的执行周期
				gridBlock.level="高级模式";	//设置模式为高级模式
				mainFrame.setLabelLevel("高级");	//标签显示当前处于高级模式
			}else if ((gridBlock.socre>=600)) {	//分数大于600分处于变态模式
				loopTime=50;	//设置变态模式的下路速度
				restartLevel();	//重新开始定时器新的执行周期
				gridBlock.level="变态模式";	//设置当前模式为变态模式
				mainFrame.setLabelLevel("变态");	//标签显示当前模式为变态模式
			}
		}
	}
	/**
	 * 单/双人游戏结束时的提示框选择
	 */
	private void showDialog(){
		if (mainFrame.registerFrame.gameUserTextField.getText().equals("")) {	//当前没有玩家注册则只出现游戏结束提示框
			showGameOverDialog();	//弹出游戏结束提示框
		}else {	//有玩家注册时游戏结束时
			if (gridBlock.socre>0) {	//玩家分数大于0才提示是否上传分数
				showUploadScore();	//上传分数提示框
			}else {
				showGameOverDialog();	//玩家分数等于0则只出现游戏结束提示框
			}
		}
	}
	/**
	 * 单/双人游戏结束提示框设置
	 */
	private void showGameOverDialog(){
		mainFrame.messageDialog.setTitle("游戏结束");		//设置标题
		mainFrame.messageDialog.labelMessage.setText("哎呀，这么快结束了！！！");	//设置提示内容
		mainFrame.messageDialog.btnOk.setVisible(false);	//隐藏确定按钮
		mainFrame.messageDialog.btnCancel.setBtnText("返回");	//修改取消按钮文本为返回
		mainFrame.messageDialog.setVisible(true);	//显示提示框
	}
	/**
	 * 单/双人上传分数提示框设置
	 */
	private void showUploadScore(){
		mainFrame.messageDialog.setTitle("上传分数");		//设置标题
		mainFrame.messageDialog.labelMessage.setText("游戏结束，是否上传游戏得分？？？");	//设置提示内容
		mainFrame.messageDialog.btnOk.setBtnText("上传");	//设置确定按钮文本
		mainFrame.messageDialog.btnOk.setVisible(true);		//显示确定按钮
		mainFrame.messageDialog.btnCancel.setBtnText("取消");	//设置取消按钮文本
		mainFrame.messageDialog.setVisible(true);	//显示提示框
	}
	/**
	 * 获取定期器执行周期
	 * @return	定期器执行周期
	 */
	public long getLoopTime() {
		return loopTime;
	}
	/**
	 * 设置新的定时器周期并通过反射修改新的执行周期
	 * @param gameLevel	新的定时器周期
	 */
	public void starNewLoopTime(long gameLevel) {
		setRunningOrPause(true);	//设置为游戏中
		this.loopTime = gameLevel;	//获取新的执行周期
		restartLevel();	//开始新的执行周期
	}
	/**
	 * 设置游戏状态，true为游戏中，false为暂停
	 * @param startGame 游戏状态,true为游戏中，false为暂停
	 */
	public void setRunningOrPause(boolean startGame) {
		this.runningOrPause = startGame;
	}
	/**
	 * 获取游戏状态
	 * @return 游戏状态，true为游戏中，false为暂停
	 */
	public boolean getRunningOrPause(){
		return runningOrPause;
	}
	/**
	 * 设置重新开始游戏的标志
	 * @param restartGame 重新开始游戏的标志,true为重新开始
	 */
	public void setRestartGame(boolean restartGame) {
		this.restartGame = restartGame;
	}
}
