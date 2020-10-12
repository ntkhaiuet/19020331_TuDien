import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class DictionaryManagement {
    public Dictionary insertFromCommandline(Dictionary dic) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap so tu muon chen:");
        int number = scan.nextInt();
        scan.nextLine();
        for(int i = 0; i < number; i++) {
            System.out.println("Tieng Anh:");
            String target = scan.nextLine();
            System.out.println("Tieng Viet:");
            String explain = scan.nextLine();
            Word e = new Word(target.toLowerCase(), explain.toLowerCase());
            dic.arr.add(e);
        }
        scan.close();
        return dic;
    }

    public Dictionary insertFromFile(Dictionary dic) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("dictionaries.txt"));
        while (scan.hasNext()) {
            String line = scan.nextLine();
            String[] arr = line.split("[   ]+",2);
            Word e = new Word(arr[0].toLowerCase(), arr[1].toLowerCase());
            dic.arr.add(e);
        }
        Collections.sort(dic.arr, new Word());
        return dic;
    }

    public void dictionaryLookup(ArrayList<Word> array) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap tu tieng Anh:");
        String en = scan.nextLine();
        scan.close();
        int low = 0;
        int high = array.size();
        int mid = (low + high) / 2;
        while ((mid != low && mid != high)){
            if (array.get(mid).getWord_target().compareTo(en) > 0){
                high = mid;
                mid = (low + high) / 2;
            } else {
                low = mid;
                mid = (low + high) / 2;
            }
        }
        if(!en.equalsIgnoreCase(array.get(mid).getWord_target()))
            System.out.println("Khong tim thay tu vua nhap");
        else
            System.out.println(array.get(mid).getWord_explain());
    }
}
