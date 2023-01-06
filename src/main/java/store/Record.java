package store;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.UUID;

public class Record implements Serializable {
    String id;

    public String getTitle() {
        return title;
    }

    String title;
    String description;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getCost() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(this.cost) + " " + "USD";
    }

    public Categories getCategory() {
        return category;
    }

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
