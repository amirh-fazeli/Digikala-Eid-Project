public class ExternalSupplies extends Electronics {
    private String compatibleDevices;

    public void setCompatibleDevices(String compatibleDevices) {
        this.compatibleDevices = compatibleDevices;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", compatibleDevices: '" + compatibleDevices + '\'';
    }
}
