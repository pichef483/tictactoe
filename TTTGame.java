//Scott Smith
//SSM155
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
public class TTTGame extends JFrame implements MouseListener,ActionListener{
	//field declaration
	private int p1Wins;
	private int p2Wins;
	private int draws;
	private int[][] boardArr;
	private Board board;
	private boolean p1turn;
	private int x,y;
	private JFrame appFrame;
	private JTextField t1;
	private JTextArea t2;
	private int hypoWins;
	private int hypoLosses;
	//constructor
	public TTTGame(){
		
	    JButton b=new JButton("New Game");  
	    b.setBounds(20, 20, 100, 60);  
	    t1=new JTextField("P1's turn");
	    p1Wins=0;
	    hypoWins=0;
	    hypoLosses=0;
		p2Wins=0;
		draws = 0;
	    t2=new JTextArea("P1 wins: "+p1Wins+"\nP2 wins: "+p2Wins+"\nDraws: "+draws,0,3);
	    t1.setEditable(false);
	    t2.setEditable(false);
	    t1.setBounds(190, 20, 80, 60);
        t2.setBounds(360, 20, 80, 60);
		boardArr = new int[3][3];
		p1turn=true;
		//creating frame
		appFrame = new JFrame();
		appFrame.setTitle("Tic-Tac-Toe");
	    appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    appFrame.add(b);
	    appFrame.add(t1);
	    appFrame.add(t2);
	    //setting up ttt board
	    board = new Board(boardArr);   
	    appFrame.add(board);
        appFrame.setSize(480,580);
        appFrame.setVisible(true);
	    appFrame.setResizable(false);
		//adding listeners
	    appFrame.addMouseListener(this);
		b.addActionListener(this);
		compTurn();
	}
	public void compTurn() {
		int[] dec = decision(copier(boardArr));
		boardArr[dec[0]][dec[1]]=2;
		board.set(boardArr);
		board.repaint();
	}
	public int[][] copier(int[][] boardToCop){
		int[][] boardCop = new int[3][3];
		for(int i =0;i<3;i++) {
			for(int j = 0;j<3;j++) {
				boardCop[j][i] = boardToCop[j][i];
			}
		}
		return boardCop;
	}
	public int[] decision(int[][] hypoBoard) {
		
		int[] action = new int[2];
		int maxDiff=0;
		int util=0;
		int maxUtil = -1;
		
		for (int i =0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(boardArr[j][i]==0) {
					hypoWins=0;
					hypoLosses=0;
					boardArr[j][i]=2;
					util = MIN(copier(boardArr));
					boardArr[j][i]=0;
					if(util>maxUtil) {
						maxUtil=util;
						action[0]=j;
						action[1]=i;
					}
					if(hypoWins-hypoLosses>maxDiff) {
						maxDiff=hypoWins-hypoLosses;
						if(util==maxUtil) {
							action[0]=j;
							action[1]=i;
						}
					}
					System.out.println(hypoWins+" "+hypoLosses);
					System.out.println(util);
				}
				
			}
		}
		System.out.println(Arrays.toString(action));
		
		return action;
	}
	public int MAX(int[][] hypoBoard) {
		int util=-2;

		if(1==winChecker(hypoBoard)) {
			hypoWins++;
			return -1;
		}
		if(3==winChecker(hypoBoard)) {
			return 0;
		}
		for (int i =0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(hypoBoard[j][i]==0) {
					hypoBoard[j][i]=2;
					util =Math.max(util, MIN(copier(hypoBoard)));
					hypoBoard[j][i]=0;
				}
				
			}
		}
		
		return util;
	}
	public int MIN(int[][] hypoBoard) {
		int util=2;
		if(2==winChecker(hypoBoard)) {
			hypoLosses++;
			return 1;
		}
		if(3==winChecker(hypoBoard)) {
			return 0;
		}
		for (int i =0;i<3;i++) {
			for(int j=0;j<3;j++) {
				if(hypoBoard[j][i]==0) {
					hypoBoard[j][i]=1;
					util =Math.min(util, MAX(copier(hypoBoard)));
					hypoBoard[j][i]=0;
				}
			}
		}
		return util;
	}
	//method to check for win conditions
	public void winPrinter() {
		if(p1turn == false) {
			t1.setText("P2's turn");
		}else {
			t1.setText("P1's turn");
		}
		
		if(winChecker(boardArr)==1) {
			t1.setText("P1 wins!");
			p1Wins++;
			t2.setText("P1 wins: "+p1Wins+"\nP2 wins: "+p2Wins+"\nDraws: "+draws);
			
		}
		if(winChecker(boardArr)==2) {
			t1.setText("P2 wins!");
			p2Wins++;
			t2.setText("P1 wins: "+p1Wins+"\nP2 wins: "+p2Wins+"\nDraws: "+draws);
			
		}
		if(winChecker(boardArr)==3) {
			draws++;
			t2.setText("P1 wins: "+p1Wins+"\nP2 wins: "+p2Wins+"\nDraws: "+draws);
			t1.setText("Draw!");
		}
	}
	public int winChecker(int[][] boardCheck) {
		int win = 0;
		for(int i =0;i<3;i++) {
			if(boardCheck[i][0]==boardCheck[i][1]&&boardCheck[i][1]==boardCheck[i][2]) {
				win = boardCheck[i][0];
				break;
			}
			if(boardCheck[0][i]==boardCheck[1][i]&&boardCheck[1][i]==boardCheck[2][i]) {
				win = boardCheck[0][i];
				break;
			}
			if(boardCheck[0][0]==boardCheck[1][1]&&boardCheck[1][1]==boardCheck[2][2]) {
				win = boardArr[0][0];
				break;
			}
			if(boardCheck[0][2]==boardCheck[1][1]&&boardCheck[1][1]==boardCheck[2][0]) {
				win = boardCheck[0][2];
				break;
			}
		}
		boolean draw = true;
		//draw checker
		for(int i = 0;i<3;i++) {
			for(int j = 0;j<3;j++) {
				if(boardCheck[i][j]==0) {
					draw = false;
					break;
				}
				if(i==2&&j==2&&win==0) {
					win=3;
				}
			}
			if (draw==false) {
				break;
			}
		}
		return win;
	}
	//button actionlistener, will create a new game when pressed
	@Override
    public void actionPerformed(ActionEvent e) {
		
		boardArr = new int[3][3];
		compTurn();
		board.set(boardArr);
		board.repaint();
		t1.setText("P1's turn");
		
    }
	//mouselistener, finds the coords of the mouse click and converts that into the right quadrant
	public void mouseClicked(MouseEvent m) {
		x=m.getX()-8;
		y=m.getY()-30;
		//checking to see if click is in bounds of board
		if(90<x&&390>x&&y>190&&y<490) {	
			x=x-90;
			y=y-190;
			x=x/100;
			y=y/100;
			//checks to make sure game isnt over and that the square isnt already taken up
			if (boardArr[x][y]==0&&winChecker(boardArr)==0) {
				
				if (p1turn ==true) {
					boardArr[x][y]=1;
					board.set(boardArr);
					board.repaint();
				}else {
					boardArr[x][y]=2;
					board.set(boardArr);
					board.repaint();
				}
				//if (winChecker(boardArr)==0) {
					//compTurn();
				//}
				winPrinter();
				if(winChecker(boardArr)==0) {
					compTurn();
					winPrinter();
				}
				
				
				
			}
			
		}
	
	}
	@Override
	public void mouseExited(MouseEvent event) {
		
	}
	@Override
	public void mouseReleased(MouseEvent event) {
		
	}
	@Override
	public void mousePressed(MouseEvent event) {
	
	}
	@Override
	public void mouseEntered(MouseEvent event) {
		
	}
	//main method
	public static void main(String[] args) {
		TTTGame ttt = new TTTGame();
	}
}
