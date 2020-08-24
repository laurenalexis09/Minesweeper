import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


 public class ResetListener implements ActionListener {

		private Minesweeper game;

		public ResetListener(Minesweeper game) {
			this.game = game;
		}
		
		public void actionPerformed(ActionEvent e) {
			game.dispose();
			new Minesweeper();
			
	
			
		}

	}

