package com.algowizards.finalpricetracker;

import java.util.*;

/**
 *
 * @author AlgoWizards
 *
 * Class Description: This class manages all the instances/objects of the Product class.
 *
 */

public class ProductManager
{

    static Scanner keyboard = new Scanner(System.in);

    // Instance variables
    private static List<Product> productList = new ArrayList<>();
    private static int productListSize = productList.size();
    private static Map<Integer, Product> productMap = new HashMap<>();
    private static int productMapSize = productMap.size();

    private static List<Product> shoppingCartList = new ArrayList<>();
    private static Map<Integer, Product> shoppingCartMapping = new HashMap<>();

    // Constructors

    // Getter and setter methods
    // Getter methods
    static List<Product> getProductList()
    {

        return productList;

    }

    static Map<Integer, Product> getProductMap()
    {

        return productMap;

    }

    static int getProductListSize()
    {

        return productListSize;

    }

    static int getProductMapSize()
    {

        return productMapSize;

    }

    public static List<Product> getShoppingCartList()
    {

        return shoppingCartList;

    }

    public static Map<Integer, Product> getShoppingCartMapping()
    {

        return shoppingCartMapping;

    }

    // Setter methods
    static void setProductList(List<Product> newProductList)
    {

        productList = newProductList;
        productListSize = newProductList.size();

    }

    static void setProductListSize(int newListSize)
    {

        if (newListSize >= 0)
        {

            productListSize = newListSize;

        } else {

            throw new IllegalArgumentException("List size of productList must be at least 0");

        }

    }

    static void setProductListSize(List<Product> newProductList)
    {

        if (newProductList != null)
        {

            productListSize = newProductList.size();

        } else {

            throw new IllegalArgumentException("List of products passed in argument cannot be null");

        }

    }

    static void setProductMap(Map<Integer, Product> newProductMap)
    {

        productMap = newProductMap;
        productMapSize = newProductMap.size();

    }

    static void setProductMapSize(int newMapSize)
    {

        if (newMapSize >= 0)
        {

            productMapSize = newMapSize;

        } else {

            throw new IllegalArgumentException("Map size of productMap must be at least 0");

        }

    }

    static void setProductMapSize(Map<Integer, Product> newProductMap)
    {

        if (newProductMap != null)
        {

            productMapSize = newProductMap.size();

        } else {

            throw new IllegalArgumentException("Map of products passed in argument cannot be null");

        }

    }

    public static void setShoppingCartList(List<Product> shoppingCartList)
    {

        ProductManager.shoppingCartList = shoppingCartList;

    }

    public static void setShoppingCartMapping(Map<Integer, Product> shoppingCartMapping)
    {

        ProductManager.shoppingCartMapping = shoppingCartMapping;

    }

    // Other methods
    static void addProductToList(Product product) // Add a product object to the list of products
    {

        // Add the product to the list of products
        productList.add(product);
        setProductListSize(productList);

    }

    static void addProductToShoppingCartList(Product product)
    {

        shoppingCartList.add(product);

    }

    static void addProductToCart(Product product)
    {

        UserManager.getCurrentUser().getShoppingCartList().add(product);

    }

    static void addProductToShoppingCartMapping(Product product)
    {


        shoppingCartMapping.put(shoppingCartMapping.size() + 1, product);

    }

    static void addProductToMap(Product product) // Add a product object to the mapping of products
    {

        // Add a mapping of (item code, product object) in productMap
        productMap.put(product.getItemCode(), product);
        setProductMapSize(productMap);

    }

    static Product getProductByKey(int itemCode) // Get the product object if itemCode is provided
    {

        return productMap.get(itemCode);

    }

    // Transferred from DataInitialisation class
    // Generates list of products
    static void generateListOfProducts(DataStructure.List2D<String> lookupItem)
    {

        for (List<String> row : lookupItem.getList2D())
        {

            Product product = new Product(Integer.parseInt(row.get(0)), row.get(1), row.get(2), row.get(3), row.get(4));

            addProductToList(product);

        }

    }

    static void generateListOfCartProducts(DataStructure.List2D<String> cartData)
    {

        for (List<String> row : cartData.getList2D())
        {

            Product product = productMap.get(Integer.parseInt(row.getFirst()));
            product.setQuantity(Integer.parseInt(row.getLast()));

            addProductToCart(product);

        }

    }

    // Generates mapping of products
    static void generateMapOfProducts(DataStructure.List2D<String> lookupItem)
    {

        for (List<String> row : lookupItem.getList2D())
        {

            Product product = new Product(Integer.parseInt(row.get(0)), row.get(1), row.get(2), row.get(3), row.get(4));

            addProductToMap(product);

        }

    }

    // Methods for Browse Product by Categories
    // Method to get the product group list
    static DataStructure.List1D<String> getProductGroupList()
    {

        Set<String> uniqueGroups = new HashSet<>();

        for (Product product : productList)
        {

            String group = product.getItemGroup();

            uniqueGroups.add(group);

        }

        return new DataStructure.List1D<>(new ArrayList<>(uniqueGroups));

    }

    // Method to get the product group mapping
    static DataStructure.Mapping<Integer, String> getProductGroupMapping(DataStructure.List1D<String> productGroupList)
    {

        Map<Integer, String> productGroupMapping = new HashMap<>();

        for (int i = 0; i < productGroupList.getList1DSize(); i++)
        {

            productGroupMapping.put(i + 1, productGroupList.getList1DValue(i));

        }

        return new DataStructure.Mapping<>(productGroupMapping);

    }

    // After obtaining the chosen group, get its list of categories
    static DataStructure.List1D<String> getProductCategoryList(String chosenGroup)
    {

        Set<String> uniqueCategories = new HashSet<>();

        for (Product product : productList)
        {

            String category = product.getItemCategory();

            if((product.getItemGroup().equals(chosenGroup)))
            {

                uniqueCategories.add(category);

            }

        }

        return new DataStructure.List1D<>(new ArrayList<>(uniqueCategories));

    }

    // After obtaining the chosen group, get its mapping of categories
    static DataStructure.Mapping<Integer, String> getProductCategoryMapping(DataStructure.List1D<String> productCategoryList)
    {

        Map<Integer, String> productCategoryMapping = new HashMap<>();

        for (int i = 0; i < productCategoryList.getList1DSize(); i++)
        {

            productCategoryMapping.put(i + 1, productCategoryList.getList1DValue(i));

        }

        return new DataStructure.Mapping<>(productCategoryMapping);

    }

    static DataStructure.List1D<Product> getCategorisedProductList(String chosenCategory)
    {

        List<Product> categorisedProductList = new ArrayList<>();

        for (Product product : productList)
        {

            if(product.getItemCategory().equals(chosenCategory))
            {

                categorisedProductList.add(product);

            }

        }

        return new DataStructure.List1D<>(categorisedProductList);

    }

    static DataStructure.Mapping<Integer, Integer> getCategorisedProductMapping(DataStructure.List1D<Product> categorisedProductList)
    {

        Map<Integer, Integer> categorisedProductMapping = new HashMap<>();

        for (int i = 0; i < categorisedProductList.getList1DSize(); i++)
        {

            categorisedProductMapping.put(i + 1, categorisedProductList.getList1DValue(i).getItemCode());

        }

        return new DataStructure.Mapping<>(categorisedProductMapping);

    }

    static List<Product> searchProduct(String searchKey, String unitKey)
    {

        List<Product> searchProductList = new ArrayList<>();

        for (Product product: productList)
        {

            if ((unitKey.isEmpty()) ? (product.getItemName().equalsIgnoreCase(searchKey)) : (product.getItemName().equalsIgnoreCase(searchKey) && product.getUnit().equalsIgnoreCase(unitKey)))
            {

                searchProductList.add(product);

            }

        }

        return searchProductList;

    }

//    static void

}