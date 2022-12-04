package gui;

import api.BookApi;
import api.ItemApi;
import entity.Book;
import entity.Item;
import login.LoginMember;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BodyMyPage extends JPanel {
	Body body;

	long member_key = LoginMember.getLoginMember().getMemberKey();
	List<entity.Book> bookList = new ArrayList<>();
	BookPanel bookPanel[];
	JPanel IconPanel;
	JPanel ChargePanel;
	int member_point;

	BodyMyPage(Body body) {
		this.body = body;
		System.out.println(LoginMember.getLoginMember().getMemberName());
		bookList = BookApi.bookList(LoginMember.getLoginMember().getMemberKey());
		bookPanel = new BookPanel[bookList.size()];

		setDesign();

		addProfile();

		addIcon();

		addChargePanel();

		addBookList();
	}

	void setDesign() {
		setPreferredSize(new Dimension(1080,630));
		//setBorder(new LineBorder(Colors.gray_b));
		setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		setBackground(Color.white);
	}

	void addProfile() {
		String member_id = LoginMember.getLoginMember().getMemberId();
		String member_name = LoginMember.getLoginMember().getMemberName();
		String member_phone = LoginMember.getLoginMember().getMemberPhone();
		String member_age = String.valueOf(LoginMember.getLoginMember().getMemberAge());
		member_point = 1000000; // 멤버 포인트 API

		JPanel Profile = new JPanel();
		Profile.setPreferredSize(new Dimension(520,300));
		Profile.setBorder(new LineBorder(Colors.gray_b));
		Profile.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		Profile.setBackground(Color.white);
		add(Profile);

		JLabel space1 = new JLabel();
		space1.setPreferredSize(new Dimension(500,50));
		Profile.add(space1);

		JLabel icon = new JLabel(Tools.resizeImage(new ImageIcon("src/img/member.png"), 90,90));
		Profile.add(icon);

		JLabel profile_name = new JLabel(member_name + "님 환영합니다.");
		profile_name.setPreferredSize(new Dimension(500,40));
		profile_name.setFont(Fonts.f9);
		profile_name.setForeground(Colors.gray);
		profile_name.setHorizontalAlignment(JLabel.CENTER);
		profile_name.setVerticalTextPosition(JLabel.BOTTOM);
		Profile.add(profile_name);

		JLabel space2 = new JLabel();
		space2.setPreferredSize(new Dimension(500,30));
		Profile.add(space2);

		JLabel profile_etc = new JLabel(member_id + " / " + member_name + " / " + member_age + "세 / " + member_phone);
		profile_etc.setPreferredSize(new Dimension(500,20));
		profile_etc.setFont(Fonts.f3);
		profile_etc.setForeground(Colors.gray);
		profile_etc.setHorizontalAlignment(JLabel.CENTER);
		profile_etc.setVerticalTextPosition(JLabel.BOTTOM);
		Profile.add(profile_etc);



	}

	void addIcon() {

		IconPanel = new JPanel();
		IconPanel.setPreferredSize(new Dimension(520,300));
		IconPanel.setBorder(new LineBorder(Colors.gray_b));
		IconPanel.setLayout(null);
		IconPanel.setBackground(Color.white);
		add(IconPanel);

		ImageIcon img_book = Tools.resizeImage(new ImageIcon("src/img/book.png"), 90,90);
		JLabel icon_book = new JLabel(img_book);
		icon_book.setSize(90,90);
		icon_book.setLocation(130,70);
		IconPanel.add(icon_book);

		ImageIcon img_point = Tools.resizeImage(new ImageIcon("src/img/point.png"), 90,90);
		JLabel icon_point = new JLabel(img_point);
		icon_point.setSize(90,90);
		icon_point.setLocation(300,70);
		IconPanel.add(icon_point);

		JLabel book = new JLabel(bookList.size()+"개");
		book.setSize(90,20);
		book.setLocation(130, 170);
		book.setFont(Fonts.f2);
		book.setForeground(Colors.gray);
		book.setHorizontalAlignment(JLabel.CENTER);
		IconPanel.add(book);

		JLabel point = new JLabel(Tools.pointConvert(member_point));
		point.setSize(130,20);
		point.setLocation(280, 170);
		point.setFont(Fonts.f2);
		point.setForeground(Colors.gray);
		point.setHorizontalAlignment(JLabel.CENTER);
		IconPanel.add(point);

		JLabel btnChargePanel = new JLabel("충전");
		btnChargePanel.setBounds(300,195,90,40);
		btnChargePanel.setFont(Fonts.f2);
		btnChargePanel.setForeground(Color.white);
		btnChargePanel.setBackground(Colors.blue);
		btnChargePanel.setOpaque(true);
		btnChargePanel.setHorizontalAlignment(JLabel.CENTER);
		btnChargePanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				IconPanel.setVisible(false);
				ChargePanel.setVisible(true);
			}
		});

		IconPanel.add(btnChargePanel);

	}

	void addChargePanel() {
		ChargePanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10,10));
		ChargePanel.setPreferredSize(new Dimension(520,300));
		ChargePanel.setBorder(new LineBorder(Colors.gray_b));
		ChargePanel.setBackground(Color.white);
		ChargePanel.setVisible(false);

		JLabel label = new JLabel("포인트 충전");
		label.setPreferredSize(new Dimension(500,100));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setFont(Fonts.f1);
		label.setForeground(Colors.gray);
		ChargePanel.add(label);

		JLabel pointLabel = new JLabel("포인트");
		pointLabel.setPreferredSize(new Dimension(300,20));
		//pointLabel.setHorizontalAlignment(JLabel.CENTER);
		pointLabel.setFont(Fonts.f2);
		pointLabel.setForeground(Colors.gray);
		ChargePanel.add(pointLabel);
		
		JTextField text_point = new JTextField();
		text_point.setPreferredSize(new Dimension(300,32));
		text_point.setFont(Fonts.f5);
		text_point.setBorder(new LineBorder(Colors.gray_b));
		text_point.setForeground(Colors.gray);
		ChargePanel.add(text_point);

		JLabel space1 = new JLabel();
		space1.setPreferredSize(new Dimension(500,0));
		ChargePanel.add(space1);

		JLabel btnCancel = new JLabel("취소");
		btnCancel.setPreferredSize(new Dimension(145,50));
		btnCancel.setHorizontalAlignment(JLabel.CENTER);
		btnCancel.setFont(Fonts.f2);
		btnCancel.setForeground(Color.white);
		btnCancel.setBackground(Colors.blue);
		btnCancel.setOpaque(true);
		btnCancel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				ChargePanel.setVisible(false);
				IconPanel.setVisible(true);
			}
		});
		ChargePanel.add(btnCancel);
		
		JLabel btnCharge = new JLabel("충전하기");
		btnCharge.setPreferredSize(new Dimension(145,50));
		btnCharge.setHorizontalAlignment(JLabel.CENTER);
		btnCharge.setFont(Fonts.f2);
		btnCharge.setForeground(Color.white);
		btnCharge.setBackground(Colors.blue);
		btnCharge.setOpaque(true);
		btnCharge.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				body.showMyPage();
				//포인트 충전 API
			}
		});
		ChargePanel.add(btnCharge);

		add(ChargePanel);

	}

	void addBookList() {
		JPanel BookPanel = new JPanel();
		BookPanel.setPreferredSize(new Dimension(1050,295));
		BookPanel.setBorder(new LineBorder(Colors.gray_b));
		BookPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		BookPanel.setBackground(Color.white);
		add(BookPanel);

		JLabel space = new JLabel(); // 공백
		space.setPreferredSize(new Dimension(1000,10));
		BookPanel.add(space);

		BookPanel.add(new FormPanel());

		JPanel list = new JPanel();
		list.setPreferredSize(new Dimension(1080,270));
		list.setLayout(new FlowLayout(FlowLayout.CENTER,0,1));
		list.setBackground(Color.white);

		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(1080,list.getPreferredSize().height));
		scroll.getVerticalScrollBar().setUnitIncrement(10); // 스크롤 속도
		scroll.setBorder(null);

		JPanel coverPanel = new JPanel();
		coverPanel.setPreferredSize(new Dimension(1040,list.getPreferredSize().height-23));
		coverPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,0));

		coverPanel.add(scroll);
		BookPanel.add(coverPanel);

		for (int i = 0; i < bookList.size(); i++) {
			Book book = bookList.get(bookList.size()-i-1);
			bookPanel[i] = new BookPanel(body, book);
			list.add(bookPanel[i]);
			if(i>=8) list.setPreferredSize(new Dimension(list.getPreferredSize().width,list.getPreferredSize().height+31));
		}
	}
}

class BookPanel extends JPanel {
	Body body;

	Item item;

	long book_key;

	String item_name ;
	String item_date;
	int item_price ;

	JLabel key;
	JLabel item_label;
	JLabel date;
	JLabel price;
	JLabel btn_cancel;

	BookPanel(Body body, Book book) {
		this.body = body;
		item = ItemApi.findItemByKey(book.getItemKey());

		book_key = book.getBookKey();
		item_name = item.getItemName();
		item_date = book.getItemDate();
		item_price = item.getItemPrice();

		setPreferredSize(new Dimension(850,30));
		setLayout(new FlowLayout(FlowLayout.CENTER,1,0));
		setBackground(Color.white);

		int border_line_size = 0;

		key = new JLabel(book.getBookKey()+"");
		key.setPreferredSize(new Dimension(100,30));
		key.setFont(Fonts.f6);
		key.setForeground(Colors.gray);
		key.setBackground(Colors.sky);
		key.setOpaque(true);
		key.setHorizontalAlignment(JLabel.CENTER);
		key.setBorder(new LineBorder(Colors.gray_b,border_line_size));

		item_label = new JLabel(item_name);
		item_label.setPreferredSize(new Dimension(240,30));
		item_label.setFont(Fonts.f6);
		item_label.setForeground(Colors.gray);
		item_label.setBackground(Colors.sky);
		item_label.setOpaque(true);
		item_label.setHorizontalAlignment(JLabel.CENTER);
		item_label.setBorder(new LineBorder(Colors.gray_b,border_line_size));
		item_label.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				body.showItem(book.getItemKey());
			}
		});

		date = new JLabel(item_date);
		date.setPreferredSize(new Dimension(300,30));
		date.setFont(Fonts.f6);
		date.setForeground(Colors.gray);
		date.setBackground(Colors.sky);
		date.setOpaque(true);
		date.setHorizontalAlignment(JLabel.CENTER);
		date.setBorder(new LineBorder(Colors.gray_b,border_line_size));

		price = new JLabel(Tools.priceConvert(item_price));
		price.setPreferredSize(new Dimension(100,30));
		price.setFont(Fonts.f6);
		price.setForeground(Colors.gray);
		price.setBackground(Colors.sky);
		price.setOpaque(true);
		price.setHorizontalAlignment(JLabel.CENTER);
		price.setBorder(new LineBorder(Colors.gray_b,border_line_size));

		btn_cancel = new JLabel("예약취소");
		btn_cancel.setPreferredSize(new Dimension(100,30));
		btn_cancel.setHorizontalAlignment(JLabel.CENTER);
		btn_cancel.setFont(Fonts.f3);
		btn_cancel.setForeground(Color.white);
		btn_cancel.setBackground(Colors.red);
		btn_cancel.setOpaque(true);
		btn_cancel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (JOptionPane.showOptionDialog(null, item.getItemName() + "\r\n예약을 취소하겠습니까?", "EveryBook",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, Tools.btnYesOrNo, "아니오") == 0) {
					BookApi.bookCancel(book.getBookKey());
					body.showMyPage();
					JOptionPane.showMessageDialog(null, "예약을 취소했습니다.", "EveryBook", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		add(key);
		add(item_label);
		add(date);
		add(price);
		add(btn_cancel);


	}
}
class FormPanel extends JPanel {
	JLabel key;
	JLabel item_Label;
	JLabel date;
	JLabel price;
	JLabel btn_cancel;

	FormPanel() {

		setPreferredSize(new Dimension(1000, 30));
		setLayout(new FlowLayout(FlowLayout.CENTER, 1, 0));
		setBackground(Color.white);

		int border_line_size = 0;

		key = new JLabel("예약번호");
		key.setPreferredSize(new Dimension(100, 30));
		key.setFont(Fonts.f5);
		key.setForeground(Color.white);
		key.setBackground(Colors.blue);
		key.setOpaque(true);
		key.setHorizontalAlignment(JLabel.CENTER);
		key.setBorder(new LineBorder(Colors.gray_b, border_line_size));

		item_Label = new JLabel("상품");
		item_Label.setPreferredSize(new Dimension(240, 30));
		item_Label.setFont(Fonts.f5);
		item_Label.setForeground(Color.white);
		item_Label.setBackground(Colors.blue);
		item_Label.setOpaque(true);
		item_Label.setHorizontalAlignment(JLabel.CENTER);
		item_Label.setBorder(new LineBorder(Colors.gray_b, border_line_size));

		date = new JLabel("예약일시");
		date.setPreferredSize(new Dimension(300, 30));
		date.setFont(Fonts.f5);
		date.setForeground(Color.white);
		date.setBackground(Colors.blue);
		date.setOpaque(true);
		date.setHorizontalAlignment(JLabel.CENTER);
		date.setBorder(new LineBorder(Colors.gray_b, border_line_size));

		price = new JLabel("결제금액");
		price.setPreferredSize(new Dimension(100, 30));
		price.setFont(Fonts.f5);
		price.setForeground(Color.white);
		price.setBackground(Colors.blue);
		price.setOpaque(true);
		price.setHorizontalAlignment(JLabel.CENTER);
		price.setBorder(new LineBorder(Colors.gray_b, border_line_size));

		btn_cancel = new JLabel();
		btn_cancel.setPreferredSize(new Dimension(100, 30));
		btn_cancel.setFont(Fonts.f5);
		btn_cancel.setForeground(Color.white);
		btn_cancel.setBackground(Colors.blue);
		btn_cancel.setOpaque(true);
		btn_cancel.setHorizontalAlignment(JLabel.CENTER);
		btn_cancel.setBorder(new LineBorder(Colors.gray_b, border_line_size));

		add(key);
		add(item_Label);
		add(date);
		add(price);
		add(btn_cancel);


	}
}