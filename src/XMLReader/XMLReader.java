package XMLReader;

import XMLTag.TagXML;

import java.util.Stack;

public class XMLReader  {
    private TagXML documentRoot = null;

    public XMLReader(XMLStream fileStream){
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
                    child.attachClosingTag(tag);
                    nesting.peek().addChildren(child);
                } else {
                    child.attachClosingTag(tag);
                    documentRoot = child;
                }
            } else {
                nesting.push(tag);
            }
        }
    }

    public TagXML getDocumentRootNode(){
        return this.documentRoot;
    }

}
