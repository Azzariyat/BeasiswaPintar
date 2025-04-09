import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new ConsoleUI();
        NotificationService notification = new NotificationProvider(ui);
        ScholarshipService scholarship = new ScholarshipManager(notification);

        RegisterButton registerButton = new RegisterButton(scholarship);
        ReportButton reportButton = new ReportButton(scholarship);
        Reviewer reviewer = new Reviewer(scholarship);

        Scanner scanner = new Scanner(System.in);
        int role;
        do {
            System.out.println("\n===== Selamat Datang di Sistem Beasiswa Pintar =====");
            System.out.println("Pilih peran Anda:");
            System.out.println("1. Pendaftar");
            System.out.println("2. Reviewer");
            System.out.println("3. Admin");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");
            role = scanner.nextInt();
            scanner.nextLine();

            switch (role) {
                case 1:
                    int userChoice;
                    do {
                        System.out.println("\n--- Menu Pendaftar ---");
                        System.out.println("1. Daftar Beasiswa Pintar");
                        System.out.println("2. Tampilkan ID Pendaftar Saya");
                        System.out.println("3. Cek Pengumuman Persetujuan");
                        System.out.println("0. Kembali");
                        System.out.print("Pilih menu: ");
                        userChoice = scanner.nextInt();
                        scanner.nextLine();
    
                        if (userChoice == 1) {
                            System.out.print("Nama lengkap: ");
                            String name = scanner.nextLine();
                            System.out.print("IPK: ");
                            double gpa = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("Email: ");
                            String email = scanner.nextLine();
                            System.out.print("No. Telepon: ");
                            String phone = scanner.nextLine();
                            System.out.print("Jurusan: ");
                            String major = scanner.nextLine();
                            System.out.print("Alamat: ");
                            String address = scanner.nextLine();
                            registerButton.click(name, gpa, email, phone, major, address);
                        } else if (userChoice == 2) {
                            String lastId = registerButton.getLastApplicantId();
                            if (lastId != null) {
                                ui.display("ID pendaftar terakhir Anda adalah: " + lastId);
                            } else {
                                ui.display("Belum ada pendaftaran yang dilakukan.");
                            }
                        } else if (userChoice == 3) {
                            String lastId = registerButton.getLastApplicantId();
                            if (lastId != null) {
                                boolean reviewed = scholarship.isApplicantReviewed(lastId);
                                if (!reviewed) {
                                    ui.display("Pendaftaran Anda masih dalam proses peninjauan oleh reviewer.");
                                } else {
                                    boolean approved = scholarship.isApplicantApproved(lastId);
                                    if (approved) {
                                        ui.display("Selamat! Pendaftaran Anda telah disetujui oleh reviewer.");
                                    } else {
                                        ui.display("Mohon maaf, pendaftaran Anda tidak disetujui oleh reviewer.");
                                    }
                                }
                            } else {
                                ui.display("Belum ada pendaftaran yang dilakukan.");
                            }
                        }
                    } while (userChoice != 0);
                    break;
                case 2:
                    int reviewerChoice;
                    do {
                        System.out.println("\n--- Menu Reviewer ---");
                        System.out.println("1. Setujui Pendaftar Berdasarkan ID");
                        System.out.println("0. Kembali");
                        System.out.print("Pilih menu: ");
                        reviewerChoice = scanner.nextInt();
                        scanner.nextLine();
    
                        if (reviewerChoice == 1) {
                            System.out.print("Masukkan ID pendaftar yang akan disetujui: ");
                            String approveId = scanner.nextLine();
                            reviewer.approve(approveId);
                        }
                    } while (reviewerChoice != 0);
                    break;
                case 3:
                    int adminChoice;
                    do {
                        System.out.println("\n--- Menu Admin ---");
                        System.out.println("1. Lihat Semua Laporan Pendaftar");
                        System.out.println("0. Kembali");
                        System.out.print("Pilih menu: ");
                        adminChoice = scanner.nextInt();
                        scanner.nextLine();
    
                        if (adminChoice == 1) {
                            reportButton.click();
                        }
                    } while (adminChoice != 0);
                    break;
                case 0:
                    System.out.println("Terima kasih telah menggunakan sistem Beasiswa Pintar. Sampai jumpa!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
    
        } while (role != 0);
    
        scanner.close();
    }
}