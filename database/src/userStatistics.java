
public class userStatistics {

	protected String email;
	protected int buys;
	protected int sells;
	protected int transfers;
	protected int nftsOwned;
	
	public userStatistics(String email, int buys, int sells, int transfers, int nftsOwned) {
		super();
		this.email = email;
		this.buys = buys;
		this.sells = sells;
		this.transfers = transfers;
		this.nftsOwned = nftsOwned;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getBuys() {
		return buys;
	}

	public void setBuys(int buys) {
		this.buys = buys;
	}

	public int getSells() {
		return sells;
	}

	public void setSells(int sells) {
		this.sells = sells;
	}

	public int getTransfers() {
		return transfers;
	}

	public void setTranfers(int tranfers) {
		this.transfers = tranfers;
	}

	public int getNftsOwned() {
		return nftsOwned;
	}

	public void setNftsOwned(int nftsOwned) {
		this.nftsOwned = nftsOwned;
	}
	
	
	
}
