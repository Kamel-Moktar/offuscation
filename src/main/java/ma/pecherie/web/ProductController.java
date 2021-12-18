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

   @GetMapping("/getAllProducts")
    public ArrayList<Product> getAllProduct(){
       return  productRepository.finAllOrderByName();
    }

    @PostMapping("/addProduct")
    public Product saveProduct(@RequestBody Product product){
        return  productRepository.save(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id ){
       Product p=productRepository.findProductById(id);
       productRepository.delete(p);    }


    @GetMapping("/getProductByName/{name}")
    public Product getProductByName(@PathVariable  String name){
        return  productRepository.findProductByName(name);
    }

    @PutMapping("/updateProduct")
    public Product updateProduct(@RequestBody  Product product){
        return  productService.updateProduct(product);
    }

    @PutMapping("/saveProductToProvide")
    public List<Product> saveProductToProvide(@RequestBody ArrayList<Product> products){

       return productService.saveProductsToProvide(products);
    }

    @PutMapping("/saveProductToSle")
    public List<Product> saveProductToSale(@RequestBody ArrayList<Product> products){

        return productService.saveProductsToSale(products);
    }



}
