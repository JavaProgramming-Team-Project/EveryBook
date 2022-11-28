package gui;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class BodyMyPage extends JPanel {
	Body body;

	int member_key;
	Book book[];

	BodyMyPage(Body body, int member_key) {
		this.body = body;
		this.member_key = member_key;
		book = new Book[5];

		setDesign();

		addProfile();

		addIcon();

		addBookList();
	}

	void setDesign() {
		setPreferredSize(new Dimension(1080,630));
		//setBorder(new LineBorder(Colors.gray_b));
		setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		setBackground(Color.white);
	}

	void addProfile() {
		String member_id = "jaypark9490";
		String member_name = "박재정";
		String member_phone = "010-3456-4567";
		String member_age = "21";

		JPanel Profile = new JPanel();
		Profile.setPreferredSize(new Dimension(520,300));
		Profile.setBorder(new LineBorder(Colors.gray_b));
		Profile.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		Profile.setBackground(Color.white);
		add(Profile);

		ImageIcon img_profile = Tools.resizeImage(new ImageIcon("src/img/member.png"), 32,32);
		JLabel icon = new JLabel(img_profile);
		Profile.add(icon);

		JLabel profile = new JLabel("회원정보");
		profile.setPreferredSize(new Dimension(440,42));
		profile.setFont(Fonts.f1);
		profile.setForeground(Colors.gray);
		profile.setVerticalAlignment(JLabel.BOTTOM);
		Profile.add(profile);

		JLabel space = new JLabel();
		space.setPreferredSize(new Dimension(480,20));
		Profile.add(space);

		int form_width = 120;
		int profile_width = 360;
		int profile_height = 25;

		JLabel form_id = new JLabel("아이디");
		form_id.setPreferredSize(new Dimension(form_width,profile_height));
		form_id.setFont(Fonts.f2);
		form_id.setForeground(Colors.gray);
		form_id.setHorizontalAlignment(JLabel.RIGHT);

		JLabel form_name = new JLabel("이름");
		form_name.setPreferredSize(new Dimension(form_width,profile_height));
		form_name.setFont(Fonts.f2);
		form_name.setForeground(Colors.gray);
		form_name.setHorizontalAlignment(JLabel.RIGHT);

		JLabel form_phone = new JLabel("전화번호");
		form_phone.setPreferredSize(new Dimension(form_width,profile_height));
		form_phone.setFont(Fonts.f2);
		form_phone.setForeground(Colors.gray);
		form_phone.setHorizontalAlignment(JLabel.RIGHT);

		JLabel form_age = new JLabel("나이");
		form_age.setPreferredSize(new Dimension(form_width,profile_height));
		form_age.setFont(Fonts.f2);
		form_age.setForeground(Colors.gray);
		form_age.setHorizontalAlignment(JLabel.RIGHT);

		JLabel profile_id = new JLabel(member_id);
		profile_id.setPreferredSize(new Dimension(profile_width,profile_height));
		profile_id.setFont(Fonts.f2);
		profile_id.setForeground(Colors.gray);

		JLabel profile_name = new JLabel(member_name);
		profile_name.setPreferredSize(new Dimension(profile_width,profile_height));
		profile_name.setFont(Fonts.f2);
		profile_name.setForeground(Colors.gray);

		JLabel profile_phone = new JLabel(member_phone);
		profile_phone.setPreferredSize(new Dimension(profile_width,profile_height));
		profile_phone.setFont(Fonts.f2);
		profile_phone.setForeground(Colors.gray);

		JLabel profile_age = new JLabel(member_age);
		profile_age.setPreferredSize(new Dimension(profile_width,profile_height));
		profile_age.setFont(Fonts.f2);
		profile_age.setForeground(Colors.gray);


		Profile.add(form_id);
		Profile.add(profile_id);

		Profile.add(form_name);
		Profile.add(profile_name);

		Profile.add(form_phone);
		Profile.add(profile_phone);

		Profile.add(form_age);
		Profile.add(profile_age);



	}

	void addIcon() {
		JPanel Icon = new JPanel();
		Icon.setPreferredSize(new Dimension(520,300));
		Icon.setBorder(new LineBorder(Colors.gray_b));
		Icon.setLayout(null);
		//Icon.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		Icon.setBackground(Color.white);
		add(Icon);

		ImageIcon img_book = Tools.resizeImage(new ImageIcon("src/img/book.png"), 90,90);
		JLabel icon_book = new JLabel(img_book);
		icon_book.setSize(90,90);
		icon_book.setLocation(170-40,70);
		Icon.add(icon_book);

		ImageIcon img_point = Tools.resizeImage(new ImageIcon("src/img/point.png"), 90,90);
		JLabel icon_point = new JLabel(img_point);
		icon_point.setSize(90,90);
		icon_point.setLocation(260+40,70);
		Icon.add(icon_point);

		JLabel book = new JLabel("<html><center>예약<br>5개");
		book.setSize(90,90);
		book.setLocation(170-40, 150);
		book.setFont(Fonts.f2);
		book.setForeground(Colors.gray);
		book.setHorizontalAlignment(JLabel.CENTER);
		Icon.add(book);

		JLabel point = new JLabel("<html><center>포인트<br>10,000P");
		point.setSize(90,90);
		point.setLocation(260+40, 150);
		point.setFont(Fonts.f2);
		point.setForeground(Colors.gray);
		point.setHorizontalAlignment(JLabel.CENTER);
		Icon.add(point);


	}

	void addBookList() {
		JPanel list = new JPanel();
		list.setPreferredSize(new Dimension(1050,295));
		list.setBorder(new LineBorder(Colors.gray_b));
		list.setLayout(new FlowLayout(FlowLayout.CENTER,10,2));
		list.setBackground(Color.white);
		add(list);

		ImageIcon img_book = Tools.resizeImage(new ImageIcon("src/img/book.png"), 32,32);
		JLabel icon = new JLabel(img_book);
		list.add(icon);

		JLabel booklist = new JLabel("예약내역");
		booklist.setPreferredSize(new Dimension(960,44));
		booklist.setFont(Fonts.f1);
		booklist.setForeground(Colors.gray);
		booklist.setVerticalAlignment(JLabel.BOTTOM);
		list.add(booklist);

		JLabel space = new JLabel();
		space.setPreferredSize(new Dimension(1000,10));
		list.add(space);

		for (int i = 0; i < book.length; i++) {
			int book_key = 221121001;
			book[i] = new Book(body,book_key);
			list.add(book[i]);
		}

	}
}

class Book extends JPanel {
	Body body;

	int book_key;
	int member_key;
	String item_name = "롯데호텔 서울";
	String item_date = "2022-11-23";
	String item_price = Tools.priceConvert(236800);

	JLabel key;
	JLabel item;
	JLabel date;
	JLabel price;
	JLabel btn_cancel;

	Book(Body body, int book_key) {
		this.body = body;
		this.book_key = book_key;

		setPreferredSize(new Dimension(1000,30));
		//setBorder(new LineBorder(Colors.gray_b));
		setLayout(new FlowLayout(FlowLayout.CENTER,2,0));
		setBackground(Color.white);

		key = new JLabel(book_key+"");
		key.setPreferredSize(new Dimension(100,30));
		key.setFont(Fonts.f6);
		key.setForeground(Colors.gray);
		key.setBackground(Colors.sky);
		key.setOpaque(true);
		key.setHorizontalAlignment(JLabel.CENTER);

		item = new JLabel(item_name);
		item.setPreferredSize(new Dimension(250,30));
		item.setFont(Fonts.f6);
		item.setForeground(Colors.gray);
		item.setBackground(Colors.sky);
		item.setOpaque(true);
		item.setHorizontalAlignment(JLabel.CENTER);


		date = new JLabel(item_date);
		date.setPreferredSize(new Dimension(250,30));
		date.setFont(Fonts.f6);
		date.setForeground(Colors.gray);
		date.setBackground(Colors.sky);
		date.setOpaque(true);
		date.setHorizontalAlignment(JLabel.CENTER);

		price = new JLabel(item_price);
		price.setPreferredSize(new Dimension(100,30));
		price.setFont(Fonts.f6);
		price.setForeground(Colors.gray);
		price.setBackground(Colors.sky);
		price.setOpaque(true);
		price.setHorizontalAlignment(JLabel.CENTER);

		String btn[] = {"예", "아니오"};
		btn_cancel = new JLabel("예약취소");
		btn_cancel.setPreferredSize(new Dimension(100,30));
		btn_cancel.setHorizontalAlignment(JLabel.CENTER);
		btn_cancel.setFont(Fonts.f3);
		btn_cancel.setForeground(Color.white);
		btn_cancel.setBackground(Colors.red);
		btn_cancel.setOpaque(true);
		btn_cancel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (JOptionPane.showOptionDialog(null, "해당 예약을 취소하겠습니까?", "EveryBook",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, btn, "아니오") == 0) {
					body.showMyPage(member_key);
					JOptionPane.showMessageDialog(null, "해당 예약을 취소했습니다.", "EveryBook", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		add(key);
		add(item);
		add(date);
		add(price);
		add(btn_cancel);


	}
}
