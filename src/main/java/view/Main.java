package view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import store.Record;
import store.RecordList;
import store.Categories;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();

        RecordList records = new RecordList();

        records.addRecord(new Record("Cabbage", "Something about", 200f, Categories.FOOD));
        records.addRecord(new Record("Egypt travel", "Something about", 1000.23f, Categories.TRAVEL));
        records.addRecord(new Record("Sea", "Something about", 200f, Categories.TRAVEL));
        records.addRecord(new Record("Fish", "Something about", 200f, Categories.FOOD));
        records.addRecord(new Record("Tablet", "Something about", 200f, Categories.OTHER));

        records.writeRecordsTo("records.rec");
        records.readRecordsFrom("records.rec");
        records.printRecords();
        records.addRecord(new Record("Cake", "Something about", 200f, Categories.FOOD));
        records.printRecords();

    }
}