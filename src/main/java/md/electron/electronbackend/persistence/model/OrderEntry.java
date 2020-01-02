package md.electron.electronbackend.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orderentries")
public class OrderEntry implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ORDER_ENTRY_ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", foreignKey = @ForeignKey(name = "FK_PRODUCT_ID"))
    private Product product;

    @Column(name = "QUANTITY")
    private Long quantity;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", foreignKey = @ForeignKey(name = "FK_ORDER_ID"))
    private Order order;
}