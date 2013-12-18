package dbsDiaryDataInterpreter;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class SelectorFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SelectorFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 211, 103);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Translate Blobo Events");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  new BloboEventDataInterpreter().start();
			}
		});
		btnNewButton.setBounds(6, 6, 198, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Translate Recorded Diaries");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  new DiaryDataInterpreter().start();
			}
		});
		btnNewButton_1.setBounds(6, 47, 198, 29);
		contentPane.add(btnNewButton_1);
	}
}
