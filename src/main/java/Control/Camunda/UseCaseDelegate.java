package Control.Camunda;

import Control.Parser.Parser;
import Control.Strategy.Strategy;
import Control.Strategy.UseCaseStrategy.Control.UseCaseStrategy;
import Model.UMLComponent;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import javax.inject.Named;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

@Named("UCmain")
public class UseCaseDelegate implements JavaDelegate {
    private final Logger LOGGER = Logger.getLogger(UseCaseDelegate.class.getName());

    public void execute(DelegateExecution execution) throws Exception {

        LOGGER.info("\n\n  ... LoggerDelegate invoked by "
                + "processDefinitionId=" + execution.getProcessDefinitionId()
                + ", activtyId=" + execution.getCurrentActivityId()
                + ", activtyName='" + execution.getCurrentActivityName() + "'"
                + ", processInstanceId=" + execution.getProcessInstanceId()
                + ", businessKey=" + execution.getProcessBusinessKey()
                + ", executionId=" + execution.getId()
                + " \n\n");

        String homeDir = System.getProperty("user.home");
        String file = homeDir +"/Korrektur/GoodUseCase.uxf";//"src/main/resources/Solutions/GoodUseCase.uxf";
        File testFile = new File(file);
        Parser a = new Parser();
        List<UMLComponent> l = a.parseFile(testFile);
        Strategy strategy = new UseCaseStrategy(0.2,0.1,0.4,2);
        strategy.analyzeUML(l);
        file = homeDir +"/Korrektur/GoodUseCase.uxf";//"src/main/resources/Solutions/GoodUseCase.uxf";
        testFile = new File(file);
        l = a.parseFile(testFile);
        strategy.analyzeUML(l);
    }
}
