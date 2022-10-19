package emails.ses;

public class EmailRequest {
    public String to;
    public String from;
    public String subject;
    public String textBody;
    public String htmlBody;

    @Override
    public String toString() {
        return "EmailRequest{" +
                "to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", textBody='" + textBody + '\'' +
                ", htmlBody='" + htmlBody + '\'' +
                '}';
    }
}
