package gui;

import Domain.Activity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import service.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private Service service;

    @FXML
    private TextField DateTextField;

    @FXML
    private ListView<Activity> ListViewAllActivities;

    @FXML
    private TextField action1TextField;

    @FXML
    private TextField action2TextField;

    @FXML
    private Button addButton;

    @FXML
    private TextField minutes1TextField;

    @FXML
    private TextField minutes2TextField;

    @FXML
    private TextField nbOfHoursTextField;

    @FXML
    private TextField nbOfStepsTextField;

    @FXML
    private Button filterButton;

    @FXML
    private TextField filterByTextField;

    @FXML
    private Button sortedButton;

    @FXML
    void sort(ActionEvent event) {
        List<Activity> sortedActivities=service.getSortedByDate();
        ObservableList<Activity>observableActivities=FXCollections.observableArrayList(sortedActivities);
        ListViewAllActivities.setItems(observableActivities);
    }

    @FXML
    void filterActivities(ActionEvent event) {
        int aux =Integer.parseInt(filterByTextField.getText());
        ArrayList<Activity>sorted=service.getFilteredActivitiesByValue(aux);
        ObservableList<Activity>sortedActivity=FXCollections.observableArrayList(sorted);
        ListViewAllActivities.setItems(sortedActivity);
    }

    @FXML
    void add(ActionEvent event) throws SQLException {
        String dateAdded=DateTextField.getText();
        int nbOfStepsAdded= Integer.parseInt(nbOfStepsTextField.getText());
        double hoursOfSleepAdded=Double.parseDouble(nbOfHoursTextField.getText());
        String activity1Added=action1TextField.getText();
        int minutes1Added= Integer.parseInt(minutes1TextField.getText());
        String activity2Added=action2TextField.getText();
        int minutes2Added= Integer.parseInt(minutes2TextField.getText());
        Activity act=new Activity(dateAdded,nbOfStepsAdded,hoursOfSleepAdded,activity1Added,minutes1Added,activity2Added,minutes2Added);
        service.add(act);
        populateList();

    }

    @FXML
    private ListView<String> showStepsTotalTextField;

    public void initialize() throws SQLException {
//        List<String>activitySteps=service.getSteps();
//        ObservableList<String>getSteps=FXCollections.observableList(activitySteps);
//        showStepsTotalTextField.setItems(getSteps);
        populateList();
    }

    void populateList()
    {
        List<Activity> activities = service.getActivities();
        ObservableList<Activity> obsCities = FXCollections.observableList(activities);
        ListViewAllActivities.setItems(obsCities);
    }




    public Controller(Service service) {
        this.service = service;
    }
}
