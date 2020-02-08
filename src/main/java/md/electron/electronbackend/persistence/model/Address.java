package md.electron.electronbackend.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STREET")
    private String street;

    @OneToOne(mappedBy = "userAddress", fetch = FetchType.LAZY)
    private User user;

    @OneToOne(mappedBy = "deliveryAddress", fetch = FetchType.LAZY)
    private Order order;
}
