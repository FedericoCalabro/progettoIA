package coloruid.dlv;

import coloruid.gui.GraphPanel;
import it.unical.mat.embasp.base.Callback;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;

public class DlvCallback implements Callback {
    @Override
    public void callback(Output output) {
        GraphPanel.getInstance().getTextArea().append("Execution finished!"+ System.lineSeparator());
        AnswerSets answersets = (AnswerSets) output;

        for(AnswerSet a:answersets.getAnswersets()){
            try {
                for(Object obj:a.getValue()){
                    GraphPanel.getInstance().getTextArea().append(obj.toString() + System.lineSeparator());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
