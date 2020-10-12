import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
        return dic;
    }

    public Dictionary insertFromFile(Dictionary dic) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("dictionaries.txt")).useDelimiter("\\s*:\\s*");
        while (scan.hasNext()) {
            String target = scan.next();
            String explain = scan.nextLine();
            explain = explain.substring(3);
            Word e = new Word(target.toLowerCase(), explain.toLowerCase());
            dic.arr.add(e);
        }
        Collections.sort(dic.arr, new Word());
        return dic;
    }

    public void dictionaryLookup(ArrayList<Word> array) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Nhap tu tieng Anh:");
        String en = scan.nextLine();
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
            System.out.println("Khong tim thay tu vua nhap.");
        else
            System.out.println(array.get(mid).getWord_explain());
    }

    public void deleteWord (Dictionary dic){
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("Nhap tu can xoa bang tieng Anh:");
            String word = scan.nextLine();
            for (Word i : dic.arr){
                if (i.getWord_target().equals(word)) {
                    dic.arr.remove(dic.arr.indexOf(i));
                }
            }
        }
        catch (Exception ex){
        }
    }

    public void fixWord (Dictionary dic) {
        try{
            Scanner scan = new Scanner(System.in);
            System.out.println("Nhap tu can sua bang tieng Anh:");
            String en = scan.nextLine();
            int low = 0;
            int high = dic.arr.size();
            int mid = (low + high) / 2;
            while ((mid != low && mid != high)) {
                if (dic.arr.get(mid).getWord_target().compareTo(en) > 0){
                    high = mid;
                    mid = (low + high) / 2;
                }
                else {
                    low = mid;
                    mid = (low + high) / 2;
                }
            }
            if( !en.equalsIgnoreCase(dic.arr.get(mid).getWord_target()))
                System.out.println("Khong tim thay tu vua nhap.");
            else {
                System.out.println(dic.arr.get(mid).getWord_target()+" : "+dic.arr.get(mid).getWord_explain());
                System.out.println("Sua tu tieng Anh:" );
                String fixEn = scan.nextLine();
                System.out.println("Sua tu tieng Viet:" );
                String fixVi = scan.nextLine();
                Word fixWord = new Word(fixEn,fixVi);
                dic.arr.set(mid,fixWord);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void dictionaryExportToFile(Dictionary dic) {
        try {
            File file = new File("dictionaries.txt");
            FileWriter fileWrite = new FileWriter(file);

            for(int i = 0; i < dic.arr.size(); i++) {
                fileWrite.write(dic.arr.get(i).getWord_target()+ " : " + dic.arr.get(i).getWord_explain() + "\n");
            }
            fileWrite.close();
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
