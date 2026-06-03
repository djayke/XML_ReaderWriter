import XMLReader.XMLReader;
import XMLReader.XMLStreamer;
import XMLTag.TagXML;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    private static final String filePath = ".\\src\\resources\\data.xml";

    public static void main() {

        try {
            // Scan file into a string
            StringBuilder strBuilder = new StringBuilder();
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                strBuilder.append(scanner.nextLine());
            }

            // Stream file into token then read it
            XMLStreamer streamer = new XMLStreamer(strBuilder.toString());
            XMLReader reader = new XMLReader(streamer);

            // test
            TagXML cur = reader.getDocumentRootNode();
            while (cur.hasChildren()) {
                for(TagXML node : cur.getChildNode()){

                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}