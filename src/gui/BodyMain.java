package gui;

import api.ItemApi;
import dto.ItemListDto;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BodyMain extends JPanel {

    Body body;
    JLabel btn_category[] = new JLabel[9];
    AdPanel ad[] = new AdPanel[5];
    BodyMain(Body body) {
        this.body = body;
        setDesign();
        addIcon();
        addBanner();
        addAd();
    }
    void setDesign() {
        setPreferredSize(new Dimension(1296, 650));
        //setBorder(new LineBorder(Colors.gray_b));
        setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        setBackground(Color.white);
    }

    void addIcon() {
        for (int i = 0; i < btn_category.length; i++) {
            int index = i+1;
            ImageIcon img_src = Tools.resizeImage(new ImageIcon("src/img/icon_" + index + ".png"), 83,123);
            btn_category[i] = new JLabel(img_src);
            btn_category[i].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    body.showItemList(index);
                }
            });
            add(btn_category[i]);
        }
    }

    void addBanner() {
        ImageIcon img_src = Tools.resizeImage(new ImageIcon("src/img/banner.png"), 1296,300);
        JLabel banner = new JLabel(img_src);
        add(banner);
    }
    void addAd() {
        List<ItemListDto> list = ItemApi.itemList();

        JPanel adPanel = new JPanel();
        adPanel.setLayout(new FlowLayout(FlowLayout.CENTER,10, 0));
        adPanel.setBackground(Color.white);

        int index[] = new int[ad.length];
        for(int i=0; i<index.length; i++) {
            index[i] = (int)(Math.random() * list.size());
            for(int j=0; j<i; j++) if(index[i]==index[j]) i--;
        }

        for (int i = 0; i < ad.length; i++) {
            ItemListDto item = list.get(index[i]);
            ad[i] = new AdPanel(item);
            ad[i].addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    body.showItem(item.getItemKey());
                }
            });
            adPanel.add(ad[i]);
            add(adPanel);
        }

    }
}

class AdPanel extends JPanel {
    String item_picture;
    String item_name;
    JLabel picture;
    JLabel name;
    AdPanel(ItemListDto item) {
        setPreferredSize(new Dimension(200, 200));
        //setBorder(new LineBorder(Colors.gray_b));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        setBackground(Color.white);

        item_picture = item.getItemImage();
        item_name = item.getItemName();

        try {
            picture = new JLabel(Tools.resizeImage(Tools.urlImage(item_picture), 160, 120));
        } catch (Exception e) {
            System.out.println(item_name + " : 이미지 로드 실패");
            try {
                picture = new JLabel(Tools.resizeImage(Tools.urlImage("https://www.hotelrating.or.kr/imageViewSlide/202111251802069d1c9424AbeefA4b65A98f5A038d1008bd470.do"), 160, 120));
            } catch (Exception ee) { }
        }

        name = new JLabel(item_name);
        name.setPreferredSize(new Dimension(200,20));
        name.setFont(Fonts.f6);
        name.setForeground(Colors.gray);
        name.setHorizontalAlignment(JLabel.CENTER);

        add(picture);
        add(name);
    }
}