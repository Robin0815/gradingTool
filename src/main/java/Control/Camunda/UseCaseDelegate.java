package Control.Camunda;

import Control.Parser.Parser;
import Control.Strategy.UseCaseStrategy.Control.UseCaseStrategy;
import Model.UMLComponent;
import com.opencsv.CSVWriter;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Named("UCmain")
public class UseCaseDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {

        String homeDir = System.getProperty("user.home");
        homeDir = homeDir.replace("\\", "\\\\");
        File KorrekturDir =  new File(homeDir +"\\Documents\\Korrektur");
        File[] filesList = KorrekturDir.listFiles();

        double beta = ((Long) execution.getVariable("beta"))/(double)100;
        boolean checksimilarity = (Boolean) execution.getVariable("checksimilarity");
        assert filesList != null;
        Parser a = new Parser();
        UseCaseStrategy strategy = new UseCaseStrategy(beta, filesList.length);
        if (checksimilarity) {
            double alpha = ((Long) execution.getVariable("alpha"))/(double)100;
            double delta = ((Long) execution.getVariable("delta"))/(double)100;
            strategy = new UseCaseStrategy(beta,alpha,delta, filesList.length);
        }

        try {
            File csvfile = new File(homeDir +"\\Documents\\Rückmeldung\\response.csv");
            csvfile.getParentFile().mkdirs();
            CSVWriter writer = new CSVWriter(new FileWriter(csvfile));
            for (File file : filesList) {
                List<UMLComponent> list = a.parseFile(file);
                strategy.analyzeUML(list);


                List<String> csvlist = new ArrayList<String>();
                csvlist.add(file.getName());
                csvlist.add(strategy.getStatus());
                String[] nextLine = csvlist.toArray(new String[0]);
                System.out.println(Arrays.toString(nextLine));
                writer.writeNext(nextLine);
            }
            writer.close();
        } catch (Exception e){
            System.out.println("Something went wrong when writing the csv file");
        }



        execution.setVariable("Report", strategy.getReport());
    }
}



