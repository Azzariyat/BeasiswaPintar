public class ReportButton {
    private final ScholarshipService service;

    public ReportButton(ScholarshipService service) {
        this.service = service;
    }

    public void click() {
        service.showAllApplicantsReport();
    }
}