package gui;

import api.ItemApi;
import dto.ItemListDto;
import entity.Book;
import entity.Item;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
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
            btn_category[i].setPreferredSize(new Dimension(83,123));
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
        banner.setPreferredSize(new Dimension(1296,300));
        add(banner);
    }
    void addAd() {
        List<ItemListDto> list = ItemApi.itemList();

        int index[] = new int[ad.length];
        for(int i=0; i<index.length; i++) {
            index[i] = (int)(Math.random() * list.size());
            for(int j=0; j<i; j++) if(index[i]==index[j]) i--;
        }

        for (int i = 0; i < ad.length; i++) {
            ad[i] = new AdPanel(list.get(index[i]));
            add(ad[i]);
        }

    }
}

class AdPanel extends JPanel {
    AdPanel(ItemListDto item) {
        setPreferredSize(new Dimension(150, 150));
        setBorder(new LineBorder(Colors.gray_b));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBackground(Color.white);

        JLabel test = new JLabel(item.getItemName());

        add(test);
    }
}