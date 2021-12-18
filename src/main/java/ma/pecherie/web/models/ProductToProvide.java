package ma.pecherie.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.pecherie.dao.product.Product;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductToProvide  {
   public  Product  product=null;
   public  Double   requiredQuantity=0D;
   public  Boolean  isSelected =false;
}
