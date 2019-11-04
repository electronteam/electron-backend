package md.electron.electronbackend.converters;

import md.electron.electronbackend.data.ProductData;
import md.electron.electronbackend.persistence.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter
{
    public ProductData convert(final Product product)
    {
        final ProductData productData = new ProductData();
        productData.setCode(product.getCode());
        productData.setName(product.getName());
        productData.setDescription(product.getDescription());
        productData.setPrice(product.getPrice());

        return productData;
    }
}
