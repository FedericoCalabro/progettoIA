package coloruid.dlv;

import coloruid.gui.GraphPanel;
import it.unical.mat.embasp.base.Callback;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DlvCallback implements Callback {
    @Override
    public void callback(Output output) {
        GraphPanel.getInstance().getTextArea().append("Execution finished!"+ System.lineSeparator());
        AnswerSets answersets = (AnswerSets) output;
        List<String> temp = new ArrayList<String>();
        for(AnswerSet a:answersets.getOptimalAnswerSets()){
            try {
                for(Object obj:a.getValue()){
                    temp.add(obj.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if(temp.isEmpty()){
            GraphPanel.getInstance().getTextArea().append("No solution found!" + System.lineSeparator());
        }else {
            temp.sort(new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    Integer s1 = Integer.parseInt(o1.split(",")[2].substring(0, o1.split(",")[2].length() - 1));
                    Integer s2 = Integer.parseInt(o2.split(",")[2].substring(0, o2.split(",")[2].length() - 1));
                    return s1.compareTo(s2);
                }
            });
            for (String s : temp) {
                GraphPanel.getInstance().getTextArea().append(s + System.lineSeparator());
            }
        }
    }
}
