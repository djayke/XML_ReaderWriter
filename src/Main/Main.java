package Main;

import XMLReader.XMLReader;
import XMLReader.XMLStream;
import XMLTag.TagXML;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final String filePath = "resources/data.xml";

    //TODO closing tag />
    //TODO comment

    public static void main(String[] args) {

            try {
                // Stream file into token then read it
                XMLStream streamer = new XMLStream(filePath);
                XMLReader reader = new XMLReader(streamer);


                TagXML cur = reader.getDocumentRootNode();
                System.out.println( cur.toString() );

            } catch (FileNotFoundException e) {
                System.out.println("File Not Exist");
            }
    }

}
