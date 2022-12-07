package gui;

import api.MemberApi;
import dto.LoginDto;
import login.LoginMember;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class BodyLogin extends JPanel {

	Body body;
	JLabel label1 = new JLabel("로그인");
	JTextField text_id = new JTextField("아이디", 17);
	JPasswordField text_pw = new JPasswordField("비밀번호", 17);
	JLabel btn_register = new JLabel("회원가입");
	JLabel btn_login = new JLabel("로그인");

	public BodyLogin(Body body) {
		this.body = body;

		setDesign();

		btn_register.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				body.showSignUp();
			}
		});

		btn_login.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				btnLogin();
			}
		});

		text_id.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) { if(text_id.getText().equals("아이디")) text_id.setText(""); }
		});

		text_pw.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) { if(text_pw.getText().equals("비밀번호")) text_pw.setText("");}
		});

		text_id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin();
			}
		});

		text_pw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnLogin();
			}
		});

	}

	void setDesign() {

		setPreferredSize(new Dimension(400,280));
		setBorder(new LineBorder(Colors.gray_b));
		setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		setBackground(Color.white);

		label1.setPreferredSize(new Dimension(400,80));
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setFont(Fonts.f1);
		label1.setForeground(Colors.gray);

		text_id.setPreferredSize(new Dimension(400,32));
		text_id.setFont(Fonts.f5);
		text_id.setBorder(new LineBorder(Colors.gray_b));
		text_id.setForeground(Colors.gray);

		text_pw.setPreferredSize(new Dimension(400,32));
		text_pw.setFont(Fonts.f5);
		text_pw.setBorder(new LineBorder(Colors.gray_b));
		text_pw.setForeground(Colors.gray);
		text_pw.setEchoChar('●');

		btn_login.setPreferredSize(new Dimension(150,50));
		btn_login.setHorizontalAlignment(JLabel.CENTER);
		btn_login.setFont(Fonts.f2);
		btn_login.setForeground(Color.white);
		btn_login.setBackground(Colors.blue);
		btn_login.setOpaque(true);

		btn_register.setPreferredSize(new Dimension(150,50));
		btn_register.setHorizontalAlignment(JLabel.CENTER);
		btn_register.setFont(Fonts.f2);
		btn_register.setForeground(Color.white);
		btn_register.setBackground(Colors.blue);
		btn_register.setOpaque(true);

		add(label1);
		add(text_id);
		add(text_pw);
		add(btn_register);
		add(btn_login);
	}

	void btnLogin() {
		if(!text_id.getText().isEmpty() && !text_pw.getText().isEmpty()) {
			LoginDto loginDto = new LoginDto(text_id.getText(),text_pw.getText());
			MemberApi.login(loginDto);
			body.mf.head.logo.addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					body.showMain();
				}
			});
			body.mf.head.menu.setVisible(true);
			body.showMain();
			body.previous_page = null;
		} else {
			JOptionPane.showMessageDialog(null, "존재하지 않는 회원입니다.", "EveryBook", JOptionPane.ERROR_MESSAGE);
		}
	}
}