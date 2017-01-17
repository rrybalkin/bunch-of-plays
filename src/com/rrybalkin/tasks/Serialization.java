package com.rrybalkin.tasks;

import java.io.*;

/**
 * Created by Roman Rybalkin
 * 15.10.16
 */
public class Serialization {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Data data = new Data(10);
        ChildData childData = new ChildData(44);
        data.childData = childData;
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.bin"));
        oos.writeObject(data);
        oos.writeObject(childData);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.bin"));
        Data d1 = (Data) ois.readObject();
        ChildData childData1 = (ChildData) ois.readObject();
        System.out.println(d1);
        System.out.println(d1 == data);
        System.out.println(d1.childData);
        System.out.println(childData == d1.childData);
        System.out.println(childData1 == d1.childData);
    }

}

class Data implements Serializable {
    int i = 5;
    ChildData childData;

    public Data(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "data = " + i;
    }
}

class ChildData implements Serializable {
    int y = 1;

    public ChildData(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "childData = " + y;
    }
}