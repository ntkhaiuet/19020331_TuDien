import java.util.Scanner;

public class DictionaryManagement {
    public Dictionary insertFromCommandline(Dictionary dic) {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        scan.nextLine();
        for(int i = 0; i < number; i++) {
            String target = scan.nextLine();
            String explain = scan.nextLine();
            Word e = new Word(target, explain);
            dic.arr.add(e);
        }
        scan.close();
        return dic;
    }
}
