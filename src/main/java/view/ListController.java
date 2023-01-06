package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import store.Record;
import store.RecordList;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    @FXML
    ListView<Record> recordListView;
    RecordList recordList = new RecordList("records.rec");
    Record currentRecord;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        recordListView.setCellFactory(new ListCellFactory());
        recordListView.getItems().addAll(recordList.getRecords());
    }
}