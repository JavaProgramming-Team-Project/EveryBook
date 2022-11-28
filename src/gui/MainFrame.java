package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {

	Head head = new Head(this);
	Body body = new Body(this);

	MainFrame() {

		setDesign();

	}

	void setDesign() {
		setTitle("EveryBook");
		setSize(1296,759); // 1280 x 720
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		setVisible(true);

		add(head);
		add(body);
	}

}