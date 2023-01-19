package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import store.Categories;
import store.Record;
import store.RecordList;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListCellController implements Initializable {

    Record record;

    public void setRecord(Record record) {
        this.record = record;
    }

    @FXML
    public Button deleteButton;
    @FXML
    public Text titleText;
    @FXML
    public Text descriptionText;
    @FXML
    public Text costText;
    @FXML
    public Text categoryText;
    @FXML
    public GridPane gridPane;
    ObservableList<Record> mainRecordList;

    public void setMainRecordList(ObservableList<Record> mainRecordList) {
        this.mainRecordList = mainRecordList;
    }

    public void setTitle(String titleText) {
        this.titleText.setText(titleText);
    }

    public void setDescription(String descriptionText) {
        this.descriptionText.setText(descriptionText);
    }

    public void setCost(String costText) {
        this.costText.setText(costText);
    }

    public void setCategory(Categories categoryText) {
        this.categoryText.setText(categoryText.getCategoryName());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
