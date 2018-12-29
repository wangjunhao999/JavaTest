package wangleijin;

import javax.swing.*;

public class RandomThread extends Thread {

    private JTextField text;

    //随机数生成面板
    public RandomThread(JTextField text){
        super();
        this.text = text;
    }

    public void run(){
        while (true){
            double number = Math.random() * 100;
            text.setText(number + "");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
