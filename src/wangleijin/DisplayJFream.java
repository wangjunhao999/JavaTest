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



//下面是数据库的创建语句

/*

    CREATE DATABASE MyDB
        ON PRIMARY
        (
        NAME = 'mydb_data',
        FILENAME = '/root/Sql/mydb_data.mdf',
        SIZE = 5MB,
        MAXSIZE = 50MB,
        FILEGROWTH = 5%
        )
        LOG ON
        (
        NAME = 'mydb_log',
        FILENAME = '/root/Sql/mydb_log.ldf',
        SIZE = 5MB,
        MAXSIZE = 50MB ,
        FILEGROWTH = 5%
        )

        USE MyDB
        CREATE TABLE goods
        (
        goodsID NVARCHAR(8) PRIMARY KEY ,
        type NVARCHAR(4),
        name NVARCHAR(10),
        price FLOAT,
        number INT
        )

*/
