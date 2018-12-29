package wangleijin;

import common.SpiltType;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LeftJpanel extends JPanel implements ActionListener {

    protected JTextField goodIDTextField;
    protected JComboBox typeComboBox;
    protected DefaultComboBoxModel typeComboBoxModel;
    protected JComboBox nameComboBox;
    protected DefaultComboBoxModel nameComboBoxModel;
    protected JTextField priceTextField;
    protected JTextField numberTextField;
    protected JButton queryButton;
    protected JButton modifyButton;

    protected northJpanel northJpanel;


    protected String[] str = {"家电","服装","食品"};
    protected String[][] str1 = {{"电视","冰箱","洗衣机","热水器"},{"西服","牛仔裤","衬衣","裤子"},{"面包","饼干","方便面","火腿肠"}};


    //左端输入面板
    public LeftJpanel(northJpanel northJpanel){
        super();
        this.northJpanel = northJpanel;

        this.setBorder(new TitledBorder("商品信息"));
        this.setLayout(new GridLayout(6,1));

        JPanel tmpJpanel = new JPanel();
        tmpJpanel.add(new JLabel("编号:"));
        tmpJpanel.add(goodIDTextField = new JTextField(10));
        this.add(tmpJpanel);

        tmpJpanel = new JPanel();
        typeComboBoxModel = new DefaultComboBoxModel(str);
        typeComboBox = new JComboBox(typeComboBoxModel);
        typeComboBox.setEditable(true);
        tmpJpanel.add(typeComboBox);
        this.add(tmpJpanel);
        typeComboBox.addActionListener(this);

        tmpJpanel = new JPanel();
        nameComboBoxModel = new DefaultComboBoxModel(str1[0]);
        nameComboBox = new JComboBox(nameComboBoxModel);
        nameComboBox.setEditable(true);
        tmpJpanel.add(nameComboBox);
        this.add(tmpJpanel);

        tmpJpanel = new JPanel();
        tmpJpanel.add(new JLabel("价格:"));
        tmpJpanel.add(priceTextField = new JTextField(10));
        this.add(tmpJpanel);

        tmpJpanel = new JPanel();
        tmpJpanel.add(new JLabel("数量:"));
        tmpJpanel.add(numberTextField = new JTextField(10));
        this.add(tmpJpanel);

        tmpJpanel = new JPanel();
        tmpJpanel.add(queryButton = new JButton("类别商品查询"));
        tmpJpanel.add(modifyButton = new JButton("修改"));
        queryButton.addActionListener(this);
        modifyButton.addActionListener(this);
        this.add(tmpJpanel);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        //这里注释掉的是用固定数组替代的方法

        /*if(actionEvent.getSource().equals(typeComboBox)){
            nameComboBoxModel.removeAllElements();
            int index = typeComboBox.getSelectedIndex();
            for(int i = 0;i < str1[index].length;i++){
                nameComboBoxModel.addElement(str1[index][i]);
            }
        }*/

        //这里是利用SpiltType类使用的方法
        if(actionEvent.getSource().equals(typeComboBox)){
            String type = (String) typeComboBoxModel.getSelectedItem();
            SpiltType spiltType = new SpiltType(type);
            nameComboBoxModel.removeAllElements();
            for(int i = 0;i < spiltType.str.length;i++){
                nameComboBoxModel.addElement(spiltType.str[i]);
            }
        }

        //查询按钮
        else if(actionEvent.getSource().equals(queryButton)){
            String sql = "SELECT goodsID AS 商品编号,type AS 类型,name AS 名称,price AS 价格,number AS 数量\n" +
                    "FROM goods\n" +
                    "WHERE type = " + "N" + "\'" + typeComboBoxModel.getSelectedItem() + "\'";
           /* northJpanel.remove(northJpanel.table);*/
            northJpanel.remove(0);
            northJpanel.add(northJpanel.scrollPane = new JScrollPane(northJpanel.table = DataBaseOperation.query(sql)));
            northJpanel.table.setEnabled(false);
            /*northJpanel.add(northJpanel.scrollPane.add(DataBaseOperation.query(sql)));*/
            //修改后重绘
            northJpanel.revalidate();
        }


        //修改按钮
        else if(actionEvent.getSource().equals(modifyButton)){
            String goodID = goodIDTextField.getText();
            String price = priceTextField.getText();
            String number = numberTextField.getText();

            DefaultTableModel tableModel = (DefaultTableModel) northJpanel.table.getModel();
            for(int i = 0;i < tableModel.getRowCount();i++){
                if(tableModel.getValueAt(i,0).equals(goodID)){
                    tableModel.setValueAt(price,i,3);
                    tableModel.setValueAt(number,i,4);

                    String sql = "UPDATE goods\n" +
                            "SET price = " + price + ",number = " + number + "\n" +
                            "WHERE goodsID = " + goodID;

                    //所有修改均写入数据库
                    DataBaseOperation.update(sql);
                    JOptionPane.showMessageDialog(null,"更新成功");
                    return;
                }
            }

            JOptionPane.showMessageDialog(null,"您输入的商品编号有误,请重新输入!");
        }
    }
}
