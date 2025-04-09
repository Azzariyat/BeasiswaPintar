public class Reviewer {
    private final ScholarshipService service;

    public Reviewer(ScholarshipService service) {
        this.service = service;
    }

    public void approve(String id) {
        service.approveApplicantById(id);
    }
}