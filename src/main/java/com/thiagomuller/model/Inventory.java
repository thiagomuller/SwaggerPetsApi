package com.thiagomuller.model;

public class Inventory {
	private int availablePetsQty;
	
	private int pendingPetsQty;
	
	private int soldPetsQty;

	public Inventory(int availablePetsQty, int pendingPetsQty, int soldPetsQty) {
		this.availablePetsQty = availablePetsQty;
		this.pendingPetsQty = pendingPetsQty;
		this.soldPetsQty = soldPetsQty;
	}

	public int getAvailablePetsQty() {
		return availablePetsQty;
	}

	public void setAvailablePetsQty(int availablePetsQty) {
		this.availablePetsQty = availablePetsQty;
	}

	public int getPendingPetsQty() {
		return pendingPetsQty;
	}

	public void setPendingPetsQty(int pendingPetsQty) {
		this.pendingPetsQty = pendingPetsQty;
	}

	public int getSoldPetsQty() {
		return soldPetsQty;
	}

	public void setSoldPetsQty(int soldPetsQty) {
		this.soldPetsQty = soldPetsQty;
	}
	
	
	
	
}
