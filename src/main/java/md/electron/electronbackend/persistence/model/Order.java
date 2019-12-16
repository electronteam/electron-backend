package md.electron.electronbackend.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable
{
    @Id
    @Column(name = "ORDER_ID", unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderEntry> entries = new ArrayList<>();

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;
}
