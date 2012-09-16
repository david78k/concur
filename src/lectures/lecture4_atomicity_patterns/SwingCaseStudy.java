package lectures.lecture4_atomicity_patterns;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class SwingCaseStudy {
	
	public void initialize() {
		JButton button = new JButton("Select Me");

		MouseListener mouseListener = new MouseAdapter() {
			@Override public void mousePressed(MouseEvent e) {
				System.out.println("button pressed");
				// doLongComputationAndDisplayResult()
			}
			@Override public void mouseReleased(MouseEvent e) {
				System.out.println("button released");
			}
		};
		
		button.addMouseListener(mouseListener);
	}
}
