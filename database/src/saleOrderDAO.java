import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServlet;

public class saleOrderDAO {
	
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public saleOrderDAO(){}
	
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
    
    public void insert(saleOrder saleOrders) throws SQLException {
    	System.out.println("In saleListingDAO insert() method");
    	connect_func();   
    	
    	//insert sale order into sale_order table
		String sql = "insert into sale_orders(saleID, sale_Listing, nftToBeSold, nftSeller, soldTo, dateSold) values (?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, null);
		preparedStatement.setInt(2, saleOrders.getSale_listing());
		preparedStatement.setInt(3, saleOrders.getNftToBeSold());
		preparedStatement.setString(4, saleOrders.getNftSeller());
		preparedStatement.setString(5, saleOrders.getSoldTo());
		preparedStatement.setObject(6, java.sql.Date.valueOf(saleOrders.getDateSold()));
		preparedStatement.executeUpdate();
        preparedStatement.close();
        
        disconnect();
     
    }
    
    public void transferNftOwnership(saleOrder saleOrders) throws SQLException {
    	System.out.println("In saleOrderDAO transferNFTOwnership() method");
    	connect_func();
    	   
    	String nftID = String.valueOf(saleOrders.getNftToBeSold());
    	String sqlGetNFT = "select * from NFT where NFTID=" + "\"" + nftID + "\""; //Get the NFTID from the NFT Table
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetNFT, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
        //Executing the query
        ResultSet rs = preparedStatement.executeQuery();
        
        //Updating the owner of the NFT to the new who it was transferred to
        if(rs.next()) {
                         
          //Updating the owner
          rs.updateString("nftOwner", saleOrders.getSoldTo());
          //Updating the row
          rs.updateRow();
           
        }
    	
        preparedStatement.close();
        disconnect();
    }
    
    public void exchangeEth(saleOrder saleOrders) throws SQLException {
    	System.out.println("In saleListingDAO exchangeEth() method");
    	connect_func();
    	
    	//get nft Price
    	double getNftPrice = 0.00;
    	
    	String sqlGetNftPrice = "select price from sale_listings where nftListed=" + saleOrders.getNftToBeSold();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetNftPrice);
		
		resultSet = preparedStatement.executeQuery();
		
		if(resultSet.next()) {
			getNftPrice = resultSet.getDouble("price");
		}
        preparedStatement.close();
        
        // Subtract price from buyer's balance
        String subPrice = "select * from User where email=" + "\"" + saleOrders.getSoldTo() + "\""; 
		preparedStatement = (PreparedStatement) connect.prepareStatement(subPrice, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
        //Executing the query
        ResultSet rs = preparedStatement.executeQuery();
        
        if(rs.next()) {
           double newBalance = rs.getDouble("balance") - getNftPrice;
           
          //Updating the buyers's balance
          rs.updateDouble("balance", newBalance);
          //Updating the row
          rs.updateRow();
           
        }
        preparedStatement.close();
        
        // Add price amount to seller's balance
        String addPrice = "select * from User where email=" + "\"" + saleOrders.getNftSeller() + "\""; 
  		preparedStatement = (PreparedStatement) connect.prepareStatement(addPrice, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
  		
          //Executing the query
          ResultSet rs2 = preparedStatement.executeQuery();
          
          if(rs2.next()) {
             double newBalance = rs2.getDouble("balance") + getNftPrice;
             
            //Updating the sellers's balance
            rs2.updateDouble("balance", newBalance);
            //Updating the row
            rs2.updateRow();
             
          }
          preparedStatement.close();
          
          disconnect();
    }
    
   public saleOrder createSaleOrderByNftName(String nftName, String nftBuyer)throws SQLException {
  		System.out.println("In saleOrderDAO createSaleOrderByNftName() method");
	   // get nftID from nftName
    	connect_func();   
    	
    	String sqlGetNFTID = "select nftID from NFT where nftName=" + "\"" + nftName + "\"";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetNFTID);
		
		resultSet = preparedStatement.executeQuery();
		int nftId = 0;
		if(resultSet.next()) {
			nftId = resultSet.getInt("nftID");
		}
        preparedStatement.close();

	   //get saleListing ID and seller from sale_listings
    	String sql = "select listID,nftSeller from sale_listings where nftListed=" + nftId;
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		
		resultSet = preparedStatement.executeQuery();
		int listId = 0;
		String nftSeller = null;
		if(resultSet.next()) {
			listId = resultSet.getInt("listID");
			nftSeller = resultSet.getString("nftSeller");
		}
        preparedStatement.close();
	   
        // get the current date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate localDate = LocalDate.now();
        resultSet.close();

        disconnect();
       // return a new saleOrderObject 
	   return new saleOrder(listId, nftId, nftSeller, nftBuyer, dtf.format(localDate));
   }
}
    

