package gui;

import entity.Book;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

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
        for (int i = 0; i < ad.length; i++) {
            ad[i] = new AdPanel();
            add(ad[i]);
        }
    }
}

class AdPanel extends JPanel {
    AdPanel() {
        setPreferredSize(new Dimension(150, 150));
        setBorder(new LineBorder(Colors.gray_b));
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        setBackground(Color.white);
    }
}