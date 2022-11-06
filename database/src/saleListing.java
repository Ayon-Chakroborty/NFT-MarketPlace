
public class saleListing {
	
	String nftListed;
	String nftSeller;
	double price;
	String datePosted;
	String endingDate;
	
	public saleListing(String nftListed, String nftSeller, double price, String datePosted, String endingDate) {
		super();
		this.nftListed = nftListed;
		this.nftSeller = nftSeller;
		this.price = price;
		this.datePosted = datePosted;
		this.endingDate = endingDate;
	}

	public String getNftListed() {
		return nftListed;
	}

	public void setNftListed(String nftListed) {
		this.nftListed = nftListed;
	}

	public String getNftSeller() {
		return nftSeller;
	}

	public void setNftSeller(String nftSeller) {
		this.nftSeller = nftSeller;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}

	public String getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}
	
	

}
