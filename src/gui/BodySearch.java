package gui;

import api.ItemApi;
import dto.ItemListDto;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BodySearch extends JPanel {
    Body body;

    List<ItemListDto> itemList;

    JTextField text_search; // 검색창
    JLabel btn_search; // 검색 버튼

    JScrollPane scroll;
    ItemPanel itemPanel[];

    BodySearch(Body body, String searchWord){
        this.body = body;

        setDesign();

        addSearch();

        addItemList(searchWord);
    }

    void setDesign(){
        setPreferredSize(new Dimension(1080,650));
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        setBackground(Color.white);
    }

    void addSearch(){
        JPanel SearchPanel = new JPanel();
        SearchPanel.setPreferredSize(new Dimension(520,100));
        //SearchPanel.setSize(490,92);
        //SearchPanel.setLocation(580,440);
        //SearchPanel.setBorder(new LineBorder(Colors.gray_b));
        SearchPanel.setLayout(null);
        SearchPanel.setBackground(Color.white);

        text_search = new JTextField();
        text_search.setSize(360,50);
        text_search.setLocation(10,10);
        text_search.setFont(Fonts.f6);
        text_search.setBorder(new LineBorder(Colors.gray_b));
        text_search.setForeground(Colors.gray);

        btn_search = new JLabel("검색");
        btn_search.setSize(100,50);
        btn_search.setLocation(380,10);
        btn_search.setHorizontalAlignment(JLabel.CENTER);
        btn_search.setFont(Fonts.f5);
        btn_search.setForeground(Color.white);
        btn_search.setBackground(Colors.blue);
        btn_search.setOpaque(true);


        btn_search.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                body.showSearch(text_search.getText());
            }
        });

        SearchPanel.add(text_search);
        SearchPanel.add(btn_search);
        add(SearchPanel);
    }

    void addItemList(String searchWord) {
        if(!(searchWord == null)){
            System.out.println(searchWord);
            itemList = ItemApi.findItemByName(searchWord);
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
            scroll.setBorder(new LineBorder(Color.gray));

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
