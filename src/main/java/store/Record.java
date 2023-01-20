package store;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.UUID;

public class Record implements Serializable {
    private String id;
    private final String title;
    private final String description;
    private final Float cost;
    private final Categories category;
    private LocalDate date;
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
    public LocalDate getDate() {
        return this.date;
    }
    public Record(String title, Float cost, Categories category) {
        super();
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.cost = cost;
        this.category = category;
        this.description = "";
        this.date = LocalDate.now().minusMonths(2);
    }
    public Record(String title, String description, Float cost, Categories category) {
        super();
        this.id = this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.cost = cost;
        this.category = category;
        this.date = LocalDate.now();;
    }

    @Override
    public String toString() {
        return  "id: " + this.id + "\n" +
                "title: " + this.title + "\n" +
                "description: " + this.description + "\n" +
                "cost: " + this.cost + "\n" +
                "date: " + this.date + "\n" +
                "category: " + this.category +  "\n";

    }
}
