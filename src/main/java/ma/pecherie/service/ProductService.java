package ma.pecherie.service;


import ma.pecherie.dao.customer.Customer;
import ma.pecherie.dao.customer.CustomerRepository;
import ma.pecherie.dao.product.Product;
import ma.pecherie.dao.product.ProductRepository;
import ma.pecherie.dao.provider.Provider;
import ma.pecherie.dao.provider.ProviderRepository;
import ma.pecherie.dao.provision.Provision;
import ma.pecherie.dao.provision.ProvisionRepository;
import ma.pecherie.dao.provisionproduct.ProvisionProduct;
import ma.pecherie.dao.provisionproduct.ProvisionProductRepository;
import ma.pecherie.dao.sale.Sale;
import ma.pecherie.dao.sale.SaleRepository;
import ma.pecherie.dao.saleproduct.SaleProduct;

import ma.pecherie.dao.saleproduct.SaleProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProvisionRepository provisionRepository;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    AccountService accountService;
    @Autowired
    SaleRepository saleRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProvisionProductRepository provisionProductRepository;
    @Autowired
    SaleProductRepository saleProductRepository;


    public Product updateProduct(Product product) {
        Product oldProduct = productRepository.findProductById(product.getId());
        oldProduct.setName(product.getName());
        oldProduct.setQuantity(product.getQuantity());
        return oldProduct;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);

    }
/*Approvisionnement de stocke /achat*/
   public List<Product> saveProductsToProvide(List<Product> productsToProvide){

       Provision provision=new Provision();
       Provider provider=providerRepository.findProviderById(1L);//pour le moument on vas selimiter ua un seut privider.
       provision.setProvider(provider);
       provision.setUser(accountService.getCurrentUser());
       provision.setDate(new Date());
       List<Product> products=new ArrayList<Product>();
       provisionRepository.save(provision);
       productsToProvide.forEach(p->{
           Product product=productRepository.findProductByName(p.getName());
           ProvisionProduct provisionProduct=new ProvisionProduct(
                   null,
                   product,
                   provision,
                   p.getQuantity()
           );
           provision.getProvisionProducts().add(provisionProductRepository.save(provisionProduct));
           product.setQuantity(product.getQuantity()+p.getQuantity());
           products.add(product);
       });


        return products;
    }

    /*Vente de produit*/
    public List<Product> saveProductsToSale(List<Product> productsToProvide){

        Sale sale=new Sale();
        Customer customer=customerRepository.findCustomerById(1L);//pour le moument on vas selimiter ua un seut privider.
        sale.setCustomer(customer);
        sale.setUser(accountService.getCurrentUser());
        sale.setDate(new Date());
        List<Product> products=new ArrayList<Product>();
        saleRepository.save(sale);
        productsToProvide.forEach(p->{
            Product product=productRepository.findProductByName(p.getName());
            SaleProduct saleProduct=new SaleProduct(
                    null,
                    product,
                    sale,
                    p.getQuantity()
            );
            sale.getSaleProducts().add(saleProductRepository.save(saleProduct));
            product.setQuantity(product.getQuantity()-p.getQuantity());
            products.add(product);
        });



        return products;
    }


}
