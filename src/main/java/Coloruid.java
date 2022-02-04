import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

import java.io.IOException;
import java.util.List;

public class Coloruid {

    private static String encodingResource="src/main/resources/coloruid";
    private static Handler handler;

    public static void main(String[] args) {
        System.out.println("Coloruid");

        handler = new DesktopHandler(new DLV2DesktopService("src/main/resources/dlv2.exe"));

        InputProgram facts = new ASPInputProgram();
        facts.addFilesPath("src/main/resources/levels/level1.txt");
        handler.addProgram(facts);

        InputProgram encoding = new ASPInputProgram();
        encoding.addFilesPath(encodingResource);
        handler.addProgram(encoding);


        Output o =  handler.startSync();
        System.out.println(o.getOutput());

        AnswerSets answersets = (AnswerSets) o;
        for(AnswerSet a:answersets.getAnswersets()){
            try {
                for(Object obj:a.getAtoms()){
                    System.out.println(obj);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
