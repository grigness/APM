package service;


import Domain.Activity;
import repository.Repository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public ArrayList<Activity> getFilteredActivitiesByValue(int value) {
        return(ArrayList<Activity>) repo.getActivities().stream()
                .filter(activity ->
                        (activity.getMinutes1()+activity.getMinutes2()> value))
                .collect(Collectors.toList());
    }

    public void add(Activity activity) throws SQLException {
        this.repo.add(activity);
    }

    public List<Activity> getActivities(){
        return repo.getActivities();
    }

//    public void getSteps() throws SQLException {
//        repo.getActivitySteps();
//    }


    public List<Activity> getSortedByDate(){
        DateTimeFormatter dateFormatter=DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return this.repo.getActivities().stream()
                .sorted(Comparator.comparing((activity -> LocalDate.parse(activity.getDate(),dateFormatter))))
                .collect(Collectors.toList());
    }

    public void showTotalNbOfSteps(){

    }
}
