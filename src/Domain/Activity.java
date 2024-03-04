package Domain;

public class Activity {
    public String date;
    public int nbOfSteps;
    public double hoursOfSleep;

    public String activities1;

    public int minutes1;
    public String activities2;
    public int minutes2;


    public Activity(String date, int nbOfSteps, double hoursOfSleep, String activities1, int minutes1, String activities2, int minutes2) {
        this.date = date;
        this.nbOfSteps = nbOfSteps;
        this.hoursOfSleep = hoursOfSleep;
        this.activities1 = activities1;
        this.minutes1 = minutes1;
        this.activities2 = activities2;
        this.minutes2 = minutes2;
    }

    public double getHoursOfSleep() {
        return hoursOfSleep;
    }

    public void setHoursOfSleep(double hoursOfSleep) {
        this.hoursOfSleep = hoursOfSleep;
    }

    public int getNbOfSteps() {
        return nbOfSteps;
    }

    public void setNbOfSteps(int nbOfSteps) {
        this.nbOfSteps = nbOfSteps;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getActivities1() {
        return activities1;
    }

    public void setActivities1(String activities1) {
        this.activities1 = activities1;
    }

    public String getActivities2() {
        return activities2;
    }

    public void setActivities2(String activities2) {
        this.activities2 = activities2;
    }

    public int getMinutes2() {
        return minutes2;
    }

    public void setMinutes2(int minutes2) {
        this.minutes2 = minutes2;
    }

    public int getMinutes1() {
        return minutes1;
    }

    public void setMinutes1(int minutes1) {
        this.minutes1 = minutes1;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "date='" + date + '\'' +
                ", nbOfSteps=" + nbOfSteps +
                ", hoursOfSleep=" + hoursOfSleep +
                ", activities1='" + activities1 + '\'' +
                ", minutes1=" + minutes1 +
                ", activities2='" + activities2 + '\'' +
                ", minutes2=" + minutes2 +
                '}';
    }
}
