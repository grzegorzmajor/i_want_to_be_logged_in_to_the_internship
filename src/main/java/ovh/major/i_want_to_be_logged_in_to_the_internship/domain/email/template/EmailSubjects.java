package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.template;

enum EmailSubjects {

    EMAIL_CONFIRMATION("Email confirmation from The Internship Application."),
    SECURITY_INFORMATION("Security information from The Internship Application.");


    private String s;

    EmailSubjects(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
