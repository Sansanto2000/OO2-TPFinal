package com.demo.model;

import com.demo.util.waytopay.WayToPayDict;

import java.util.Objects;

import com.demo.util.deliverymechanism.DeliveryMechanismDict;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ORDEN")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int ammount;
	@OneToOne(fetch = FetchType.EAGER)
	private Client client;
	@OneToOne(fetch = FetchType.EAGER)
	private Product product;
	private String deliverymechanism;
	private String waytopay;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	
	public Order(int ammount, Client client, Product product, String deliverymechanism, String waytopay) {
		this.ammount = ammount;
		this.client = client;
		this.product = product;
		this.deliverymechanism = deliverymechanism;
		this.waytopay = waytopay;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", ammount=" + ammount + ", client=" + client + ", product=" + product
				+ ", deliverymechanism=" + deliverymechanism + ", waytopay=" + waytopay + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ammount, client, deliverymechanism, id, product, waytopay);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return ammount == other.ammount && Objects.equals(client, other.client)
				&& Objects.equals(deliverymechanism, other.deliverymechanism) && id == other.id
				&& Objects.equals(product, other.product) && Objects.equals(waytopay, other.waytopay);
	}

	public double calculateTotalCost() {
        Double costo_bruto = this.ammount*this.product.getPrice();
        Double costo_envio = DeliveryMechanismDict.get(deliverymechanism).cost(this);
        Double costo_forma_de_pago = WayToPayDict.get(waytopay).cost(this)*(costo_bruto+costo_envio);
        return costo_bruto+costo_envio+costo_forma_de_pago;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmmount() {
		return ammount;
	}

	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getDeliverymechanism() {
		return deliverymechanism;
	}

	public void setDeliverymechanism(String deliverymechanism) {
		this.deliverymechanism = deliverymechanism;
	}

	public String getWaytopay() {
		return waytopay;
	}

	public void setWaytopay(String waytopay) {
		this.waytopay = waytopay;
	}

}