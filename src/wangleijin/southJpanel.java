package wangleijin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class southJpanel extends JPanel implements ActionListener {

    protected JTextField randomTextField;
    protected JButton runButton;
    protected JButton stopButton;
    protected RandomThread thread;

    //右边的南部面板
    public southJpanel(){
        super();
        this.add(randomTextField = new JTextField(15));
        this.add(runButton = new JButton("启动线程"));
        this.add(stopButton = new JButton("停止线程"));
        runButton.addActionListener(this);
        stopButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource().equals(runButton)){
            thread = new RandomThread(randomTextField);
            thread.start();
        }
        else if(actionEvent.getSource().equals(stopButton)){
           /* thread.interrupt();*/
            thread.stop();
        }
    }
}
