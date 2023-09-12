//https://youtu.be/r-7_GhZPjxk

import java.util.*;


	public class Grid {
		private int numBombs;
		private int numColumns;
		private int numRows;
		private int [][] countGrid;
		private boolean [][] bombGrid;
		
	public Grid() {
			this.numBombs = 25;
			this.numColumns = 10;
			this.numRows = 10;
			this.countGrid = new int[10][10];
			this.bombGrid = new boolean[10][10];
			createBombGrid();
			createCountGrid();
			}

	public Grid(int rows, int columns) {
			this.numBombs = 25;
			this.numColumns = columns;
			this.numRows = rows;
			this.countGrid = new int[rows][columns];
			this.bombGrid = new boolean[rows][columns];
			createBombGrid();
			createCountGrid();
			}

			
	public Grid(int rows, int columns, int numBombs) {
			this.numBombs = numBombs;
			this.numColumns = columns;
			this.numRows = rows;
			this.countGrid = new int[rows][columns];
			this.bombGrid = new boolean[rows][columns];
			createBombGrid();
			createCountGrid();
			}
			
	public int getNumRows() { return this.numRows; }
	public int getNumColumns() { return this.numColumns; }
	public int getNumBombs() { return this.numBombs; }

				
	public boolean [][] getBombGrid(){
					
			boolean[][] result = new boolean[numRows][numColumns];

				for (int i = 0; i < numRows; i++) {
					
					for(int j=0;j<numColumns;j++)
					
						result[i][j] = bombGrid[i][j];

					
				}
				
				return result;
					
			}

	public int[][] getCountGrid() {
					
			int[][] result = new int[numRows][numColumns];

					
				for (int i = 0; i < numRows; i++) {
					
					for(int j=0;j<numColumns;j++)
					
						result[i][j] = countGrid[i][j];
					
				}

					
				return result;
					
	}
			
	public boolean isBombAtLocation(int row, int column) { return bombGrid[row][column]; }
			
	public int getCountAtLocation(int row, int column) {
			
		int bombCount = 0;
			
			if (row > 0 && column < numColumns - 1 && bombGrid[row - 1][column + 1] == true) {
			bombCount++;
			}
			if (column < numColumns - 1 && bombGrid[row][column + 1] == true) {
			bombCount++;
			}
			if (row < numRows - 1 && column < numColumns - 1 && bombGrid[row + 1][column + 1] == true) {
			bombCount++;
			}
			if (row > 0 && bombGrid[row - 1][column] == true) {
			bombCount++;
			}
			if (bombGrid[row][column] == true) {
			bombCount++;
			}
			if (row < numRows - 1 && bombGrid[row + 1][column] == true) {
			bombCount++;
			}
			if (row > 0 && column > 0 && bombGrid[row - 1][column - 1] == true) {
			bombCount++;
			}
			if (column > 0 && bombGrid[row][column - 1] == true) {
			bombCount++;
			}
			if (row < numRows - 1 && column > 0 && bombGrid[row + 1][column - 1] == true) {
			bombCount++;
			}
			return bombCount;
			}
			
	public void createBombGrid() {
				
		for (int i = 0; i < numRows; i++) {
				
			for (int j = 0; j < numColumns; j++) {
				
				bombGrid[i][j] = false;
				
			}
				
		}
				
		for (int i = 0; i < numBombs; i++) {
				
			int rand1 = (int) (Math.random() * numRows);
				
			int rand2 = (int) (Math.random() * numColumns);
				
			if (bombGrid[rand1][rand2]) { i--; } 
			
			else { bombGrid[rand1][rand2] = true; }
				
		}
				
	}

			
			public void createCountGrid() {
			
				for (int i = 0; i < numRows; i++) {
			
					for (int j = 0; j < numColumns; j++) { countGrid[i][j] = getCountAtLocation(i, j); }
			}
		}
			
	}

//GUI GRID CLASS
/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

	public class Grid extends JFrame {
	
		private int numBombs;
		private int numColumns;
		private int numRows;
		private int [][] countGrid;
		private boolean [][] bombGrid;
		private int safeSpace;
		private JButton buttons[][];
	
	public Grid() { this (10, 10, 25); }

	public Grid(int rows, int columns) { this (rows, columns, 25); }

	public Grid(int rows, int columns, int numBombs) {
			
		
		super("MineSweeper");
		this.numBombs = numBombs;
		this.numColumns = columns;
		this.numRows = rows;
		createBombGrid();
		createCountGrid();
		
	
		safeSpace = (getNumRows() * getNumColumns()) - getNumBombs();
	
		buttons = new JButton[getNumRows()][getNumColumns()];
	
		setLayout(new GridLayout(getNumRows(), getNumColumns()));
		
			for (int i = 0; i < getNumRows(); i++) {
		
				for (int j = 0; j < getNumColumns(); j++) {
			
					buttons[i][j] = new JButton();

						buttons[i][j].addActionListener(new ButtonClickListener(i, j));
						
							add(buttons[i][j]);
			}
	}
			
					setDefaultCloseOperation(EXIT_ON_CLOSE);
					setSize(700, 700);
					setVisible(true);
}

	private void createBombGrid() {


		bombGrid = new boolean[numRows][numColumns];


		for (int i = 0; i < numRows; i++) {


			for (int j = 0; j < numColumns; j++) {


				bombGrid[i][j] = false;


			}


		}


		for (int i = 0; i < numBombs; i++) {
	
	
			int rand1 = (int) (Math.random() * numRows);
		
	
			int rand2 = (int) (Math.random() * numColumns);
		
	
			if (bombGrid[rand1][rand2]) { i--; } 
	
	
			else { bombGrid[rand1][rand2] = true; }
		
}
		

}


	private void createCountGrid() {


		countGrid = new int[numRows][numColumns];

		for (int i = 0; i < numRows; i++) {


			for (int j = 0; j < numColumns; j++) {

				countGrid[i][j] = getCountAtLocation(i, j);


			}


		}


	}

	public boolean isBombAtLocation(int row, int column) { return bombGrid[row][column]; }


	public int getCountAtLocation(int row, int column) {
		
		int bombCount = 0;
		
			if (row > 0 && column < numColumns - 1 && bombGrid[row - 1][column + 1] == true) {
				bombCount++;
			}
			if (column < numColumns - 1 && bombGrid[row][column + 1] == true) {
				bombCount++;
			}
			if (row < numRows - 1 && column < numColumns - 1 && bombGrid[row + 1][column + 1] == true) {
				bombCount++;
			}
			if (row > 0 && bombGrid[row - 1][column] == true) {
				bombCount++;
			}
			if (bombGrid[row][column] == true) {
				bombCount++;
			}
			if (row < numRows - 1 && bombGrid[row + 1][column] == true) {
				bombCount++;
			}
			if (row > 0 && column > 0 && bombGrid[row - 1][column - 1] == true) {
				bombCount++;
			}
			if (column > 0 && bombGrid[row][column - 1] == true) {
				bombCount++;
			}
			if (row < numRows - 1 && column > 0 && bombGrid[row + 1][column - 1] == true) {
				bombCount++;
			}
			return bombCount;
			}

	public int getNumRows() { return this.numRows; }
	public int getNumColumns() { return this.numColumns; }
	public int getNumBombs() { return this.numBombs; }

	public boolean [][] getBombGrid(){
	
		boolean[][] result = new boolean[numRows][numColumns];

			for (int i = 0; i < numRows; i++) {
			
				for(int j=0;j<numColumns;j++)
			
					result[i][j] = bombGrid[i][j];

			
			}
		
			return result;
			
	}

	public int[][] getCountGrid() {
			
		int[][] result = new int[numRows][numColumns];

			
			for (int i = 0; i < numRows; i++) {
			
				for(int j=0;j<numColumns;j++)
			
					result[i][j] = countGrid[i][j];
			
		}

			
			return result;
			
}
	
	private void reset() {

		createBombGrid();
		createCountGrid();

		safeSpace = (getNumRows() * getNumColumns()) - getNumBombs();

		for (int i = 0; i < getNumRows(); i++) {

			for (int j = 0; j < getNumColumns(); j++) {
				
				buttons[i][j].setText("");
				buttons[i][j].setEnabled(true);

			}
			
		}

}


	
	private class ButtonClickListener implements ActionListener {

		int row;
		int column;

	public ButtonClickListener(int row, int column) { this.row = row; this.column = column; }

	@Override 
	public void actionPerformed(ActionEvent e) {

		if (isBombAtLocation(row, column)) {
		
			int[][] counts = getCountGrid();


				for (int i = 0; i < getNumRows(); i++) {


					for (int j = 0; j < getNumColumns(); j++) {

		if (isBombAtLocation(i, j)) {


						buttons[i][j].setText("BOOM!");

} 
		else { buttons[i][j].setText(String.valueOf(counts[i][j])); }
	
	
						buttons[i][j].setEnabled(false);

			}

}

int option = JOptionPane.showConfirmDialog(null, "You lost! Would you like to play again?", "Game over", JOptionPane.YES_NO_OPTION);


	if (option == JOptionPane.YES_OPTION) { reset(); } 

		else { System.exit(0); }

		} 

		else { buttons[row][column].setText(String.valueOf(getCountAtLocation(row, column)));

			buttons[row][column].setEnabled(false);

				safeSpace--;


	if (safeSpace == 0) { int status = JOptionPane.showConfirmDialog(null, "You won! Do you want to play again?", "Game over", JOptionPane.YES_NO_OPTION);

if (status == JOptionPane.YES_OPTION) { reset(); } 

else { System.exit(0);

					}

				}

			}

		}

	}

	public static void main(String args[]) {

		Grid MineSweeper = new Grid();

}

	

}*/
	
	