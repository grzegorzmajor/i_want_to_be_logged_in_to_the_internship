package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.sender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder(builderClassName = "Builder")
@AllArgsConstructor
@NoArgsConstructor
class EmailWithLinkContent {

    private String title;
    private String textBeforeLink;
    private String link;
    private String textAfterLink;

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
        content.append(link);
        content.append("</a>");

        content.append("<p>");
        content.append(textAfterLink);
        content.append("</p>");
        content.append("</html></body>");

        return content.toString();
    }

}
