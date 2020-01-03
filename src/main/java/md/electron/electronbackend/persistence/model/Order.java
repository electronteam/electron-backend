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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ORDER_ID", unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntry> entries = new ArrayList<>();

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "FK_USER_ID"))
    private User user;
}
