package com.www.gamemodel;

import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
	/**
	 * 文件读取写入类
	 * @author wWw
	 *
	 */
public class RWScore {
	/**@see 文件对象*/
	private File scoreFile;	
	/**@see 读取缓存对象*/
	private BufferedReader reader;	
	/**@see 写入缓存对象*/
	private BufferedWriter writer;	
	/**@see 文件输入流对象*/
	private FileInputStream fileInputStream;	
	/**@see 文件输出流对象*/
	private FileOutputStream fileOutputStream;	
	/**@see 读取输入流*/
	private InputStreamReader inputStreamReader;	
	/**@see 写入输出流*/
	private OutputStreamWriter outputStreamWriter;	
	/**@see 从文本读取完所有分数数据后的缓存集合*/
	private HashMap<Integer, String> scoreList=new HashMap<Integer, String>();	
	/**@see 缓存初级模式的分数数据集合*/
	public HashMap<Integer, String> simpleScoreList=new HashMap<Integer, String>();	
	/**@see 缓存中级模式的分数数据集合*/
	public HashMap<Integer, String> middleScoreList=new HashMap<Integer, String>();	
	/**@see 缓存高级模式的分数数据集合*/
	public HashMap<Integer, String> seniorScoreList=new HashMap<Integer, String>();	
	/**@see 缓存变态模式的分数数据集合*/
	public HashMap<Integer, String> nonhumanScoreList=new HashMap<Integer, String>();	
	/**
	 * 创建文件对象，判断文本是否存在
	 */
	public RWScore() {
		scoreFile =new File("score.w");
		createScoreFile();
	}
	/**
	 * 读取文件内容
	 */
	public void readerScore(){
		scoreList.clear();	//集合清除数据
		if (scoreFile.exists()) {	//文件存在则读取文件
			int i=0;
			try {
				createInStream();	//创建输入流
				String line;
				while ((line=reader.readLine())!=null) {	//等待文件数据读取完毕
					scoreList.put(i++, line);	//数据写入集合中
				}
				reader.close();	//关闭输入流
				inputStreamReader.close();
				fileInputStream.close();
				scoreGroup();	//分数类型分类
			} catch (IOException e) {
				JOptionPane.showConfirmDialog(null, "无法对文件读取！", "提示", JOptionPane.CLOSED_OPTION);
			}
		}else {	//文件不存在则创建文件
			createScoreFile();
			readerScore();
		}
	}
	/**
	 * 将接收到的数据分类存在不同的集合中
	 */
	private void scoreGroup(){
		try {
			int i=0; int j=0; int k=0; int z=0;
			simpleScoreList.clear();	//清除集合数据
			middleScoreList.clear();
			seniorScoreList.clear();
			nonhumanScoreList.clear();
			for (int x = 0; x < scoreList.size(); x++) {
				if (scoreList.get(x).substring(scoreList.get(x).indexOf("Model:")+6, scoreList.get(x).indexOf("Score:")).equals("初级模式")) {
					simpleScoreList.put(i++, scoreList.get(x));	//接收初级模式玩家的数据
				}else if (scoreList.get(x).substring(scoreList.get(x).indexOf("Model:")+6, scoreList.get(x).indexOf("Score:")).equals("中级模式")) {
					middleScoreList.put(j++, scoreList.get(x));	//接收中级模式的玩家数据
				}else if (scoreList.get(x).substring(scoreList.get(x).indexOf("Model:")+6, scoreList.get(x).indexOf("Score:")).equals("高级模式")) {
					seniorScoreList.put(k++, scoreList.get(x));	//接收高级模式的玩家数据
				}else if (scoreList.get(x).substring(scoreList.get(x).indexOf("Model:")+6, scoreList.get(x).indexOf("Score:")).equals("变态模式")) {
					nonhumanScoreList.put(z++, scoreList.get(x));	//接收变态模式的玩家数据
				}
			}
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(null, "文件被不法分子改动，请报警！！！", "提示", JOptionPane.CLOSED_OPTION);
		}
	}
	/**
	 * 写入数据到文本中
	 *1、输出完毕后不关闭输出流则无法在txt中写入数据
	 *2、输出完毕后只关闭输出流不重新赋值流对象则只能写入一次
	 *3、重新赋值流对象后输出完再关闭输出流，则写入没有问题
	 */
	public void writeScore(String str){
		if (scoreFile.exists()) {	//文件存在则写入数据
			try {
				createOutStream();	//创建输出流
				writer.write(str+"\n");	//写入数据
				writer.close();	//关闭输出流
				outputStreamWriter.close();
				fileOutputStream.close();
			} catch (IOException e) {
				JOptionPane.showConfirmDialog(null, "无法对文件写入！", "提示", JOptionPane.CLOSED_OPTION);
			}
		}else {	//文件不存在则创建文件
			createScoreFile();
			writeScore(str);
		}
	}
	/**
	 * 分数排序：利用冒泡排序
	 * 1、分数排序必须保证最后一行为空，且只能是最后一行才能为空
	 */
	public void scoreRank(){
		readerScore();	//获取文本数据
		for (int i = 0; i < scoreList.size() -1; i++){    //冒泡排序，最多做n-1趟排序  
			for(int j = 0 ;j < scoreList.size()-i-1; j++){   
				int x=Integer.parseInt(scoreList.get(j).substring(scoreList.get(j).indexOf("Score:")+6, scoreList.get(j).length()));
				int y=Integer.parseInt(scoreList.get(j+1).substring(scoreList.get(j+1).indexOf("Score:")+6, scoreList.get(j+1).length()));
				if(x<y){ 
					String temp=scoreList.get(j);
					scoreList.put(j, scoreList.get(j+1));
					scoreList.put(j+1, temp);
				}  
			}   
		}
		scoreFile.delete();	//删除文件
		createScoreFile();	//重新创建文件
		for (int i = 0; i < scoreList.size(); i++) {
			writeScore(scoreList.get(i)); 	//重新写入数据
		}
	}
	/**
	 * 文件不存在则创建文件
	 */
	private void createScoreFile(){
		if (!scoreFile.exists()) {
			try {
				scoreFile.createNewFile();
			} catch (IOException e) {
				JOptionPane.showConfirmDialog(null, "无法创建文件！", "提示", JOptionPane.CLOSED_OPTION);
			}
		}
	}
	/**
	 * 文件存在创建输入流
	 */
	private void createInStream(){
		if (scoreFile.exists()) {
			try {
				fileInputStream=new FileInputStream(scoreFile);	//创建文件输入流对象
				inputStreamReader=new InputStreamReader(fileInputStream);//创建输入流对象
				reader=new BufferedReader(inputStreamReader);	//创建读取缓存对象
			} catch (FileNotFoundException e) {
				JOptionPane.showConfirmDialog(null, "无法创建输入流！", "提示", JOptionPane.CLOSED_OPTION);
			}
		}
	}
	/**
	 * 文件存在创建输出流
	 */
	private void createOutStream(){
		if (scoreFile.exists()) {
			try {
				fileOutputStream=new FileOutputStream(scoreFile,true);	//创建文件输出流对象
				outputStreamWriter=new OutputStreamWriter(fileOutputStream);	//创建输出流对象
				writer=new BufferedWriter(outputStreamWriter);	//创建写入缓存对象
			} catch (FileNotFoundException e) {
				JOptionPane.showConfirmDialog(null, "无法创建输出流！", "提示", JOptionPane.CLOSED_OPTION);
			}
		}
	}
}
