import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
	
	private Minesweeper game;
	
	public TimerListener(Minesweeper game){
		this.game = game;
	}
	
	public void actionPerformed(ActionEvent e) {
		game.time();
	}

}
