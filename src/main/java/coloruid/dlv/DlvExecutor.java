package coloruid.dlv;

import coloruid.gui.GraphPanel;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;

public class DlvExecutor {

    public static final String ENCODING_STD = "Encoding Standard";
    public static final String ENCODING_CUST = "Encoding Custom";
    public static final String ENCODING_RES_CUST = "src/main/resources/coloruidCustom";
    public static final String ENCODING_RES_STD = "src/main/resources/coloruidStandard";
    public static final String FACTS_RES = "src/main/resources/facts";
    public static Handler handler;

    public static void execute(){
        handler = new DesktopHandler(new DLV2DesktopService("src/main/resources/dlv2.exe"));

        InputProgram facts = new ASPInputProgram();
        facts.addFilesPath(FACTS_RES);
        handler.addProgram(facts);

        InputProgram encoding = new ASPInputProgram();
        String encCB = GraphPanel.getInstance().getControlPanel().getComboBoxEncoder().getSelectedItem().toString();
        String encToUse = encCB.equals(ENCODING_STD) ? ENCODING_RES_STD : ENCODING_RES_CUST;
        encoding.addFilesPath(encToUse);
        handler.addProgram(encoding);

        handler.startAsync(new DlvCallback());
        GraphPanel.getInstance().getTextArea().append("Executing..." + System.lineSeparator());
    }
}
