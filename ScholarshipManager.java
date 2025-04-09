import java.util.*;

public class ScholarshipManager implements ScholarshipService {
    private final List<Applicant> applicants = new ArrayList<>();
    private final NotificationService notification;
    private final double minimumGpa = 3.00;
    private final String scholarshipName = "Beasiswa Pintar";

    public ScholarshipManager(NotificationService notification) {
        this.notification = notification;
    }

    @Override
    public void registerApplicant(Applicant applicant) {
        assert applicant != null : "Applicant cannot be null";

        applicants.add(applicant); // Tetap simpan semua pendaftar

        if (applicant.getGpa() >= minimumGpa) {
            notification.sendNotification("Pendaftar '" + applicant.getName() +
                    "' berhasil didaftarkan dengan ID " + applicant.getId() + " untuk " + scholarshipName);
        } else {
            notification.sendNotification("Pendaftar '" + applicant.getName() +
                    "' berhasil didaftarkan dengan ID " + applicant.getId() +
                    ", namun belum memenuhi syarat IPK minimum. Silakan menunggu hasil review.");
        }
    }

    @Override
public void approveApplicantById(String id) {
    for (Applicant a : applicants) {
        if (a.getId().equals(id)) {
            if (a.getGpa() >= minimumGpa) {
                a.approve(); // ini akan set approved = true & reviewed = true
                notification.sendNotification("Pendaftar dengan ID '" + id + "' telah disetujui oleh reviewer.");
            } else {
                a.markReviewed(); // ini hanya set reviewed = true
                notification.sendNotification("Pendaftar dengan ID '" + id + "' tidak disetujui karena IPK kurang dari 3.00.");
            }
            return;
        }
    }
    notification.sendNotification("Pendaftar dengan ID '" + id + "' tidak ditemukan.");
}

    @Override
    public void showAllApplicantsReport() {
        notification.sendNotification("Laporan Semua Pendaftar untuk " + scholarshipName + ":");
        for (Applicant a : applicants) {
            System.out.println(" - " + a);
        }
    }

    @Override
    public boolean isApplicantApproved(String id) {
        for (Applicant a : applicants) {
            if (a.getId().equals(id)) {
                return a.isApproved();
            }
        }
        return false;
    }

    @Override
public boolean isApplicantReviewed(String id) {
    for (Applicant a : applicants) {
        if (a.getId().equals(id)) {
            return a.isReviewed();
        }
    }
    return false;
}
}
