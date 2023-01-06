package view;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import store.Categories;
import store.Record;
import store.RecordList;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    public BorderPane root;
    @FXML
    ListView<Record> recordListView;
    RecordList records;;
    ObservableList<Record> recordList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        records = new RecordList("records.rec");
        recordList = FXCollections.observableArrayList();
        recordList.addAll(records.getRecords());

        recordList.addListener(new ListChangeListener<Record>() {
            @Override
            public void onChanged(Change<? extends Record> change) {
                ArrayList<Record> current = new ArrayList<Record>(recordListView.getItems());
                records.setRecords(current);
                records.writeRecordsTo("records.rec");
            }
        });

        recordListView.setItems(recordList);
        recordListView.setCellFactory(new ListCellFactory());
    }
    @FXML
    public void addRecord() {
        recordList.add(new Record("Test", "Test", 10.54f, Categories.OTHER));
    }
}