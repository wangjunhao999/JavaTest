package wangleijin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class DataBaseOperation {

    //这里指定数据库的连接信息
    private static String jdbcDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static String url = "jdbc:sqlserver://120.78.209.49;database=MyDB";
    private static String username = "SA";
    private static String password = "Wang13587";
    //我的服务器上数据库的连接信息

    //数据库查询
    public static JTable query(String sql){

        DefaultTableModel tmptableModel = new DefaultTableModel();
        JTable tmpTable = new JTable(tmptableModel);

        try {
            Class.forName(jdbcDriver);
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rset.getMetaData();
            int n = rsmd.getColumnCount();
            for(int i = 0;i < n;i++){
                tmptableModel.addColumn(rsmd.getColumnName(i+1));//添加列名
            }
            while (rset.next()){
                String[] str = new String[n];
                for(int i = 0;i < n;i++){
                    str[i] = rset.getString(i+1);
                }
                tmptableModel.addRow(str);
            }
            rset.close();
            stmt.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tmpTable;
    }


    //数据库更新
    public static void update(String sql){
        try {
            Class.forName(jdbcDriver);
            Connection con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
