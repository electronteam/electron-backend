package md.electron.electronbackend.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Category implements Serializable
{
    @Id
    @Column(name = "CATEGORY_ID", unique = true, nullable = false)
    private String id;
    private String code;
    private String name;
    private String description;
}
