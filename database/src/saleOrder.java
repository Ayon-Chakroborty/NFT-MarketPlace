
public class saleOrder {
	protected int sale_listing;
	protected int nftToBeSold;
	protected String nftSeller;
	protected String soldTo;
	protected String dateSold;
	
	public saleOrder(int sale_listing, int nftToBeSold, String nftSeller, String soldTo, String dateSold) {
		super();
		this.sale_listing = sale_listing;
		this.nftToBeSold = nftToBeSold;
		this.nftSeller = nftSeller;
		this.soldTo = soldTo;
		this.dateSold = dateSold;
	}

	public int getSale_listing() {
		return sale_listing;
	}

	public void setSale_listing(int sale_listing) {
		this.sale_listing = sale_listing;
	}

	public int getNftToBeSold() {
		return nftToBeSold;
	}

	public void setNftToBeSold(int nftToBeSold) {
		this.nftToBeSold = nftToBeSold;
	}

	public String getNftSeller() {
		return nftSeller;
	}

	public void setNftSeller(String nftSeller) {
		this.nftSeller = nftSeller;
	}

	public String getSoldTo() {
		return soldTo;
	}

	public void setSoldTo(String soldTo) {
		this.soldTo = soldTo;
	}

	public String getDateSold() {
		return dateSold;
	}

	public void setDateSold(String dateSold) {
		this.dateSold = dateSold;
	}
	
	
}
