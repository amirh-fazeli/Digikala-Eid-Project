import java.util.ArrayList;

public class Phone extends Electronics{
    private int ram;
    private String camera;
    private ArrayList<String> ports;

    public void setCamera(String camera) {
        this.camera = camera;
    }

    public void setPorts(ArrayList<String> ports) {
        this.ports = ports;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

}
