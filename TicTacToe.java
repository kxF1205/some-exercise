import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class TicTacToe {
//Name:Kexin Fang 
	
//A. A method called createBoard that takes as input one integer n, representing the dimension of the board, and returns an n by n array of characters.
	public static char[][] creatBoard(int n) {
		char[][]a=new char[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				a[i][j]=' ';
			}	
		}
		return a;
	}
	
//B. A method called displayBoard that takes a 2 dimensional array of character as input and prints out the board. 
	public static void displayBoard(char[][]a) {
		int b = a.length;
		//Find out the size of the board
		for(int i=0;i<b;i++) {
			for(int j=0;j<b;j++){
				System.out.print("+"+"-");
			}
			System.out.println("+");
			
			for(int j=0;j<b;j++){
				System.out.print("|");
				char val=a[i][j];
				System.out.print(val);
			}
			System.out.println("|");
		}	
			 for (int j=0; j<b;j++){
			      System.out.print("+"+"-");
			 }
		     System.out.println("+");
		     
	}
	
//C. A method that takes as input a 2D dimensional array, the character to write, two integers x and y.
	public static void writeOnBoard(char[][]a,char symbol,int x,int y) {
		int n=a.length;
		//Find out the size of the board
		
		if(x<0||y<0||x>n-1||y>n-1) {
			throw new IllegalArgumentException("The position"+"("+x+","+y+")" +"does not represent a cell on the board.");
		}else if(a[x][y]!=' ') {
			throw new IllegalArgumentException("The cell already contains a character.");
		}else {
			a[x][y]=symbol;
		}
		
	}
	
//D. This method uses Scanner to get a move from the user.
	public static void getUserMove(char[][]a) {
		int x =0;
   		int y= 0;
   		boolean firstmove = true;
   		int n=a.length;
   		//Setting the user's first move by true initially so it must enter the while loop
		while(firstmove||x<0||y<0||x>n-1||y>n-1||a[x][y]!=' ') {
			firstmove = false;
			Scanner myScan=new Scanner(System.in);
	   		x= myScan.nextInt();
	   		y= myScan.nextInt();
	   		if(x<0||y<0||x>n-1||y>n-1||a[x][y]!=' ')
		    System.out.println("The move is invalid. Please enter a new move.");
		}
		writeOnBoard(a,'x',x,y);
	}
	
//Below there are 5 helper methods.	
	//A method counting how many c are in the array.
	public static int numberOfCharater(char[]x,char c) {
		int counter=0;
		for(int i=0;i<x.length;i++) {
			if(x[i]==c){
			counter++;
		}
		}
		return counter;
	}
	
	//A method that takes the board as input and returns a column of the board
	public static char[] column(char[][]a,int x){
		//x means the xth column.
		int n=a.length;
		//Find out how many characters are in a column
		char[]b=new char[n];
		for(int i=0;i<n;i++) {
			b[i]=a[i][x];
		}
		return b;
	}
	
	//A method that takes the board as input and returns a row of the board
		public static char[] row(char[][]a,int x){
			//x means the xth row
			int n=a.length;
			//Find out how many characters are in a row
			char[]b=new char[n];
			for(int i=0;i<n;i++) {
				b[i]=a[x][i];
			}
			return b;
		}
	
	//A method that takes the board as input and returns one of the two diagonals
	public static char[] diagonals(char[][]a,String x) {
		//String x determinds the diagonal starts from the left side or right side, because there are two diagonals
		int n=a.length;
		//Find out how many characters are in a column
		char[]b=new char[n];
		if(x=="left") {

			for(int i=0;i<n;i++) {
				b[i]=a[i][i];
			}
		}else if(x=="right") {
	
			for(int i=n-1;i>=0;i--) {
				b[i]=a[i][n-1-i];
			}
		}
		return b;
	}
	
	//A method that looks for a cell with a space character along a row/column/diagonal
	public static int findSpace(char[]x) {
		int n=x.length;
		int a = -1;
		for(int i=0;i<n;i++){
			if(x[i]==' ') {
				a=i;
				}
			}
			return a;	
	//if there is no space,then it will return -1. And this will help checkForObviousMove
	}
	
//E. A method called checkForObviousMove that takes the board as input and returns true if there's an "obvious move" the AI should do, false otherwise.	
	public static boolean checkForObviousMove(char[][]a) {
		int n=a.length;
		
		char[]d1=diagonals(a,"left");
		char[]d2=diagonals(a,"right");
		//put the elememts in each diagonal into two arrays
		
		int oNumOfd1=numberOfCharater(d1,'o');
		int oNumOfd2=numberOfCharater(d2,'o');
		//find out the number of 'o' in one diagonals
		int xNumOfd1=numberOfCharater(d1,'x');
		int xNumOfd2=numberOfCharater(d2,'x');
		//find out the number of 'x' in one diagonals
		
		//first check 'o', if there is a move then the AI win
		if(oNumOfd1==n-1){
			if(findSpace(d1)!=-1) {
		//check if there must be a space
			int x=findSpace(d1);
			writeOnBoard(a,'o',x,x);
			return true;
			}
		}else if(oNumOfd2==n-1){
			if(findSpace(d2)!=-1) {
		//check if there must be a space
		    int x=findSpace(d2);
			writeOnBoard(a,'o',x,n-1-x);
			return true;
			}
		}
		//these is for the two diagonals
		for(int i=0;i<n;i++) {
			char[]r=row(a,i);
			//put the elements in each row into n arrays 
			char[]c=column(a,i);
			//put the elements in each column into n arrays 
			int oNumOfRow=numberOfCharater(r,'o');
			//find out the number of 'o' in one row
			int oNumOfColumn=numberOfCharater(c,'o');
			//find out the number of 'o' in one column
			if(oNumOfRow==n-1){
			  if(findSpace(r)!=-1) {
			  //check if there must be a space
			  int x=findSpace(r);
			  writeOnBoard(a,'o',i,x);
			  return true;
			  }
			}else if(oNumOfColumn==n-1){
				//first check 'o', if there is a move then the AI win
				if(findSpace(c)!=-1) {
				//check if there must be a space
				int x=findSpace(c);
				writeOnBoard(a,'o',x,i);
				return true;
				}
			}
		}
		//these is for the rows and columns
		
	    //Then check 'x'
		if(xNumOfd2==n-1){
			if(findSpace(d2)!=-1) {
		//check if there must be a space
			int x=findSpace(d2);
			writeOnBoard(a,'o',x,n-1-x);
			return true;
			}
		}else if(xNumOfd1==n-1){
			if(findSpace(d1)!=-1) {
		//check if there must be a space
			int x=findSpace(d1);
			writeOnBoard(a,'o',x,x);
			return true;
			}
		}
		//these is for the two diagonals
		for(int j=0;j<n;j++){
			char[]r=row(a,j);
			//put the elements in each row into n arrays
			char[]c=column(a,j);
			//put the elements in each column into n arrays 
			int xNumOfColumn=numberOfCharater(c,'x');
			//find out the number of 'x' in one column
			int xNumOfRow=numberOfCharater(r,'x');
			//find out the number of 'x' in one row
			
		 if(xNumOfRow==n-1){
			if(findSpace(r)!=-1) {
			//check if there must be a space
			int x=findSpace(r);
		    writeOnBoard(a,'o',j,x);
		    return true;
			}
		}else if(xNumOfColumn==n-1){
				if(findSpace(c)!=-1) {
				//check if there must be a space
				int x=findSpace(c);
				writeOnBoard(a,'o',x,j);
				return true;
				}
			}
		}
		//these for all the rows and columns
		return false;
	}

//F. A method first check if an "obvious move" is possible for the AI and carry it out
	public static void getAIMove(char[][]a){
		int n= a.length;
		Random number1=new Random();	
		Random number2=new Random();
		int x = number1.nextInt(n);
		int y = number2.nextInt(n);
		if(checkForObviousMove(a)==false){
		   while(a[x][y]!=' ') {
			   x = number1.nextInt(n);
			   y = number2.nextInt(n);
		  } 
		   writeOnBoard(a,'o',x,y);
		}
	}

//G.The method check whether either the user or the AI have won the game.
	 public static char checkForWinner(char[][]a) {
		int n = a.length;
		for(int i=0;i<n;i++) {
			char[]row=row(a,i);
			int numberOfUser=numberOfCharater(row,'x');
			int numberOfAI=numberOfCharater(row,'o');
			if(numberOfUser==n) {
				return 'x';
			}else if(numberOfAI==n) {
				return 'o';
			}
		}
		//check every row in the board
			
		for(int j=0;j<n;j++) {
			char[]column=column(a,j);
			int numberOfUser=numberOfCharater(column,'x');
			int numberOfAI=numberOfCharater(column,'o');
			if(numberOfUser==n) {
				return 'x';
			}else if(numberOfAI==n) {
				return 'o';
			}
		}
		//check every column in the board
		
		char[]d1=diagonals(a,"left");
		char[]d2=diagonals(a,"right");
		int oNumOfd1=numberOfCharater(d1,'o');
		int oNumOfd2=numberOfCharater(d2,'o');
		int xNumOfd1=numberOfCharater(d1,'x');
		int xNumOfd2=numberOfCharater(d2,'x');
		if(oNumOfd1==n||oNumOfd2==n) {
			return 'o';
		}else if(xNumOfd1==n||xNumOfd2==n) {
			return 'x';
		}
		//check the two diagonals
		return ' ';
	 }

//H. This method implement a game of Tic Tac Toe between the user and the AI.
	 public static void play() {
		 System.out.println("Please enter your name :");
		 Scanner name=new Scanner(System.in);
	   	 String userName= name.nextLine();
	   	 //Ask the user for their name and store it in an appropriate variable called "userName"
	   	 System.out.println("Welcome,"+" "+userName+"!"+" "+"Are you ready to play?");
	   	 
	   	 System.out.print("Please choose the dimension of your board :");
	   	 int size=0;
	   	 while(size==0) {
	   	 try {
                        Scanner myScan=new Scanner(System.in);
	   		size = myScan.nextInt();
	   	 }
	   	 catch(InputMismatchException exception){
	   		 System.out.println("This is not an integer.Please enter a new one.");
	   	 }
	   	 //If the input is invalid, it won't crash and keep asking for an input of the correct type until it receives one.
	   	 }
	   	 //Ask the user for an integer indicating the dimension of the board the user wants to play with
	   	 System.out.println();
	   	 
	   	char[][]board=creatBoard(size);
	   	//creat a board
	   	int n=board.length;
	   	
	   	Random coinToss=new Random();
	   	int  firstMove= coinToss.nextInt(2);
	   	//if firstStep equals to 1,then AI has the first move; if firstStep equals to 0,then user has the first move
	   	
	   	int counter=0;
	   	//the counter is for counting the moves for AI and user
	   	
	   	//This for when the AI has the first move
	   	if(firstMove==1){
	   		System.out.println("The result of the coin toss is :"+" "+firstMove);
	   		System.out.println("The AI has the first move");
	   		while(checkForWinner(board)==' '&&counter<n*n) {
	   		System.out.println("The AI made its move:");
	   		getAIMove(board);
	   		displayBoard(board);
	   		counter++;
	   		////after the AI's move, check if there is a winner
	   		if(checkForWinner(board)==' '&&counter<n*n) {
	   		System.out.print("Please enter your move:");
	   		getUserMove(board);
	   		displayBoard(board);
	   		counter++;
	   		}
	   		
	   		}
	   		
	   		if(checkForWinner(board)=='o') {
	   			System.out.println("GAME OVER!");
	   			System.out.print("You lost");
	   		}else if(checkForWinner(board)=='x') {
	   			System.out.println("GAME OVER!");
	   			System.out.print("You win");
	   		}else if(checkForWinner(board)==' '&&counter==n*n){
	   			System.out.println("GAME OVER!");
	   			System.out.println("It's a tie");
	   		}
	   	}
	   	//This for when the user has the first move
	   	if(firstMove==0) {
	   		System.out.println("The result of the coin toss is :"+" "+firstMove);
	   		System.out.println("You have the first move");
	   		while(checkForWinner(board)==' '&&counter<n*n) {
	   		System.out.print("Please enter your move :");
	   		getUserMove(board);
	   		displayBoard(board);
	   		counter++;
	   		//after the user's move, check if there is a winner
	   		if(checkForWinner(board)==' '&&counter<n*n) {
	   		System.out.println("The AI made its move:");
	   		getAIMove(board);
	   		displayBoard(board);
	   		counter++;
	   		}
	   		
	   		}
	   		
	   		if(checkForWinner(board)=='o') {
	   			System.out.println("GAME OVER!");
	   			System.out.print("You lost");
	   		}else if(checkForWinner(board)=='x') {
	   			System.out.println("GAME OVER!");
	   			System.out.print("You win");
	   		}else if(checkForWinner(board)==' '&&counter==n*n){
	   			System.out.println("GAME OVER!");
	   			System.out.println("It's a tie");
	   		}
	   	} 	   	 
	 }
	
}
