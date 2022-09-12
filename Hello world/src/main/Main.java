package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D_cv");
		
		Game_Window game_Window = new Game_Window();
		window.add(game_Window);
		
		
		window.pack();
		
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		game_Window.Start_Game_Thread();
	}

}
