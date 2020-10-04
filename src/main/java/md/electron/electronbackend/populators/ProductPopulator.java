package md.electron.electronbackend.populators;

import md.electron.electronbackend.data.ProductData;
import md.electron.electronbackend.persistence.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductPopulator
{
    public void populate(final ProductData source, final Product target)
    {
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setPrice(source.getPrice());
    }
}
