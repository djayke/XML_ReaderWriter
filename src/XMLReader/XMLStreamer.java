package XMLReader;

import XMLTag.TagXML;

import java.util.ArrayList;

public class XMLStreamer {

    private ArrayList<Integer> content = new ArrayList<>();
    private int currentPosition = 0;

    public XMLStreamer(String strContent) {
        strContent.chars().forEach(c -> {
            content.add(c);
        });
    }

    public ArrayList<TagXML> extractTagFromString() {
        ArrayList<TagXML> documentNode = new ArrayList<>();
        while (currentPosition < content.size() - 1){
            int startIndex = findNextDelimStart();
            int endIndex = findNextDelimEnd();

            if(startIndex != -1 && endIndex != -1) {
                TagXML tag = new TagXML(startIndex,endIndex, content);
                documentNode.add(tag);
            }
        }
        return documentNode;
    }

    private int findNextDelimEnd() {
        return findNextChar(62);
    }

    private int findNextDelimStart() {
        return findNextChar(60);
    }

    private int findNextChar(int _char){
        while(content.get(currentPosition) != _char) {
            currentPosition++;
        }
        return currentPosition;
    }

}
