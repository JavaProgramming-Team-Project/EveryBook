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

	String str_category[] = {"전체", "숙박", "레저", "축제", "공연", "전시", "티켓", "식당", "뷰티", "렌트"};
	JLabel btn_category[] = new JLabel[str_category.length];

	ItemPanel itemPanel[];

	List<ItemListDto> itemList;
	JLabel btn_page[] = new JLabel[5];

	BodyItemList(Body body, int category) {
		
		this.body = body;
		this.category = category;

		setDesign();

		addCategory();

		addItemList();

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
					body.showItemList(index);
				}
			});
			add(btn_category[i]);
		}
		btn_category[category].setForeground(Color.white);
		btn_category[category].setBackground(Colors.blue);
	}

	void addItemList() {
		
		if (category == 0) itemList = ItemApi.itemList();
		else itemList = ItemApi.itemListByCategory(str_category[category]);

		itemPanel = new ItemPanel[itemList.size()];

		JPanel items = new JPanel();
		items.setPreferredSize(new Dimension(820,728));
		items.setLayout(new FlowLayout(FlowLayout.LEFT,10,10));
		items.setBackground(Color.white);

		JPanel list = new JPanel();
		list.setPreferredSize(new Dimension(1080,728));
		list.setLayout(new FlowLayout(FlowLayout.CENTER,10,0));
		list.setBackground(Color.white);
		list.add(items);

		JScrollPane scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension(1120, 728)); // 1080 + 40
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

			if( i>=9 && i%2!=0 ) {
				items.setPreferredSize(new Dimension(820,items.getPreferredSize().height+180));
				list.setPreferredSize(new Dimension(1080,list.getPreferredSize().height+180));
			}
		}

		add(scroll);
	}
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