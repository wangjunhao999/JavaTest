package wangleijin;

import javax.swing.*;

public class northJpanel extends JPanel {

    protected JTable table;
    protected JScrollPane scrollPane;

    //右边的上方的表格面板
    public northJpanel(String sql){
        super();
        this.table = DataBaseOperation.query(sql);
        this.add(scrollPane = new JScrollPane(this.table));
    }
}
