package gui;

import api.ItemApi;
import dto.ItemListDto;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BodySearch extends JPanel {
    Body body;

    List<ItemListDto> itemList;

    JTextField text_search = new JTextField();
    JLabel btn_search = new JLabel("검색");

    JScrollPane scroll;
    ItemPanel itemPanel[];
    String searchWord;

    BodySearch(Body body, String searchWord) {
        this.body = body;
        this.searchWord = searchWord;
        text_search.setText(searchWord);

        setDesign();

        addSearch();

        addItemList(searchWord);
    }

    void setDesign() {
        setPreferredSize(new Dimension(1080, 650));
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        setBackground(Color.white);
    }

    void addSearch() {

        text_search.setPreferredSize(new Dimension(400,40));
        text_search.setFont(Fonts.f5);
        text_search.setBorder(new LineBorder(Colors.gray_b));
        text_search.setForeground(Colors.gray);
        text_search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                body.showSearch(text_search.getText());
            }
        });

        btn_search.setPreferredSize(new Dimension(80,40));
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

        add(text_search);
        add(btn_search);
    }

    void addItemList(String searchWord) {
        if (!(searchWord == null)) {
            itemList = ItemApi.findItemByName(searchWord);
            itemPanel = new ItemPanel[itemList.size()];

            JPanel items = new JPanel();
            items.setPreferredSize(new Dimension(820, 750));
            items.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
            items.setBackground(Color.white);

            JPanel list = new JPanel();
            list.setPreferredSize(new Dimension(1080, 750));
            list.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
            list.setBackground(Color.white);
            list.add(items);

            JScrollPane scroll = new JScrollPane(list);
            scroll.setPreferredSize(new Dimension(1120, 545)); // 1080 + 40
            scroll.getVerticalScrollBar().setUnitIncrement(10); // 스크롤 속도
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
                    int height_extend = 150;
                    items.setSize(items.getWidth(),items.getHeight() + height_extend);
                    list.setPreferredSize(new Dimension(list.getPreferredSize().width,list.getPreferredSize().height + height_extend));
                }
            }

            add(scroll);
        }
    }



    class ItemPanel extends JPanel {

        int item_star = 3;
        String item_picture;
        String item_name;
        String item_address;

        String item_price;

        JLabel picture;
        JLabel name;
        JLabel address;
        JLabel icon;
        JLabel star;
        JLabel price;

        ItemPanel(ItemListDto itemListDto) {

            item_picture = itemListDto.getItemImage();
            item_name = itemListDto.getItemName();
            item_address = itemListDto.getItemAddress();
            item_price = Tools.priceConvert(itemListDto.getItemPrice());
            item_star = Integer.parseInt(String.format("%.0f", itemListDto.getAvgRating()));

            setPreferredSize(new Dimension(400,140));
            setBorder(new LineBorder(Colors.gray_b));
            setLayout(null);
            setBackground(Color.white);

            try {
                picture = new JLabel(Tools.resizeImage(Tools.urlImage(item_picture), 160, 120));
            } catch (Exception e) {
                System.out.println(item_name + " : 이미지 로드 실패");
                try {
                    picture = new JLabel(Tools.resizeImage(Tools.urlImage(Tools.defaultImage), 160, 120));
                } catch (Exception ee) { }
            }
            picture.setSize(160, 120);
            picture.setLocation(10,10);

            name = new JLabel(item_name);
            name.setSize(210, 25);
            name.setLocation(180,10);
            name.setFont(Fonts.f5);
            name.setForeground(Colors.gray);
            name.setVerticalAlignment(JLabel.TOP);

            icon = new JLabel(Tools.resizeImage(new ImageIcon("src/img/address.png"), 16,16));
            icon.setBounds(180,37,16,16);

            address = new JLabel(item_address);
            address.setSize(190, 20);
            address.setLocation(200,35);
            address.setFont(Fonts.f3);
            address.setForeground(Colors.gray);

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
            add(address);
            add(icon);
            add(star);
            add(price);

        }

    }
}
