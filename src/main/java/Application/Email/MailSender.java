package Application.Email;

import Application.Entities.CustomPie;
import Application.Entities.Order;
import Application.Entities.ProductInfo;
import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class MailSender {
    private static final String SMTP_SERVER = "smtp.yandex.ru";
    private static final String SMTP_PORT = "465";
    private static final String USERNAME = "kaa5843771@yandex.ru";
    private static final String PASSWORD = "starwars2001";
    private static final String EMAIL_FROM = "kaa5843771@yandex.ru";
    private static final String EMAIL_SUBJECT = "Pie paradise order";

    public static void send(Order order) {
        String name = order.getName();
        String lastName = order.getLastName();
        String mail = order.getMail();
        List<ProductInfo> productInfoList = order.getProductInfoList();
        List<CustomPie> customPieList = order.getCustomPieList();

        String emailText = "Dear " + name + " " + lastName + "!\n" +
                " We thank you for choosing our bakery, your order will be ready as soon as possible.\n" +
                " Order information:\n";
        for (ProductInfo productInfo : productInfoList) {
            emailText += " - x" + productInfo.getAmount() + " " + productInfo.getName() + " ..... " + productInfo.getPrice() + " rub\n";
        }

        for (CustomPie customPie : customPieList) {
            emailText += " - Custom pie: ";

            List<String> ingredients = customPie.getIngredients();
            for (String ingredient : ingredients) {
                emailText += ingredient + ", ";
            }
        }

        emailText += "\nHoping to see you soon!\nHave a nice meal!\n\nPie paradise team";

        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", SMTP_SERVER);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, null);
        Message msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(EMAIL_FROM));

            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mail, false));

            msg.setSubject(EMAIL_SUBJECT);

            msg.setText(emailText);

            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            t.connect(SMTP_SERVER, USERNAME, PASSWORD);
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
