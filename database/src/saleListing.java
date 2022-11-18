
public class saleListing {
	
	protected String nftListed;
	protected String nftSeller;
	protected double price;
	protected String datePosted;
	protected String endingDate;
	protected int nftSold;
	

	public saleListing(String nftListed, String nftSeller, double price, String datePosted, String endingDate, int nftSold) {
		super();
		this.nftListed = nftListed;
		this.nftSeller = nftSeller;
		this.price = price;
		this.datePosted = datePosted;
		this.endingDate = endingDate;
		this.nftSold = nftSold;
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
	
	public int getNftSold() {
		return nftSold;
	}

	public void setNftSold(int nftSold) {
		this.nftSold = nftSold;
	}
	
	

}
