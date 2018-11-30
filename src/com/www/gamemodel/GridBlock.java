package com.www.gamemodel;

import java.util.Random;
import java.util.HashMap;
import java.util.ArrayList;

public class GridBlock {
	/**@see 游戏分数*/
	public int socre=0;	
	/** @see 随机到的第几个方块*/
	public int shapeNum =0;
	/**@see 下落区域列数*/
	public static int COL=15;	
	/**@see 下落区域行数*/
	public static int ROW=25;	
	/**@see 网格大小*/
	public static final int SIZE=20;	
	/** @see 存放随机选择后的图片*/
	public static String shapeImage="";
	/** @see 当前正在下落的方块对象*/
	private BaseShape player1NowShape;
	/** @see 当前正在下落的方块对象*/
	private BaseShape player2NowShape;
	/**@see 玩家1方块对象*/
	public BaseShape player1BaseShape;	
	/**@see 玩家2方块对象*/
	public BaseShape player2BaseShape;	
	/** @see 设置模式*/
	public String level="初级模式";
	/**@see 是否绘制网格线*/
	public static boolean helpLine=false;	
	/** @see 所有图片集合*/
	private ArrayList<String> imageList=new ArrayList<String>();
	/** @see 玩家1所有方块形状集合*/
	private ArrayList<BaseShape> player1ShapeList=new ArrayList<BaseShape>();
	/** @see 玩家2所有方块形状集合*/
	private ArrayList<BaseShape> player2ShapeList=new ArrayList<BaseShape>();
	/**@see 用于存放是否绘制网格的集合*/
	public static HashMap<Integer, DrawArea> xyList=new HashMap<Integer, DrawArea>();
	/**
	 * 网格模型构造方法初始化玩家1、2的方块集合
	 */
	public GridBlock() {
		clearBlockPanel();
		initShape();
	}
	/**
	 * 下落区域初始化清除
	 */
	public static void clearBlockPanel(){
		xyList.clear();
		for (int i = 1; i <= COL*ROW; i++) {
			xyList.put(i, new DrawArea(intToShapeX(i),intToShapeY(i),false,"",false));
		}
	}
	/**
	 * 玩家1左移动下落区域中的方块
	 */
	public void player1Left(){
		player1BaseShape.left();
	}
	/**
	 * 玩家1右移动下落区域中的方块
	 */
	public void player1Right(){
		player1BaseShape.right();
	}
	/**
	 * 玩家1加快下落区域中方块的下落速度
	 */
	public void player1Down(){
		player1BaseShape.down();
	}
	/**
	 * 玩家1旋转下落区域中的方块
	 */
	public void player1Rotate(){
		player1BaseShape.rotate();
	}
	/**
	 * 玩家1判断下落区域中的方块是否能继续移动
	 * @return
	 */
	public boolean player1IsCanMove(){
		return player1BaseShape.isCanMove();
	}
	/**
	 * 玩家1判断游戏是否结束
	 * @return
	 */
	public boolean player1IsGameOver(){
		return player1BaseShape.isGameOver();
	}
	/**
	 * 玩家2左移动下落区域中的方块
	 */
	public void player2Left(){
		player2BaseShape.left();
	}
	/**
	 * 玩家2右移动下落区域中的方块
	 */
	public void player2Right(){
		player2BaseShape.right();
	}
	/**
	 * 玩家2加快下落区域中方块的下落速度
	 */
	public void player2Down(){
		player2BaseShape.down();
	}
	/**
	 * 玩家2旋转下落区域中的方块
	 */
	public void player2Rotate(){
		player2BaseShape.rotate();
	}
	/**
	 * 玩家2判断下落区域中的方块是否能继续移动
	 * @return
	 */
	public boolean player2IsCanMove(){
		return player2BaseShape.isCanMove();
	}
	/**
	 * 玩家2判断游戏是否结束
	 * @return
	 */
	public boolean player2IsGameOver(){
		return player2BaseShape.isGameOver();
	}
	/**
	 * 玩家1重新设置游戏开始
	 */
	public void player1SetGameOverToRestart(){
		player1BaseShape.setGameOverToRestart(false);
	}
	/**
	 * 玩家2重新设置游戏开始
	 */
	public void player2SetGameOverToRestart(){
		player2BaseShape.setGameOverToRestart(false);
	}
	/**
	 * 方块添加到下落区域中
	 * @param baseShape 具体方块类型
	 */
	private void player1InitBlock(BaseShape baseShape){
		this.player1BaseShape=baseShape;
		this.player1BaseShape.init();
		this.player1BaseShape.gameOver=this.player1BaseShape.initGameOver();
	}
	/**
	 * 方块添加到下落区域中
	 * @param baseShape 具体方块类型
	 */
	private void player2InitBlock(BaseShape baseShape){
		this.player2BaseShape=baseShape;
		this.player2BaseShape.init();
		for (int i = 0; i < this.player2BaseShape.shapeX.length; i++) {
			this.player2BaseShape.shapeX[i]+=10;
		}
		this.player2BaseShape.gameOver=this.player2BaseShape.initGameOver();
	}
	/**
	 * 用于重新产生玩家1方块
	 */
	public void player1CreateShape(boolean haveBomb){
		player1NowShape=player1ShapeList.get(shapeNum);	//获取玩家1的方块
		player1NowShape.setImage(shapeImage);	//设置当前方块图片
		player1InitBlock(player1NowShape);	//初始化玩家1的方块位置
		randomShape(haveBomb);
	}
	/**
	 * 用于重新产生玩家2方块
	 */
	public void player2CreateShape(boolean haveBomb){
		player2NowShape=player2ShapeList.get(shapeNum);	//获取玩家2的方块
		player2NowShape.setImage(shapeImage);	//设置当前方块图片
		player2InitBlock(player2NowShape);	//初始化玩家2的方块位置
		randomShape(haveBomb);
	}
	/**
	 * 将方块序号转换成对应X坐标
	 * @param i	方块序号
	 * @return	X坐标
	 */
	private static int intToShapeX(int i){
		if ((i%COL)==0) {
			return COL;
		}else {
			return i%COL;
		}
	}
	/**
	 * 将方块序号转换成对应Y坐标
	 * @param i	方块序号
	 * @return	Y坐标
	 */
	private static int intToShapeY(int i){
		if ((i%COL)==0) {
			return i/COL;
		}else {
			return (i/COL)+1;
		}
	}
	/**
	 * 将xy坐标转换成对应的方块序号
	 * @param x	x坐标
	 * @param y	y坐标
	 * @return	方块序号
	 */
	public static int shapeXYToInt(int x,int y){
		return (x+COL*(y-1));
	}
	/**
	 * 玩家1出现炸弹的消行处理
	 * @return true为炸弹，false不为炸弹
	 */
	public boolean player1IsBomb(){
		if (!player1BaseShape.isCanMove()&&player1BaseShape.isBomb) {
			socre+=20;	//分数+20
			for (int x = 1; x <=ROW; x++) {	//清除玩家1炸弹所在列
				if (xyList.get(shapeXYToInt(player1BaseShape.shapeX[0], x)).isBottomBlock()) {
					xyList.get(shapeXYToInt(player1BaseShape.shapeX[0], x)).setDraw(false);
					xyList.get(shapeXYToInt(player1BaseShape.shapeX[0], x)).setBottomBlock(false);
				}
			}
			for (int y = 1; y <=COL; y++) {	//清除玩家1炸弹所在行
				if (xyList.get(shapeXYToInt(y, player1BaseShape.shapeY[0])).isBottomBlock()) {
					xyList.get(shapeXYToInt(y, player1BaseShape.shapeY[0])).setDraw(false);
					xyList.get(shapeXYToInt(y, player1BaseShape.shapeY[0])).setBottomBlock(false);
				}
			}
			for (int x = 1; x <=COL; x++) {	//方块下移一行
				for (int y = player1BaseShape.shapeY[0]-1; y >=1; y--) {
					if (xyList.get(shapeXYToInt(x, y)).isBottomBlock()) {
						xyList.get(shapeXYToInt(x, y)).setDraw(false);
						xyList.get(shapeXYToInt(x, y)).setBottomBlock(false);
						xyList.get(shapeXYToInt(x, y+1)).setDraw(true);
						xyList.get(shapeXYToInt(x, y+1)).setBottomBlock(true);
						xyList.get(shapeXYToInt(x, y+1)).setImage(xyList.get(shapeXYToInt(x, y)).getImage());
					}
				}
			}
		}
		return player1BaseShape.isBomb;
	}
	/**
	 * 玩家2出现炸弹的消行处理
	 * @return true为炸弹，false不为炸弹
	 */
	public boolean player2IsBomb(){
		if (!player2BaseShape.isCanMove()&&player2BaseShape.isBomb) {
			socre+=20;	//分数+20
			for (int x = 1; x <=ROW; x++) {	//清除玩家2炸弹所在列
				if (xyList.get(shapeXYToInt(player2BaseShape.shapeX[0], x)).isBottomBlock()) {
					xyList.get(shapeXYToInt(player2BaseShape.shapeX[0], x)).setDraw(false);
					xyList.get(shapeXYToInt(player2BaseShape.shapeX[0], x)).setBottomBlock(false);
				}
			}
			for (int y = 1; y <=COL; y++) {	//清除玩家2炸弹所在行
				if (xyList.get(shapeXYToInt(y, player2BaseShape.shapeY[0])).isBottomBlock()) {
					xyList.get(shapeXYToInt(y, player2BaseShape.shapeY[0])).setDraw(false);
					xyList.get(shapeXYToInt(y, player2BaseShape.shapeY[0])).setBottomBlock(false);
				}
			}
			for (int x = 1; x <=COL; x++) {	//方块下移一行
				for (int y = player2BaseShape.shapeY[0]-1; y >=1; y--) {
					if (xyList.get(shapeXYToInt(x, y)).isBottomBlock()) {
						xyList.get(shapeXYToInt(x, y)).setDraw(false);
						xyList.get(shapeXYToInt(x, y)).setBottomBlock(false);
						xyList.get(shapeXYToInt(x, y+1)).setDraw(true);
						xyList.get(shapeXYToInt(x, y+1)).setBottomBlock(true);
						xyList.get(shapeXYToInt(x, y+1)).setImage(xyList.get(shapeXYToInt(x, y)).getImage());
					}
				}
			}
		}
		return player2BaseShape.isBomb;
	}
	/**
	 * 随机产生方块和颜色，集合shapeList中0~6位基本方块
	 */
	public void randomShape(boolean haveBomb){
		Random random=new Random();
		if (level.equals("初级模式")) {
			if (haveBomb) {	//初级模式选择炸弹	
				player1ShapeList.set(7, new BombShape());//player1ShapeList的第8个对象为炸弹
				player2ShapeList.set(7, new BombShape());//player2ShapeList的第8个对象为炸弹
				shapeNum=random.nextInt(player1ShapeList.size()-2);
			}else {
				shapeNum=random.nextInt(player1ShapeList.size()-3);
			}
		}else if(level.equals("中级模式")){
			if (haveBomb) {	//中级模式选择炸弹
				player1ShapeList.set(7, new ConcaveShape());//player1ShapeList的第9个对象为炸弹
				player1ShapeList.set(8, new BombShape());
				player2ShapeList.set(7, new ConcaveShape());//player2ShapeList的第9个对象为炸弹
				player2ShapeList.set(8, new BombShape());
				shapeNum=random.nextInt(player1ShapeList.size()-1);
			}else {
				player1ShapeList.set(7, new ConcaveShape());
				player2ShapeList.set(7, new ConcaveShape());
				shapeNum=random.nextInt(player1ShapeList.size()-2);
			}
		}else if(level.equals("高级模式")||level.equals("变态模式")){	//高级模式选择炸弹
			if (haveBomb) {	
				player1ShapeList.set(7, new ConcaveShape());	//player1ShapeList的第10个对象为炸弹
				player1ShapeList.set(8, new CrossShape());
				player1ShapeList.set(9, new BombShape()); 
				player2ShapeList.set(7, new ConcaveShape());	//player2ShapeList的第10个对象为炸弹
				player2ShapeList.set(8, new CrossShape());
				player2ShapeList.set(9, new BombShape()); 
				shapeNum=random.nextInt(player1ShapeList.size());
			}else {
				player1ShapeList.set(7, new ConcaveShape());
				player1ShapeList.set(8, new CrossShape());
				player2ShapeList.set(7, new ConcaveShape());
				player2ShapeList.set(8, new CrossShape());
				shapeNum=random.nextInt(player1ShapeList.size()-1);
			}
		}
		shapeImage=imageList.get(random.nextInt(imageList.size()));	//随机获取图片
	}
	/**
	 * 单/双人游戏的方块随机出现
	 */
	public boolean blockRandomOpen(boolean isSelected,int randomTime){
		Random random=new Random();
		if (isSelected) {	//方块随机单选按钮选中
			if (randomTime!=0) {	
				for (int i =1; i <=20; i++) {
					int x=random.nextInt(xyList.size())+shapeXYToInt(1, 15);
					if (x<=xyList.size()) {
						xyList.get(x).setDraw(true);
						xyList.get(x).setBottomBlock(true);
						xyList.get(x).setImage(imageList.get(random.nextInt(imageList.size())));
					}
				}
				return true;
			}
		}
		return false;
	}
	/**
	 * 方块自动上涨
	 */
	public boolean blockAutoUp(boolean isSelected,int upTime){
		Random random=new Random();
		if (isSelected) {	//方块自动上涨单选按钮选中
			if (upTime!=0) {	//方块上涨一行
				for (int y = 3; y <=ROW; y++) {
					for (int x = 1; x <=COL; x++) {
						if (xyList.get(shapeXYToInt(x, y)).isBottomBlock()) {
							xyList.get(shapeXYToInt(x, y)).setDraw(false);
							xyList.get(shapeXYToInt(x, y)).setBottomBlock(false);
							xyList.get(shapeXYToInt(x, y-1)).setDraw(true);
							xyList.get(shapeXYToInt(x, y-1)).setBottomBlock(true);
							xyList.get(shapeXYToInt(x, y-1)).setImage(xyList.get(shapeXYToInt(x, y)).getImage());
						}
					}
				}
				//上涨后的行随机填充方块
				for (int i = 1; i <=COL; i++) {
					int x=random.nextInt(COL)+1;
					xyList.get(shapeXYToInt(x,ROW)).setDraw(true);
					xyList.get(shapeXYToInt(x,ROW)).setBottomBlock(true);
					xyList.get(shapeXYToInt(x,ROW)).setImage(imageList.get(random.nextInt(imageList.size())));
				}
				return true;
			}
		}
		return false;
	}
	/**
	 * 单/双人游戏的方块消行判断处理
	 * @return true为已经消行，false为没有需要消行的
	 */
	public boolean blockDisappear(){
		if ((player1BaseShape.isCanMove()==false)||(player2BaseShape.isCanMove()==false)) {
			int[] disappearY=new int[ROW];	//要消除的行
			boolean disappear=false;
			//先判断哪几行需要消除，保存在disappearY数组中，数组中的值对应要消除的Y坐标
			for (int i = 0; i < disappearY.length; i++) {
				disappearY[i]=0;
			}
			//判断一行是否全有方块，canDrawXNum=COL表示该行全部由方块
			for (int y = 1; y <= ROW; y++) {
				int canDrawXNum=0;
				for (int x = 1; x <= COL; x++) {	
					if (xyList.get(shapeXYToInt(x, y)).isDraw()) {
						canDrawXNum++;
					}
				}
				if (canDrawXNum==COL) {
					disappear=true;
					disappearY[y-1]=y;
				}
			}
			//数组中的值不为0的行即为要清除该行，该行设为不可绘制和不是底部方块
			for (int y = ROW-1; y >=0; y--) {
				if (disappearY[y]!=0) {
					socre+=10;	//可以消行则分数增加
					for (int x = 1; x <=COL; x++) {
						xyList.get(shapeXYToInt(x, disappearY[y])).setDraw(false);
						xyList.get(shapeXYToInt(x, disappearY[y])).setBottomBlock(false);
					}
				}
			}
			//方块整体下移，如果是底部方块，则下移
			for (int y = 0; y <ROW; y++) {	//从上检查行是否为空
				if (disappearY[y]!=0) {
					for (int x = 1; x <=COL; x++) {
						for (int i =  disappearY[y]-1; i >=1 ; i--) {
							if (xyList.get(shapeXYToInt(x, i)).isBottomBlock()) {	//判断是否是底部方块
								xyList.get(shapeXYToInt(x, i)).setDraw(false);
								xyList.get(shapeXYToInt(x, i)).setBottomBlock(false);
								xyList.get(shapeXYToInt(x, i+1)).setDraw(true);
								xyList.get(shapeXYToInt(x, i+1)).setBottomBlock(true);
								xyList.get(shapeXYToInt(x, i+1)).setImage(xyList.get(shapeXYToInt(x, i)).getImage());
							}
						}
					}
				}
			}
			return disappear;
		}else {
			return false;
		}
	}
	/**
	 * 初始化随机出现方块和颜色数组
	 */
	private void initShape(){
		//初始化玩家1 的方块集合
		player1ShapeList.add(0, new LeftLShape()); 	
		player1ShapeList.add(1, new RightLShape());
		player1ShapeList.add(2, new TShape());
		player1ShapeList.add(3, new LeftZShape());
		player1ShapeList.add(4, new RightZShape());
		player1ShapeList.add(5, new SquareShape());
		player1ShapeList.add(6, new LineShape());
		player1ShapeList.add(7, new ConcaveShape());
		player1ShapeList.add(8, new CrossShape());
		player1ShapeList.add(9, new BombShape()); 
		//初始化玩家2 的方块集合
		player2ShapeList.add(0, new LeftLShape()); 
		player2ShapeList.add(1, new RightLShape());
		player2ShapeList.add(2, new TShape());
		player2ShapeList.add(3, new LeftZShape());
		player2ShapeList.add(4, new RightZShape());
		player2ShapeList.add(5, new SquareShape());
		player2ShapeList.add(6, new LineShape());
		player2ShapeList.add(7, new ConcaveShape());
		player2ShapeList.add(8, new CrossShape());
		player2ShapeList.add(9, new BombShape()); 
		imageList.add(0, "image/blue.png");
		imageList.add(1, "image/green.png");
		imageList.add(2, "image/purple.png");
		imageList.add(3, "image/red.png");
		imageList.add(4, "image/yellow.png");
	}
	/**
	 * 游戏结束时方块拼接成game over字样
	 */
	public void showGameOver(){
		String gColor="image/blue.png";
		String aColor="image/green.png";
		String mColor="image/purple.png";
		String geColor="image/red.png";
		String oColor="image/yellow.png";
		String vColor="image/purple.png";
		String oeColor="image/green.png";
		String rColor="image/blue.png";
		clearBlockPanel();
		int[] gNum={shapeXYToInt(1, 2),shapeXYToInt(2, 2),shapeXYToInt(3, 2),shapeXYToInt(4, 2),shapeXYToInt(1, 3),shapeXYToInt(4, 3),
				shapeXYToInt(1, 4),shapeXYToInt(1, 5),shapeXYToInt(1, 6),shapeXYToInt(1, 7),
				shapeXYToInt(3, 7),shapeXYToInt(4, 7),shapeXYToInt(1, 8),shapeXYToInt(4, 8),
				shapeXYToInt(1, 9),shapeXYToInt(4, 9),shapeXYToInt(1, 10),shapeXYToInt(2, 10),shapeXYToInt(3, 10),shapeXYToInt(4, 10)};
		int[] aNum={shapeXYToInt(5, 6),shapeXYToInt(6, 6),shapeXYToInt(7, 6),shapeXYToInt(5, 7),shapeXYToInt(7, 7),shapeXYToInt(5, 8),
				shapeXYToInt(6, 8),shapeXYToInt(7, 8),shapeXYToInt(5, 9),shapeXYToInt(7, 9),
				shapeXYToInt(5, 10),shapeXYToInt(7, 10)};
		int[] mNum={shapeXYToInt(8, 6),shapeXYToInt(9, 6),shapeXYToInt(10, 6),shapeXYToInt(11, 6),shapeXYToInt(12, 6),shapeXYToInt(8, 7),shapeXYToInt(10, 7),
				shapeXYToInt(12, 7),shapeXYToInt(12, 7),shapeXYToInt(8, 8),shapeXYToInt(10, 8),
				shapeXYToInt(12, 8),shapeXYToInt(8, 9),shapeXYToInt(10, 9),shapeXYToInt(12, 9),
				shapeXYToInt(8, 10),shapeXYToInt(10, 10),shapeXYToInt(12, 10)};
		int[] geNum={shapeXYToInt(13, 6),shapeXYToInt(14, 6),shapeXYToInt(15, 6),shapeXYToInt(13, 7),shapeXYToInt(15, 7),shapeXYToInt(13, 8),
				shapeXYToInt(14, 8),shapeXYToInt(15, 8),shapeXYToInt(13, 9),shapeXYToInt(13, 10),shapeXYToInt(14, 10),
				shapeXYToInt(15, 10)};
		int[] oNum={shapeXYToInt(2, 13),shapeXYToInt(3, 13),shapeXYToInt(4, 13),shapeXYToInt(5, 13),shapeXYToInt(2, 14),shapeXYToInt(5, 14),
				shapeXYToInt(2, 15),shapeXYToInt(5, 15),shapeXYToInt(2, 16),shapeXYToInt(5, 16),
				shapeXYToInt(2, 17),shapeXYToInt(5, 17),shapeXYToInt(2, 18),shapeXYToInt(5, 18),
				shapeXYToInt(2, 19),shapeXYToInt(5, 19),shapeXYToInt(2, 20),shapeXYToInt(5, 20),shapeXYToInt(2, 21),shapeXYToInt(3, 21),shapeXYToInt(4, 21),shapeXYToInt(5, 21)};
		int[] vNum={shapeXYToInt(6, 17),shapeXYToInt(8, 17),shapeXYToInt(6, 18),shapeXYToInt(8, 18),
				shapeXYToInt(6, 19),shapeXYToInt(8, 19),shapeXYToInt(6, 20),shapeXYToInt(8, 20),shapeXYToInt(6, 21),shapeXYToInt(8, 21),
				shapeXYToInt(7, 21)};
		int[] oeNum={shapeXYToInt(9, 17),shapeXYToInt(10, 17),shapeXYToInt(11, 17),shapeXYToInt(9, 18),shapeXYToInt(11, 18),shapeXYToInt(9, 19),
				shapeXYToInt(10, 19),shapeXYToInt(11, 19),shapeXYToInt(9, 20),shapeXYToInt(10, 21),shapeXYToInt(9, 21),
				shapeXYToInt(11, 21)};
		int[] rNum={shapeXYToInt(12, 17),shapeXYToInt(13, 17),shapeXYToInt(14, 17),shapeXYToInt(12, 18),
				shapeXYToInt(14, 18),shapeXYToInt(12, 19),shapeXYToInt(12, 20),shapeXYToInt(12, 21)};
		for (int i = 0; i < gNum.length; i++) {
			xyList.get(gNum[i]).setDraw(true);
			xyList.get(gNum[i]).setImage(gColor);
		}
		for (int i = 0; i < aNum.length; i++) {
			xyList.get(aNum[i]).setDraw(true);
			xyList.get(aNum[i]).setImage(aColor);
		}
		for (int i = 0; i < mNum.length; i++) {
			xyList.get(mNum[i]).setDraw(true);
			xyList.get(mNum[i]).setImage(mColor);
		}
		for (int i = 0; i < geNum.length; i++) {
			xyList.get(geNum[i]).setDraw(true);
			xyList.get(geNum[i]).setImage(geColor);
		}
		for (int i = 0; i < oNum.length; i++) {
			xyList.get(oNum[i]).setDraw(true);
			xyList.get(oNum[i]).setImage(oColor);
		}
		for (int i = 0; i < vNum.length; i++) {
			xyList.get(vNum[i]).setDraw(true);
			xyList.get(vNum[i]).setImage(vColor);
		}
		for (int i = 0; i < oeNum.length; i++) {
			xyList.get(oeNum[i]).setDraw(true);
			xyList.get(oeNum[i]).setImage(oeColor);
		}
		for (int i = 0; i < rNum.length; i++) {
			xyList.get(rNum[i]).setDraw(true);
			xyList.get(rNum[i]).setImage(rColor);
		}
	}
}
