package CommandLine;

public class Word implements Comparable<Word> {
    private String word_target;
    private String word_explain;
    private String word_sound;

    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public String getWord_sound() {
        return word_sound;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public void setWord_sound(String word_sound) {
        this.word_sound = word_sound;
    }

    public Word(String word_target) {
        this.word_target = word_target;
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word(String word_target,String word_sound, String word_explain) {
        this.word_target = word_target;
        this.word_sound = word_sound;
        this.word_explain = word_explain;
    }

    public  String toString0() {
        return getWord_target() + "\t" + getWord_explain() + "\n";
    }

    public String toString() {
        return getWord_target() + "    " + getWord_sound()+"\n" + getWord_explain() + "\n";
    }

    public String toString2()
    {
        String w = getWord_explain();
        int m = w.indexOf("\n",0);
        if (m!=-1) m = w.indexOf("\n", m+1);
        if (m==-1) m = w.length();
        return getWord_target() + "    " + getWord_sound() + "\n" +w.substring(0,m) ;
    }

    @Override
    public int compareTo(Word w) {
        return this.getWord_target().compareTo(w.getWord_target());
    }

}
