import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class Coloruid {

    private static String encodingResource="src/main/resources/coloruidStandard";
    private static Handler handler;

    public static void main(String[] args) {
        System.out.println("Coloruid");

        handler = new DesktopHandler(new DLV2DesktopService("src/main/resources/dlv2.exe"));

        InputProgram facts = new ASPInputProgram();
        facts.addFilesPath("src/main/resources/levels/level14.txt");
        handler.addProgram(facts);

        InputProgram encoding = new ASPInputProgram();
        encoding.addFilesPath(encodingResource);
        handler.addProgram(encoding);

        long start = System.currentTimeMillis();
        Output o =  handler.startSync();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println(timeElapsed);

        AnswerSets answersets = (AnswerSets) o;
        System.out.println(o.getOutput());
//        for(AnswerSet a:answersets.getAnswersets()){
//            try {
//                for(Object obj:a.getAtoms()){
//                    System.out.println(obj);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
    }
}
