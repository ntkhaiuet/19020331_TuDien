package CommandLine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class DictionaryManagement extends Dictionary {
    public static void insertFromCommandline() {
        Scanner scan = new Scanner(System.in);
        int number = scan.nextInt();
        scan.nextLine();
        for(int i = 0; i < number; i++) {
            String target = scan.nextLine();
            String explain = scan.nextLine();
            Word e = new Word(target.toLowerCase(), explain.toLowerCase());
            arr.add(e);
        }
    }

    public static void insertFromFile() {
        File file = new File("dictionaries.txt");
        Scanner scan = null;
        try {
            scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String[] string = scan.nextLine().split("\t");
                Word e = new Word(string[0].toLowerCase(), string[1].trim().toLowerCase());
                arr.add(e);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    public static void insertFromFileDICT() {
        try {
            String content = readFile("defaultDictionary.dict", Charset.defaultCharset());
            String[] words = content.split("@");
            for (String word: words) {
                String result[] = word.split("\r?\n", 2);
                if (result.length >1) {
                    String wordExplain1 = new String();
                    String wordTarget1 = new String();
                    String wordSound1 = new String();
                    if(result[0].contains("/")) {
                        String firstmeaning = result[0].substring(0, result[0].indexOf("/"));
                        String lastSoundMeaning = result[0].substring(result[0].indexOf("/"), result[0].length());
                        wordTarget1 = firstmeaning;
                        wordSound1 = lastSoundMeaning;
                    }
                    else
                    {
                        wordTarget1 = result[0];
                        wordSound1 = "";
                    }
                    wordExplain1 = result[1];
                    arr.add( new Word(wordTarget1.trim(),  wordSound1.trim() , wordExplain1.trim()));
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static Word dictionaryLookup(String wString) {
        Word w1 = new Word(wString);
        Word w2 = new Word(wString + "z");
        TreeSet<Word> list = (TreeSet<Word>) arr.subSet(w1,w2);
        Iterator<Word> i = list.iterator();
        if (i.hasNext()) {
            Word s = i.next();
            if(s.getWord_target().equals(wString)) return s ;
        }
        return new Word(wString,"","This word is not already existed");
    }

    public static String deleteWord(String wTarget) {
        wTarget.trim();
        wTarget.replaceAll("/t","");
        Word word = new Word(wTarget);
        if (wTarget.equals("")) return "Write a word";
        if (!arr.contains(word)) return "This word is not already existed";
        arr.remove(word);
        return "Successfully!";
    }

    public static String fixWord(String target, String explain) {
        target.trim();
        explain.trim();
        target.replaceAll("/t","");
        explain.replaceAll("/t","");
        Word word = dictionaryLookup(target);
        word.setWord_explain(explain);
        if (target.equals("")) return "Write a word";
        if (!arr.contains(word)) return  "This word is not already existed";
        if (explain.equals("")) return "Write definition";
        arr.remove(word);
        arr.add(word);
        return "Successfully!";
    }

    public static String addWord(String target,String sound, String explain) {
        target.trim();
        sound.trim();
        explain.trim();
        target.replaceAll("/t","");
        explain.replaceAll("/t","");
        if (target.equals("")) return "Write a word";
        if (arr.contains(new Word(target))) return "This word is already existed";
        if (sound.equals("")) return "Write phonetic";
        if (!sound.startsWith("/") || !sound.endsWith("/")) return "Phonetic: Wrong format";
        if (explain.equals("")) return "Write definition";
        Word word = new Word(target,sound,explain);
        arr.add(word);
        return "Successfully!";
    }

    public static void dictionaryExportToFile() throws IOException {
        FileWriter fileWrite = new FileWriter("dictionaries.txt");
        for(Word w : arr) fileWrite.write(w.toString0());
        fileWrite.close();
    }

    public static void insertFromDict() throws IOException {
        insertFromFileDICT();
    }

    public static void resetToDefaultDictionary() {
        arr.clear();
        insertFromFileDICT();
    }
}
