package gui;

import api.ItemApi;
import dto.ItemListDto;
import login.LoginMember;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.border.LineBorder;

public class BodyItemList extends JPanel {
	Body body;

	int category;
	int page;
	int page_max = 7;

	String str_category[] = {"전체", "숙박", "레저", "축제", "공연", "전시", "티켓", "식당", "뷰티", "렌트"};
	JLabel btn_category[] = new JLabel[str_category.length];

	ItemPanel itemPanel[];

	List<ItemListDto> itemList;
	JLabel btn_page[] = new JLabel[5];

	BodyItemList(Body body, int category, int page) {
		
		this.body = body;
		this.category = category;
		this.page = page;

		setDesign();

		addCategory();

		addItemList();

		//addPageNav();
	}

	void setDesign() {

		setPreferredSize(new Dimension(1080,650));
		setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		setBackground(Color.white);

	}

	void addCategory() {
		for (int i = 0; i < btn_category.length; i++) {
			btn_category[i] = new JLabel(str_category[i]);
			btn_category[i].setPreferredSize(new Dimension(80,30));
			btn_category[i].setHorizontalAlignment(JLabel.CENTER);
			btn_category[i].setFont(Fonts.f2);
			btn_category[i].setForeground(Colors.gray);
			btn_category[i].setBackground(Color.white);
			btn_category[i].setOpaque(true);
			int index = i;
			btn_category[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					body.showItemList(index, 1);
				}
			});
			add(btn_category[i]);
		}
		btn_category[category].setForeground(Color.white);
		btn_category[category].setBackground(Colors.blue);
	}

	void addItemList() {
		
		itemList = ItemApi.itemListByCategory(str_category[category]);
		itemPanel = new ItemPanel[itemList.size()];

		JPanel items = new JPanel();
		items.setPreferredSize(new Dimension(820,750));
		items.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		items.setBackground(Color.white);

		JPanel list = new JPanel();
		list.setPreferredSize(new Dimension(1080,750));
		list.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		list.setBackground(Color.white);
		list.add(items);

		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(1120, 545)); // 1080 + 40
		scroll.getVerticalScrollBar().setUnitIncrement(5); // 스크롤 속도
		scroll.setBorder(null);

		for (int i = 0; i < itemList.size(); i++) {
			ItemListDto tmp = itemList.get(i);
			System.out.println(tmp.getItemName());

			itemPanel[i] = new ItemPanel(tmp);
			items.add(itemPanel[i]);
			itemPanel[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					body.showItem(tmp.getItemKey());
				}
			});
		}

		add(scroll);
	}

	void addPageNav() {

		JLabel left = new JLabel("<");
		left.setPreferredSize(new Dimension(24,24));
		left.setHorizontalAlignment(JLabel.CENTER);
		left.setFont(Fonts.f3);
		left.setForeground(Colors.gray);
		left.setBackground(Color.white);
		left.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(page>1) body.showItemList(category, page-1);
			}
		});


		JLabel right = new JLabel(">");
		right.setPreferredSize(new Dimension(24,24));
		right.setHorizontalAlignment(JLabel.CENTER);
		right.setFont(Fonts.f3);
		right.setForeground(Colors.gray);
		right.setBackground(Color.white);
		right.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(page<page_max) body.showItemList(category, page+1);
			}
		});


		add(left);

		for (int i = 0; i < btn_page.length; i++) {
			int page_new = (i+1)+((page / 5) * 5);
			if (page % 5 == 0) page_new -= 5;

			btn_page[i] = new JLabel(page_new+"");
			btn_page[i].setPreferredSize(new Dimension(24,24));
			btn_page[i].setHorizontalAlignment(JLabel.CENTER);
			btn_page[i].setFont(Fonts.f3);
			btn_page[i].setForeground(Colors.gray);
			btn_page[i].setBackground(Color.white);
			btn_page[i].setOpaque(true);

			int index = page_new;
			btn_page[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					body.showItemList(category, index);
				}
			});
			if (page_new <= page_max) add(btn_page[i]);
		}

		add(right);

		int page_new = page-1;
		if (page>=5) page_new = page - ((page/5) * 5) - 1;
		if (page % 5 == 0) page_new += 5;
		btn_page[page_new].setForeground(Color.white);
		btn_page[page_new].setBackground(Colors.blue);


	}

	class ItemPanel extends JPanel {

		int item_star = 3;
		String item_picture;
		String item_name;
		String item_price;

		JLabel picture;
		JLabel name;
		JLabel star;
		JLabel price;

		ItemPanel(ItemListDto itemListDto) {

			item_picture = "https://www.hotelrating.or.kr/imageViewSlide/202111251802069d1c9424AbeefA4b65A98f5A038d1008bd470.do";
			item_name = itemListDto.getItemName();
			item_price = Tools.priceConvert(itemListDto.getItemPrice());
			item_star = (int) itemListDto.getAvgRating();

			setPreferredSize(new Dimension(400,140));
			setBorder(new LineBorder(Colors.gray_b));
			setLayout(null);
			setBackground(Color.white);

			try {
				picture = new JLabel(Tools.resizeImage(Tools.urlImage(item_picture), 160, 120));
			} catch (Exception e) { }
			picture.setSize(160, 120);
			picture.setLocation(10,10);
			picture.setBackground(Colors.blue);
			picture.setOpaque(true);

			name = new JLabel("<html>" + item_name);
			name.setSize(200, 45);
			name.setLocation(180,10);
			name.setFont(Fonts.f5);
			name.setForeground(Colors.gray);
			name.setVerticalAlignment(JLabel.TOP);

			ImageIcon img_star = Tools.resizeImage(new ImageIcon("src/img/star_" + item_star + ".png"), 125,20);
			star = new JLabel(img_star);
			star.setSize(125,20);
			star.setLocation(265,80);

			price = new JLabel(item_price);
			price.setSize(125,20);
			price.setLocation(260,105);
			price.setFont(Fonts.f4);
			price.setForeground(Colors.red);
			price.setHorizontalAlignment(JLabel.RIGHT);

			add(picture);
			add(name);
			add(star);
			add(price);

		}

	}
}
