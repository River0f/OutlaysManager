package view;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import store.Categories;
import store.Record;
import store.RecordList;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class ListController implements Initializable {
    public BorderPane root;
    @FXML
    public Button addRecordButton;
    @FXML
    public ChoiceBox categoryFilter = new ChoiceBox<>();
    @FXML
    public ChoiceBox dateFilter = new ChoiceBox<>();
    @FXML
    public PieChart recordsChart;
    @FXML
    ListView<Record> recordListView;
    RecordList records;
    ObservableList<Record> recordList;
    ObservableList<PieChart.Data> recordsChartData;
    FilteredList<Record> filteredRecordList;

    private Float getTotalCost () {
        return (float) this.filteredRecordList.stream().mapToDouble(Record::getCost).sum();
    }

    private Float getPersentOfCosts(Categories category) {
        Float totalCostPerCategory = getTotalCostByCategory(category);
        Float totalCost = getTotalCost();
        return  totalCostPerCategory / totalCost * 100;
    }

    private Float getTotalCostByCategory(Categories category) {
        return (float) this.filteredRecordList.stream().filter(o -> o.getCategory() == category).mapToDouble(Record::getCost).sum();
    }

    private void updateRecordChart() {
        this.recordsChart.setTitle("Total: " + getTotalCost() + " USD");
        this.recordsChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data(Categories.HOME.getCategoryName() + ": " + getTotalCostByCategory(Categories.HOME) + " USD", getPersentOfCosts(Categories.HOME)),
                        new PieChart.Data(Categories.FOOD.getCategoryName() + ": " + getTotalCostByCategory(Categories.FOOD) + " USD", getPersentOfCosts(Categories.FOOD)),
                        new PieChart.Data(Categories.TRAVEL.getCategoryName()+ ": " + getTotalCostByCategory(Categories.TRAVEL) + " USD", getPersentOfCosts(Categories.TRAVEL)),
                        new PieChart.Data(Categories.OTHER.getCategoryName() + ": " + getTotalCostByCategory(Categories.OTHER) + " USD", getPersentOfCosts(Categories.OTHER)));
        recordsChart.setData(recordsChartData);
    }

    private boolean checkRecordDate (Record record) {
        LocalDate date = record.getDate();
        switch (dateFilter.getValue().toString()) {
            case "today" -> {
                return LocalDate.now().equals(date);
            }
            case "last 3 days" -> {
                return date.isAfter(LocalDate.now().minusDays(3));
            }
            case "last week" -> {
                return  date.isAfter(LocalDate.now().minusWeeks(1));
            }
            case "last month" -> {
                return  date.isAfter(LocalDate.now().minusMonths(1));
            }
            case "last year" -> {
                return  date.isAfter(LocalDate.now().minusYears(1));
            }
            default -> {
                return false;
            }
        }
    }

    private void filterRecords() {

        if(Objects.equals(categoryFilter.getValue(), "all")) {
            filteredRecordList.setPredicate(this::checkRecordDate);
            return;
        }
        filteredRecordList.setPredicate(record -> Objects.equals(record.getCategory().getCategoryName(), categoryFilter.getValue()) && checkRecordDate(record));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        records = new RecordList("records.rec");
        recordList = FXCollections.observableArrayList();
        if(records.getRecords() != null) {
            recordList.addAll(records.getRecords());
        }

        filteredRecordList = new FilteredList<>(recordList, s -> true);

        updateRecordChart();

        recordList.addListener(new ListChangeListener<Record>() {
            @Override
            public void onChanged(Change<? extends Record> change) {
                ArrayList<Record> current = new ArrayList<Record>(recordList.stream().toList());
                records.setRecords(current);
                records.writeRecordsTo("records.rec");
                updateRecordChart();
            }
        });

        filteredRecordList.addListener((ListChangeListener<Record>) change -> {
            recordListView.setItems(filteredRecordList);
            updateRecordChart();
        });

        categoryFilter.getItems().add("all");
        for(var category: Categories.values()) {
            categoryFilter.getItems().add(category.getCategoryName());
        }
        categoryFilter.setValue(categoryFilter.getItems().get(0));

        dateFilter.getItems().addAll(new String[]{"today", "last 3 days", "last week", "last month", "last year"});

        dateFilter.setValue(dateFilter.getItems().get(0));

        categoryFilter.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observableValue, s, t1) -> {
            filterRecords();
        });

        dateFilter.getSelectionModel().selectedItemProperty().addListener((ChangeListener<String>) (observableValue, s, t1) -> {
            filterRecords();
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