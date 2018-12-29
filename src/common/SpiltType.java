package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SpiltType implements ISpiltStr {

    public String str[] = new String[512];

    public SpiltType(){

    }

    //注意！文本文档必须采用UTF-8编码
    public SpiltType(String type){
        getTypes(type);
    }


    //根据传入的type类型,从文件types.txt中读取相应的类别，返回该类别下所有商品
    @Override
    public String[] getTypes(String type) {
        if(type.equals("")){
            return null;
        }
        try {
            String oneLine;
            FileReader fin = new FileReader("./src/common/types.txt");
            BufferedReader bin = new BufferedReader(fin);
            while((oneLine = bin.readLine()) != null){
                if(oneLine.startsWith(type)){
                    str = oneLine.substring(3).split("，");
                    return str;
                }
            }
            bin.close();
            fin.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    //测试代码
    public static void main(String[] args) {
        SpiltType spiltType = new SpiltType("食品");
        for(int i = 0;i < spiltType.str.length;i++){
            System.out.println(spiltType.str[i]);
        }
    }
}
