package emails.ses;

// these are the imports for SDK v1

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;

import java.util.Date;

public class EmailSender {
    public EmailResult handleRequest(EmailRequest request, Context context) {

        LambdaLogger logger = context.getLogger();
        logger.log("Entering send_email");

        EmailResult result = new EmailResult();

        try {
            AmazonSimpleEmailService client =
                    AmazonSimpleEmailServiceClientBuilder.standard()
                            .withRegion(Regions.US_WEST_2).build();

            logger.log("Request object: " + request);

            SendEmailRequest sendReq = new SendEmailRequest()
                    .withSource(request.from)
                    .withDestination(new Destination()
                            .withToAddresses(request.to))
                    .withMessage(new Message()
                            .withSubject(new Content(request.subject))
                            .withBody(new Body()
                                    .withText(new Content(request.textBody))
                                    .withHtml(new Content(request.htmlBody))));

            client.sendEmail(sendReq);

            logger.log("Email sent!");

            result.message = "Email sent!";
            result.timestamp = Long.toString(new Date().getTime());
        } catch (Exception ex) {
            logger.log("The email was not sent. Error message: " + ex.getMessage());

            result.message = "The email was not sent. Error message: " + ex.getMessage();
            result.timestamp = Long.toString(new Date().getTime());
            throw new RuntimeException(ex);
        }
        finally {
            logger.log("Leaving send_email");
        }

        // TODO:
        // Return EmailResult

        return result;
    }

}