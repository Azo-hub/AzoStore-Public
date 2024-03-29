package com.AzoStore001.Model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Entity
@Data
public class Product  implements Serializable {

	private static final long serialVersionUID = 1L;
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
