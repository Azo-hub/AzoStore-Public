package com.AzoStore001.Model;



import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Product {
	
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String brand;
	private String category;
	private double listPrice;
	private double ourPrice;
	private boolean active = true;
	
	@Column(columnDefinition = "text")
	private String description;
	private int inStockNumber;
	
	@Transient
	private MultipartFile productImage;
	
	
	private Date uploadTime;
	
	

}
