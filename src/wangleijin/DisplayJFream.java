package wangleijin;

import javax.swing.*;
import java.awt.*;

public class DisplayJFream extends JFrame {

    private LeftJpanel leftJpanel;
    private southJpanel southJpanel;
    private northJpanel northJpanel;
    private String sql = "SELECT goodsID AS 商品编号,type AS 类型,name AS 名称,price AS 价格,number AS 数量\n" +
            "FROM goods";

    //JFream程序主窗口
    public DisplayJFream(){
        super("201700800377 王蕾锦");
        this.setSize(800,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.southJpanel = new southJpanel();
        this.northJpanel = new northJpanel(sql);
        this.leftJpanel = new LeftJpanel(northJpanel);

        JPanel rightJpanel = new JPanel(new BorderLayout());
        rightJpanel.add(northJpanel,BorderLayout.CENTER);
        rightJpanel.add(southJpanel,BorderLayout.SOUTH);

        this.add(new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftJpanel,rightJpanel));

        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new DisplayJFream();
    }
}
