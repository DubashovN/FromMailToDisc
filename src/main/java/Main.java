import java.io.*;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {


        File file = new File(Path.of("src", "two.pdf").toString());

        try (FileInputStream fileInputStream = new FileInputStream(new File(Path.of("one.pdf").toString()));
             FileOutputStream fileOutputStream = new FileOutputStream(file)) {

            byte[] cash = new byte[1024];
            int size;
            while ((size = fileInputStream.read(cash)) > 0) {
                fileOutputStream.write(cash, 0, size);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

//        try {
//            Files.copy(Paths.get("one.pdf"), Paths.get("src", "two.pdf"), StandardCopyOption.REPLACE_EXISTING);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
