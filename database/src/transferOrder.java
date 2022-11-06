
public class transferOrder {
	
	protected String nftToBeTransferred;
	protected String transferredTo;
	protected String transferredFrom;
	protected String dateTransferred;
	
	public transferOrder(String nftToBeTransferred, String transferredTo, String transferredFrom, String dateTransferred) {
		this.nftToBeTransferred = nftToBeTransferred;
		this.transferredTo = transferredTo;
		this.transferredFrom = transferredFrom;
		this.dateTransferred = dateTransferred;
	}

	public String getNftToBeTransferred() {
		return nftToBeTransferred;
	}

	public void setNftToBeTransferred(String nftToBeTransferred) {
		this.nftToBeTransferred = nftToBeTransferred;
	}

	public String getTransferredTo() {
		return transferredTo;
	}

	public void setTransferredTo(String transferredTo) {
		this.transferredTo = transferredTo;
	}

	public String getTransferredFrom() {
		return transferredFrom;
	}

	public void setTransferredFrom(String transferredFrom) {
		this.transferredFrom = transferredFrom;
	}

	public String getDateTransferred() {
		return dateTransferred;
	}

	public void setDateTransferred(String dateTransferred) {
		this.dateTransferred = dateTransferred;
	}
	
	
	
	

	
	

}
