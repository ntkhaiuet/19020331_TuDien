import java.io.FileNotFoundException;
import java.util.Scanner;

public class DictionaryCommandLine {
    Dictionary dic = new Dictionary();
    DictionaryManagement management1 = new DictionaryManagement();

    public void showAllWords() {
        System.out.printf("| %-7s| %-50s| %-50s|\n","No","English","Vietnamese");
        for(int i = 0; i < dic.arr.size(); i++) {
            System.out.printf("| %-7d| %-50s| %-50s|\n", i+1, dic.arr.get(i).word_target, dic.arr.get(i).word_explain);
        }
    }

    public void dictionaryBasic() {
        try {
            management1.insertFromFile(dic);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Lua chon cua ban:");
        System.out.println("1: Chen tu dong lenh");
        System.out.println("2: Hien thi danh sach tu dien");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        switch (number) {
            case 1:
                management1.insertFromCommandline(dic);
                break;
            case 2:
                showAllWords();
                break;
            default: 
                System.out.println("Khong hop le");
        }
    }

    public void dictionaryAdvanced() {
        try {
            management1.insertFromFile(dic);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Lua chon cua ban:");
        System.out.println("1: Tra cuu tu dien");
        System.out.println("2: Hien thi danh sach tu dien");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        switch (number) {
            case 1:
                management1.dictionaryLookup(dic.arr);
                break;
            case 2:
                showAllWords();
                break;
            default:
                System.out.println("Khong hop le");
        }
        dic.arr.clear();
    }
}