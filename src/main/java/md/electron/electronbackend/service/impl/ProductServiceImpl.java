package md.electron.electronbackend.service.impl;

import md.electron.electronbackend.converters.ProductConverter;
import md.electron.electronbackend.data.ProductData;
import md.electron.electronbackend.persistence.model.Product;
import md.electron.electronbackend.persistence.repositories.ProductRepository;
import md.electron.electronbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService
{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductConverter productConverter;

    @Override
    public List<ProductData> getAllProducts()
    {
        final List<ProductData> products = new ArrayList<>();
        final List<Product> productsDB = productRepository.findAll();
        productsDB.forEach(product -> products.add(productConverter.convert(product)));

        return products;
    }
}
