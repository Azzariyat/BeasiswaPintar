public interface ScholarshipService {
    void registerApplicant(Applicant applicant);
    void approveApplicantById(String id);
    void showAllApplicantsReport();
    boolean isApplicantApproved(String id);
    boolean isApplicantReviewed(String id); 
}