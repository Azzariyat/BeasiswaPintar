public class RegisterButton {
    private final ScholarshipService service;
    private String lastApplicantId;

    public RegisterButton(ScholarshipService service) {
        this.service = service;
    }

    public void click(String name, double gpa, String email, String phone, String major, String address) {
        Applicant applicant = new Applicant(name, gpa, email, phone, major, address);
        lastApplicantId = applicant.getId();
        service.registerApplicant(applicant);
    }

    public String getLastApplicantId() {
        return lastApplicantId;
    }
}