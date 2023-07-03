package com.irina.otto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "IPRANGE")
public class IpRange {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    Long id;
	    
		@Column(name = "ipPrefix")
	    String ipPrefix;
	    
	    @Column(name = "region")
	    String region;
	    
	    @Column(name = "service")
	    String service;
	    
	    @Column(name = "networkBorderGroup")
	    String networkBorderGroup;
	    
	    public IpRange() {
			super();
		}
	    	    
		public IpRange(String ipPrefix, String region, String service, String networkBorderGroup) {
			super();
			this.ipPrefix = ipPrefix;
			this.region = region;
			this.service = service;
			this.networkBorderGroup = networkBorderGroup;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getIpPrefix() {
			return ipPrefix;
		}
		public void setIpPrefix(String ipPrefix) {
			this.ipPrefix = ipPrefix;
		}
		public String getRegion() {
			return region;
		}
		public void setRegion(String region) {
			this.region = region;
		}
		public String getService() {
			return service;
		}
		public void setService(String service) {
			this.service = service;
		}
		public String getNetworkBorderGroup() {
			return networkBorderGroup;
		}
		public void setNetworkBorderGroup(String networkBorderGroup) {
			this.networkBorderGroup = networkBorderGroup;
		}

		@Override
		public String toString() {
			return "<p>IpRange [ipPrefix=" + ipPrefix + ", region=" + region + ", service=" + service
					+ ", networkBorderGroup=" + networkBorderGroup + "]</p><br>";
		}
	    
	    
	    
	}



