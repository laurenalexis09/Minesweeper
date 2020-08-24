import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class RulesListener implements ActionListener {

	  LocalDate now = LocalDate.now();  
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	JOptionPane.showMessageDialog(null, "Clicking on a square will give you a blank square, a mine, or a number. \n That number is the number of how many mines are surrounding it. \n If there is no number, that means there are no mines surrounding it. A mine will remain hidden unless it is clicked on. \n If you suspect a square is a mine, you flag it with the right click. \n Clicking a mine will end the game. \n To win, you need to expose all of the non mine squares, and flag all the mines.\n \n Galaxy Minesweeper 2018. Last Played : " +now, "🌌  Rules 🌌 ", JOptionPane.PLAIN_MESSAGE);
	
	}

}
