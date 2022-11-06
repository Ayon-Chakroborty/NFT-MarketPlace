import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;

public class transferOrderDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public transferOrderDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/NFTDB?allowPublicKeyRetrieval=true&useSSL=false&user=john&password=john1234");
            System.out.println(connect);
        }
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(transferOrder transferOrders) throws SQLException {
    	System.out.println("In transferOrdersDAO insert() method");
    	connect_func();   
    	
    	//String sql = "insert into sale_listings(listID, nftListed, nftSeller, price, datePosted, endingDate) values (?, ?, ?, ?, ?, ?)";
		//preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
    	
    	int nftId = getNftId(transferOrders);
    	
		String nftFrom = transferOrders.getTransferredFrom();
		String nftTo = transferOrders.getTransferredTo();
    	
		String sql = "INSERT INTO transfer_orders (transferID, nftToBeTransferred, nftOwner, transferredTo, dateTransferred) VALUES (?, ?, ?, ?, ?)";

		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, null);
		preparedStatement.setInt(2, nftId);
		preparedStatement.setString(3, nftFrom);
		preparedStatement.setString(4, nftTo);
		preparedStatement.setObject(5, java.sql.Date.valueOf(transferOrders.getDateTransferred()));
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public void transferNFT(transferOrder transferOrders) throws SQLException{
    	System.out.println("In transferOrdersDAO transferNFT() method");
    	
    	String nftID = String.valueOf(getNftId(transferOrders));
    	String sqlGetNFT = "select * from NFT where NFTID=" + "\"" + nftID + "\""; //Get the NFTID from the NFT Table
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetNFT, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
        //Executing the query
        ResultSet rs = preparedStatement.executeQuery();
        
        //Updating the owner of the NFT to the new who it was transferred to
        if(rs.next()) {
                         
          //Updating the owner
          rs.updateString("nftOwner",transferOrders.getTransferredTo());
          //Updating the row
          rs.updateRow();
           
        }
    	
		//preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public int getNftId(transferOrder transferOrders) throws SQLException {
    	
    	System.out.println("In transferOrdersDAO getNftId() method");

    	String sqlGetNFTID = "select NFTID from NFT where nftName=" + "\"" + transferOrders.getNftToBeTransferred() + "\""; //Get the NFTID from the NFT Table
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetNFTID);
		
		resultSet = preparedStatement.executeQuery();
		int nftId = 0;
		if(resultSet.next()) {
			nftId = resultSet.getInt("nftID");
		}
		
		System.out.print("This is the nftID of this NFT: ");
		System.out.println(nftId);
        preparedStatement.close();

	    return nftId;

    }
    

}

