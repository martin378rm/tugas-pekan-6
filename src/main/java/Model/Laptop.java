package Model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.ws.rs.Consumes;

@Entity
public class Laptop extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String brands;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer ram;
    @Column(name = "internal_storage", nullable = false)
    private Integer internalStorage;


   @JsonIgnore
    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Integer getInternalStorage() {
        return internalStorage;
    }

    public void setInternalStorage(Integer internalStorage) {
        this.internalStorage = internalStorage;
    }
}
