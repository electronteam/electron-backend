package md.electron.electronbackend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListViewData
{
    private Long id;
    private String userName;
    private String userLastName;
    private double totalPrice;
}
