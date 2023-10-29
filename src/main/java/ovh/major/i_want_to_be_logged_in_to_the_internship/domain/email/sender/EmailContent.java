package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender;

import lombok.Builder;

@Builder(builderClassName = "Builder")
class EmailContent {

    private String title;
    private String textBeforeLink;
    private String link;
    private String textAfterLink;

    private EmailContent() {}

    public String toString() {
        StringBuilder content = new StringBuilder();
        content.append("<html><body>");
        content.append("<h1>");
        content.append(title);
        content.append("</h1>");
        content.append("<p>");
        content.append(textBeforeLink);
        content.append("</p>");

        content.append("<a href=\"");
        content.append(link);
        content.append("\">");

        content.append("<p>");
        content.append(textAfterLink);
        content.append("</p>");
        content.append("</html></body>");

        return content.toString();
    }

}
