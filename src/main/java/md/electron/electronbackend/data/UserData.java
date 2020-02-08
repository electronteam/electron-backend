package md.electron.electronbackend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData
{
    private String name;
    private String lastName;
    private String email;
    private AddressData address;
    private String phone;
    private String password;
    private String role;
}
