import javax.imageio.ImageIO;


public class nft {
	protected int nftId;
	protected String NFTname;
	protected String NFTDescription;
	protected String NFTOwner;
	protected String createdBy;
	protected String dateCreated;
	protected String ImageLink;
	protected int num;

	
	
	public nft(String nFTname, String nFTDescription, String nFTOwner, String createdBy, String dateCreated, String imageLink) {
		super();
		NFTname = nFTname;
		NFTDescription = nFTDescription;
		NFTOwner = nFTOwner;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		ImageLink = imageLink;
	}
	
	public nft(int nftId, String nFTname, String nFTDescription, String nFTOwner, String createdBy, String dateCreated,
			String imageLink) {
		super();
		this.nftId = nftId;
		NFTname = nFTname;
		NFTDescription = nFTDescription;
		NFTOwner = nFTOwner;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
		ImageLink = imageLink;
	}

	public nft(int nftId, String nFTname, String nFTDescription, String nFTOwner, String imageLink) {
		super();
		this.nftId = nftId;
		NFTname = nFTname;
		NFTDescription = nFTDescription;
		NFTOwner = nFTOwner;
		ImageLink = imageLink;
	}

	public nft(String nFTname, String nFTDescription, String nFTOwner, String imageLink) {
		NFTname = nFTname;
		NFTDescription = nFTDescription;
		NFTOwner = nFTOwner;
		ImageLink = imageLink;
	}
	
	public nft(String createdBy, int num) {
		super();
		this.createdBy = createdBy;
		this.num = num;
	}
	
	public nft(String nFTname) {
		super();
		NFTname = nFTname;
	}
		
	public int getNftId() {
		return nftId;
	}

	public void setNftId(int nftId) {
		this.nftId = nftId;
	}

	public String getNFTname() {
		return NFTname;
	}
	public void setNFTname(String nFTname) {
		NFTname = nFTname;
	}
	public String getNFTDescription() {
		return NFTDescription;
	}
	public void setNFTDescription(String nFTDescription) {
		NFTDescription = nFTDescription;
	}
	public String getNFTOwner() {
		return NFTOwner;
	}
	public void setNFTOwner(String nFTOwner) {
		NFTOwner = nFTOwner;
	}
	public String getImageLink() {
		return ImageLink;
	}
	public void setImageLink(String imageLink) {
		ImageLink = imageLink;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	
}
