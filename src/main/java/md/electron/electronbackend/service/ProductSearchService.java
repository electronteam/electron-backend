package md.electron.electronbackend.service;

import md.electron.electronbackend.search.model.ProductSolrData;

import java.util.List;

public interface ProductSearchService
{
    List<ProductSolrData> getAllProducts();
}
