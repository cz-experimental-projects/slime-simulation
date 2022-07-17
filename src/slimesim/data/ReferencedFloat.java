package slimesim.data;

public class ReferencedFloat {
    private float value;
    
    public ReferencedFloat(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }
    
    public void add(float value) {
        this.value += value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}