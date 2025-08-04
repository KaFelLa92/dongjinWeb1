package example.day04;

public class TaskDto {
    // 멤버변수
    private String name;
    private int age;

    // 생성자 (빈 생성자까지 다 만드셈)
    public TaskDto(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public TaskDto(String name) {
        this.name = name;
    }

    public TaskDto(int age) {
        this.age = age;
    }

    public TaskDto() {
    }

    //게터앤세터

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

    // toString

    @Override
    public String toString() {
        return "TaskDto{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
