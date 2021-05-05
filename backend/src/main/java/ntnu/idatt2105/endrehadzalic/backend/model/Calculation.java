package ntnu.idatt2105.endrehadzalic.backend.model;

public class Calculation {
    private String calculation;
    private float answer;

    public Calculation(String calculation, float answer) {
        this.calculation = calculation;
        this.answer = answer;
    }

    public String getCalculation() {
        return calculation;
    }

    public void setCalculation(String calculation) {
        this.calculation = calculation;
    }

    public float getAnswer() {
        return answer;
    }

    public void setAnswer(float answer) {
        this.answer = answer;
    }
}
