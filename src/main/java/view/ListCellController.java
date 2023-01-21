package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import store.Categories;
import store.Record;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ListCellController implements Initializable {
    @FXML
    public Button deleteButton;
    @FXML
    public Text titleText;
    @FXML
    public Text descriptionText;
    @FXML
    public Text costText;
    @FXML
    public Text dateText;
    @FXML
    public Text categoryText;
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

    public void setDate(LocalDate date) {
        this.dateText.setText(date.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
