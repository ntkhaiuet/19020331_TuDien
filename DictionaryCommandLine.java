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

    public void dictionarySeacher (){
        Scanner scan = new Scanner(System.in);
        System.out.println("Tim kiem cac tu bat dau bang:");
        String findWord = scan.nextLine();
        for (Word i : dic.arr) {
            if (i.getWord_target().startsWith(findWord)) {
                System.out.println(i.getWord_target()+" : "+ i.getWord_explain());
            }
        }
    }

    public void dictionaryAdvanced() {
        try {
            management1.insertFromFile(dic);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Lua chon cua ban:");
        System.out.println("1: Chen tu dong lenh");
        System.out.println("2: Tra cuu tu dien");
        System.out.println("3: Hien thi danh sach tu dien");
        System.out.println("4: Xoa tu");
        System.out.println("5: Sua tu");
        System.out.println("6: Tim kiem");
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        switch (number) {
            case 1:
                management1.insertFromCommandline(dic);
                management1.dictionaryExportToFile(dic);
                break;
            case 2:
                management1.dictionaryLookup(dic.arr);
                break;
            case 3:
                showAllWords();
                break;
            case 4:
                management1.deleteWord(dic);
                management1.dictionaryExportToFile(dic);
                break;
            case 5:
                management1.fixWord(dic);
                management1.dictionaryExportToFile(dic);
                break;
            case 6:
                dictionarySeacher();
                break;
            default:
                System.out.println("Khong hop le");
        }
        dic.arr.clear();
    }

    
}