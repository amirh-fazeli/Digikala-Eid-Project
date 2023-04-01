public class Kid extends Product{
    private String age;

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", recommended age: '" + age + '\'';
    }
}
