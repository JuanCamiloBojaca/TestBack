package com.example.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Bus")
public class Bus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "type", length = 100)
	private String type;

	@Column(name = "motor", length = 50)
	private String motor;

	@Column(name = "brakes", length = 100)
	private String brakes;

	@ManyToOne
	@JoinColumn(name = "concessionaireId", nullable = true)
	private Concessionaire concessionaire;

	@OneToMany(mappedBy = "bus", cascade = CascadeType.REMOVE)
	private Set<Device> devices;

	public Bus() {
	}

	public Bus(String type, String motor, String brakes, Concessionaire concessionaire) {
		this.type = type;
		this.motor = motor;
		this.brakes = brakes;
		this.concessionaire = concessionaire;
	}

	public Integer getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMotor() {
		return motor;
	}

	public void setMotor(String motor) {
		this.motor = motor;
	}

	public String getBrakes() {
		return brakes;
	}

	public void setBrakes(String brakes) {
		this.brakes = brakes;
	}

	public Concessionaire getConcessionaire() {
		return concessionaire;
	}

	public void setConcessionaire(Concessionaire concessionaire) {
		this.concessionaire = concessionaire;
	}

	public Set<Device> devices() {
		return devices;
	}

}
