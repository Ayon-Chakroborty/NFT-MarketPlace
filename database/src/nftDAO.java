import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;

public class nftDAO {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public nftDAO(){}
	
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
    
    public void insert(nft nfts) throws SQLException {
    	connect_func();         
		String sql = "insert into nft(NFTID, nftName, nftDescription, imageURL, nftOwner, createdBy, dateCreated) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, null);
		preparedStatement.setString(2, nfts.getNFTname());
		preparedStatement.setString(3, nfts.getNFTDescription());
		preparedStatement.setString(4, nfts.getImageLink());
		preparedStatement.setString(5, nfts.getNFTOwner());
		preparedStatement.setString(6, nfts.getCreatedBy());
		preparedStatement.setObject(7, java.sql.Date.valueOf(nfts.getDateCreated()));
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    
    public nft getNftInfoByName(String nftName) throws SQLException {
    	connect_func();
    	System.out.println("In getNftInfoByName() in nftDAO class");
    	
    	String sqlGetNFTID = "select * from NFT where nftName=" + "\"" + nftName + "\""; //Get the NFTID from the NFT Table
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetNFTID);
		
		resultSet = preparedStatement.executeQuery();
		int nftId = 0;
		String nftDescription = null;
		String nftImageUrl = null;
		String nftOwner = null;
		if(resultSet.next()) {
			nftId = resultSet.getInt("nftID");
			nftDescription = resultSet.getString("nftDescription");
			nftImageUrl = resultSet.getString("imageUrl");
			nftOwner = resultSet.getString("nftOwner");
			
		}

        preparedStatement.close();
        return new nft(nftId, nftName, nftDescription,nftOwner, nftImageUrl);
    	
    }
    
    public Boolean doesNftExist(String nftName) throws SQLException {
    	connect_func();
    	System.out.println("In doesNftExist() in nftDAO class");
    	
    	String sqlGetNFTID = "select count(nftName) from NFT where nftName=" + "\"" + nftName + "\""; //Get the NFTID from the NFT Table
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetNFTID);
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
    
    public List<nft> listAllNFTs(String userName) throws SQLException {
        List<nft> listUser = new ArrayList<nft>();        
        String sql = "select * from NFT where nftOwner=" + "\"" + userName + "\""; ;      
        connect_func();      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        int nftId = 0;
		String nftDescription = null;
		String nftImageUrl = null;
		String nftOwner = null;
		String nftName = null;
        while (resultSet.next()) {
        	nftId = resultSet.getInt("nftID");
        	nftName = resultSet.getString("nftName");
			nftDescription = resultSet.getString("nftDescription");
			nftImageUrl = resultSet.getString("imageUrl");
			nftOwner = resultSet.getString("nftOwner");
             
           nft nft = new nft(nftId, nftName, nftDescription,nftOwner, nftImageUrl);
        listUser.add(nft);
        }        
        resultSet.close();
        disconnect();        
        return listUser;
    }
    
    public List<nft> listAllNftsMinted(String currentUser) throws SQLException{
    	System.out.println("In listAllNftsMinted in nftDAO class");
    	connect_func();         
		List<nft> listAllNftsMinted = new ArrayList<nft>();
		String sql = "select * from NFT where createdby=" + "\"" + currentUser + "\"";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		int count = 0;
		while(resultSet.next()) {
			count++;
			String nftName = resultSet.getString("nftName");
			String nftDescription = resultSet.getString("nftDescription");
			String imageUrl = resultSet.getString("imageUrl");
			String nftOwner = resultSet.getString("nftOwner");
			String createdBy = resultSet.getString("createdBy");
			Date dateCreated = resultSet.getDate("dateCreated");

			nft nfts = new nft(nftName, nftDescription, nftOwner, createdBy, dateCreated.toString(), imageUrl);
			listAllNftsMinted.add(nfts);
		}
		if (count == 0) {
			return null;
		}
		else
			return listAllNftsMinted;
    	
    }
    
    public List<nft> listAllNftsPurchased(String currentUser) throws SQLException{
    	System.out.println("In listAllNftsPurchased in nftDAO class");
    	connect_func();         
		List<nft> listAllNftsPurchased = new ArrayList<nft>();
		String sql = "select * from NFT inner join sale_orders on NFT.NFTID=sale_orders.nftToBeSold and sale_orders.soldTo=" + "\"" + currentUser + "\"";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		int count = 0;
		while(resultSet.next()) {
			count++;
			String nftName = resultSet.getString("nftName");
			String nftDescription = resultSet.getString("nftDescription");
			String imageUrl = resultSet.getString("imageUrl");
			String nftOwner = resultSet.getString("nftOwner");
			String createdBy = resultSet.getString("createdBy");
			Date dateCreated = resultSet.getDate("dateCreated");

			nft nfts = new nft(nftName, nftDescription, nftOwner, createdBy, dateCreated.toString(), imageUrl);
			listAllNftsPurchased.add(nfts);
		}
		if (count == 0) {
			return null;
		}
		else
			return listAllNftsPurchased;
    	
    }
    
    public List<nft> listAllNftsSold(String currentUser) throws SQLException{
    	System.out.println("In listAllNftsPurchased in nftDAO class");
    	connect_func();         
		List<nft> listAllNftsSold = new ArrayList<nft>();
		String sql = "select * from NFT inner join sale_orders on NFT.NFTID=sale_orders.nftToBeSold and sale_orders.nftSeller=" + "\"" + currentUser + "\"";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		resultSet = preparedStatement.executeQuery();
		int count = 0;
		while(resultSet.next()) {
			count++;
			String nftName = resultSet.getString("nftName");
			String nftDescription = resultSet.getString("nftDescription");
			String imageUrl = resultSet.getString("imageUrl");
			String nftOwner = resultSet.getString("nftOwner");
			String createdBy = resultSet.getString("createdBy");
			Date dateCreated = resultSet.getDate("dateCreated");

			nft nfts = new nft(nftName, nftDescription, nftOwner, createdBy, dateCreated.toString(), imageUrl);
			listAllNftsSold.add(nfts);
		}
		if (count == 0) {
			return null;
		}
		else
			return listAllNftsSold;
    	
    }

	public List<user> listAllBigCreators() throws SQLException {
    	System.out.println("In listAllBigCreators in nftDAO class");
    	connect_func();         
		List<user> listAllBigCreators = new ArrayList<user>();
		
		String sql2 = "select createdBy, Num from mostCreated\r\n"
				+ "where Num = (select max(num) from mostCreated);";
		
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
		resultSet = preparedStatement.executeQuery();
		int count = 0;
		while(resultSet.next()) {
			count++;
			String nftOwner = resultSet.getString("createdBy");

			user users = new user(nftOwner);
			listAllBigCreators.add(users);
		}
		if (count == 0) {
			return null;
		}
		else
			return listAllBigCreators;
	}

	public List<nft> listHotNfts() throws SQLException {
	   	System.out.println("In listHotNfts in nftDAO class");
	   	connect_func();         
			List<nft> listAllHotNfts = new ArrayList<nft>();
			
			String sql2 = "select nftName from getHotNft\r\n"
					+ "where Num = (select max(num) from getHotNft);";
			
			preparedStatement = (PreparedStatement) connect.prepareStatement(sql2);
			resultSet = preparedStatement.executeQuery();
			int count = 0;
			while(resultSet.next()) {
				count++;
				String nftName = resultSet.getString("nftName");
				nft nfts = new nft(nftName);
				listAllHotNfts.add(nfts);
			}
			if (count == 0) {
				return null;
			}
			else
				return listAllHotNfts;
	  }
	
	public nft getNftInfoById(int nftId) throws SQLException {
    	connect_func();
    	System.out.println("In getNftInfoById() in nftDAO class");
    	
    	String sqlGetNFTID = "select * from NFT where NFTID=" + nftId; //Get the NFTID from the NFT Table
		preparedStatement = (PreparedStatement) connect.prepareStatement(sqlGetNFTID);
		
		resultSet = preparedStatement.executeQuery();
		String nftName = null;
		String nftDescription = null;
		String nftImageUrl = null;
		String nftOwner = null;
		if(resultSet.next()) {
			nftName = resultSet.getString("nftName");
			nftDescription = resultSet.getString("nftDescription");
			nftImageUrl = resultSet.getString("imageUrl");
			nftOwner = resultSet.getString("nftOwner");
			
		}

        preparedStatement.close();
        return new nft(nftId, nftName, nftDescription,nftOwner, nftImageUrl); 
    	
    }

}
