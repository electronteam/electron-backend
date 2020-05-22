package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.converters.ProductConverter;
import md.electron.electronbackend.data.ProductData;
import md.electron.electronbackend.persistence.model.Product;
import md.electron.electronbackend.persistence.repositories.ProductRepository;
import md.electron.electronbackend.service.ProductService;
import md.electron.electronbackend.service.storage.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static md.electron.electronbackend.constants.GenericConstants.DOT;

@Service
public class ProductServiceImpl implements ProductService
{
    public static final String PRODUCT_IMAGE_FILE_PREFIX = "product_";

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private StorageService storageService;

    @Value("${data.media.products.dir}")
    private String productsFolder;

    @Override
    public List<ProductData> getAllProducts()
    {
        final List<ProductData> products = new ArrayList<>();
        final List<Product> productsDB = productRepository.findAll();
        productsDB.forEach(product -> products.add(productConverter.convert(product)));

        return products;
    }

    @Override
    public Page<ProductData> getProducts(final Pageable page)
    {
        final Page<Product> productsDB = productRepository.findAll(page);

        return productsDB.map(product -> productConverter.convert(product));
    }

    @Override
    public ProductData getProductByCode(final String code)
    {
        final Product product = productRepository.getProductByCode(code);
        return productConverter.convert(product);
    }

    @Override
    public void uploadProductImage(final MultipartFile file, final String code)
    {
        final String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        final String productImageFileName = PRODUCT_IMAGE_FILE_PREFIX + code + DOT + fileExtension;
        final String productImageUrl = productsFolder + productImageFileName;

        storageService.storeMedia(file, productImageUrl);
        saveProductImageURL(code, productImageUrl);
    }

    @Override
    public String getProductImageURL(final String code)
    {
        return productRepository.getProductImageURL(code);
    }

    private void saveProductImageURL(final String code, final String productImageUrl)
    {
        final Product product = productRepository.getProductByCode(code);
        product.setImageURL(productImageUrl);
        productRepository.save(product);
    }
}
