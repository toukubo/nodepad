/*
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package enclosing.application.node.ncplugins;

import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.theuniversalgraph.application.nodepad.NodeComponent;
import com.theuniversalgraph.application.nodepad.NodeObserver;

/**
 * @author Administrator
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DumpContentToText {
    public DumpContentToText(NodeComponent component, NodeObserver nodeObserver,String xmltag,String filename){
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream("./dump",true));
            writer.write("<"+xmltag + ">");
            writer.write(component.getNodeInterface().getContent());
            writer.write("</"+xmltag + ">\r\n");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
