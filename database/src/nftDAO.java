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
		String sql = "insert into nft(NFTID, nftName, nftDescription, imageURL, nftOwner) values (?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, null);
		preparedStatement.setString(2, nfts.getNFTname());
		preparedStatement.setString(3, nfts.getNFTDescription());
		preparedStatement.setString(4, nfts.getImageLink());
		preparedStatement.setString(5, nfts.getNFTOwner());

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
    

}
