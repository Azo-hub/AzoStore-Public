package AzoStore.ControllerPackage;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.api.ApiResponse;
import com.cloudinary.utils.ObjectUtils;

import AzoStore.ModelPackage.Product;
import AzoStore.RepositoryPackage.CartItemRepository;
import AzoStore.RepositoryPackage.ProductRepository;
import AzoStore.RepositoryPackage.ProductToCartItemRepository;
import AzoStore.ServicePackage.CloudinaryService;
import AzoStore.ServicePackage.ProductService;

@Controller
@RequestMapping("/adminportal")
public class AdminPortalProductController {
	
	
	@Autowired
	private ProductToCartItemRepository productToCartItemRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	
	private final Cloudinary cloudinary = Singleton.getCloudinary();
	private Api api;
	String version = "";

	// @Autowired
	// private CloudinaryService cloudinaryService;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductService productService;

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EDITOR')")
	@GetMapping("/product/add")
	public String addProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);

		return "addProduct";
	}

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EDITOR')")
	@PostMapping("/product/add")
	public String addProduct(Model model, @ModelAttribute("product") Product product, HttpServletRequest request) {

		product.setUploadTime(new Date());
		productService.save(product);

		MultipartFile productImage = product.getProductImage();

		try {
			byte[] bytes = productImage.getBytes();
			String name = "product" + product.getId();
			File file = new File(name);
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(bytes);
			fos.close();
			Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));
			String publicId = uploadResult.get("public_id").toString();
			/*
			 * version += "/v"+ uploadResult.get("version").toString();
			 * model.addAttribute("version",version);
			 */
			cloudinary.uploader().rename(publicId, name, ObjectUtils.asMap("invalidate", true));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return "redirect:productList";

	}

	/*
	 * 
	 * @PostMapping("/upload") public String uploadFile() {
	 * 
	 * return "File uploaded successfully: File path :  " + url; }
	 */

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EDITOR')")
	@GetMapping("/product/productList")
	public String productList(Model model) {
		List<Product> productList = productRepository.findAll();
		model.addAttribute("productList", productList);

		return "adminproductList";
	}

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EDITOR')")
	@GetMapping("/productInfo/{id}")
	public String productInfo(@PathVariable(name = "id") Long id, Model model) {
		Product product = productService.getOne(id);
		model.addAttribute("product", product);

		/*
		 * ApiResponse result = api.resources(ObjectUtils.asMap( "type", "upload",
		 * "prefix", "product"+product.getId()));
		 * 
		 * model.addAttribute("result", result);
		 * 
		 * version += ""; model.addAttribute("version", version);
		 * model.addAttribute("cloudinary", cloudinary);
		 */

		return "adminproductInfo";

	}

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EDITOR')")
	@GetMapping("/updateProduct/{id}")

	public String updateProduct(@PathVariable(name = "id") Long id, Model model) {
		Product product = productService.getOne(id);
		model.addAttribute("product", product);

		return "updateProduct";

	}

	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EDITOR')")
	@PostMapping("/updateProduct")
	public String updateProductPost(RedirectAttributes model, @ModelAttribute("product") Product product,
			HttpServletRequest request) {

		productService.save(product);

		MultipartFile productImage = product.getProductImage();

		if (!productImage.isEmpty()) {

			try {
				byte[] bytes = productImage.getBytes();
				String name = "product" + product.getId();
				cloudinary.uploader().destroy(name, ObjectUtils.asMap("invalidate", true));

				File file = new File(name);
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(bytes);
				fos.close();
				Map uploadResult = cloudinary.uploader().upload(bytes, ObjectUtils.asMap("invalidate", true));
				String publicId = uploadResult.get("public_id").toString();
				/*
				 * version += "/v"+ uploadResult.get("version").toString();
				 * model.addFlashAttribute("version",version);
				 */
				cloudinary.uploader().rename(publicId, name, ObjectUtils.asMap("invalidate", true));

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

	@Secured("ADMIN")
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id, @ModelAttribute("product") Product product) {
		
		

		try {
			String name = "product" + product.getId();
			cloudinary.uploader().destroy(name, ObjectUtils.emptyMap());
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
		
		
		product = productService.getOne(id);
		productToCartItemRepository.deleteByProduct(product);
		cartItemRepository.deleteByProduct(product);
		productService.removeOne(id);
		

		// List<Product> productList = productService.findAll();
		// model.addAttribute("productList", productList);

		return "redirect:/adminportal/product/productList";

	}

}
