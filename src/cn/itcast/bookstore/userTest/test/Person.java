package cn.itcast.bookstore.userTest.test;

public class Person {
    private String name;
    private int age;
    private String sex;

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Person(){}
    public Person(String name,String sex,int age){
        this.age = age;
        this.name = name;
        this.sex = sex;
    }
}
