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

//Code von Herrn Kowollik genutzt, abgeändert für eigene Zwecke

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

        int adap = (int)((long) execution.getVariable("adapter"));
        int sing = (int)((long) execution.getVariable("singleton"));
        int stra = (int)((long) execution.getVariable("strategy"));
        String mode = (String) execution.getVariable("similarityType");
        Parser a = new Parser();

        try {
            File csvfile = new File(KorrekturDir + "\\response.csv");
            csvfile.getParentFile().mkdirs();
            CSVWriter writer = new CSVWriter(new FileWriter(csvfile));
            for (File file : filesList) {
                ClassStrategy strategy = new ClassStrategy(mode,adap,sing,stra);
                List<UMLComponent> list = a.parseFile(file);
                strategy.analyzeUML(list);

                List<String> csvlist = new ArrayList<String>();
                csvlist.add(file.getName());
                csvlist.add(strategy.passed());
                csvlist.add(strategy.getResult());
                String[] nextLine = csvlist.toArray(new String[0]);
                writer.writeNext(nextLine);

            }
            writer.close();
        }catch (Exception e) {
            System.out.println("Something went wrong when writing the csv file");
            e.printStackTrace();
        }
    }
}
