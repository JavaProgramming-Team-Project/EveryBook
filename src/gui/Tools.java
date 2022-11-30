package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.io.*;
import javax.imageio.*;
import java.net.*;
import java.text.DecimalFormat;

class Tools {
	static String btnYesOrNo[] = {"예", "아니오"};

	static ImageIcon resizeImage(ImageIcon img, int width, int height) {
		return new ImageIcon(img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}

	static ImageIcon urlImage(String url) throws Exception {
		return new ImageIcon(ImageIO.read(new URL(url)));
	}

	static String priceConvert(int price) {
		DecimalFormat df = new DecimalFormat("###,###");
		String str_price = df.format(price) + "원";
		return str_price;
	}

}

class Fonts {

	static Font f1 = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 32);
	static Font f2 = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 20);
	static Font f3 = new Font("G마켓 산스 TTF Medium", Font.PLAIN, 16);
	static Font f4 = new Font("G마켓 산스 TTF Bold", Font.PLAIN, 20);
	static Font f5 = new Font("나눔스퀘어 Bold", Font.PLAIN, 20);
	static Font f6 = new Font("나눔스퀘어 Regular", Font.PLAIN, 16);
	static Font f7 = new Font("여기어때 잘난체",Font.PLAIN,28);

}

class Colors {

	static Color gray = new Color(0x787878); // 글씨 회색
	static Color gray_b = new Color(0xE1E1E1); // 테두리 회색
	static Color blue = new Color(0x58CCFF); // 배경색 파란색
	static Color sky = new Color(0xDCF4FF); // 배경색 하늘색
	static Color red = new Color(0xFC5230); // 글씨 빨간색


}