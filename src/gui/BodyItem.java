package gui;

import api.BookApi;
import api.ItemApi;
import api.ReviewApi;
import dto.ReviewListDto;
import entity.Book;
import entity.Item;
import entity.Review;
import login.LoginMember;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class BodyItem extends JPanel {
	Body body;
	long member_key = LoginMember.getLoginMember().getMemberKey();
	List<ReviewListDto> reviewList;
	Item item;
	long item_key;
	int item_price;
	int item_star;
	String item_name;
	String item_body;
	String item_address;
	String item_phone;
	String item_picture;

	ReviewPanel reviewPanel[];

	JTextArea text_review; // 등록할 리뷰
	JLabel btn_write; // 리뷰 등록
	JComboBox combo_star; // 리뷰 별점

	JComboBox combo_date;
	JLabel price;
	JLabel btn_book;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	BodyItem(Body body, long item_key) {
		this.body = body;
		this.item_key = item_key;

		item = ItemApi.findItemByKey(item_key);
		item_picture = item.getItemImage();
		item_name = item.getItemName();
		item_star = (int) item.getAvgRating();
		item_price = item.getItemPrice();
		item_body = item.getItemBody();
		item_address = item.getItemAddress();
		item_phone = item.getItemPhone();

		setDesign();

		addImage();

		addReviewList();

		addWriteReview();

		addBody();

		addBook();

	}

	void setDesign() {
		setPreferredSize(new Dimension(1080,650));
		setLayout(null);
		setBackground(Color.white);
	}

	void addImage() {
		JPanel Image = new JPanel();
		Image.setSize(560,420);
		Image.setLocation(10,10);
		//Image.setPreferredSize(new Dimension(560,420));
		Image.setBorder(new LineBorder(Colors.gray_b));
		Image.setLayout(new FlowLayout(FlowLayout.CENTER,5,6));
		Image.setBackground(Color.white);
		add(Image);

		JLabel picture = null;
		try {
			picture = new JLabel(Tools.resizeImage(Tools.urlImage(item_picture), 540, 405));
		} catch (Exception e) { }

		Image.add(picture);
	}

	void addReviewList() {

		reviewList = ReviewApi.reviewList(item_key);
		reviewPanel = new ReviewPanel[reviewList.size()];

		JPanel list = new JPanel();
		list.setPreferredSize(new Dimension(490,420));
		list.setLayout(new FlowLayout(FlowLayout.LEFT,0,10));
		list.setBackground(Color.white);

		JScrollPane scroll = new JScrollPane(list);
		scroll.setSize(530,420); // 1080 + 40
		scroll.setLocation(580,10);
		scroll.getVerticalScrollBar().setUnitIncrement(5); // 스크롤 속도
		scroll.setBorder(null);

		for (int i = 0; i < reviewList.size(); i++) {

			reviewPanel[i] = new ReviewPanel(reviewList.get(reviewList.size()-i-1),body,item_key);
			list.add(reviewPanel[i]);
			if(i>=4) list.setPreferredSize(new Dimension(list.getPreferredSize().width,list.getPreferredSize().height+102));
		}

		add(scroll);

		if(reviewList.size() == 0) { // 리뷰가 없을경우
			list.setBorder(new LineBorder(Colors.gray_b));

			JLabel noReview = new JLabel("리뷰가 아직 없습니다.");
			noReview.setPreferredSize(new Dimension(490,420));
			noReview.setFont(Fonts.f8);
			noReview.setForeground(Colors.gray);
			noReview.setHorizontalAlignment(JLabel.CENTER);

			list.add(noReview);
		}
	}

	void addWriteReview() {
		JPanel Write = new JPanel();
		Write.setSize(490,92);
		Write.setLocation(580,440);
		Write.setBorder(new LineBorder(Colors.gray_b));
		Write.setLayout(null);
		Write.setBackground(Color.white);

		text_review = new JTextArea();
		text_review.setSize(360,72);
		text_review.setLocation(10,10);
		text_review.setFont(Fonts.f6);
		text_review.setBorder(new LineBorder(Colors.gray_b));
		text_review.setForeground(Colors.gray);
		text_review.setLineWrap(true);
		text_review.setText("리뷰를 작성해주세요.");
		text_review.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				if(text_review.getText().equals("리뷰를 작성해주세요.")) text_review.setText("");
			}
		});

		btn_write = new JLabel("작성");
		btn_write.setSize(100,35);
		btn_write.setLocation(380,40);
		btn_write.setHorizontalAlignment(JLabel.CENTER);
		btn_write.setFont(Fonts.f5);
		btn_write.setForeground(Color.white);
		btn_write.setBackground(Colors.blue);
		btn_write.setOpaque(true);


		btn_write.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				List<Book> list = BookApi.bookList(member_key);

				boolean isBooked = false;
				for (int i = 0; i < list.size(); i++) if(list.get(i).getItemKey() == item_key) isBooked = true;

				if(text_review.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "내용을 입력해 주세요.", "EveryBook", JOptionPane.ERROR_MESSAGE);
				} else if(!isBooked) {
					JOptionPane.showMessageDialog(null, "예약 내역이 없습니다.", "EveryBook", JOptionPane.ERROR_MESSAGE);
				} else if (JOptionPane.showOptionDialog(null, "리뷰를 작성하겠습니까?", "EveryBook",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, Tools.btnYesOrNo, "아니오") == 0) {
					Review review = new Review(0L, member_key, item_key, Integer.parseInt(combo_star.getSelectedItem().toString()), text_review.getText(), LocalDate.now().toString());
					ReviewApi.write(review);
					body.showItem(item_key);
				}
			}
		});

		combo_star = new JComboBox();
		combo_star.setSize(100,20);
		combo_star.setLocation(380,15);
		combo_star.setFont(Fonts.f6);

		for (int i = 1; i <= 5; i++) combo_star.addItem(i);

		Write.add(text_review);
		Write.add(combo_star);
		Write.add(btn_write);
		add(Write);
	}

	void addBody() {

		JPanel Body = new JPanel();
		Body.setSize(560,190);
		Body.setLocation(10,440);
		//Body.setPreferredSize(new Dimension(560,190));
		Body.setBorder(new LineBorder(Colors.gray_b));
		Body.setLayout(null);
		Body.setBackground(Color.white);
		add(Body);

		JLabel name = new JLabel(item_name);
		name.setSize(425, 20);
		name.setLocation(10,15);
		name.setFont(Fonts.f5);
		name.setForeground(Colors.gray);
		Body.add(name);

		ImageIcon img_star = Tools.resizeImage(new ImageIcon("src/img/star_" + item_star + ".png"), 125,20);
		JLabel star = new JLabel(img_star);
		star.setSize(125,20);
		star.setLocation(425,10);
		Body.add(star);

		JLabel body = new JLabel("<html>" + item_body);
		body.setSize(540, 115);
		body.setLocation(10,45);
		body.setFont(Fonts.f6);
		body.setForeground(Colors.gray);
		body.setVerticalAlignment(JLabel.TOP);
		Body.add(body);

		JLabel address = new JLabel("<html>" + item_address + " / " + item_phone);
		address.setSize(540, 20);
		address.setLocation(10,160);
		address.setFont(Fonts.f6);
		address.setForeground(Colors.gray);
		Body.add(address);
	}

	void addBook() {

		JPanel BookPanel = new JPanel();
		BookPanel.setSize(490,90);
		BookPanel.setLocation(580,540);
		//Book.setPreferredSize(new Dimension(490,190));
		BookPanel.setBorder(new LineBorder(Colors.gray_b));
		BookPanel.setLayout(null);
		BookPanel.setBackground(Color.white);
		add(BookPanel);

		combo_date = new JComboBox();
		combo_date.setSize(150,25);
		combo_date.setLocation(70,20);
		combo_date.setFont(Fonts.f5);
		for (int i = 0; i < 31; i++) {
			Calendar date = Calendar.getInstance();
			date.add(date.DATE, i);
			combo_date.addItem(sdf.format(date.getTime()));
		}
		BookPanel.add(combo_date);

		price = new JLabel(Tools.priceConvert(item_price));
		price.setSize(150,20);
		price.setLocation(250,15);
		price.setFont(Fonts.f4);
		price.setForeground(Colors.red);
		price.setHorizontalAlignment(JLabel.CENTER);
		BookPanel.add(price);

		btn_book = new JLabel("예약하기");
		btn_book.setSize(150,40);
		btn_book.setLocation(250,40);
		btn_book.setHorizontalAlignment(JLabel.CENTER);
		btn_book.setFont(Fonts.f2);
		btn_book.setForeground(Color.white);
		btn_book.setBackground(Colors.red);
		btn_book.setOpaque(true);
		btn_book.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (JOptionPane.showOptionDialog(null, combo_date.getSelectedItem() + "\r\n해당 날짜에 상품을 예약하겠습니까?", "EveryBook",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, Tools.btnYesOrNo, "아니오") == 0) {
					Book book = new Book(0L, LoginMember.getLoginMember().getMemberKey(), item_key,LocalDate.now().toString(),combo_date.getSelectedItem().toString());
					BookApi.booking(book);
					JOptionPane.showMessageDialog(null, "상품을 예약했습니다.", "EveryBook", JOptionPane.INFORMATION_MESSAGE);
					body.showMyPage();
				}
			}
		});
		BookPanel.add(btn_book);
	}
}

class ReviewPanel extends JPanel {

	int review_star = 3;
	String review_name = "박재정";
	String review_date = "2022-12-24";
	String review_body = "체크인 할 때 직원분들이 친절했어요. 방 청소도 잘되어있어서 깔끔해서 좋았어요. 다음에도 이 주변 오면 여기서 숙박하고 싶네요. 좋은 추억 많이 남기고 갑니다.";

	JLabel star;
	JLabel name;
	JLabel body;
	JLabel btn_delete;

	ReviewPanel(ReviewListDto review, Body gui_body, long item_key) {

		review_name = String.valueOf(review.getMemberName());
		review_star = review.getReviewStar();
		review_date = review.getReviewDate();
		review_body = review.getReviewBody();

		setPreferredSize(new Dimension(490,92));
		setBorder(new LineBorder(Colors.gray_b));
		setLayout(null);
		setBackground(Color.white);

		ImageIcon img_star = Tools.resizeImage(new ImageIcon("src/img/star_" + review_star + ".png"), 100,16);
		star = new JLabel(img_star);
		star.setSize(100,16);
		star.setLocation(10,5);
		add(star);

		name = new JLabel(review_name + " " + review_date);
		name.setSize(200, 20);
		name.setLocation(10,25);
		name.setFont(Fonts.f6);
		name.setForeground(Colors.gray);
		add(name);

		btn_delete = new JLabel("삭제");
		btn_delete.setSize(50, 20);
		btn_delete.setLocation(430,10);
		btn_delete.setFont(Fonts.f6);
		btn_delete.setHorizontalAlignment(JLabel.CENTER);
		btn_delete.setForeground(Color.white);
		btn_delete.setBackground(Colors.blue);
		btn_delete.setOpaque(true);
		btn_delete.setVisible(false);
		if(review.getMemberKey().equals(LoginMember.getLoginMember().getMemberKey())) btn_delete.setVisible(true);
		btn_delete.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (JOptionPane.showOptionDialog(null, "댓글을 삭제하겠습니까?", "EveryBook",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, Tools.btnYesOrNo, "아니오") == 0) {
					ReviewApi.deleteReview(review.getReviewKey());
					JOptionPane.showMessageDialog(null, "댓글을 삭제했습니다.", "EveryBook", JOptionPane.INFORMATION_MESSAGE);
					gui_body.showItem(item_key);
				}
			}
		});
		add(btn_delete);


		body = new JLabel("<html>" + review_body);
		body.setSize(450, 38);
		body.setLocation(10,45);
		body.setFont(Fonts.f6);
		body.setForeground(Colors.gray);
		body.setVerticalAlignment(JLabel.TOP);
		add(body);
	}
}