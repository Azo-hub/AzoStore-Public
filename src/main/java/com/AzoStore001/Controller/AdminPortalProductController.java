package com.AzoStore001.Controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AzoStore001.Model.Product;
import com.AzoStore001.Repository.ProductRepository;
import com.AzoStore001.Service.ProductService;
import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;

import jakarta.servlet.http.HttpServletRequest;





@Controller
@RequestMapping("/adminportal")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminPortalProductController {
	
	private final Cloudinary cloudinary = Singleton.getCloudinary();
	String version = "";
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@GetMapping("/product/add")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);

		return "addProduct";
	}

	@PostMapping("/product/add")
	public String addProduct(
			Model model,
			@ModelAttribute("product") Product product, HttpServletRequest request) {

		product.setUploadTime(new Date());
		productService.save(product);
	
		MultipartFile productImage = product.getProductImage();
		
		
		try {
			byte[] bytes = productImage.getBytes();
			String name = "product" +product.getId();
		
			Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate",true));
            String publicId = uploadResult.get("public_id").toString();
          
            cloudinary.uploader().rename(publicId, name, 
            		  ObjectUtils.asMap("invalidate",true));
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

		
		return "redirect:productList";

	}
	
	
	

	@GetMapping("/product/productList")
	public String productList(Model model) {
		List <Product> productList = productRepository.findAll();
		model.addAttribute("productList", productList);
		

		return "adminproductList";
	}

	@GetMapping("/productInfo/{id}")
	public String productInfo(@PathVariable(name = "id") Long id, Model model) {
		Product product = productService.getOne(id);
		model.addAttribute("product", product);
		
		
		return "adminproductInfo";

	}

	@GetMapping("/updateProduct/{id}")

	public String updateProduct(@PathVariable(name = "id") Long id, Model model) {
		Product product = productService.getOne(id);
		model.addAttribute("product", product);
		
		
		return "updateProduct";

	}

	@PostMapping("/updateProduct")
	public String updateProductPost(
			RedirectAttributes model,
			@ModelAttribute("product") Product product, HttpServletRequest request) {

		productService.save(product);

		MultipartFile productImage = product.getProductImage();

		if (!productImage.isEmpty()) {
			
			
			try {
				byte[] bytes = productImage.getBytes();
				String name = "product" +product.getId();
				cloudinary.uploader().destroy(name, ObjectUtils.asMap("invalidate",true));
				
						  
				File file = new File(name);
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(bytes);
		        fos.close();
				Map uploadResult = cloudinary.uploader().upload(bytes, 
						ObjectUtils.asMap(
								"invalidate",true));
	            String publicId = uploadResult.get("public_id").toString();
	    
	            cloudinary.uploader().rename(publicId, name, 
	            		  ObjectUtils.asMap("invalidate",true));
	            
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }

		}

		return "redirect:/adminportal/productInfo/" + product.getId();

	}

	
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id,
			@ModelAttribute("product") Product product) {
		
		productService.removeOne(id);
		
		try {
			String name = "product" +product.getId();
			cloudinary.uploader().destroy(name,
					  ObjectUtils.emptyMap());
		}
		catch (Exception e) {
            throw new RuntimeException(e);
			
		}
		
	
		return "redirect:/adminportal/product/productList";

	}
	
	
	

}
