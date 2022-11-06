import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		
		
		String sql = "insert into sale_listings(listID, nftListed, nftSeller, price, datePosted, endingDate) values (?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, null);
		preparedStatement.setInt(2, nftId);
		preparedStatement.setString(3, saleListings.getNftSeller());
		preparedStatement.setDouble(4, saleListings.getPrice());
		System.out.println("prePared statement for dates");
		preparedStatement.setDate(5, java.sql.Date.valueOf(saleListings.getDatePosted()));
		preparedStatement.setObject(6, java.sql.Date.valueOf(saleListings.getEndingDate()));

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
