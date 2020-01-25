package md.electron.electronbackend.constants;

public class RequestMappings
{
    public static final String PRODUCTS = "api/products";
    public static final String PRODUCT_DETAILS = "api/product/{code}";
    public static final String SOLR_PRODUCTS = "api/solr/products";
    public static final String SOLR_SEARCH_PRODUCTS = "api/solr/searchProducts";
    public static final String ADD_TO_CART = "/api/addtocart";
    public static final String CURRENT_CART = "/api/currentCart";
    public static final String PLACE_ORDER = "/api/placeOrder";

    public static final String ADMIN_ORDERS = "/api/admin/orders";
    public static final String ADMIN_CREATE_USER = "/api/admin/createUser";

    public static final String SOLR_PRODUCTS_INDEXING = "api/solr/products/index";
}
