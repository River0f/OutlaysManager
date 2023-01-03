package store;

import java.io.*;
import java.util.ArrayList;

public class RecordList {
    ArrayList<Record> records;
    public RecordList() {
        this.records = new ArrayList<>();
    }
    public RecordList(ArrayList<Record> records) {
        this.records = records;
    }
    public ArrayList<Record> getRecords() {
        return this.records;
    }
    public void addRecord(Record record) {
        this.records.add(record);
    }

    public void printRecords() {
        for (var record : this.records) {
            System.out.println(record);
        }
    }

    public void writeRecordsTo(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(this.records);
            oos.close();
            fos.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    public void readRecordsFrom(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.records = (ArrayList<Record>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }
}
