package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.template;

enum EmailMessages {

    TITLE_CONFIRMATION("Email confirmation!"),
    TITLE_SECURITY_INFORMATION("Security message!"),
    EXPIRATION_MESSAGE("The link will expire after 5 minutes!"),
    CONFIRMATION("You have registered in The Internship Application. If you registered," +
            "you must confirm your email. Click on the link below or copy and paste it into your browser."),
    UPDATE_MESSAGE("  has bean changed!"),
    LINK_URL("http://localhost:8888/");


    private String s;

    EmailMessages(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
