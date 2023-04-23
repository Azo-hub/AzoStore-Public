package com.AzoStore001.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.AzoStore001.Model.Product;
import com.cloudinary.Cloudinary;
import com.cloudinary.Singleton;
import com.cloudinary.utils.ObjectUtils;



@Service
public class CloudinaryService {
		
		Product product;
		private final Cloudinary cloudinary = Singleton.getCloudinary();
	
	   // @Autowired
	    //private Cloudinary cloudinaryConfig;

	    public String uploadFile(MultipartFile file) {
	        try {
	            File uploadedFile = convertMultiPartToFile(file);
	            Map uploadResult = cloudinary.uploader().upload(uploadedFile, ObjectUtils.emptyMap());
	            return  uploadResult.get("url").toString();
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	    private File convertMultiPartToFile(MultipartFile file) throws IOException {
	    	String name = "product" +product.getId() + ".png";
	        File convFile = new File(name);
	        FileOutputStream fos = new FileOutputStream(convFile);
	        fos.write(file.getBytes());
	        fos.close();
	        return convFile;
	    }

	
	   	    

}
