package ntnu.idatt2105.endrehadzalic.backend.service;

import ntnu.idatt2105.endrehadzalic.backend.model.Calculation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * A simple calculator-service
 *
 * @author Endré Hadzalic
 */
@Service
public class CalculatorService {

    static Logger logger = LoggerFactory.getLogger(CalculatorService.class);

    private LinkedList<Calculation> history;

    public CalculatorService(){
        this.history  = new LinkedList<>();
    }

    public float plus(float x, float y){
        logger.info("adding " + x + " to " + y);
        float answer = x+y;
        this.history.push(new Calculation(x + "+" + y, answer));
        logger.info("answer was: " + answer);
       return answer;
    }

    public float minus(float x, float y){
        logger.info("subtracting " + y + " from " + x);
        float answer = x-y;
        this.history.push(new Calculation(x + "-" + y, answer));
        logger.info("answer was: " + answer);
        return answer;
    }

    public float multiply(float x, float y){
        logger.info("multiplying " + x + " with " + y);
        float answer = x*y;
        this.history.push(new Calculation(x + "*" + y, answer));
        logger.info("answer was: " + answer);
        return answer;
    }

    public float divide(float x, float y){
        logger.info("dividing " + x + " with " + y);
        float answer = x/y;
        this.history.push(new Calculation(x + "/" + y, answer));
        logger.info("answer was: " + answer);
        return answer;
    }

    public LinkedList<Calculation> getHistory(){
        logger.info("returning the history of size: " + this.history.size());
        return this.history;
    }

    public void clearHistory(){
        logger.info("clearing the history.");
        this.history = new LinkedList<>();
    }

}
