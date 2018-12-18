package boot;

import util.HtmlParserUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ParseHtmlTest {
    public static void main(String[] args){
        HtmlParserUtil util = new HtmlParserUtil();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(new File(System.getProperty("user.dir")
                            + File.separator + "src" + File.separator + "main" + File.separator
                            + "resources" + File.separator + "html" + File.separator + "fullText.html"))
            ));

            StringBuilder builder = new StringBuilder();
            String html;
            while((html = reader.readLine()) != null)
                builder.append(html + "\n");

            util.parseHtml(builder.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
