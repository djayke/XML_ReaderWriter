package XMLTag;

import java.util.ArrayList;
import java.util.List;

public class TagXML {
    private final List<Integer> charIndexContent;
    private final int startIndex;
    private final int endIndex;
    private String tagName = "";
    private TagXML closeTag = null;
    private final ArrayList<TagAttribute> tagAttribute = new ArrayList<>(); //change for hashmap
    private final ArrayList<TagXML> nodes = new ArrayList<>();

    public TagXML(int startIndex, int endIndex, List<Integer> content){
        this.startIndex = startIndex;
        this.endIndex = endIndex + 1;
        this.charIndexContent = content;
        extractTagProperties();
    }

    private String extractFromContent(int start, int end){
        StringBuilder pink = new StringBuilder();
        for(Integer yellow : charIndexContent.subList(start, end)){
            pink.append(Character.toString(yellow));
        }
        return pink.toString();
    }
    private void extractTagProperties(){
        String[] ctx = extractFromContent(this.startIndex,this.endIndex).split(" ");

        // tag name
        this.tagName = ctx[0];

        // attributes
        for(int i=1; i<ctx.length; i++)
            this.tagAttribute.add(new TagAttribute(ctx[i]));

    }

    public void attachClosingTag(TagXML closingTag) {
        this.closeTag = closingTag;
    }
    public void addChildren(TagXML child) {
        this.nodes.add(child);
    }

    public boolean hasChildren() {
        return !this.nodes.isEmpty();
    }
    public boolean hasCloseTag(){
        if(this.closeTag == null)
            return false;
        return true;
    }
    public boolean hasAttribute(){
        return !this.tagAttribute.isEmpty();
    }
    public boolean isOpeningTag(){
        return this.tagName.charAt(1) != 47;
    }

    public String getTag(){
        return this.tagName;
    }
    public ArrayList<TagAttribute> getAttribute(){
        return this.tagAttribute;
    }
    public ArrayList<TagXML> getChildNode() {
        return this.nodes;
    }
    public TagXML getCloseTag() { return this.closeTag; }

    private String getContent() {
        if(!this.hasCloseTag())
            return "";

        return this.extractFromContent(this.endIndex, this.closeTag.startIndex);
    }


    @Override
    public String toString() {
        if(this.isOpeningTag()) {
            StringBuilder strTag = new StringBuilder();

            strTag.append(this.tagName);

            this.tagAttribute.forEach(n-> strTag.append(" " + n.toString() + " "));
            if(this.hasAttribute()) strTag.append(">");

            this.nodes.forEach(n -> strTag.append("\n" + n.toString()));
            if(!this.hasChildren()) strTag.append(this.getContent());

            if(this.hasCloseTag()) strTag.append(this.closeTag);

            return strTag.toString();
        } else {
            return this.tagName;
        }
    }


}
