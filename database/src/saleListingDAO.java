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
    	connect_func();         
		String sql = "insert into nft(listID, nftListed, nftSeller, price, datePosted, endingDate) values (?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, null);
		preparedStatement.setString(2, saleListings.getNftListed());
		preparedStatement.setString(3, saleListings.getNftSeller());
		preparedStatement.setDouble(4, saleListings.getPrice());
		preparedStatement.setObject(5, LocalDate.parse(saleListings.getDatePosted(), DateTimeFormatter.ofPattern("dd/MM/uuuu")));
		preparedStatement.setObject(6, LocalDate.parse(saleListings.getEndingDate(), DateTimeFormatter.ofPattern("dd/MM/uuuu")));

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
