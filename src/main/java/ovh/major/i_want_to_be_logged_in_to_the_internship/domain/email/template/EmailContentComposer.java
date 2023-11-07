package ovh.major.i_want_to_be_logged_in_to_the_internship.domain.email.template;

class EmailContentComposer {

    public static String emailWithLink(String title, String textBefore, String link, String textAfter) {
        String stringBuilder = headerStart() +
                title(title) +
                textLine(textBefore) +
                link(link) +
                textLine(textAfter) +
                headerEnd();
        return stringBuilder;
    }

    public static String email(String title, String text) {
        String stringBuilder = headerStart() +
                title(title) +
                textLine(text) +
                headerEnd();
        return stringBuilder;
    }

    private static String title(String tittle) {
        return "<h1>" + tittle + "</h1>";
    }

    private static String textLine(String text) {
        return "<p>" + text + "<p>";
    }

    private static String headerStart() {
        return "<html><body>";
    }

    private static String headerEnd() {
        return "</body></html>";
    }

    private static String link(String link, String linkName) {
        return "<a href=\"" + link + "\">" + linkName + "</a>";
    }

    private static String link(String link) {
        return link(link, link);
    }
}
