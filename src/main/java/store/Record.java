package store;

import java.io.Serializable;
import java.util.UUID;

public class Record implements Serializable {
    String id;
    String title;
    String description;
    Float cost;
    Categories category;

    public Record(String title, Float cost, Categories category) {
        super();
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.cost = cost;
        this.category = category;
        this.description = "";
    }
    public Record(String title, String description, Float cost, Categories category) {
        super();
        this.id = this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.category = category;
    }
    @Override
    public String toString() {
        return  "id: " + this.id + "\n" +
                "title: " + this.title + "\n" +
                "description: " + this.description + "\n" +
                "cost: " + this.cost + "\n" +
                "category: " + this.category +  "\n";

    }
}
