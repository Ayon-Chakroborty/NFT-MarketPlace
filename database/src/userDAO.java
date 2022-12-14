import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/userDAO")
public class userDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public userDAO(){}
	
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
    
    public boolean database_login(String email, String password) throws SQLException{
    	try {
    		connect_func("john","john1234");
    		String sql = "select * from user where email = ?";
    		preparedStatement = connect.prepareStatement(sql);
    		preparedStatement.setString(1, email);
    		ResultSet rs = preparedStatement.executeQuery();
    		return rs.next();
    	}
    	catch(SQLException e) {
    		System.out.println("failed login");
    		return false;
    	}
    }
	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/userdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
        }
    }
    
    public List<user> listAllUsers() throws SQLException {
        List<user> listUser = new ArrayList<user>();        
        String sql = "SELECT * FROM User";      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            String email = resultSet.getString("email");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            String password = resultSet.getString("userPass");
            String birthday = resultSet.getString("birthday");
            String address = resultSet.getString("address");
            String address_city = resultSet.getString("address_city"); 
            String address_state = resultSet.getString("address_state"); 
            String address_zip_code = resultSet.getString("address_zip_code"); 
            double balance = resultSet.getDouble("balance");
             
           user users = new user(email,password, firstName, lastName, birthday, address,  address_city, address_state ,address_zip_code, balance);
        listUser.add(users);
        }   
        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    public void insert(user users) throws SQLException {
    	connect_func("root","pass1234");         
		String sql = "insert into User(email, userPass, firstName, lastName, birthday, address, address_city, address_state, address_zip_code, balance) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, users.getEmail());
			preparedStatement.setString(2, users.getPassword());
			preparedStatement.setString(3, users.getFirstName());
			preparedStatement.setString(4, users.getLastName());
			preparedStatement.setString(5, users.getBirthday());
			preparedStatement.setString(6, users.getAddress());		
			preparedStatement.setString(7, users.getAddress_city());		
			preparedStatement.setString(8, users.getAddress_state());		
			preparedStatement.setString(9, users.getAddress_zip_code());		
			preparedStatement.setDouble(10, users.getBalance());				

		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public boolean delete(String email) throws SQLException {
        String sql = "DELETE FROM User WHERE email = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;     
    }
     
    public boolean update(user users) throws SQLException {
        String sql = "update User set firstName=?, lastName =?,password = ?, birthday=?, address=?, address_city=?, address_state=?,  address_zip_code=?, cash_bal=?, PPS_bal =? where email = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, users.getEmail());
		preparedStatement.setString(2, users.getFirstName());
		preparedStatement.setString(3, users.getLastName());
		preparedStatement.setString(4, users.getPassword());
		preparedStatement.setString(5, users.getBirthday());
		preparedStatement.setString(6, users.getAddress());	
		preparedStatement.setString(7, users.getAddress_city());		
		preparedStatement.setString(8, users.getAddress_state());		
		preparedStatement.setString(9, users.getAddress_zip_code());		
//		preparedStatement.setInt(11, users.getCash_bal());		
//		preparedStatement.setInt(12, users.getPPS_bal());
         
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;     
    }
    
    public user getUser(String email) throws SQLException {
    	user user = null;
        String sql = "SELECT * FROM User WHERE email = ?";
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
         
        ResultSet resultSet = preparedStatement.executeQuery();
         
        if (resultSet.next()) {
        	 String firstName = resultSet.getString("firstName");
             String lastName = resultSet.getString("lastName");
             String password = resultSet.getString("userPass");
             String birthday = resultSet.getString("birthday");
             String address = resultSet.getString("address");
             String address_city = resultSet.getString("address_city"); 
             String address_state = resultSet.getString("address_state"); 
             String address_zip_code = resultSet.getString("address_zip_code"); 
             double balance = resultSet.getDouble("balance");
             user = new user(email,password, firstName, lastName, birthday, address,  address_city, address_state ,address_zip_code, balance);
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }
    
    public boolean checkEmail(String email) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE email = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, email);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
    	return checks;
    }
    
    public boolean checkPassword(String password) throws SQLException {
    	boolean checks = false;
    	String sql = "SELECT * FROM User WHERE userPass = ?";
    	connect_func();
    	preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        System.out.println(checks);	
        
        if (resultSet.next()) {
        	checks = true;
        }
        
        System.out.println(checks);
       	return checks;
    }
    
    
    
    public boolean isValid(String email, String password) throws SQLException
    {
    	String sql = "SELECT * FROM User";
    	connect_func();
    	statement = (Statement) connect.createStatement();
    	ResultSet resultSet = statement.executeQuery(sql);
    	
    	resultSet.last();
    	
    	int setSize = resultSet.getRow();
    	resultSet.beforeFirst();
    	
    	for(int i = 0; i < setSize; i++)
    	{
    		resultSet.next();
    		if(resultSet.getString("email").equals(email) && resultSet.getString("userPass").equals(password)) {
    			return true;
    		}		
    	}
    	return false;
    }
    
    public Boolean doesUserExist(String userName) throws SQLException {
    	connect_func();
    	System.out.println("In doesUserExist() in userDAO class");
    	
    	String sqlGetUserID = "select count(email) from User where email=" + "\"" + userName + "\""; //Get the userID from the User Table
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetUserID);
		resultSet = preparedStatement.executeQuery();
		
    	if(resultSet.next()) {
    		int count = resultSet.getInt(1);
    		if (count == 1) {
    	        resultSet.close();
    	        preparedStatement.close();
    	        disconnect();
    			return true;
    		}
    	}
    	
        preparedStatement.close();
    	return false;
    }
    
    // works on initialzing DB
    public void init() throws SQLException, FileNotFoundException, IOException{
    	connect_func();
        statement =  (Statement) connect.createStatement();
        
        String[] INITIAL = {"drop database if exists NFTDB; ",
					        "create database NFTDB; ",
					        "use NFTDB; ",
					        "drop table if exists User; ",
					        ("CREATE TABLE if not exists User( " +
					            "email varchar(100) NOT NULL, " + 
					            "userPass varchar(100) NOT NULL, " +
					            "firstName varchar(50) NOT NULL, " +
					            "lastName varchar(50) NOT NULL, " +
					            "birthday DATE NOT NULL, " +
					            "address VARCHAR(60), "+ 
					            "address_city VARCHAR(20), "+ 
					            "address_state VARCHAR(2)," + 
					            "address_zip_code VARCHAR(5),"+ 
					            "balance decimal(65,2) NOT NULL default 100,"+
					            "PRIMARY KEY (email) "+"); ")
        					};
        String[] TUPLES = {("insert into User(email, userPass, firstName, lastName, birthday, address, address_city, address_state, address_zip_code, balance)"+
        			"values ('jamil@gmail.com', 'jamil123', 'Jamil', 'Ali', '2000-06-27', '1234', 'Detroit', 'MI', '48201', '100'),"+
			    		 	"('ayon@gmail.com', 'ayon123', 'Ayon', 'Chakroborty', '2000-05-27', '1234', 'Detroit', 'MI', '48201','100'),"+
			    	 	 	"('jo@gmail.com', 'jo123', 'Jo', 'jo', '2000-04-27', '1234', 'Southfield', 'MI', '48201','100'),"+
			    		 	"('wallace@gmail.com', 'wallace123', 'Wallace', 'Smith', '2000-03-27', '1234', 'Rochestor', 'MI', '48201','100'),"+
			    		 	"('kamil@gmail.com', 'kamil123', 'Kamil', 'Ali', '2000-02-27', '1234', 'Detroit', 'MI', '48201','100'),"+
			    			"('root', 'pass1234', 'Default','Default', '0000-00-00', '0000', 'Default', '00', '0000', '1000000000');")
			    			};
        
        //for loop to put these in database
        for (int i = 0; i < INITIAL.length; i++)
        	statement.execute(INITIAL[i]);
        for (int i = 0; i < TUPLES.length; i++)	
        	statement.execute(TUPLES[i]);
        disconnect();
    }

	public List<user> listInactiveUsers() throws SQLException, FileNotFoundException, IOException {
	   	System.out.println("In listAllInactiveUsers() in userDAO class");
	   	connect_func();         
			List<user> listAllInactiveUsers = new ArrayList<user>();
			
			String sql = "select * from innactiveUsers;";
			
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int count = 0;
			while(resultSet.next()) {
				count++;
				String email = resultSet.getString("email");
				user users = new user(email);
				listAllInactiveUsers.add(users);
			}
			if (count == 0) {
				return null;
			}
			else
				return listAllInactiveUsers;
	   }

	public List<userStatistics> listUserStatistics(String userName) throws SQLException {
	   	System.out.println("In listUserStatistics() in userDAO class");
	   	connect_func();         
			List<userStatistics> listUserStatistics = new ArrayList<userStatistics>();
			
			String sql = "select * from userStatistics where email=" +"\"" + userName + "\"";
			
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int count = 0;
			while(resultSet.next()) {
				count++;
				String email = resultSet.getString("email");
				int buys = resultSet.getInt("buys");
				int sells = resultSet.getInt("sells");
				int transfers = resultSet.getInt("transfers");
				int nftsOwned = resultSet.getInt("NftsOwned");
				userStatistics users = new userStatistics(email, buys, sells, transfers, nftsOwned);
				listUserStatistics.add(users);
			}
			if (count == 0) {
				return null;
			}
			else
				return listUserStatistics;
	}

	public List<nft> commonNfts(String user1, String user2) throws SQLException {
	   	System.out.println("In commonNfts in nftDAO class");
	   	connect_func();         
			List<nft> commonNfts = new ArrayList<nft>();
			
			String sql2 = "select s1.nftToBeSold as nft\r\n"
					+ "from sale_orders s1, sale_orders s2, sale_orders s3, sale_orders s4\r\n"
					+ "where (s1.nftSeller=? or\r\n"
					+ "	s2.nftSeller=?) and\r\n"
					+ "    (s3.soldTo=? or\r\n"
					+ "    s4.soldTo=?)\r\n"
					+ "union \r\n"
					+ "select t1.nftToBeTransferred\r\n"
					+ "from transfer_orders t1, transfer_orders t2, transfer_orders t3, transfer_orders t4\r\n"
					+ "where (t1.nftOwner=? or\r\n"
					+ "	t2.nftOwner=?) and\r\n"
					+ "    (t3.transferredTo=? or\r\n"
					+ "    t4.transferredTo=?);";
			
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
			preparedStatement.setString(1, user1);
			preparedStatement.setString(2, user2);
			preparedStatement.setString(3, user1);
			preparedStatement.setString(4, user2);
			preparedStatement.setString(5, user1);
			preparedStatement.setString(6, user2);
			preparedStatement.setString(7, user1);
			preparedStatement.setString(8, user2);

			resultSet = preparedStatement.executeQuery();
			nftDAO nftDAO = new nftDAO();
			int count = 0;
			while(resultSet.next()) {
				count++;
				int nftID = resultSet.getInt("nft");
				nft nfts = nftDAO.getNftInfoById(nftID);
				commonNfts.add(nfts);
			}
			if (count == 0) {
				return null;
			}
			else
				return commonNfts;
	}
}
    
    
   
    
    
    
    
    
	
