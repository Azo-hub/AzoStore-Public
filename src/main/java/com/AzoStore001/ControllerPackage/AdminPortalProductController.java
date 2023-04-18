package com.AzoStore001.ControllerPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AzoStore001.ModelPackage.Product;
import com.AzoStore001.RepositoryPackage.ProductRepository;
import com.AzoStore001.ServicePackage.ProductService;
import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;

import jakarta.servlet.http.HttpServletRequest;





@Controller
@RequestMapping("/adminportal")
public class AdminPortalProductController {
	
	private final Cloudinary cloudinary = Singleton.getCloudinary();
	private Api api;
	String version = "";
	
	//@Autowired
    //private CloudinaryService cloudinaryService;

	
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
			File file = new File(name);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(bytes);
	        fos.close();
			Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate",true));
            String publicId = uploadResult.get("public_id").toString();
            /*version += "/v"+ uploadResult.get("version").toString();
            model.addAttribute("version",version);*/
            cloudinary.uploader().rename(publicId, name, 
            		  ObjectUtils.asMap("invalidate",true));
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

		
		return "redirect:productList";

	}
	
	
	
	/*
	 * 
    @PostMapping("/upload")
    public String uploadFile() {
        
        return "File uploaded successfully: File path :  " + url;
    }
	 */

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
		
		/*ApiResponse result = api.resources(ObjectUtils.asMap(
				  "type", "upload", 
				  "prefix", "product"+product.getId()));
		
		model.addAttribute("result", result); 
		
		version += "";
		model.addAttribute("version", version);
		model.addAttribute("cloudinary", cloudinary);*/
		
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
	           /* version += "/v"+ uploadResult.get("version").toString();
	            model.addFlashAttribute("version",version);*/
	            cloudinary.uploader().rename(publicId, name, 
	            		  ObjectUtils.asMap("invalidate",true));
	            
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }

		}

		return "redirect:/adminportal/productInfo/" + product.getId();

	}

	/*
	 * @PostMapping("/remove") public String remove(@ModelAttribute("id") String id,
	 * Model model) {
	 * 
	 * productService.removeOne(Long.parseLong(id.substring(8))); List<Product>
	 * productList = productService.findAll(); model.addAttribute("productList",
	 * productList);
	 * 
	 * return "redirect:/product/productList";
	 * 
	 * 
	 * }
	 * 
	 */

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
		
		
		// List<Product> productList = productService.findAll();
		// model.addAttribute("productList", productList);

		return "redirect:/adminportal/product/productList";

	}
	
	
	

}
