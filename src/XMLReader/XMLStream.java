package XMLReader;

import XMLTag.TagXML;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class XMLStream {

    private ArrayList<Integer> content = new ArrayList<>();
    private int currentPosition = 0;

    private void XMLStreamInit(String strContent) {
        strContent.chars().forEach(c -> {
            content.add(c);
        });
    }

    public XMLStream(String filePath) throws FileNotFoundException {
        StringBuilder strBuilder = new StringBuilder();

        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine())
            strBuilder.append(scanner.nextLine());

        this.XMLStreamInit( strBuilder.toString() );
    }
    public XMLStream(File file) throws FileNotFoundException {
        this(file.getPath());
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
