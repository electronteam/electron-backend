package md.electron.electronbackend.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductData
{
    private String code;
    private String name;
    private String description;
    private double price;
    private String imageURL;
}
