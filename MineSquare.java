import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

@SuppressWarnings("serial")
public class MineSquare extends JButton {
	public boolean mine;
	boolean exposed;
	boolean flagged = false;
	int count;
	public int r;
	public int c;
	public Minesweeper game;
	
	public static Color colors;


	public MineSquare(int r, int c, boolean mine, Minesweeper game) {
		this.mine = mine;
		this.game = game;
		exposed = flagged = false;
		count = 0;
		this.r = r;
		this.c = c;
		addMouseListener(new ButtonListener());
		colors = new Color(147 ,112, 219);
	}

	public void setExposed() {
		exposed = true;
	}

	



	public void expose() {
		

		if(exposed || flagged)
			return;

		if (game.secretMineCount == 0) {
			game.win();
		}

		exposed = true;
		if(mine) {
			this.setText("💥");
			game.resetButton.setText("💀");
			this.setBackground(Color.RED);

			for(int i = 1; i < game.board.length-1; i++)
				for(int j = 1; j < game.board.length-1; j++) {
					game.board[i][j].setEnabled(false);	
				}
			game.timer.stop();


			int input = JOptionPane.showConfirmDialog(null, "Game over! Do you want to play again? (Yes/No) ", "You lose!", JOptionPane.YES_NO_OPTION);

			if(input == JOptionPane.YES_OPTION) {
				game.dispose();
				new Minesweeper();
				

			}
			if(input == JOptionPane.NO_OPTION){
				game.dispose();
			}
		} 
		else {
			Color color = new Color((int)(Math.random()*60),0,(int)(Math.random()*255));
			setBackground(color);
			game.board[r][c].setForeground(Color.WHITE);
			setEnabled(false);
			int count = 0;
			for(int i = -1; i <= 1; i++)
				for(int j = -1; j <=1; j++) {
					if (game.board[r+i][c+j].mine)
						count++;
				}
			if (count > 0) 
				setText(count + "");
			else
				for(int i = -1; i <= 1; i++)
					for(int j = -1; j <=1; j++)
						if (!game.board[r+i][c+j].exposed)
							game.board[r+i][c+j].expose();
		}
	}

	class ButtonListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(arg0.getButton()== MouseEvent.BUTTON1){

				game.resetButton.setText("😯");

				expose();

				game.resetButton.setText("🙂");

				for( r = 0; r < game.board.length; r++) 
					for(c = 0; c<game.board[r].length; c++){
						if(game.board[r][c].exposed == true)
							game.exposedNumbers++;
					}


				if (game.secretMineCount == 0) {
					game.win();
				}
				
				
			}
			if(arg0.getButton()== MouseEvent.BUTTON3){
				if (flagged == true){
					flagged = false;
					setText(null);
					setBackground(colors);
					game.flagCount++;
					game.counts.setText("Mines on board: "+game.flagCount);
					
					
						if(mine == true){
							game.secretMineCount++;
				

						if (count > 0) 
							setText(count + "");

					}


				}
				else{
					if(exposed == true) {
						return;
					}
					else {
						setText("🚩");
						setBackground(Color.WHITE);
						flagged = true;
						exposed = false;
						if(mine == true) {
							game.secretMineCount--;			
						}
					}

					game.flagCount--;
					game.counts.setText("Mines on board: "+game.flagCount);
					
					
					if (game.secretMineCount == 0) {
						game.win();
					}
				}
			}

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {


		}

		@Override
		public void mouseExited(MouseEvent arg0) {


		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

		}

	}



}