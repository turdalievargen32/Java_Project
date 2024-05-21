package Java_Project;

public class Pattern {

    private String   name;
    private String[] pattern;

    public String getName() {
        return name;
    }

    void setName(String nazwa) {
        this.name = nazwa;
    }

    public String[] getPattern() {
        return pattern;
    }

    public void setPattern(String[] wzorzec) {
        this.pattern = wzorzec;
    }

    public Pattern(String nazwa, String[] wzorzec) {
        this.name = nazwa;
        this.pattern = wzorzec;
    }

    public String toString() {
        return name;
    }
    public static Pattern[] setPattern() {
        Pattern[] pattern = new Pattern[1];
        pattern[0] = new Pattern("Worm", new String[]{"0#0", "0##", "#0#"});
        return pattern;
    }
}