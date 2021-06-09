import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.IOException;


import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;



public class Test {
    public static void main(String[] args) throws IOException {

        JSONParser jsonParser = new JSONParser();
        JSONObject declare = new JSONObject();
        JSONObject Global = new JSONObject();
        JSONObject Local = new JSONObject();
        JSONObject Declarations = new JSONObject();

        try (FileReader reader = new FileReader("naming.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            declare = (JSONObject) obj;// System.out.println(declare);
            Global = (JSONObject) declare.get("Global");
            Local = (JSONObject) declare.get("Local");
            Declarations = (JSONObject) declare.get("Declarations");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }



        String InputFile = "test.c";
        FileInputStream is = new FileInputStream(InputFile);
        ANTLRInputStream input = new ANTLRInputStream(is);
        CLexer lexer = new CLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CParser parser = new CParser( tokens);
        ParseTree tree = parser.compilationUnit();

//        CStandard CheckNames = new CStandard();
//        CheckNames.visit(tree);
        CStandard  CStandard = new CStandard(Global,Local,Declarations);
        CStandard.visit(tree);



    }

}
