public class DictionaryCommandLine {
    Dictionary dic = new Dictionary();
    DictionaryManagement management1 = new DictionaryManagement();

    public void showAllWords() {
        System.out.printf("|%-7s|%-50s|%-50s|\n","No","English","Vietnamese");
        for(int i = 0; i < dic.arr.size(); i++) {
            System.out.printf("|%-7d|%-50s|%-50s|\n", i+1, dic.arr.get(i).word_target, dic.arr.get(i).word_explain);
        }
    }

    public void dictionaryBasic() {
        management1.insertFromCommandline(dic);
        showAllWords();
    }
}