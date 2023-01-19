package store;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class RecordList {
    ArrayList<Record> records;
    public RecordList() {
        this.records = new ArrayList<>();
    }
    public RecordList(ArrayList<Record> records) {
        this.records = records;
    }

    public RecordList(String fileName) {
        readRecordsFrom(fileName);
    }
    public ArrayList<Record> getRecords() {
        return this.records;
    }
    public void addRecord(Record record) {
        this.records.add(record);
    }
    public void deleteRecord(Record record) {
        records.removeIf(item -> item.getId().equals(record.getId()));
    }

    public void printRecords() {
        for (var record : this.records) {
            System.out.println(record);
        }
    }

    public void setRecords(ArrayList<Record> records) {
        this.records = records;
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
            File recordsFile = new File(fileName);
            recordsFile.createNewFile();
            FileInputStream fis = new FileInputStream(recordsFile);
            ObjectInputStream ois = new ObjectInputStream(fis);

            this.records = (ArrayList<Record>) ois.readObject();
            ois.close();
            fis.close();
        }
        catch (EOFException eof) {
            System.out.println("End of file");
        }
        catch (FileNotFoundException fnf) {
            System.out.println("File not found");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }
}
