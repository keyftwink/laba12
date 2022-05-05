import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
   private static final String IPV4_PATTERN = "(?:25[0-5]|2[0-4]\\d|[01]?\\d\\d?)";
    private static final Pattern pattern = Pattern.compile("(?<!\\d)" + IPV4_PATTERN + "(?:\\." + IPV4_PATTERN +"){3}(?!\\d)");

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите айпи: ");
        String IPAddresses = in.nextLine();

        try {
            File file = new File("ipList.txt");
            file.createNewFile();

            ipCheck(IPAddresses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void ipCheck(String... line) {

        for(String nIP : line) {
            Matcher matcher = pattern.matcher(nIP);

            if (matcher.find()){
                System.out.println("Найден верный ip-адрес");
                System.out.println(matcher.group());

                try {
                    FileWriter writer = new FileWriter("ipList.txt");
                    writer.write(matcher.group());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("ip-адрес не найден");
            }
        }
    }
}