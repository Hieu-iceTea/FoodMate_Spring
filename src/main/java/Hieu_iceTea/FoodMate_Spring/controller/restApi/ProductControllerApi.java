package Hieu_iceTea.FoodMate_Spring.controller.restApi;

import Hieu_iceTea.FoodMate_Spring.model.Product;
import Hieu_iceTea.FoodMate_Spring.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/restApi/products")
public class ProductControllerApi {

    //TODO: Sửa lỗi vòng lặp đệ quy khi xử lý JSON, lý do relationship giữa các bảng.
    //Video hướng dẫn của cô ThiDK: http://youtube.com/watch?v=pMxgLOPe_OE

    //region - Autowired Service -
    @Autowired
    private ProductService productService;
    //endregion


    //region - Display -
    @GetMapping(path = {"", "/", "/index"})
    public List<Product> index(@RequestParam(required = false) String search) {

        return productService.getAll(search);

    }

    @GetMapping(path = {"/{id}/", "/{id}"})
    public Product show(@PathVariable int id) {

        return productService.findById(id);

    }
    //endregion
}
