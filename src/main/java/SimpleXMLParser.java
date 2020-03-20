import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleXMLParser {
    public String xml;

    public SimpleXMLParser(String xml) {
        this.xml = xml;
    }

    public String findByTagName(String tagName) {
        Pattern regEx = Pattern.compile("<" + tagName + ">(.*)</" + tagName + ">");
        Matcher matcher = regEx.matcher(xml);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    public ArrayList parseTagAttribute(String attr) {
        Pattern regEx = Pattern.compile(attr + "=\"(.*?)\"");
        Matcher matcher = regEx.matcher(xml);
        var attributes = new ArrayList();
        while (matcher.find()) {
            attributes.add(matcher.group(1));
        }
        return attributes;
    }
}

