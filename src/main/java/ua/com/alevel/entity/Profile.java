package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile extends AbstractEntity{
    private String name;
    private String job;
    private Integer age;
    private Integer salary;

    public Profile(){
        super();
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
