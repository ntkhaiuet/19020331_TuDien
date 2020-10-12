import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DictionaryCommandLine a = new DictionaryCommandLine();
        Scanner scan = new Scanner(System.in);
        while (true) {
            a.dictionaryAdvanced();
            System.out.println("Exit?     1.Yes     2.No");
            int i = scan.nextInt();
            if(i == 1) {
                System.out.println("Tam biet. Hen gap lai!");
                break;
            }
            if (i!=1 && i!=2) {
                System.out.println("Khong hop le. Thoat chuong trinh!");
                break;
            }
        }
        scan.close();
    }
}