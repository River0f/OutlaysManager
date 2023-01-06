package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import store.Categories;

import java.net.URL;
import java.util.ResourceBundle;

public class ListCellController implements Initializable {
    public Text titleText;
    public Text descriptionText;
    public Text costText;
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
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
