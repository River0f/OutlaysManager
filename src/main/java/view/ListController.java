package view;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import store.Categories;
import store.Record;
import store.RecordList;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    public BorderPane root;
    @FXML
    public Button addRecordButton;
    @FXML
    public ChoiceBox categoryFilter;
    @FXML
    public PieChart recordsChart;
    @FXML
    ListView<Record> recordListView;
    RecordList records;
    ObservableList<Record> recordList;
    ObservableList<PieChart.Data> recordsChartData;
    FilteredList<Record> filteredRecordList;

    private void updateRecordChart() {
        this.recordsChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(Categories.HOME.getCategoryName(), 13),
                        new PieChart.Data(Categories.FOOD.getCategoryName(), 25),
                        new PieChart.Data(Categories.TRAVEL.getCategoryName(), 10),
                        new PieChart.Data(Categories.OTHER.getCategoryName(), 22));
        recordsChart.setData(recordsChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        records = new RecordList("records.rec");
        if(records.getRecords() != null) {
            recordList.addAll(records.getRecords());
        }
        recordList = FXCollections.observableArrayList();
        filteredRecordList = new FilteredList<>(recordList, s -> true);

        updateRecordChart();

        recordList.addListener(new ListChangeListener<Record>() {
            @Override
            public void onChanged(Change<? extends Record> change) {
                ArrayList<Record> current = new ArrayList<Record>(recordList.stream().toList());
                records.setRecords(current);
                records.writeRecordsTo("records.rec");
            }
        });

        filteredRecordList.addListener(new ListChangeListener<Record>() {
            @Override
            public void onChanged(Change<? extends Record> change) {
                recordListView.setItems(filteredRecordList);
            }
        });

        categoryFilter.getItems().add("all");
        for(var category: Categories.values()) {
            categoryFilter.getItems().add(category.getCategoryName());
        }
        categoryFilter.setValue(categoryFilter.getItems().get(0));

        categoryFilter.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(observableValue.getValue() == "all") {
                    filteredRecordList.setPredicate(c -> true);
                    return;
                }
                filteredRecordList.setPredicate(category -> Objects.equals(category.getCategory().getCategoryName(), categoryFilter.getValue()));
            }
        });

        recordListView.getItems().setAll(filteredRecordList);
        recordListView.setCellFactory(new ListCellFactory());
    }
    @FXML
    public void addRecord() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("record-form-view.fxml"));
        Parent parent = loader.load();
        RecordFormController controller = loader.getController();
        controller.setMainRecordList(recordList);

        Scene scene = new Scene(parent,300, 200);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.showAndWait();

    }

    public void deleteRecord(ActionEvent actionEvent) {
        recordList.remove( recordListView.getSelectionModel().getSelectedIndex());
    }
}