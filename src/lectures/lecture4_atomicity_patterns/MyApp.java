package lectures.lecture4_atomicity_patterns;

import javax.swing.SwingUtilities;

public class MyApp implements Runnable {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MyApp());
	}

	@Override
	public void run() {
		// Invoked on the event dispatching thread.
		// Construct and show GUI
	}

}
