package ma.pecherie.web;
import ma.pecherie.dao.product.Product;
import ma.pecherie.dao.product.ProductRepository;
import ma.pecherie.service.ProductService;
import ma.pecherie.web.models.ProductToProvide;
import ma.pecherie.web.models.ProduitsJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
   ProductRepository productRepository;
    @Autowired
    ProductService productService;

   @GetMapping("ma.pecherie/getAllProducts")
    public ArrayList<Product> getAllProduct(){
       return  productRepository.finAllOrderByName();
    }

    @PostMapping("ma.pecherie/addProduct")
    public Product saveProduct(@RequestBody Product product){
        return  productRepository.save(product);
    }

    @DeleteMapping("ma.pecherie/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id ){
       Product p=productRepository.findProductById(id);
       productRepository.delete(p);    }


    @GetMapping("ma.pecherie/getProductByName/{name}")
    public Product getProductByName(@PathVariable  String name){
        return  productRepository.findProductByName(name);
    }

    @PutMapping("ma.pecherie/updateProduct")
    public Product updateProduct(@RequestBody  Product product){
        return  productService.updateProduct(product);
    }

    @PutMapping("ma.pecherie/saveProductToProvide")
    public List<Product> saveProductToProvide(@RequestBody ArrayList<Product> products){

       return productService.saveProductsToProvide(products);
    }

    @PutMapping("ma.pecherie/saveProductToSle")
    public List<Product> saveProductToSale(@RequestBody ArrayList<Product> products){

        return productService.saveProductsToSale(products);
    }



}
