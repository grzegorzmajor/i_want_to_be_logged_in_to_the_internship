package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication.error;

public enum ExceptionMessages {
    NO_PERMISSION("");

    private String s;

    ExceptionMessages(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
