import java.sql.Connection;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServlet;

public class saleListingDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public saleListingDAO(){}
	
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
    
    public void insert(saleListing saleListings) throws SQLException {
    	System.out.println("In saleListingDAO insert() method");
    	connect_func();   
    	
    	String sqlGetNFTID = "select nftID from NFT where nftName=" + "\"" + saleListings.getNftListed() + "\""; //Get the NFTID from the NFT Table
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetNFTID);
		
		resultSet = preparedStatement.executeQuery();
		int nftId = 0;
		if(resultSet.next()) {
			nftId = resultSet.getInt("nftID");
		}
		
		System.out.print("This is the nftID of this NFT: ");
		System.out.println(nftId);
		
		
		String sql = "insert into sale_listings(listID, nftListed, nftSeller, price, datePosted, endingDate, nftSold) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, null);
		preparedStatement.setInt(2, nftId);
		preparedStatement.setString(3, saleListings.getNftSeller());
		preparedStatement.setDouble(4, saleListings.getPrice());
		System.out.println("prePared statement for dates");
		preparedStatement.setDate(5, java.sql.Date.valueOf(saleListings.getDatePosted()));
		preparedStatement.setObject(6, java.sql.Date.valueOf(saleListings.getEndingDate()));
		preparedStatement.setInt(7,  0); // 0 means nftSold = 0, nft has not sold
		preparedStatement.executeUpdate();
        preparedStatement.close();
        
    }
    
    public saleListing doesSaleListingExist(int nftId) throws SQLException {
    	connect_func();
    	System.out.println("In doesSaleListingExist() in saleListingDAO class");
    	//saleListing listingNotSold = getSaleListingInfoByName(nftId);

    	
    	String sql = "select * from sale_listings where nftListed=" + nftId; //Get the NFTID from the NFT Table
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		
		//int count = 0;
		int sold = -1;
		int listId = -1;
    	while(resultSet.next()) {
    		sold = resultSet.getInt("nftSold");
    		listId = resultSet.getInt("listID");
    		if (sold == 0) {
    			preparedStatement.close();
    			saleListing saleListings = getSaleListingInfoByListId(listId);
    			return saleListings;
    		}
    	}
    	
    	
    	
        preparedStatement.close();
    	return null;
    }
    
    public saleListing getSaleListingInfoByListId(int listId) throws SQLException {
    	connect_func();
    	System.out.println("In getSaleListingInfoByName() in saleListingDAO class");
    	
    	String sqlGetNFTID = "select * from sale_listings where listID=" + listId; //Get the NFTID from the NFT Table
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetNFTID);
		
		resultSet = preparedStatement.executeQuery();
		String nftListed = null;
		String nftSeller = null;
		double nftPrice = -1.00;
		int nftSold = 0;
		String startDate = null;
		String endDate = null;
		
		if(resultSet.next()) {
			nftListed = resultSet.getString("nftListed");
			nftSeller = resultSet.getString("nftSeller");
			nftPrice = resultSet.getDouble("price");
			nftSold = resultSet.getInt("nftSold");	
			Date datePosted = resultSet.getDate("datePosted");
			Date endingDate = resultSet.getDate("endingDate");
			
			startDate = datePosted.toString();
			endDate = endingDate.toString();
		}
		
		preparedStatement.close();
        return new saleListing(listId, nftListed, nftSeller, nftPrice, startDate, endDate, nftSold );
    	
    }
    
    public void updateNftSold(saleOrder saleOrders) throws SQLException {
    	 // Subtract price from buyer's balance
    	System.out.println("In updateNftSold() in saleListingDAO class");
        String updateSold = "select * from sale_listings where listID=" + saleOrders.getSale_listing(); //get the sale listing to update the sold to 1 (true)
		preparedStatement = (PreparedStatement) connect.prepareStatement(updateSold, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
        //Executing the query
        ResultSet rs = preparedStatement.executeQuery();
        
        if(rs.next()) {
           int sold = rs.getInt("nftSold");
           if (sold == 0) {
        	   System.out.println("In changing sold to true");
        	   rs.updateByte("nftSold", (byte) 1);
        	   rs.updateRow();
           }
           
        }
        preparedStatement.close();
    }

}
