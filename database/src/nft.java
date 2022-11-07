import javax.imageio.ImageIO;


public class nft {
	int nftId;
	String NFTname;
	String NFTDescription;
	String NFTOwner;
	String ImageLink;

	
	
	
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
	
	
	
}
