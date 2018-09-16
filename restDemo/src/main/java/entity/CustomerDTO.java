package entity;

/**
 *
 * @author tha
 */
public class CustomerDTO {
    private Integer id;
    private String name;
    private int age;
    private String city; //Changed type here from City to String

    public CustomerDTO(Customer customer) {
        this.name = customer.getName();
        this.age = customer.getAge();
        this.city = customer.getCity().getName();
    }
    
    public CustomerDTO(Integer id, String name, int age, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public CustomerDTO(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public Integer getId() {
        return id;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
}
