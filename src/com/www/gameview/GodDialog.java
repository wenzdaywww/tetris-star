package com.www.gameview;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
	/**
	 * 分数榜对话框类
	 * @author wWw
	 *
	 */
@SuppressWarnings("serial")
public class GodDialog extends JDialog {
	/**@see 高级模式最后一名标签*/
	public JLabel seniorLastUser;	
	/**@see 初级模式最后一名标签*/
	public JLabel simpleLastUser;	
	/**@see 中级模式最后一名标签*/
	public JLabel middleLastUser;	
	/**@see 初级模式第一名标签*/
	public JLabel simpleFirstUser;	
	/**@see 初级模式第三名标签*/
	public JLabel simpleThirdUser;	
	/**@see 中级模式第一名标签*/
	public JLabel middleFirstUser;		
	/**@see 中级模式第三名标签*/
	public JLabel middleThirdUser;	
	/**@see 高级模式第一名标签*/
	public JLabel seniorFirstUser;	
	/**@see 高级模式第三名标签*/
	public JLabel seniorThirdUser;	
	/**@see 中级模式第二名标签*/
	public JLabel middleSecondUser;	
	/**@see 初级模式第二名标签*/
	public JLabel simpleSecondUser;	
	/**@see 高级模式第二名标签*/
	public JLabel seniorSecondUser;	
	/**@see 变态模式最后一名标签*/
	public JLabel nonhumanLastUser;	
	/**@see 中级榜玩家分数显示文本*/
	public JTextArea middleTextArea;
	/**@see 高级榜玩家分数显示文本*/
	public JTextArea seniorTextArea;
	/**@see 初级榜玩家分数显示文本*/
	public JTextArea simpleTextArea;
	/**@see 变态模式第三名标签*/
	public JLabel nonhumanThirdUser;
	/**@see 变态模式第一名标签*/
	public JLabel nonhumanFirstUser;
	/**@see 变态模式第二名标签*/
	public JLabel nonhumanSecondUser;
	/**@see 变态榜玩家分数显示文本*/
	public JTextArea nonhumanTextArea;
	/**
	 * 初始化各组件
	 */
	public GodDialog() {
		setTitle("超神榜");
		setSize(480, 300);
		setVisible(false);
		setModal(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/god.png"));
		BackgroupPanel godPanel=new BackgroupPanel();
		godPanel.setLayout(new BorderLayout());
		JTabbedPane tabbedPane=new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFocusable(false);
		tabbedPane.setOpaque(false);
		//初级榜组件初始化
		BackgroupPanel simpleGodPanel=new BackgroupPanel();
		simpleGodPanel.setLayout(null);
		simpleFirstUser=new JLabel("第一名");
		simpleFirstUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		simpleFirstUser.setForeground(MainFrame.FontColor);
		simpleFirstUser.setBounds(90, 5, 100, 20);
		simpleGodPanel.add(simpleFirstUser);
		simpleSecondUser=new JLabel("第二名");
		simpleSecondUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		simpleSecondUser.setForeground(MainFrame.FontColor);
		simpleSecondUser.setBounds(5, 28, 100, 20);
		simpleGodPanel.add(simpleSecondUser);
		simpleThirdUser=new JLabel("第三名");
		simpleThirdUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		simpleThirdUser.setForeground(MainFrame.FontColor);
		simpleThirdUser.setBounds(130, 33, 80, 20);
		simpleGodPanel.add(simpleThirdUser);
		simpleLastUser=new JLabel("最后一名");
		simpleLastUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		simpleLastUser.setForeground(MainFrame.FontColor);
		simpleLastUser.setBounds(85, 207, 80, 20);
		simpleGodPanel.add(simpleLastUser);
		RankPanel simpleRankPanel=new RankPanel();
		simpleRankPanel.setOpaque(false);
		simpleRankPanel.setBounds(0, 0, 200, 240);
		simpleGodPanel.add(simpleRankPanel);
		simpleTextArea=new JTextArea();
		simpleTextArea.setBounds(230, 0, 240, 243);
		simpleTextArea.setOpaque(false);
		simpleTextArea.setEditable(false);
		simpleTextArea.setFont(new Font("幼圆", Font.PLAIN, 15));
		simpleTextArea.setForeground(MainFrame.FontColor);
		JScrollPane simpleScrollPane=new JScrollPane(simpleTextArea);
		simpleScrollPane.setBounds(230, 0, 240, 243);
		simpleScrollPane.setOpaque(false);
		simpleScrollPane.getViewport().setOpaque(false);
		simpleGodPanel.add(simpleScrollPane);
		//中级榜组件初始化
		BackgroupPanel middleGodPanel=new BackgroupPanel();
		middleGodPanel.setLayout(null);
		middleFirstUser=new JLabel("第一名");
		middleFirstUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		middleFirstUser.setForeground(MainFrame.FontColor);
		middleFirstUser.setBounds(90, 5, 100, 20);
		middleGodPanel.add(middleFirstUser);
		middleSecondUser=new JLabel("第二名");
		middleSecondUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		middleSecondUser.setForeground(MainFrame.FontColor);
		middleSecondUser.setBounds(5, 28, 100, 20);
		middleGodPanel.add(middleSecondUser);
		middleThirdUser=new JLabel("第三名");
		middleThirdUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		middleThirdUser.setForeground(MainFrame.FontColor);
		middleThirdUser.setBounds(130, 33, 80, 20);
		middleGodPanel.add(middleThirdUser);
		middleLastUser=new JLabel("最后一名");
		middleLastUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		middleLastUser.setForeground(MainFrame.FontColor);
		middleLastUser.setBounds(85, 207, 80, 20);
		middleGodPanel.add(middleLastUser);
		RankPanel middleRankPanel=new RankPanel();
		middleRankPanel.setOpaque(false);
		middleRankPanel.setBounds(0, 0, 200, 240);
		middleGodPanel.add(middleRankPanel);
		middleTextArea=new JTextArea();
		middleTextArea.setBounds(230, 0, 240, 243);
		middleTextArea.setOpaque(false);
		middleTextArea.setEditable(false);
		middleTextArea.setFont(new Font("幼圆", Font.PLAIN, 15));
		middleTextArea.setForeground(MainFrame.FontColor);
		JScrollPane middleScrollPane=new JScrollPane(middleTextArea);
		middleScrollPane.setBounds(230, 0, 240, 243);
		middleScrollPane.setOpaque(false);
		middleScrollPane.getViewport().setOpaque(false);
		middleGodPanel.add(middleScrollPane);
		//高级榜组件初始化
		BackgroupPanel seniorGodPanel=new BackgroupPanel();
		seniorGodPanel.setLayout(null);
		seniorFirstUser=new JLabel("第一名");
		seniorFirstUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		seniorFirstUser.setForeground(MainFrame.FontColor);
		seniorFirstUser.setBounds(90, 5, 100, 20);
		seniorGodPanel.add(seniorFirstUser);
		seniorSecondUser=new JLabel("第二名");
		seniorSecondUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		seniorSecondUser.setForeground(MainFrame.FontColor);
		seniorSecondUser.setBounds(5, 28, 100, 20);
		seniorGodPanel.add(seniorSecondUser);
		seniorThirdUser=new JLabel("第三名");
		seniorThirdUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		seniorThirdUser.setForeground(MainFrame.FontColor);
		seniorThirdUser.setBounds(130, 33, 80, 20);
		seniorGodPanel.add(seniorThirdUser);
		seniorLastUser=new JLabel("最后一名");
		seniorLastUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		seniorLastUser.setForeground(MainFrame.FontColor);
		seniorLastUser.setBounds(85, 207, 100, 20);
		seniorGodPanel.add(seniorLastUser);
		RankPanel seniorRankPanel=new RankPanel();
		seniorRankPanel.setOpaque(false);
		seniorRankPanel.setBounds(0, 0, 200, 240);
		seniorGodPanel.add(seniorRankPanel);
		seniorTextArea=new JTextArea();
		seniorTextArea.setBounds(230, 0, 240, 243);
		seniorTextArea.setOpaque(false);
		seniorTextArea.setEditable(false);
		seniorTextArea.setFont(new Font("幼圆", Font.PLAIN, 15));
		seniorTextArea.setForeground(MainFrame.FontColor);
		JScrollPane senioeScrollPane=new JScrollPane(seniorTextArea);
		senioeScrollPane.setBounds(230, 0, 240, 243);
		senioeScrollPane.setOpaque(false);
		senioeScrollPane.getViewport().setOpaque(false);
		seniorGodPanel.add(senioeScrollPane);
		//变态榜组件初始化
		BackgroupPanel nonhumanGodPanel=new BackgroupPanel();
		nonhumanGodPanel.setLayout(null);
		nonhumanFirstUser=new JLabel("第一名");
		nonhumanFirstUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		nonhumanFirstUser.setForeground(MainFrame.FontColor);
		nonhumanFirstUser.setBounds(90, 5, 100, 20);
		nonhumanGodPanel.add(nonhumanFirstUser);
		nonhumanSecondUser=new JLabel("第二名");
		nonhumanSecondUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		nonhumanSecondUser.setForeground(MainFrame.FontColor);
		nonhumanSecondUser.setBounds(5, 28, 100, 20);
		nonhumanGodPanel.add(nonhumanSecondUser);
		nonhumanThirdUser=new JLabel("第三名");
		nonhumanThirdUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		nonhumanThirdUser.setForeground(MainFrame.FontColor);
		nonhumanThirdUser.setBounds(130, 33, 80, 20);
		nonhumanGodPanel.add(nonhumanThirdUser);
		nonhumanLastUser=new JLabel("最后一名");
		nonhumanLastUser.setFont(new Font("幼圆", Font.PLAIN, 15));
		nonhumanLastUser.setForeground(MainFrame.FontColor);
		nonhumanLastUser.setBounds(85, 207, 80, 20);
		nonhumanGodPanel.add(nonhumanLastUser);
		RankPanel nonhumanRankPanel=new RankPanel();
		nonhumanRankPanel.setOpaque(false);
		nonhumanRankPanel.setBounds(0, 0, 200, 240);
		nonhumanGodPanel.add(nonhumanRankPanel);
		nonhumanTextArea=new JTextArea();
		nonhumanTextArea.setBounds(230, 0, 240, 243);
		nonhumanTextArea.setOpaque(false);
		nonhumanTextArea.setEditable(false);
		nonhumanTextArea.setFont(new Font("幼圆", Font.PLAIN, 15));
		nonhumanTextArea.setForeground(MainFrame.FontColor);
		JScrollPane nonhumanScrollPane=new JScrollPane(nonhumanTextArea);
		nonhumanScrollPane.setBounds(230, 0, 240, 243);
		nonhumanScrollPane.setOpaque(false);
		nonhumanScrollPane.getViewport().setOpaque(false);
		nonhumanGodPanel.add(nonhumanScrollPane);
		tabbedPane.add("初级榜",simpleGodPanel);
		tabbedPane.add("中级榜",middleGodPanel);
		tabbedPane.add("高级榜",seniorGodPanel);
		tabbedPane.add("变态榜",nonhumanGodPanel);
		godPanel.add(tabbedPane,BorderLayout.CENTER);
		setContentPane(godPanel);
	}
	/**
	 * 有领奖台图片背景的容器
	 * @author wWw
	 *
	 */
	private class RankPanel extends JPanel{
		@Override
		protected void paintComponent(Graphics g) {
			Image image=Toolkit.getDefaultToolkit().getImage("image/rank.png");
			g.drawImage(image, 0, 0, this);
		}
	}
}
