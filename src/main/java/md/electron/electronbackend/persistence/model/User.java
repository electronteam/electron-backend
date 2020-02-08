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
@Table(name = "users")
//remember -  'Serializable' is mandatory; e.g. without it, hibernate won't autogenerate the ID
public class User implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)//CascadeType.ALL - this will save automatically a transient address object
    @JoinColumn(name = "USER_ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    private Address userAddress;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLE")
    private String role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();
}
