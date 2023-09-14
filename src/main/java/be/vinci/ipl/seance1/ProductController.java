package be.vinci.ipl.seance1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ProductController {
    private static final ConcurrentHashMap<Integer, Product> products = new ConcurrentHashMap<>();

    static {
        products.put(1, new Product(1,
                "table",
                "table",
                200));

        products.put(2, new Product(2,
                "chaise",
                "chaise",
                10));

        products.put(3, new Product(3,
                "corde",
                "materiel",
                2));
    }

    /**
     * Read all products
     *
     * @request GET /amazing
     * @response 200: returns all products
     */
    @GetMapping("/amazing")
    public Iterable<Product> readAll() {
        return products.values();
    }

    /**
     * Read a product
     *
     * @request GET /products/{id}
     * @response 404: product not found, 200: returns found product
     */
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> readOne(@PathVariable int id) {
        Product product = products.get(id);

        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(product, HttpStatus.OK);
    }


    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateOne(@PathVariable int id, @RequestBody Product product) {
        if (product.getId() != id)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Product oldProduct = products.put(id, product);
        return new ResponseEntity<>(oldProduct, HttpStatus.OK);
    }


    @DeleteMapping("/products")
    public void deleteAll() {
        products.clear();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteOne(@PathVariable int id) {
        Product product = products.get(id);
        if (product == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        product = products.remove(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
