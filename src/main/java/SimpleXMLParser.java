import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleXMLParser {
    public String xml;

    public SimpleXMLParser(String xml) {
        this.xml = xml;
    }

    public String findByTagName(String tagName) {
        Pattern regEx = Pattern.compile("<"+tagName+">(.*)</"+tagName+">");
        Matcher matcher = regEx.matcher(xml);

        if(matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }
}

