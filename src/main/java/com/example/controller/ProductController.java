package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.example.customeResponse.CustomResponse;
import com.example.dto.ProductDto;
import com.example.entity.Product;
import com.example.entity.ProductImage;
import com.example.exception.ProductNotFoundException;
import com.example.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private IProductService productService;

	private String code;

	private Object data;

//	@Autowired
//	private AmazonS3 amazonS3;
//
//	@Value("${bucketName}")
//	private String bucketname;

	@Autowired
    private ModelMapper modelMapper;
    
   
	// **********************************************Upload single file********************************************************************//
	
	@SuppressWarnings("finally")
	@PostMapping(path = "upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadProductImage1( Product product, @RequestParam("file") MultipartFile file) {
		try {

			Product product1 = productService.uploadImage(product, file);
			data = product1;
			code = "CREATED";
		} catch (AmazonServiceException e) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception e) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomResponse.response(code, data);
		}
	}

	// *****************************************************upload Multiple File************************************************************************//

	@SuppressWarnings("finally")
	@PostMapping(path = "uploadImg", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadProductImages1(Product product) {
		try {
			Product productImages = productService.uploadImages(product);
			data = productImages;
			code = "CREATED";
		} catch (ProductNotFoundException e) {
			data = null;
			code = "DATA_NOT_FOUND";
		} catch (AmazonServiceException e) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception e) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomResponse.response(code, data);
		}
	}

	// *************************************************Delete Image By ProductCode From S3 Bucket*********************************************************************//

	@DeleteMapping("/deleteByCode/{productCode}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productCode) {
		try {
			String product = productService.deleteFilesByProductCode(productCode);

			data = product;
			code = "SUCCESS";
		} catch (ProductNotFoundException e) {
			data = null;
			code = "DATA_NOT_FOUND";
		} catch (AmazonServiceException e) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception e) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomResponse.response(code, data);
		}
	}    
	
	// *************************************************Delete Image By ProductName From S3 Bucket*********************************************************************//
	
	@DeleteMapping("/deleteByName/{productName}")
	public ResponseEntity<?> deleteProduct(@PathVariable String productName) {
		try {
			String product = productService.deleteFilesByProductName(productName);

			data = product;
			code = "SUCCESS";
		} catch (ProductNotFoundException e) {
			data = null;
			code = "DATA_NOT_FOUND";
		} catch (AmazonServiceException e) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception e) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomResponse.response(code, data);
		}
	}

    //add one product to table
    @PostMapping
    public ResponseEntity<Object> addOneProduct(@Valid @ModelAttribute Product product) {
        try {
            Product product1 = productService.createProduct(product);
            data = product1;
            code = "CREATED";
        } catch (ProductNotFoundException productNotFoundException) {
            data =null; code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data =null; code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data =null; code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    //add list of product to table
    @PostMapping(value = "/list", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createMultipleProduct(@Validated @RequestBody List<Product> product) {
        try {
            List<Product> productList = productService.createProductList(product);
            data = productList;
            code = "CREATED";
        } catch (ProductNotFoundException productNotFoundException) {
            data =null;  code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data =null; code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data =null; code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    //modify one product by id
    @PutMapping(value = "{productCode}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> updateOneProduct(@Validated @PathVariable Integer productCode, @RequestBody Product product) {
        try {
            Product product1 = productService.updateProduct(productCode, product);
            data = product1;
            code = "CREATED";
        } catch (ProductNotFoundException productNotFoundException) {
            data =null; code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data =null;  code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data =null; code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    //partial update by id
    @PatchMapping(value = "{productcode}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> partialUpdate(@Valid @PathVariable Integer productcode, @RequestBody Map<String, Object> update) {
        try {
            Product product = productService.partialUpdate(productcode, update);
            data = product;
            code = "CREATED";
        } catch (ProductNotFoundException productNotFoundException) {
            data =null; code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data =null; code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data =null; code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    //delete product by using id
    @DeleteMapping(value = "{productCode}")
    public ResponseEntity<Object> deleteOneProduct(@Validated @PathVariable Integer productCode) {
        try {
            String product = productService.deleteProductById(productCode);
            data = product;
            code = "SUCCESS";
        } catch (ProductNotFoundException productNotFoundException) {
            data =null; code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data =null; code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data =null; code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    //delete all products
    @DeleteMapping
    public ResponseEntity<Object> deleteAllProduct() {
        try {
            String products = productService.deleteAllProducts();
            data = products;
            code = "SUCCESS";
        } catch (ProductNotFoundException productNotFoundException) {
            data =null; code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data =null; code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data =null; code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }

    //fetch product by id
    @GetMapping(value = "{productCode}")
    public ResponseEntity<Object> getOneProduct(@PathVariable Integer productCode) {
        try {
            Product oneProduct = productService.getProductById(productCode);
            data = oneProduct;
            code = "SUCCESS";
        } catch (ProductNotFoundException productNotFoundException) {
            data =null; code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data =null;code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data =null;code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }


    //fetch all products
    @GetMapping
    public ResponseEntity<Object> getAllProduct() {
        try {
            List<ProductDto> products = productService.getAllProducts().stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            data = products;
            code = "SUCCESS";
        } catch (ProductNotFoundException productNotFoundException) {
            code = "DATA_NOT_FOUND"; data =null;
        } catch (RuntimeException runtimeException) {
            code = "RUNTIME_EXCEPTION"; data =null;
        } catch (Exception exception) {
            code = "EXCEPTION"; data =null;
        } finally {
            return CustomResponse.response(code, data);
        }
    }


    //sort products according to ascending order
    @GetMapping("sort")
    public ResponseEntity<Object> getAllProductSorted() {
        try {
            List<ProductDto> products = productService.getAllSortedByProduct().stream().map(product -> modelMapper.map(product, ProductDto.class)).collect(Collectors.toList());
            data = products;
            code = "SUCCESS";
        } catch (ProductNotFoundException productNotFoundException) {
            code = "DATA_NOT_FOUND"; data =null;
        } catch (RuntimeException runtimeException) {
            code = "RUNTIME_EXCEPTION"; data =null;
        } catch (Exception exception) {
            code = "EXCEPTION"; data =null;
        } finally {
            return CustomResponse.response(code, data);
        }
    }


    //search product by productName
    @GetMapping("search/{productName}")
    public ResponseEntity<Object> searchByproduct(@PathVariable String productName) {
        try {
            List<Product> products = productService.searchByProductName(productName);
            data = products;
            code = "SUCCESS";
        } catch (ProductNotFoundException productNotFoundException) {
            code = "DATA_NOT_FOUND"; data =null;
        } catch (RuntimeException runtimeException) {
            code = "RUNTIME_EXCEPTION"; data =null;
        } catch (Exception exception) {
            code = "EXCEPTION"; data =null;
        } finally {
            return CustomResponse.response(code, data);
        }
    }


    //update price of product (increase/decrease)
    @PostMapping("{productCode}/price/{value}/{amount}")
    public ResponseEntity<Object> updatePrice(@PathVariable Integer productCode, @PathVariable String value, @PathVariable Double amount) {
        try {
            Product products = productService.incrementDecrementProductPrice(productCode, value, amount);
            data = products;
            code = "SUCCESS";
        } catch (ProductNotFoundException productNotFoundException) {
            code = "DATA_NOT_FOUND"; data =null;
        } catch (RuntimeException runtimeException) {
            code = "RUNTIME_EXCEPTION"; data =null;
        } catch (Exception exception) {
            code = "EXCEPTION"; data =null;
        } finally {
            return CustomResponse.response(code, data);
        }
    }


    //update quantity of product (increase/decrease)
    @PostMapping("{productCode}/quantity/{value}/{quantity}")
    public ResponseEntity<Object> updateQuantityInStock(@PathVariable Integer productCode, @PathVariable String value, @PathVariable Integer quantity) {
        try {
            Product products = productService.incrementDecrementProductQuantityInStock(productCode, value, quantity);
            data = products;
            code = "SUCCESS";
        } catch (ProductNotFoundException productNotFoundException) {
            data =null;  code = "DATA_NOT_FOUND";
        } catch (RuntimeException runtimeException) {
            data =null; code = "RUNTIME_EXCEPTION";
        } catch (Exception exception) {
            data =null; code = "EXCEPTION";
        } finally {
            return CustomResponse.response(code, data);
        }
    }
    
    @PostMapping("/createlist")
    public String createProductList() {
        productService.createProductsList();
        return "created";
    }

}