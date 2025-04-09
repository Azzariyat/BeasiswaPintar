public class NotificationProvider implements NotificationService {
    private final UserInterface ui;

    public NotificationProvider(UserInterface ui) {
        this.ui = ui;
    }

    @Override
    public void sendNotification(String message) {
        ui.display("[Notifikasi] " + message);
    }
}