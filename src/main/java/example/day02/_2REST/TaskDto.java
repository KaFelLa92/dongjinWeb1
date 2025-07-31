package example.day02._2REST;

public class TaskDto {
    // 멤버변수
    private String name;
    private int age;

    // 생성자 (매개변수 없이 활용 가능)
    public TaskDto(){

    }

    // 생성자 (매개변수 있음)
    public TaskDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter and setter , toString
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TaskDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
