package com.www.gameview;

import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
	/**
	 * 游戏说明对话框类
	 * @author wWw
	 *
	 */
@SuppressWarnings("serial")
public class ExplainDialog extends JDialog {
	/**@see 游戏说明文本区*/
	private JTextArea textArea;
	/**
	 * 初始化对话框中的组件
	 */
	public ExplainDialog() {
		setTitle("游戏说明");
		setSize(450, 400);
		setVisible(false);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/question.png"));
		BackgroupPanel explainPanel=new BackgroupPanel();
		explainPanel.setLayout(new BorderLayout());
		setContentPane(explainPanel);
		textArea= new JTextArea();
		textArea.setOpaque(false);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("幼圆", Font.PLAIN, 14));
		textArea.setForeground(MainFrame.FontColor);
		textArea.append("游戏操作指南：\n"
				+ "1、可以从菜单栏的“游戏”中选择单/双人游戏开始的游戏模式，若一开始从自定义游戏规则中选择开始游戏，则当前为单人游戏。若是在游戏过程中自定义游戏规则后再开始游戏，则是根据当前的游戏模式开始新的游戏。\n"
				+ "2、单/双人游戏初级模式：随机出现7种基本方块，方块自由下落时间为500ms。\n"
				+ "3、单/双人游戏中级模式：随机出现7种基本方块+凹形方块，方块自由下落时间为300ms。\n"
				+ "4、单/双人游戏高级模式：随机出现7种基本方块+凹形方块+十字形方块，方块自由下落时间为100ms。\n"
				+ "5、方块每消除一行分数加10分，出现炸弹时消除炸弹所在行和列并加20分。\n"
				+ "6、网格线：方块下落区域的网格线，辅助游戏方块移动。\n"
				+ "7、游戏炸弹：选中后在游戏中将随机出现炸弹，炸弹能消除炸弹所在行和列并加20分。\n"
				+ "8、随机出现方块次数：当方块到达底部时，在方块下落区域的底部10行方格内随机出现方块。"
				+ "9、方块预览：是否提示显示下一个方块。\n"
				+ "10、自动升级模式：根据当前游戏分数从对应的等级玩起，共四个等级，分别为：初级（0-200）、中级（200-400）、高级（400-600）、变态（>600）。每个等级分数每增加200分将进入下一个等级，变态等级方块种类和高级一样，下落速度为50ms。\n"
				+ "11、游戏区域宽度格数：改变方块下落区域的列数，并开始新的游戏。只有单人游戏才可以修改宽度，最大为24格，双人游戏固定为25格。\n"
				+ "12、更换方块：更换预览区中显示的方块。\n"
				+ "13、方块自动上涨行数：当方块到达底部时，在方块下落区域最后一行中随机出现方块，随机次数为下落区域的列数。\n"
				+ "14、控制下落速度：控制方块自由下落的速度，可以加快/减慢速度。\n"
				+ "15、界面主题：选择游戏所有界面的背景图片。\n"
				+ "16、功能区的选择可以与任意模式搭配。\n"
				+ "17、自定义游戏的选项意思皆有提示。\n"
				+ "18、必须注册玩家才能上传游戏分数到超神榜。\n"
				+ "19、以上的设置在单/双人游戏中皆可适用。\n"
				+ "20、游戏界面主题背景可以从自定义对话框中选择，或者添加自己想要的图片到image文件夹，覆盖style1.jpg、style2.jpg、style3.jpg图片后再从自定义里选择新主题。\n"
				+ "21、游戏界面的按钮需开始游戏后才能显示。");
		JScrollPane textAreaScrollPane=new JScrollPane(textArea);
		textAreaScrollPane.setOpaque(false);
		textAreaScrollPane.getViewport().setOpaque(false);
		getContentPane().add(textAreaScrollPane, BorderLayout.CENTER);
	}
}
