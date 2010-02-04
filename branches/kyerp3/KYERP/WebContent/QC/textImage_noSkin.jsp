<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.awt.image.BufferedImage"%>
<%@ page import="java.awt.*"%>
<%@ page import="javax.imageio.ImageIO"%>
<%
//图片的宽度 
int WIDTH = 860;
//图片的高度 
int HEIGHT = 150;
//要显示的文字
String timestring = "北京市清华园胶印厂";
//图片对象
BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
    BufferedImage.TYPE_INT_RGB);

//获取图形上下文 
Graphics g = image.getGraphics();

//设置背景色 
g.setColor(Color.white);
//填充背景色 
g.fillRect(0, 0, WIDTH, HEIGHT);

//设置字体 
g.setFont(new Font("黑体", Font.PLAIN, 28));
//设置字符颜色
g.setColor(Color.black);
//画字符串
g.drawString(timestring, 20, 30);

//输出
ImageIO.write(image, "jpg", response.getOutputStream());

//清空缓存
out.clear();
out = pageContext.pushBody();
%>
