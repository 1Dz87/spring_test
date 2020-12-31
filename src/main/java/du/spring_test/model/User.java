package du.spring_test.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "spring_user")
public class User {

    private Long id;

    private String login;

    private String firstName;

    private String lastName;

    private List<Car> cars;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "login", unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
