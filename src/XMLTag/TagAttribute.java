package XMLTag;

public class TagAttribute {

    private final String key;
    private final String value;

    public TagAttribute(String s){
        String tmpStripClosingChar = s.replaceAll(">","");
        String[] content = tmpStripClosingChar.split("=");
        this.key = content[0];
        this.value = content[1];
    }

    @Override
    public String toString() {
        return "[" + key + "=" + value + "]";
    }
}
