import javax.imageio.ImageIO;


public class nft {
	String NFTname;
	String NFTDescription;
	String NFTOwner;
	
	public nft(String nFTname, String nFTDescription, String nFTOwner, String imageLink) {
		NFTname = nFTname;
		NFTDescription = nFTDescription;
		NFTOwner = nFTOwner;
		ImageLink = imageLink;
	}
	String ImageLink;
	
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
