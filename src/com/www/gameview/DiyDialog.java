package com.www.gameview;

import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JDialog;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFormattedTextField;
	/**
	 * 自定义对话框类
	 * @author wWw
	 *
	 */
@SuppressWarnings("serial")
public class DiyDialog extends JDialog {
	/**@see 下落速度滑条*/
	public JSlider slider;	
	/**@see 确定开始游戏按钮*/
	public MyButtonUI btnOk;	
	/**@see 游戏炸弹复选框*/
	public JCheckBox chckbxBoom;	
	/**@see 返回按钮*/
	public MyButtonUI btnCancel;	
	/**@see 方块自动上涨按钮*/
	public JCheckBox chckbxAutoUp;	
	/**@see 显示下落速度的标签*/
	private JLabel labelShowSpeed;	
	/**@see 主题一单选按钮*/
	public JRadioButton rdbtnStyle1;
	/**@see 主题二单选按钮*/
	public JRadioButton rdbtnStyle2;
	/**@see 主题三单选按钮*/
	public JRadioButton rdbtnStyle3;
	/**@see 选择自动上涨的行数*/
	public JSpinner spinnerAutoUpY;	
	/**@see 初级模式单选按钮*/
	public JRadioButton rdbtnSimple;
	/**@see 中级模式单选按钮*/
	public JRadioButton rdbtnMiddle;
	/**@see 高级模式单选按钮*/
	public JRadioButton rdbtnSenior;
	/**@see 网格线复选框*/
	public JCheckBox chckbxHlepLine;
	/**@see 方块预览复选框*/
	public JCheckBox chckbxShowNext;
	/**@see 模式自动升级复选框*/
	public JCheckBox chckbxAutoLevel;
	/**@see 下落速度控制复选框*/
	public JCheckBox chckbxCtrlSpeed;
	/**@see 游戏区域的列数*/
	public JSpinner spinnerPanelCol;
	/**@see 更该方块复选框*/
	public JCheckBox chckbxChangeNext;
	/**@see 方块随机出现复选框*/
	public JCheckBox chckbxRandomOpen;
	/**@see 窗体改变复选框*/
	public JCheckBox checkBoxChangeCol;
	/**@see 方块随机出现次数*/
	public JSpinner spinnerRandomTime;
	/**@see 鼠标进入按钮后的颜色*/
	private Color fouceColor=new Color(75, 209, 238, 255);	
	/**@see 按钮正常状态是的颜色*/
	private Color normalColor=new Color(75, 209, 238, 120);	
	/**
	 * 初始化各类组件
	 */
	public DiyDialog() {
		setTitle("自定义规则");
		setSize(450, 350);
		setVisible(false);
		setModal(true);
		setResizable(false);
		setFocusable(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/set.png"));
		setLocationRelativeTo(null);
		BackgroupPanel setPane=new BackgroupPanel();
		setContentPane(setPane);
		slider = new JSlider();
		slider.setMinimum(50);
		slider.setMaximum(1000);
		slider.setOpaque(false);
		slider.setFocusable(false);
		slider.setValue(50);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(100);
		slider.setMinorTickSpacing(50);
		slider.setForeground(MainFrame.FontColor);
		slider.setPaintLabels(true);
		slider.setBounds(78, 9, 210, 50);
		getContentPane().add(slider);
		JLabel labelSpeed = new JLabel("下落速度：");
		labelSpeed.setFont(new Font("幼圆", Font.PLAIN, 12));
		labelSpeed.setForeground(MainFrame.FontColor);
		labelSpeed.setBounds(10, 15, 71, 15);
		getContentPane().add(labelSpeed);
		labelShowSpeed = new JLabel("50");
		labelShowSpeed.setFont(new Font("幼圆", Font.PLAIN, 13));
		labelShowSpeed.setForeground(MainFrame.FontColor);
		labelShowSpeed.setBounds(298, 13, 30, 15);
		getContentPane().add(labelShowSpeed);
		JLabel labelBlockShape = new JLabel("方块形状：");
		labelBlockShape.setFont(new Font("幼圆", Font.PLAIN, 12));
		labelBlockShape.setForeground(MainFrame.FontColor);
		labelBlockShape.setBounds(10, 59, 71, 15);
		getContentPane().add(labelBlockShape);
		rdbtnSimple = new JRadioButton("初级");
		rdbtnSimple.setToolTipText("初级方块有7种方块");
		rdbtnSimple.setFont(new Font("幼圆", Font.PLAIN, 12));
		rdbtnSimple.setForeground(MainFrame.FontColor);
		rdbtnSimple.setOpaque(false);
		rdbtnSimple.setFocusable(false);
		rdbtnSimple.setBounds(80, 55, 65, 23);
		getContentPane().add(rdbtnSimple);
		rdbtnMiddle = new JRadioButton("中级");
		rdbtnMiddle.setToolTipText("中级方块有8种方块");
		rdbtnMiddle.setFont(new Font("幼圆", Font.PLAIN, 12));
		rdbtnMiddle.setForeground(MainFrame.FontColor);
		rdbtnMiddle.setOpaque(false);
		rdbtnMiddle.setFocusable(false);
		rdbtnMiddle.setBounds(180, 55, 77, 23);
		getContentPane().add(rdbtnMiddle);
		rdbtnSenior = new JRadioButton("高级");
		rdbtnSenior.setToolTipText("高级方块有9种方块");
		rdbtnSenior.setFont(new Font("幼圆", Font.PLAIN, 12));
		rdbtnSenior.setForeground(MainFrame.FontColor);
		rdbtnSenior.setOpaque(false);
		rdbtnSenior.setFocusable(false);
		rdbtnSenior.setBounds(280, 55, 71, 23);
		getContentPane().add(rdbtnSenior);
		ButtonGroup bGroup=new ButtonGroup();
		bGroup.add(rdbtnSimple);
		bGroup.add(rdbtnMiddle);
		bGroup.add(rdbtnSenior);
		chckbxAutoUp = new JCheckBox("方块自动上涨");
		chckbxAutoUp.setToolTipText("游戏中底部方块自动上涨");
		chckbxAutoUp.setFont(new Font("幼圆", Font.PLAIN, 12));
		chckbxAutoUp.setForeground(MainFrame.FontColor);
		chckbxAutoUp.setOpaque(false);
		chckbxAutoUp.setFocusable(false);
		chckbxAutoUp.setBounds(180, 166, 103, 23);
		getContentPane().add(chckbxAutoUp);
		btnCancel = new MyButtonUI();
		btnCancel.setBtnText("返回");
		btnCancel.setNormalColor(MainFrame.FontColor, 32, 16, normalColor, 200);
		btnCancel.setFoucesdColor(fouceColor);
		btnCancel.setOpaque(false);
		btnCancel.setFocusable(false);
		btnCancel.setBounds(330, 280, 93, 23);
		getContentPane().add(btnCancel);
		btnOk = new MyButtonUI();
		btnOk.setBtnText("开始游戏");
		btnOk.setNormalColor(MainFrame.FontColor, 18, 16, normalColor, 200);
		btnOk.setFoucesdColor(fouceColor);
		btnOk.setOpaque(false);
		btnOk.setFocusable(false);
		btnOk.setVisible(false);
		btnOk.setBounds(220, 280, 93, 23);
		getContentPane().add(btnOk);
		chckbxHlepLine = new JCheckBox("网格线");
		chckbxHlepLine.setToolTipText("方块区域网格线");
		chckbxHlepLine.setFont(new Font("幼圆", Font.PLAIN, 12));
		chckbxHlepLine.setForeground(MainFrame.FontColor);
		chckbxHlepLine.setOpaque(false);
		chckbxHlepLine.setFocusable(false);
		chckbxHlepLine.setBounds(80, 90, 77, 23);
		getContentPane().add(chckbxHlepLine);
		JLabel label = new JLabel("毫秒");
		label.setFont(new Font("幼圆", Font.PLAIN, 12));
		label.setForeground(MainFrame.FontColor);
		label.setBounds(328, 13, 30, 15);
		getContentPane().add(label);
		chckbxShowNext = new JCheckBox("方块预览");
		chckbxShowNext.setToolTipText("提示下一个方块的形状");
		chckbxShowNext.setFont(new Font("幼圆", Font.PLAIN, 12));
		chckbxShowNext.setForeground(MainFrame.FontColor);
		chckbxShowNext.setOpaque(false);
		chckbxShowNext.setSelected(true);
		chckbxShowNext.setFocusable(false);
		chckbxShowNext.setBounds(80, 130, 79, 23);
		getContentPane().add(chckbxShowNext);
		chckbxAutoLevel = new JCheckBox("自动升级模式");
		chckbxAutoLevel.setToolTipText("方块种数和下落速度自动增加和加快");
		chckbxAutoLevel.setFont(new Font("幼圆", Font.PLAIN, 12));
		chckbxAutoLevel.setForeground(MainFrame.FontColor);
		chckbxAutoLevel.setOpaque(false);
		chckbxAutoLevel.setFocusable(false);
		chckbxAutoLevel.setBounds(180, 130, 103, 23);
		getContentPane().add(chckbxAutoLevel);
		chckbxChangeNext = new JCheckBox("更换方块");
		chckbxChangeNext.setToolTipText("允许更换下一个方块");
		chckbxChangeNext.setFont(new Font("幼圆", Font.PLAIN, 12));
		chckbxChangeNext.setForeground(MainFrame.FontColor);
		chckbxChangeNext.setFocusable(false);
		chckbxChangeNext.setOpaque(false);
		chckbxChangeNext.setBounds(80, 166, 100, 23);
		getContentPane().add(chckbxChangeNext);
		chckbxCtrlSpeed = new JCheckBox("控制下落速度");
		chckbxCtrlSpeed.setToolTipText("非模式自动升级下方块下落速度的控制");
		chckbxCtrlSpeed.setFont(new Font("幼圆", Font.PLAIN, 12));
		chckbxCtrlSpeed.setForeground(MainFrame.FontColor);
		chckbxCtrlSpeed.setFocusable(false);
		chckbxCtrlSpeed.setOpaque(false);
		chckbxCtrlSpeed.setBounds(80, 202, 103, 23);
		getContentPane().add(chckbxCtrlSpeed);
		chckbxBoom = new JCheckBox("游戏炸弹");
		chckbxBoom.setToolTipText("游戏中随机出现炸弹");
		chckbxBoom.setFont(new Font("幼圆", Font.PLAIN, 12));
		chckbxBoom.setForeground(MainFrame.FontColor);
		chckbxBoom.setOpaque(false);
		chckbxBoom.setFocusable(false);
		chckbxBoom.setBounds(180, 90, 103, 23);
		getContentPane().add(chckbxBoom);
		spinnerAutoUpY = new JSpinner();
		spinnerAutoUpY.setFont(new Font("幼圆", Font.PLAIN, 12));
		spinnerAutoUpY.setOpaque(false);
		spinnerAutoUpY.setToolTipText("设置要自动上涨的行数");
		spinnerAutoUpY.setFocusable(false);
		spinnerAutoUpY.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerAutoUpY.setEditor(new JSpinner.NumberEditor(spinnerAutoUpY, "0"));
		JFormattedTextField textField1 = ((JSpinner.NumberEditor)spinnerAutoUpY.getEditor()).getTextField();
        textField1.setEditable(false);//禁止输入输入功能
		spinnerAutoUpY.setBounds(285, 166, 35, 22);
		getContentPane().add(spinnerAutoUpY);
		JLabel lblNewLabel_2 = new JLabel("行");
		lblNewLabel_2.setFont(new Font("幼圆", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(MainFrame.FontColor);
		lblNewLabel_2.setBounds(330, 170, 30, 15);
		getContentPane().add(lblNewLabel_2);
		chckbxRandomOpen = new JCheckBox("随机出现方块");
		chckbxRandomOpen.setFont(new Font("幼圆", Font.PLAIN, 12));
		chckbxRandomOpen.setForeground(MainFrame.FontColor);
		chckbxRandomOpen.setToolTipText("下落区域随机出现方块");
		chckbxRandomOpen.setOpaque(false);
		chckbxRandomOpen.setFocusable(false);
		chckbxRandomOpen.setBounds(280, 89, 103, 23);
		getContentPane().add(chckbxRandomOpen);
		spinnerRandomTime = new JSpinner();
		spinnerRandomTime.setFont(new Font("幼圆", Font.PLAIN, 12));
		spinnerRandomTime.setForeground(MainFrame.FontColor);
		spinnerRandomTime.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinnerRandomTime.setEditor(new JSpinner.NumberEditor(spinnerRandomTime, "0"));
		JFormattedTextField textField2 = ((JSpinner.NumberEditor)spinnerRandomTime.getEditor()).getTextField();
        textField2.setEditable(false);//禁止输入输入功能
		spinnerRandomTime.setBounds(375, 90, 35, 22);
		getContentPane().add(spinnerRandomTime);
		JLabel lblNewLabel_3 = new JLabel("次");
		lblNewLabel_3.setFont(new Font("幼圆", Font.PLAIN, 12));
		lblNewLabel_3.setForeground(MainFrame.FontColor);
		lblNewLabel_3.setBounds(415, 93, 26, 15);
		getContentPane().add(lblNewLabel_3);
		JLabel lblFunction = new JLabel("功能区：");
		lblFunction.setForeground(MainFrame.FontColor);
		lblFunction.setFont(new Font("幼圆", Font.PLAIN, 12));
		lblFunction.setBounds(10, 93, 54, 15);
		getContentPane().add(lblFunction);
		spinnerPanelCol =new JSpinner();
		spinnerPanelCol.setModel(new SpinnerNumberModel(15, 15, 24, 1)); 
		spinnerPanelCol.setEditor(new JSpinner.NumberEditor(spinnerPanelCol, "0"));
		JFormattedTextField textField3 = ((JSpinner.NumberEditor)spinnerPanelCol.getEditor()).getTextField();
        textField3.setEditable(false);//禁止输入输入功能
		spinnerPanelCol.setBounds(375, 130, 35, 22);
		setPane.add(spinnerPanelCol); 
		JLabel lblNewLabel_5 = new JLabel("格");
		lblNewLabel_5.setForeground(MainFrame.FontColor);
		lblNewLabel_5.setFont(new Font("幼圆", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(415, 134, 19, 15);
		setPane.add(lblNewLabel_5);
		checkBoxChangeCol = new JCheckBox("游戏区域宽度：");
		checkBoxChangeCol.setToolTipText("修改方块下落区域的列数，修改完成后游戏将重新开始");
		checkBoxChangeCol.setOpaque(false);
		checkBoxChangeCol.setForeground(MainFrame.FontColor);
		checkBoxChangeCol.setFont(new Font("幼圆", Font.PLAIN, 12));
		checkBoxChangeCol.setFocusable(false);
		checkBoxChangeCol.setBounds(280, 130, 130, 23);
		setPane.add(checkBoxChangeCol);
		JLabel lblNewLabel = new JLabel("界面主题：");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("幼圆", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 243, 71, 15);
		setPane.add(lblNewLabel);
		ButtonGroup bGroup1=new ButtonGroup();
		rdbtnStyle1 = new JRadioButton("主题一");
		rdbtnStyle1.setFont(new Font("幼圆", Font.PLAIN, 12));
		rdbtnStyle1.setForeground(MainFrame.FontColor);
		rdbtnStyle1.setOpaque(false);
		rdbtnStyle1.setSelected(true);
		rdbtnStyle1.setFocusable(false);
		rdbtnStyle1.setBounds(80, 238, 93, 23);
		setPane.add(rdbtnStyle1);
		rdbtnStyle2 = new JRadioButton("主题二");
		rdbtnStyle2.setFont(new Font("幼圆", Font.PLAIN, 12));
		rdbtnStyle2.setForeground(MainFrame.FontColor);
		rdbtnStyle2.setOpaque(false);
		rdbtnStyle2.setFocusable(false);
		rdbtnStyle1.setFocusable(false);
		rdbtnStyle2.setBounds(180, 237, 71, 23);
		setPane.add(rdbtnStyle2);
		rdbtnStyle3 = new JRadioButton("主题三");
		rdbtnStyle3.setForeground(MainFrame.FontColor);
		rdbtnStyle3.setFont(new Font("幼圆", Font.PLAIN, 12));
		rdbtnStyle3.setOpaque(false);
		rdbtnStyle3.setFocusable(false);
		rdbtnStyle3.setBounds(280, 236, 121, 23);
		setPane.add(rdbtnStyle3);
		bGroup1.add(rdbtnStyle1);
		bGroup1.add(rdbtnStyle2);
		bGroup1.add(rdbtnStyle3);
	}
	/**
	 * 设置标签显示滑条的值
	 * @param looptime 要显示的定时器执行周期
	 */
	public void showLoopTime(int looptime){
		labelShowSpeed.setText(Integer.toString(looptime));
	}
}
