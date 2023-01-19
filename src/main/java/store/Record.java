package store;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.UUID;

public class Record implements Serializable {
    private String id;
    private final String title;
    private final String description;
    private final Float cost;
    private final Categories category;
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public Float getCost() {
        return cost;
    }
    public String getDescription() {
        return description;
    }
    public Categories getCategory() {
        return category;
    }
    public String getFormatedCost() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        return df.format(this.cost) + " " + "USD";
    }

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
