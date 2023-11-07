package ovh.major.i_want_to_be_logged_in_to_the_internship.infrastructure.authentication;

public enum Examples {
    BAD_CREDENTIALS("{\"username\":\"badusername\",\"password\":\"badpass\"}"),

    REGISTER_CORRECT_CREDENTIALS("{\n" +
            "\"username\":\"user\",\n" +
            "\"password\":\"pass\",\n" +
            "\"email\":\"john@do.com\"" +
            "}"),
    CORRECT_CREDENTIALS("{\n" +
            "\"username\":\"user\",\n" +
            "\"password\":\"pass\"" +
            "}"),
    UPDATED_CREDENTIALS("{\n" +
            "\"username\":\"someuser\",\n" +
            "\"password\":\"cutepassword\"" +
            "}"),
    UPDATE_DETAILS("{\n" +
            "\"username\":\"someuser\",\n" +
            "\"password\":\"cutepassword\",\n" +
            "\"email\":\"handsomejohn@do.com\"" +
            "}");

    private final String value;

    Examples(String value) {
        this.value = value;
    }

    public String toString() {
        return value;
    }

}
