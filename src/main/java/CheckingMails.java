import javax.mail.*;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

public class CheckingMails {
    public static void check(String host, String storeType, String user,
                             String password) {
        try {
            //create properties field
            Properties properties = new Properties();

            properties.put("mail.pop3.host", host);
            properties.put("mail.pop3.port", "995");
            properties.put("mail.pop3.starttls.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore("pop3s");

            store.connect(host, user, password);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("inbox");
            emailFolder.open(Folder.READ_ONLY);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();


            for (Message message : messages) {
                if (message.getContent() instanceof Multipart) {
                    Multipart multipart = (Multipart) message.getContent();
                    for (int j = 0; j < multipart.getCount(); j++) {
                        BodyPart bodyPart = multipart.getBodyPart(j);
                        if (Part.ATTACHMENT.equals(bodyPart.getDisposition())) {
                            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(Path.of("Files", MimeUtility.decodeText(bodyPart.getFileName())).toString()));
                                 InputStream inputStream = bodyPart.getInputStream()) {

                                byte[] cash = new byte[1024];
                                int size;
                                while ((size = inputStream.read(cash)) > 0) {
                                    fileOutputStream.write(cash, 0, size);
                                }
                            }
                        }
                    }
                }
            }
            //close the store and folder objects
            emailFolder.close(false);
            store.close();

            //add logging later
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
