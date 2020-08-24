import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class Minesweeper extends JFrame  {
	public int flagCount = 0;
	public int secretMineCount = 0;
	public int exposedNumbers = 0;
	public int n = 10;


	public double difficulty = .10;

	public MineSquare[][] board;

	private JPanel panel = new JPanel();
	private JPanel controlPanel = new JPanel();
	private JPanel wordsAndRulesPanel = new JPanel();
	JPanel myPanel = new JPanel();

	public long startTime;
	public long endTime = System.currentTimeMillis();

	JTextField hardness = new JTextField(10);
	JTextField boardSize = new JTextField(10);	
	public JTextField counts = new JTextField();
	public JTextField timeField = new JTextField();
	private JTextField e1 = new JTextField();
	private JTextField e2 = new JTextField();

	private JButton rulesButton = new JButton();	
	public JButton resetButton = new JButton("🙂");

	public Timer timer;


	public String diff = null;
	public String sizes = null;



	public Minesweeper(){


		secretMineCount = 0;
		exposedNumbers = 0;
		UIManager.put("OptionPane.okButtonText", "Okay!");

		myPanel.add(new JLabel("boardSize (Ex. Entering 10 gives 10x10 board): "));
		myPanel.add(boardSize);
		myPanel.add(Box.createHorizontalStrut(15));
		myPanel.add(new JLabel("Difficulty: (Easy/Medium/Hard): "));
		myPanel.add(hardness);
		myPanel.add(Box.createVerticalGlue());


		int result = JOptionPane.showConfirmDialog(null, myPanel,
				"Welcome to Minesweeper!", JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			
			sizes = boardSize.getText();
			diff =	hardness.getText();

			if(sizes.equals(null)||sizes.equals("")){
				sizes = "10";
			}

			if(diff.equals(null)|diff.equals("")){
				diff = "easy";
			}


		}

		n = Integer.parseInt(sizes);



		if( diff.equalsIgnoreCase("Easy")) {
			difficulty = 0.10;
		}
		if( diff.equalsIgnoreCase("Medium")) {
			difficulty = 0.15;
		}
		if( diff.equalsIgnoreCase("Hard")) {
			difficulty = 0.20;
		}



		int r, c;

		board = new MineSquare[n+2][n+2];

		setLayout(new BorderLayout());

		add(panel, BorderLayout.CENTER);
		add(controlPanel, BorderLayout.NORTH);
		add(wordsAndRulesPanel, BorderLayout.SOUTH);

		panel.setLayout(new GridLayout (n,n));
		controlPanel.setLayout(new GridLayout(1,3));
		wordsAndRulesPanel.setLayout(new GridLayout(1,3));

		controlPanel.add(counts);
		controlPanel.add(resetButton);
		controlPanel.add(timeField);

		wordsAndRulesPanel.add(e1);
		wordsAndRulesPanel.add(e2);
		wordsAndRulesPanel.add(rulesButton);

		counts.setHorizontalAlignment(JTextField.CENTER);
		timeField.setHorizontalAlignment(JTextField.CENTER);
		e1.setHorizontalAlignment(JTextField.CENTER);
		e2.setHorizontalAlignment(JTextField.CENTER);
		rulesButton.setHorizontalAlignment(JTextField.CENTER);


		rulesButton.setFont(rulesButton.getFont().deriveFont(15));

		resetButton.addActionListener(new ResetListener(this));
		rulesButton.addActionListener(new RulesListener());

		Color color = new Color(84, 22, 180);

		e1.setText("Galaxy");
		e2.setText("Minesweeper");
		rulesButton.setText(" 🌌  Rules 🌌 ");

		e1.setForeground(color);
		e2.setForeground(color);
		rulesButton.setForeground(color);
		counts.setForeground(color);
		resetButton.setForeground(Color.WHITE);
		resetButton.setBackground(color);
		timeField.setForeground(color);


		for( r = 0; r < board.length; r++) {
			for(c = 0; c<board[r].length; c++){
				if(r == 0 || r >= board.length - 1 || c == 0 || c >= board[r].length-1){
					board[r][c] = new MineSquare(r,c, false, this);
					board[r][c].setBackground(MineSquare.colors);
					board[r][c].setExposed();

				}

				else{
					board[r][c] = new MineSquare(r,c,Math.random()<=difficulty, this);
					panel.add(board[r][c]);	
					if(board[r][c].mine == true) {
						flagCount++;
						secretMineCount++;



						board[r][c].setBackground(MineSquare.colors);
					}
					if(board[r][c].mine == false) {
						board[r][c].setBackground(MineSquare.colors);
					}

					counts.setText("Mines on board: "+flagCount);


				}
			}
		}

		pack();
		setSize(n*60, n*60);

		resetButton.setSize(10,10);
		setTitle("⭐️  Galaxy Minesweeper ⭐️");
		setLocationRelativeTo(null);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new java.awt.event.WindowAdapter() {

			public void windowClosing(java.awt.event.WindowEvent e) {
				timer.stop();
				e.getWindow().dispose();

			}
		});

		setVisible(true);

		timer = new Timer(0, new TimerListener(this));

		startTime = System.currentTimeMillis();

		timer.start();
	}

	public void time() {
		endTime = System.currentTimeMillis();
		long currentTime = (endTime - startTime);
		timeField.setText(""+(currentTime/1000));
	}

	public void win() {
		for (int r = 0; r < board.length; r++)
			for (int c = 0; c < board[r].length; c++)
				if (!board[r][c].exposed && !board[r][c].mine)
					return;
		resetButton.setText("✨ ");
		timer.stop();
		int input = JOptionPane.showConfirmDialog(null, "  🌌  You won!!! 🌌   Do you want to play again? (Yes/No) ", "You win!", JOptionPane.YES_NO_OPTION);



		if(input == JOptionPane.YES_OPTION) {
			dispose();
			new Minesweeper();

		}
		else{
			dispose();
		}
	}

	public static void main(String[] args){
		
		new Minesweeper();




	}

}




