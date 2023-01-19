package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import store.Record;

import java.io.IOException;

class ListCellFactory implements Callback<ListView<Record>, ListCell<Record>> {
    @Override
    public ListCell<Record> call(ListView<Record> param) {
        return new ListCell<>(){
            @Override
            public void updateItem(Record record, boolean empty) {
                super.updateItem(record, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (record != null) {
                    setText(null);
                    FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("listCell-view.fxml"));
                    try {
                        Parent root = fxmlLoader.load();
                        ListCellController controller = fxmlLoader.<ListCellController>getController();

                        controller.setTitle(record.getTitle());
                        controller.setDescription(record.getDescription());
                        controller.setCost(record.getFormatedCost());
                        controller.setCategory(record.getCategory());

                        setGraphic(root);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    setText("null");
                    setGraphic(null);
                }
            }
        };
    }
}
