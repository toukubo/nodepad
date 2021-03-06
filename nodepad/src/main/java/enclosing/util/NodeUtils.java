package enclosing.util;

import java.util.Enumeration;

import com.theuniversalgraph.application.nodepad.NodeComponent;

import enclosing.model.TagHash;

public class NodeUtils {
	public static String saferStringOf(String content) {
		String returned = removeTagString(content); 
		returned = returned.replaceAll("\\s", "_");
		returned = returned.replaceAll("\\[", "");
		returned = returned.replaceAll("\\]", "");
		return returned;
	}
	public static void removeTagStringAndSave(NodeComponent nodeComponent) {
		String removed=removeTagString(nodeComponent.getNodeInterface().getContent());
		nodeComponent.setText(removed);
	}
	public static String removeTagString(String content) {
		TagHash tagHash = TagHash.getInstance();
		Enumeration<String> tags = tagHash.getTags();
		while(tags.hasMoreElements()){
			String tag = tags.nextElement();
			content = content.replaceAll(tag + "\\s", "");
			content = content.replaceAll(tag, "");
		}
		return content;
	}
	public static String comparable(String nodefield) {
		return saferStringOf(removeTagString(nodefield));
	}

}
