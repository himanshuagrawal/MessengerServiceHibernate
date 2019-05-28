package org.himmy.messenger.MessengerService;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "Students")
@NamedQuery(name = "getstudentbyid", query = "from Student where rollNum=:rollnum")
public class Student {


	@Id @GeneratedValue
	@Column(name = "RollNo")
	private int rollNum;
	private String name;
	
	//private Surname surname;
	
//	@OneToMany(cascade=CascadeType.PERSIST)
//	List<Laptop> laptops = new ArrayList<Laptop>();
//
//	
//	public List<Laptop> getLaptops() {
//		return laptops;
//	}
//
//	
//	public void setLaptops(List<Laptop> laptops) {
//		this.laptops = laptops;
//	}
//	public Surname getSurname() {
//		return surname;
//	}
//
//	public void setSurname(Surname surname) {
//		this.surname = surname;
//	}

	public Student() {
	}

	@Override
	public String toString() {
		return "Student [rollNum=" + rollNum + ", name=" + name + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRollNum() {
		return rollNum;
	}

	public void setRollNum(int rollNum) {
		this.rollNum = rollNum;
	}

	public Student( String name) {
		super();
		
		this.name = name;
	}







	

}
