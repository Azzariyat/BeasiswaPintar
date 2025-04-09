import java.util.concurrent.ThreadLocalRandom;

public class Applicant {
    private final String id;
    private final String name;
    private final double gpa;
    private final String email;
    private final String phone;
    private final String major;
    private final String address;
    private boolean approved = false;
    private boolean reviewed = false; // Tambahan properti ini

    public Applicant(String name, double gpa, String email, String phone, String major, String address) {
        assert name != null && !name.trim().isEmpty() : "Name cannot be empty";
        assert gpa >= 0.0 && gpa <= 4.0 : "Invalid GPA";
        assert email != null && email.contains("@") : "Invalid email";
        assert phone != null && !phone.trim().isEmpty() : "Phone cannot be empty";

        this.id = String.format("%04d", ThreadLocalRandom.current().nextInt(1000, 10000));
        this.name = name;
        this.gpa = gpa;
        this.email = email;
        this.phone = phone;
        this.major = major;
        this.address = address;
        this.approved = false;
        this.reviewed = false;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getMajor() {
        return major;
    }

    public String getAddress() {
        return address;
    }

    public boolean isApproved() {
        return approved;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void approve() {
        this.approved = true;
        this.reviewed = true;
    }

    public void reject() {
        this.approved = false;
        this.reviewed = true;
    }

    public void markReviewed() {
        this.reviewed = true;
    }

    @Override
    public String toString() {
        String status;
        if (!reviewed) {
            status = "Menunggu Persetujuan";
        } else {
            status = approved ? "Disetujui" : "Tidak Disetujui";
        }

        return "ID: " + id + " | Nama: " + name + " | Email: " + email + " | Telepon: " + phone +
                " | Jurusan: " + major + " | Alamat: " + address + " | IPK: " + gpa + " | Status: " + status;
    }
}
