package com.www.gamemodel;

import java.io.File;
import java.applet.Applet;
import java.applet.AudioClip;
import javax.swing.JOptionPane;
import java.net.MalformedURLException;
	/**
	 * 音频创建类
	 * @author wWw
	 *
	 */
public class SoundModel{
	/**@see 炸弹音频对象*/
	public AudioClip bombSound;		
	/**@see 旋转音频对象*/
	public AudioClip rotateSound;	
	/**@see 按钮音频对象*/
	public AudioClip buttonSound;	
	/**@see 游戏结束音频对象*/
	public AudioClip gameOverSound;	
	/**@see 方块到底音频对象*/
	public AudioClip cantMoveSound;	
	/**@see 背景音乐对象*/
	public AudioClip backgroupMusic;	
	/**@see 方块消除音频对象*/
	public AudioClip blockDisappearSound;	
	/**
	 * 创建各种音频对象
	 */
	public SoundModel() {
		bombSound();
		rotateSound();
		buttonSound();
		cantMoveSound();
		backgroupMusic();
		gameOverSounds();
		blockDisappearSound();
	}
	/**
	 * 背景音乐
	 */
	private void backgroupMusic() {
		try {
			File stepUrl=new File("sounds/backgroupmusic.wav");
			backgroupMusic=Applet.newAudioClip(stepUrl.toURI().toURL());	//从给定 URL 处获取音频剪辑。
		} catch (MalformedURLException e) {
			JOptionPane.showConfirmDialog(null, "找不到step.wav", "提示", JOptionPane.CLOSED_OPTION);
		}
	}
	/**
	 * 方块到达底部音效
	 */
	private void cantMoveSound() {
		try {
			File stepUrl=new File("sounds/cantmove.wav");
			cantMoveSound=Applet.newAudioClip(stepUrl.toURI().toURL());	//从给定 URL 处获取音频剪辑。
		} catch (MalformedURLException e) {
			JOptionPane.showConfirmDialog(null, "找不到backgroupmusic.wav", "提示", JOptionPane.CLOSED_OPTION);
		}
	}
	/**
	 * 方块消除音效
	 */
	private void blockDisappearSound() {
		try {
			File stepUrl=new File("sounds/blockdisappear.wav");
			blockDisappearSound=Applet.newAudioClip(stepUrl.toURI().toURL());	//从给定 URL 处获取音频剪辑。
		} catch (MalformedURLException e) {
			JOptionPane.showConfirmDialog(null, "blockdisappear.wav", "提示", JOptionPane.CLOSED_OPTION);
		}
	}
	/**
	 * 方块旋转音效
	 */
	private void rotateSound() {
		try {
			File stepUrl=new File("sounds/rotate.wav");
			rotateSound=Applet.newAudioClip(stepUrl.toURI().toURL());	//从给定 URL 处获取音频剪辑。
		} catch (MalformedURLException e) {
			JOptionPane.showConfirmDialog(null, "找不到rotate.wav", "提示", JOptionPane.CLOSED_OPTION);
		}
	}
	/**
	 * 游戏结束音效
	 */
	private void gameOverSounds() {
		try {
			File stepUrl=new File("sounds/ko.wav");
			gameOverSound=Applet.newAudioClip(stepUrl.toURI().toURL());	//从给定 URL 处获取音频剪辑。
		} catch (MalformedURLException e) {
			JOptionPane.showConfirmDialog(null, "找不到ko.wav", "提示", JOptionPane.CLOSED_OPTION);
		}
	}
	/**
	 * 鼠标进入按钮音效
	 */
	private void buttonSound() {
		try {
			File stepUrl=new File("sounds/button.wav");
			buttonSound=Applet.newAudioClip(stepUrl.toURI().toURL());	//从给定 URL 处获取音频剪辑。
		} catch (MalformedURLException e) {
			JOptionPane.showConfirmDialog(null, "找不到button.wav", "提示", JOptionPane.CLOSED_OPTION);
		}
	}
	/**
	 * 炸弹音效
	 */
	private void bombSound() {
		try {
			File stepUrl=new File("sounds/bomb.wav");
			bombSound=Applet.newAudioClip(stepUrl.toURI().toURL());	//从给定 URL 处获取音频剪辑。
		} catch (MalformedURLException e) {
			JOptionPane.showConfirmDialog(null, "找不到bomb.wav", "提示", JOptionPane.CLOSED_OPTION);
		}
	}
}
