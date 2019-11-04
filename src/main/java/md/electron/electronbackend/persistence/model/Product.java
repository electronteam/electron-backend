package md.electron.electronbackend.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product
{
    @Id
    private String id;
    private String code;
    private String name;
    private String description;
    private double price;
}
