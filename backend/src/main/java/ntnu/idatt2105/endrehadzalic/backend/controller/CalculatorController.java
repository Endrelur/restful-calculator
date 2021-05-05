package ntnu.idatt2105.endrehadzalic.backend.controller;

import ntnu.idatt2105.endrehadzalic.backend.model.Calculation;
import ntnu.idatt2105.endrehadzalic.backend.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CalculatorController {

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

    @GetMapping("/history")
    public List<Calculation> getHistory(){
        return this.calculatorService.getHistory();
    }

    @DeleteMapping("/history")
    public void deleteHistory(){
        this.calculatorService.clearHistory();
    }


}
