package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import store.Categories;
import store.Record;

import java.net.URL;
import java.util.ResourceBundle;

public class RecordFormController implements Initializable {
    @FXML
    private TextField cost;
    @FXML
    private ChoiceBox<String> categorySelect;
    @FXML
    private TextField title;
    @FXML
    private TextArea description;

    private ObservableList<Record> mainRecordList;

    public void setMainRecordList(ObservableList<Record> mainRecordList) {
        this.mainRecordList = mainRecordList;
    }

    public void submitForm(ActionEvent actionEvent) {
        String titleText = title.getText().trim();
        String descriptionText = description.getText().trim();
        String costText = cost.getText().trim();
        String category = categorySelect.getValue();
        try {
            Float costValue = Float.valueOf(costText);

            if (titleText.equals("")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Title must exist");
                alert.show();
                return;
            }
            System.out.println(category);

            Record record = new Record(titleText, descriptionText, costValue, Categories.valueOf(category.toUpperCase()));
            mainRecordList.add(record);
            closeStage(actionEvent);

        } catch (NumberFormatException nfe) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Cost is not valid");
            alert.show();
        }
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(var category: Categories.values()) {
            categorySelect.getItems().add(category.getCategoryName());
        }
        categorySelect.setValue(categorySelect.getItems().get(0));
    }
}
