package functionality;

import javax.swing.SwingUtilities;

import visual.MainMenu;

public class WhereItAllBegins {
	public static void main(String[] args) throws Exception{
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainMenu frame;
				try {
					frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}
}
