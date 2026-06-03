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
        extractTagMetaData();
    }

    private String extractFromContent(int start, int end){
        StringBuilder pink = new StringBuilder();
        for(Integer yellow : charIndexContent.subList(start, end)){
            pink.append(Character.toString(yellow));
        }
        return pink.toString();
    }

    private void extractTagMetaData(){
        String[] ctx = extractFromContent(this.startIndex,this.endIndex).split(" ");

        // tag name
        this.tagName = ctx[0];

        // attributes
        for(int i=1; i<ctx.length; i++)
            this.tagAttribute.add(new TagAttribute(ctx[i]));

    }

    public String getContent() {
        if(this.closeTag == null)
            return "";

        return this.extractFromContent(this.endIndex, this.closeTag.startIndex);
    }

    public String getTag(){
        return this.tagName;
    }

    public ArrayList<TagAttribute> getAttribute(){
        return this.tagAttribute;
    }

    public boolean hasAttribute(){
        return !this.tagAttribute.isEmpty();
    }

    public boolean isOpeningTag(){
        return this.tagName.charAt(1) != 47;
    }

    public void attach(TagXML closingTag) {
        this.closeTag = closingTag;
    }

    public boolean hasChildren() {
        return !this.nodes.isEmpty();
    }

    public void addChildren(TagXML child) {
        this.nodes.add(child);
    }

    public ArrayList<TagXML> getChildNode() {
        return this.nodes;
    }

    //TODO: PRETTY PRINT IT
    @Override
    public String toString() {
        if(this.isOpeningTag()) {
            StringBuilder strTag = new StringBuilder();

            strTag.append(this.tagName).append(" ");

            if(this.hasAttribute())
                strTag.append(this.tagAttribute).append(" ");

            strTag.append("{").append(this.nodes.size()).append("}\n");

            if(this.hasChildren())
                this.nodes.forEach(n -> strTag.append("\t").append(n.toString()).append("\n"));
            else
                strTag.append(this.getContent()).append("\n");

            return strTag.toString();
        } else {
            return this.tagName;
        }
    }


}
