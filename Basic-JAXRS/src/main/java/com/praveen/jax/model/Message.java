package com.praveen.jax.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Message {

	private long EmpID;
	private String Name;
	private  java.util.Date Created;
	private String EmailID;
	private List<Link> links = new ArrayList<>();


	public  Message() {

	}


	public  Message(long EmpID,String Name, String EmailID) {

		this.EmpID=EmpID;
		this.Name=Name;
		this.EmailID=EmailID;
		this.Created= new  java.util.Date();

	}


	
	public List<Link> getLinks() {
		return links;
	}


	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public long getEmpID() {
		return EmpID;
	}


	public void setEmpID(long empID) {
		EmpID = empID;
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public java.util.Date getCreated() {
		return Created;
	}


	public void setCreated(java.util.Date created) {
		Created = created;
	}


	public String getEmailID() {
		return EmailID;
	}


	public void setEmailID(String emailID) {
		EmailID = emailID;
	}



	public void addLinks(String url, String rel) {

		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		links.add(link);

	}


}
