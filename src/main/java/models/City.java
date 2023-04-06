package models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer city_id;
    @Column(name = "city_name")
    private String city_name;

    @OneToMany(mappedBy = "city")
    private List<Employee> employeeList;

    public City(Integer city_id, String city_name) {
        this.city_id = city_id;
        this.city_name = city_name;
    }

    public City(Integer city_id) {
        this.city_id = city_id;
    }

    public City() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(city_id, city.city_id) && Objects.equals(city_name, city.city_name) && Objects.equals(employeeList, city.employeeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city_id, city_name, employeeList);
    }

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + city_id +
                ", city_name='" + city_name + '\'' +
                '}';
    }
}