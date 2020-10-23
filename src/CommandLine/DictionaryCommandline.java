package CommandLine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {
    public DictionaryCommandline() {
    }

    public static void showAllWords() {
        System.out.printf("No        | English             | Vietnamese\n\n");
        int cnt = 0;
        for (Word w : arr) {
            cnt++;
            System.out.printf("%-10d| %-20s| %s\n", cnt, w.getWord_target(), w.getWord_explain());
        }
    }

    public static ArrayList<String> dictionarySearcher(String wString) {
        ArrayList<String> list = new ArrayList<String>();
        for (Word i : arr) {
            if (i.getWord_target().startsWith(wString)) {
                list.add(i.getWord_target());
            }
        }
        return list;
    }

    public static void dictionaryBasic() {
        System.out.println("Nhap so tu va danh sach cac tu");
        insertFromCommandline();
        showAllWords();
    }

    public static void dictionaryAdvanced() throws IOException {
        DictionaryCommandline d = new DictionaryCommandline();
        d.insertFromFile();
        Scanner scan = new Scanner(System.in);
        boolean Exit = false;
        System.out.println("1: Add a word");
        System.out.println("2: Lookup");
        System.out.println("3: Show all words");
        System.out.println("4: Delete a word");
        System.out.println("5: Fix a word");
        System.out.println("6: Search");
        System.out.println("7: Export to file");
        System.out.println("0: Exit");
        while (!Exit) {
            int number = scan.nextInt();
            scan.nextLine();
            switch (number) {
                case 1:
                    System.out.println("Write a word in English: ");
                    String wString = scan.nextLine();
                    System.out.println("Phonetic: ");
                    String wString1 = scan.nextLine();
                    System.out.println("Meaning: ");
                    String wString2 = scan.nextLine();
                    System.out.println(addWord(wString, wString1, wString2));
                    break;
                case 2:
                    System.out.println("Write a word: ");
                    String wString3 = scan.nextLine();
                    wString3 = d.dictionaryLookup(wString3).getWord_explain();
                    if (!wString3.contentEquals("This word is not already existed")) {
                        System.out.println("Explain:");
                    }
                    System.out.println(wString3);
                    break;
                case 3:
                    d.showAllWords();
                    break;
                case 4:
                    System.out.println("Write a word: ");
                    String wString4 = scan.nextLine();
                    System.out.println(deleteWord(wString4));
                    break;
                case 5:
                    System.out.println("Write a word: ");
                    String wString5 = scan.nextLine();
                    System.out.println("New meaning: ");
                    String wString6 = scan.nextLine();
                    System.out.println(fixWord(wString5, wString6));
                    break;
                case 6:
                    System.out.println("Write partial word: ");
                    String wString7 = scan.next();
                    ArrayList<String> arrList = dictionarySearcher(wString7);
                    for (String i : arrList) System.out.println(i);
                    break;
                case 7:
                    dictionaryExportToFile();
                    System.out.println("Successfully!");
                    break;
                case 0:
                    Exit = true;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        DictionaryCommandline dic = new DictionaryCommandline();
        try {
            dic.dictionaryAdvanced();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
