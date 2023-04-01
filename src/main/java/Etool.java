public class Etool extends Tool{
    private int voltage;

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", voltage: " + voltage;
    }
}
