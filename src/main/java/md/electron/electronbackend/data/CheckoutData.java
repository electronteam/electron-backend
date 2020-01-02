package md.electron.electronbackend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutData
{
    private String name;
    private String lastName;
    private String email;
    private String address;
    private String phone;
}
