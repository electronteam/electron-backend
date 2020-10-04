package md.electron.electronbackend.service;

import md.electron.electronbackend.data.ProductData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService
{
    List<ProductData> getAllProducts();

    Page<ProductData> getProducts(Pageable page);

    ProductData getProductByCode(String code);

    void uploadProductImage(MultipartFile file, String code);

    String getProductImageURL(String code);

    void createProduct(ProductData productData);

    void updateProduct(ProductData productData);
}
