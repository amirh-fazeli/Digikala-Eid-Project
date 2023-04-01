public class Laptop extends Electronics {
    private String gpu;
    private String cpu;
    private int ram;
    private String fingerprint;

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRam() {
        return ram;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", gpu: '" + gpu + '\'' +
                ", cpu: '" + cpu + '\'' +
                ", ram: " + ram +
                ", fingerprint: '" + fingerprint + '\'';
    }
}
