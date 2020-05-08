package md.electron.electronbackend.service;

import md.electron.electronbackend.data.ProductData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService
{
    List<ProductData> getAllProducts();

    ProductData getProductByCode(String code);

    void uploadProductImage(MultipartFile file, String code);
}
