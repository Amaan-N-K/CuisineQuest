import java.net.URL;
import java.util.Scanner;

public class testAPI {

    private static final String API_URL = "https://api.edamam.com/search?q=chicken&app_id=a00283fc&app_key=774a0bb3766d16e273b58303a97b12f8&from=0&to=1";

    public static void main(String[] args) {
        try {
            URL url = new URL(API_URL);
            Scanner scanner = new Scanner(url.openStream());

            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
