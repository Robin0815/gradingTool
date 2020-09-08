package Control.Camunda;

import Control.Parser.Parser;
import Control.Strategy.ClassStrategy.ClassStrategy;
import Model.UMLComponent;
import com.opencsv.CSVWriter;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Die Datei einlese Logik von Herrn Kowollik genutzt

@Named("CCmain")
public class ClassDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        String homeDir = System.getProperty("user.home");
        homeDir = homeDir.replace("\\", "\\\\");
        File KorrekturDir = new File(homeDir + "\\Documents\\Korrektur");
        KorrekturDir.getParentFile().mkdirs();
        File[] filesList = KorrekturDir.listFiles((FileFilter) new SuffixFileFilter(".uxf", IOCase.INSENSITIVE));
        assert filesList != null;
        Arrays.sort(filesList);
        int adap;
        int sing;
        int stra;
        try {
            adap = (int) ((long) execution.getVariable("adapter"));
            sing = (int) ((long) execution.getVariable("singleton"));
            stra = (int) ((long) execution.getVariable("strategy"));
        } catch (Exception e) {
            adap = 0;
            sing = 0;
            stra = 0;
        }
        String mode = (String) (execution.getVariable("similarityType") == null ? "" : execution.getVariable("similarityType"));
        Parser a = new Parser();

        try {
            File csvfile = new File(KorrekturDir + "\\response.csv");
            csvfile.getParentFile().mkdirs();
            CSVWriter writer = new CSVWriter(new FileWriter(csvfile));
            for (File file : filesList) {
                ClassStrategy strategy = new ClassStrategy(mode, adap, sing, stra);
                List<UMLComponent> list = a.parseFile(file);
                strategy.analyzeUML(list);

                List<String> csvlist = new ArrayList<>();
                csvlist.add(file.getName());
                csvlist.add(strategy.passed());
                csvlist.add("\"" + strategy.getResult() + "\"");
                String[] nextLine = csvlist.toArray(new String[0]);
                writer.writeNext(nextLine);

            }
            writer.close();
        } catch (Exception e) {
            System.out.println("Something went wrong when writing the csv file");
            e.printStackTrace();
        }
    }
}
