package gui;

import api.MemberApi;
import entity.Member;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class BodySignUp extends JPanel {

	Body body;
	JLabel label1 = new JLabel("회원가입");
	JLabel label2 = new JLabel("아이디");
	JLabel label3 = new JLabel("비밀번호");
	JLabel label4 = new JLabel("이름");
	JLabel label5 = new JLabel("전화번호");
	JLabel label6 = new JLabel("나이");
	JTextField text_id = new JTextField(15);
	JPasswordField text_pw = new JPasswordField(15);
	JTextField text_name = new JTextField(15);
	JTextField text_phone = new JTextField(15);
	JTextField text_age = new JTextField(15);
	JLabel btn_cancel = new JLabel("취소");
	JLabel btn_signup = new JLabel("회원가입");
	JLabel msg_error = new JLabel("");

	public BodySignUp(Body body) {
		this.body = body;

		setDesign();

		btn_cancel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				body.showLogin();
			}
		});

		btn_signup.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(text_id.getText().isBlank() || text_age.getText().isBlank() || text_name.getText().isBlank() || text_pw.getText().isBlank() || text_phone.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "회원가입 오류: 빈칸이 있습니다.", "EveryBook", JOptionPane.ERROR_MESSAGE);
				} else {
					Member member = new Member(0L,text_id.getText(),text_pw.getText(), text_name.getText(),
							text_phone.getText(), Integer.parseInt(text_age.getText()), 5000);
					System.out.println("test");
					MemberApi.signUp(member);
					JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다.", "EveryBook", JOptionPane.INFORMATION_MESSAGE);
					body.showLogin();
					body.previous_page = null;
				}

			}
		});

		text_id.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(!String.valueOf(e.getKeyChar()).matches("^[0-9a-zA-Z]*$")) {
					e.consume();
					setErrorMessage("아이디는 영어와 숫자만 입력 가능합니다.");
				} else { setErrorMessage(""); }
			}});
		text_name.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(!String.valueOf(e.getKeyChar()).matches("^[가-힣]*$")) {
					e.consume();
					setErrorMessage("이름은 한글만 입력 가능합니다.");
				} else { setErrorMessage(""); }
			}});
		text_phone.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(!String.valueOf(e.getKeyChar()).matches("^[-0-9]*$")) {
					e.consume();
					setErrorMessage("전화번호는 숫자와 하이픈만 입력 가능합니다.");
				} else { setErrorMessage(""); }
			}});
		text_age.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(!String.valueOf(e.getKeyChar()).matches("^[0-9]*$")) {
					e.consume();
					setErrorMessage("나이는 숫자만 입력 가능합니다.");
				} else { setErrorMessage(""); }
			}});
		
		
		text_pw.addKeyListener(new KeyAdapter() { public void keyTyped(KeyEvent e) { if(!String.valueOf(e.getKeyChar()).matches("^[0-9a-zA-Z]*$")) e.consume(); }});
		text_name.addKeyListener(new KeyAdapter() { public void keyTyped(KeyEvent e) { if(!String.valueOf(e.getKeyChar()).matches("^[가-힣]*$")) e.consume(); }});
		text_phone.addKeyListener(new KeyAdapter() { public void keyTyped(KeyEvent e) { if(!String.valueOf(e.getKeyChar()).matches("^[!-]*[0-9]*$")) e.consume(); }});
		text_age.addKeyListener(new KeyAdapter() { public void keyTyped(KeyEvent e) { if(!String.valueOf(e.getKeyChar()).matches("^[0-9]*$")) e.consume(); }});

	}

	void setDesign() {

		setPreferredSize(new Dimension(400,550));
		setBorder(new LineBorder(Colors.gray_b));
		setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		setBackground(Color.white);

		label1.setPreferredSize(new Dimension(400,80));
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setFont(Fonts.f1);
		label1.setForeground(Colors.gray);

		label2.setPreferredSize(new Dimension(400,20));
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setFont(Fonts.f2);
		label2.setForeground(Colors.gray);

		label3.setPreferredSize(new Dimension(400,20));
		label3.setHorizontalAlignment(JLabel.CENTER);
		label3.setFont(Fonts.f2);
		label3.setForeground(Colors.gray);

		label4.setPreferredSize(new Dimension(400,20));
		label4.setHorizontalAlignment(JLabel.CENTER);
		label4.setFont(Fonts.f2);
		label4.setForeground(Colors.gray);

		label5.setPreferredSize(new Dimension(400,20));
		label5.setHorizontalAlignment(JLabel.CENTER);
		label5.setFont(Fonts.f2);
		label5.setForeground(Colors.gray);

		label6.setPreferredSize(new Dimension(400,20));
		label6.setHorizontalAlignment(JLabel.CENTER);
		label6.setFont(Fonts.f2);
		label6.setForeground(Colors.gray);

		text_id.setPreferredSize(new Dimension(400,32));
		text_id.setFont(Fonts.f2);
		text_id.setBorder(new LineBorder(Colors.gray_b));
		text_id.setForeground(Colors.gray);

		text_pw.setPreferredSize(new Dimension(400,32));
		text_pw.setFont(Fonts.f2);
		text_pw.setBorder(new LineBorder(Colors.gray_b));
		text_pw.setForeground(Colors.gray);
		text_pw.setEchoChar('●');

		text_name.setPreferredSize(new Dimension(400,32));
		text_name.setFont(Fonts.f2);
		text_name.setBorder(new LineBorder(Colors.gray_b));
		text_name.setForeground(Colors.gray);

		text_phone.setPreferredSize(new Dimension(400,32));
		text_phone.setFont(Fonts.f2);
		text_phone.setBorder(new LineBorder(Colors.gray_b));
		text_phone.setForeground(Colors.gray);

		text_age.setPreferredSize(new Dimension(400,32));
		text_age.setFont(Fonts.f2);
		text_age.setBorder(new LineBorder(Colors.gray_b));
		text_age.setForeground(Colors.gray);


		btn_cancel.setPreferredSize(new Dimension(145,50));
		btn_cancel.setHorizontalAlignment(JLabel.CENTER);
		btn_cancel.setFont(Fonts.f2);
		btn_cancel.setForeground(Color.white);
		btn_cancel.setBackground(Colors.blue);
		btn_cancel.setOpaque(true);

		btn_signup.setPreferredSize(new Dimension(145,50));
		btn_signup.setHorizontalAlignment(JLabel.CENTER);
		btn_signup.setFont(Fonts.f2);
		btn_signup.setForeground(Color.white);
		btn_signup.setBackground(Colors.blue);
		btn_signup.setOpaque(true);

		msg_error.setPreferredSize(new Dimension(400,20));
		msg_error.setHorizontalAlignment(JLabel.CENTER);
		msg_error.setFont(Fonts.f3);
		msg_error.setForeground(Colors.red);

		add(label1);
		add(label2);
		add(text_id);
		add(label3);
		add(text_pw);
		add(label4);
		add(text_name);
		add(label5);
		add(text_phone);
		add(label6);
		add(text_age);
		add(btn_cancel);
		add(btn_signup);
		add(msg_error);
	}

	void setErrorMessage(String msg) {
		msg_error.setText(msg);
	}

}
