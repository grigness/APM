package repository;

import Domain.Activity;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    private List<Activity> activities;

    public Repository() {
        activities = new ArrayList<>();
        openConnection();
        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Activity");

            while (rs.next()) {
                activities.add(new Activity(
                        rs.getString("date"),
                        rs.getInt("nbOfSteps"),
                        rs.getDouble("hoursOfSleep"),
                        rs.getString("activities1"),
                        rs.getInt("minutes1"),
                        rs.getString("activities2"),
                        rs.getInt("minutes2")))
                ;
            }
            rs.close();
            s.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }


    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public static void executeUpdate(String query) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public void getActivitySteps() throws SQLException {
//        List<String> activity=new ArrayList<>();
//        PreparedStatement stmt = conn.prepareStatement("Select nbOfSteps from Activity");
//        ResultSet rs=stmt.executeQuery();
//        activity.add(rs.getString("nbOfSteps"));
//        ArrayList<Activity>newList=new ArrayList();
//        newList.add(activity);
//    }

    public List<Activity> getActivities() {
        List<Activity> activity = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(
                    "SELECT * from Activity");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Activity a = new Activity(
                        rs.getString("date"),
                        rs.getInt("nbOfSteps"),
                        rs.getFloat("hoursOfSleep"),
                        rs.getString("activities1"),
                        rs.getInt("minutes1"),
                        rs.getString("activities2"),
                        rs.getInt("minutes2"));
                activity.add(a);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activity;
    }


    public void add(Activity activity) throws SQLException{

        String date=activity.getDate();

        Activity existingDate=this.getActivities().stream()
                .filter(a->a.getDate().equals(date))
                .findFirst()
                .orElse(null);

        if(existingDate==null){
            try {
                PreparedStatement stmt = conn.prepareStatement(
                        "INSERT INTO Activity VALUES(?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, activity.getDate());
                stmt.setInt(2, activity.getNbOfSteps());
                stmt.setDouble(3,activity.getHoursOfSleep());
                stmt.setString(4,activity.getActivities1());
                stmt.setInt(5,activity.getMinutes1());
                stmt.setString(6,activity.getActivities2());
                stmt.setInt(7,activity.getMinutes2());

                stmt.executeUpdate();
                stmt.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }}
        else{
            PreparedStatement stmt = conn.prepareStatement("UPDATE Activity SET nbOfSteps = ?, hoursOfSleep = ?, activities1 = ?, minutes1 = ?, activities2 = ?, minutes2 = ? WHERE date=?");
            int aux=activity.getNbOfSteps();
            stmt.setInt(1, activity.getNbOfSteps()+aux);
            stmt.setDouble(2, activity.getHoursOfSleep());
            stmt.setString(3, activity.getActivities1());
            stmt.setInt(4, activity.getMinutes1());
            stmt.setString(5, activity.getActivities2());
            stmt.setInt(6, activity.getMinutes2());

            stmt.setString(7,date);

            stmt.executeUpdate();
            stmt.close();
        }
    }

}
