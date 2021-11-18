package ro.tuc.ds2020.entities;

public class Notification {

    private String notification;

    public Notification() {
    }

    public Notification(String name) {
        this.notification = name;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String name) {
        this.notification = name;
    }
}
