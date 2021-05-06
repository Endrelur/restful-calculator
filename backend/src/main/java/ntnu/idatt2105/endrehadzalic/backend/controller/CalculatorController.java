package ntnu.idatt2105.endrehadzalic.backend.controller;

import ntnu.idatt2105.endrehadzalic.backend.model.Calculation;
import ntnu.idatt2105.endrehadzalic.backend.service.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CalculatorController {
    static Logger logger = LoggerFactory.getLogger(CalculatorController.class);


    @Autowired
    CalculatorService calculatorService;

    @GetMapping("/plus/{x}/{y}")
    public float plus(@PathVariable(name = "x") float x, @PathVariable(name = "y") float y){
        return this.calculatorService.plus(x,y);
    }

    @GetMapping("/minus/{x}/{y}")
    public float minus(@PathVariable(name = "x") float x, @PathVariable(name = "y") float y){
        return this.calculatorService.minus(x,y);
    }

    @GetMapping("/multiply/{x}/{y}")
    public float multiply(@PathVariable(name = "x") float x, @PathVariable(name = "y") float y){
        return this.calculatorService.multiply(x,y);
    }

    @GetMapping("/divide/{x}/{y}")
    public float divide(@PathVariable(name = "x") float x, @PathVariable(name = "y") float y){
        return this.calculatorService.divide(x,y);
    }

    @GetMapping("/divide/{x}/0")
    public float divideByZero(@PathVariable(name = "x") float x){
        logger.info("there was a request for divide by 0, returning 0");
        return 0;
    }

    @GetMapping("/history")
    public List<Calculation> getHistory(){
        return this.calculatorService.getHistory();
    }

    @DeleteMapping("/history")
    public void deleteHistory(){
        this.calculatorService.clearHistory();
    }


}
