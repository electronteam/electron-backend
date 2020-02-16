package md.electron.electronbackend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderData
{
    private Long id;
    private List<OrderEntryData> entries;
    private double totalPrice;
    private AddressData deliveryAddress;
    private UserData userData;
}
