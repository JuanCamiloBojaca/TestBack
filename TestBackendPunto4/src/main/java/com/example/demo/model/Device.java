package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Device")
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "ip", length = 100)
	private String ip;

	@Column(name = "status", length = 20)
	private String status;

	@ManyToOne()
	@JoinColumn(name = "busId", nullable = false)
	private Bus bus;

	@ManyToOne()
	@JoinColumn(name = "deviceTypeId", nullable = true)
	private DeviceType deviceType;

	public Device() {

	}

	public Device(Integer id, String ip, String status) {
		this.id = id;
		this.ip = ip;
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getId() {
		return id;
	}

}
