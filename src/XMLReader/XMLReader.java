package XMLReader;

import XMLTag.TagXML;

import java.util.Stack;

import static javax.swing.text.html.HTML.Tag.P;

// TODO:ADD METHOD TO ITERATE THE DOCUMENT NODE
// TODO:ADD ITERATOR
public class XMLReader {
    private TagXML documentRoot = null;
    private TagXML itCurrent = null;

    public XMLReader(XMLStreamer fileStream){
        // Use a inner stack to find last entered scope
        Stack<TagXML> nesting = new Stack<>();

        // pour chaque <anchor>
        //      si debut alors ajoute a la pile
        //      sinon c'est tag fin alors on pop le dessus de pile avec l'enfan
        //      et la pile contient le parent sur la peak alors on ajoute l'enfant
        for (TagXML tag : fileStream.extractTagFromString()) {
            if (!tag.isOpeningTag()) {
                TagXML child = nesting.pop();
                if (!nesting.empty()) {
                    child.attach(tag);
                    nesting.peek().addChildren(child);
                } else {
                    documentRoot = child;
                }
            } else {
                nesting.push(tag);
            }
        }

        // current node at root
        itCurrent = documentRoot;
    }

    public TagXML getDocumentRootNode(){
        return this.documentRoot;
    }

    public TagXML next() {
        //TagXML node = this.itCurrent.getChildNode().getFirst();
        //this.itCurrent.getChildNode().removeFirst();
        return this.itCurrent;
    }

}
