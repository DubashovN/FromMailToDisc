public class Main {

    public static void main(String[] args) {
        String host = "pop.mail.ru";// change accordingly
        String mailStoreType = "pop3";
        String username = ".ru";// change accordingly
        String password = "";// change accordingly
        CheckingMails.check(host, mailStoreType, username, password);
    }
}
