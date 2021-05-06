package ntnu.idatt2105.endrehadzalic.backend.model;

public class Calculation {
    private int id;
    private String calculation;
    private float answer;

    public Calculation(int id,String calculation, float answer) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
