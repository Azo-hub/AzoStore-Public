package com.AzoStore001.Model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class persistent_logins {
	private String username;
	
	@Id
	private String series;
	
	private String token;
	
	@Basic(optional = false)
	@Column(name = "last_used", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date last_used;

}
