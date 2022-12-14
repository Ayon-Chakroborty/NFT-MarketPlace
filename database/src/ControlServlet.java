import java.io.IOException;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private nftDAO nftDAO = new nftDAO();
	    private transferOrderDAO transferOrderDAO = new transferOrderDAO();
	    private saleListingDAO saleListingDAO = new saleListingDAO();
	    private saleOrderDAO saleOrderDAO = new saleOrderDAO();
	    private String currentUser;
	    private int saleListingGlobal=-1;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	        System.out.println(action);
	    
	    try {
        	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		System.out.println("Database successfully initialized!");
        		rootPage(request,response,"");
        		break;
        	case "/root":
        		rootPage(request,response, "");
        		break;
        	case "/logout":
        		logout(request,response);
        		break;
        	case "/list": 
                 System.out.println("The action is: list");
                 listUser(request, response);           	
                 break;
        	case "/mint" :
        		System.out.println("In case Mint");
        		mint(request, response);
        		break;
        	case "/listSale" :
        		listSale(request, response);
        		break;
        	case "/transfer" :
        		transfer(request, response);
        		break;
        	case "/searchNft":
        		System.out.println("In case searchNft");
        		searchNft(request, response);
        		break;
        	case "/searchUser":
        		System.out.println("In case searchUser");
        		searchUser(request, response);
        		break;
        	case "/buyNft":
        		System.out.println("In case buyNft");
        		buyNft(request, response);
        		break;
        	case "/renderNftPage":
        		System.out.println("In case renderNftPage()");
        		renderNftPage(request, response);
        		break;
        	case "/listAllMinted":
        		System.out.println("In case listAllMinted");
        		listAllMinted(request, response);
        		break;
        	case "/listAllPurchased":
        		System.out.println("In case listAllPurchased");
        		listAllPurchased(request, response);
        		break;
        	case "/listAllSold":
        		System.out.println("In case listAllSold");
        		listAllSold(request, response);
        		break;
        	case "/bigCreators":
        		System.out.println("In case bigCreators");
        		bigCreators(request, response);
        		break;
        	case "/bigSellers":
        		System.out.println("In case bigSellers");
        		bigSellers(request, response);
        		break;
        	case "/bigBuyers":
        		System.out.println("In case bigBuyers");
        		bigBuyers(request, response);
        		break;
        	case "/hotNfts":
        		System.out.println("In case hotNfts");
        		hotNfts(request, response);
        		break;
        	case "/inactiveUsers":
        		System.out.println("In case inactiveUsers");
        		innactiveUsers(request, response);
        		break;
        	case "/goodBuyers":
        		System.out.println("In case goodBuyers");
        		goodBuyers(request, response);
        		break;
        	case "/diamondHands":
        		System.out.println("In case diamondHands");
        		diamondHands(request, response);
        		break;
        	case "/paperHands":
        		System.out.println("In case paperHands");
        		paperHands(request, response);
        		break;
        	case "/userStatistics":
        		System.out.println("In case userStatistics");
        		userStatistics(request, response);
        		break;
        	case "/commonNfts":
        		System.out.println("In case commonNfts");
        		commonNfts(request, response);
        		break;
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
	   

		private void commonNfts(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	    	System.out.println("In commonNfts() in ControlServerlet.java");
	    	String user1 = request.getParameter("user1");
	    	String user2 = request.getParameter("user2");
	    	Boolean doesUserExist = userDAO.doesUserExist(user1);
	    	Boolean doesUserExist2 = userDAO.doesUserExist(user2);
	    	if(doesUserExist == true && doesUserExist2 == true) {
		    	List<nft> commonNfts = userDAO.commonNfts(user1, user2);	        
		    	request.setAttribute("commonNfts", commonNfts); 
		    	request.setAttribute("doesUserExist", doesUserExist);

	    	}
	        RequestDispatcher dispatcher = request.getRequestDispatcher("CommonNfts.jsp");       
	        dispatcher.forward(request, response);
		}

		private void userStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("In userStatistics() in ControlServerlet.java");
	    	String UserToSearch = request.getParameter("userName");
	    	Boolean doesUserExist = userDAO.doesUserExist(UserToSearch);
	    	System.out.println("doesUserExist: " + String.valueOf(doesUserExist));
	    	if(doesUserExist == true) {
		    	List<userStatistics> listUserStatistics = userDAO.listUserStatistics(UserToSearch);	        
		    	request.setAttribute("listUserStatistics", listUserStatistics); 
		    	request.setAttribute("doesUserExist", doesUserExist);

	    	}
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserStatistics.jsp");       
	        dispatcher.forward(request, response);
			
		}

		private void paperHands(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	        System.out.println("In paperHands() in ControlServerlet.java");
	    	List<user> listPaperHands = saleOrderDAO.listPaperHands();
	        request.setAttribute("listPaperHands", listPaperHands);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("PaperHands.jsp");       
	        dispatcher.forward(request, response);
			
		}

		private void diamondHands(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	        System.out.println("In diamondHands() in ControlServerlet.java");
	    	List<user> listDiamondHands = saleOrderDAO.listDiamondHands();
	        request.setAttribute("listDiamondHands", listDiamondHands);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("DiamondHands.jsp");       
	        dispatcher.forward(request, response);
			
		}

		private void goodBuyers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	        System.out.println("In goodBuyers() in ControlServerlet.java");
	    	List<user> listAllGoodBuyers = saleOrderDAO.listAllGoodBuyers();
	        request.setAttribute("listAllGoodBuyers", listAllGoodBuyers);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("GoodBuyers.jsp");       
	        dispatcher.forward(request, response);
			
		}

		private void innactiveUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        System.out.println("In bigBuyers() in ControlServerlet.java");
	    	List<user> listAllInactiveUsers = userDAO.listInactiveUsers();
	        request.setAttribute("listAllInactiveUsers", listAllInactiveUsers);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("InactiveUsers.jsp");       
	        dispatcher.forward(request, response);
			
		}

		private void hotNfts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        System.out.println("In hotNfts() in ControlServerlet.java");
	    	List<nft> listAllHotNfts = nftDAO.listHotNfts();
	        request.setAttribute("listAllHotNfts", listAllHotNfts);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("HotNfts.jsp");       
	        dispatcher.forward(request, response);
			
		}


		private void bigBuyers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	        System.out.println("In bigBuyers() in ControlServerlet.java");
	    	List<user> listAllBigBuyers = saleOrderDAO.listAllBigBuyers();
	        request.setAttribute("listAllBigBuyers", listAllBigBuyers);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("bigBuyers.jsp");       
	        dispatcher.forward(request, response);
			
		}

		private void bigSellers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	        System.out.println("In bigSellers() in ControlServerlet.java");
	    	List<user> listAllBigSellers = saleOrderDAO.listAllBigSellers();
	        request.setAttribute("listAllBigSellers", listAllBigSellers);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("bigSellers.jsp");       
	        dispatcher.forward(request, response);
			
		}

		private void bigCreators(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
	        System.out.println("In bigCreators() in ControlServerlet.java");
	    	List<user> listAllBigCreators = nftDAO.listAllBigCreators();
	        request.setAttribute("listAllBigCreators", listAllBigCreators);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("bigCreators.jsp");       
	        dispatcher.forward(request, response);
		}

		private void listUser(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        System.out.println("listUser started: 00000000000000000000000000000000000");

	     
	        List<user> listUser = userDAO.listAllUsers();
	        request.setAttribute("listUser", listUser);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UserList.jsp");       
	        dispatcher.forward(request, response);
	     
	        System.out.println("listPeople finished: 111111111111111111111111111111111111");
	    }
	    	        
	    private void rootPage(HttpServletRequest request, HttpServletResponse response, String view) throws ServletException, IOException, SQLException{
	    	System.out.println("root view");
			request.setAttribute("listUser", userDAO.listAllUsers());
	    	request.getRequestDispatcher("rootView.jsp").forward(request, response);
	    }
	    
	    
	    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String email = request.getParameter("email");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (email.equals("root") && password.equals("pass1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 rootPage(request, response, "");
	    	 }
	    	 else if(userDAO.isValid(email, password)) 
	    	 {
			 	 
			 	 currentUser = email;
				 System.out.println("Login Successful! Redirecting");
				 session = request.getSession();
				 session.setAttribute("username", email);
				 request.getRequestDispatcher("activitypage.jsp").forward(request, response);
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String email = request.getParameter("email");
	   	 	String password = request.getParameter("password");
	   	 	String firstName = request.getParameter("firstName");
	   	 	String lastName = request.getParameter("lastName");
	   	 	String birthday = request.getParameter("birthday");
	   	 	String address = request.getParameter("address"); 
	   	 	String address_city = request.getParameter("address_city"); 
	   	 	String address_state = request.getParameter("address_state"); 
	   	 	String address_zip_code = request.getParameter("address_zip_code"); 	   	 	
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkEmail(email)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(email, password, firstName, lastName, birthday, address, address_city, address_state, address_zip_code, 100.00);
		            userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }   
	    
	    
	    private void mint(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.print("IN mint() in ControlServerlet.java");
	   	 	String nftName = request.getParameter("nftName");
	   	 	String nftDescription = request.getParameter("nftDescription");
	   	 	String imageURL = request.getParameter("imageURL");
	   	 	String nftOwner = (String) session.getAttribute("username");
	   	 	String createdBy = (String) session.getAttribute("username");
	   	 	LocalDate curDate = LocalDate.now();
	   	 	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
	   	 	String currentDate = dtf.format(curDate);
            nft nfts = new nft(nftName, nftDescription, nftOwner, createdBy, currentDate, imageURL);
            nftDAO.insert(nfts);
   	 		System.out.println("MINTING SUCCESS! Added to database");
   	 		response.sendRedirect("activitypage.jsp");
	    }    
	    
	    private void listAllPurchased(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        System.out.println("In listAllPurchased() in ControlServerlet.java");
	    	List<nft> listAllPurchased = nftDAO.listAllNftsPurchased(currentUser);
	        request.setAttribute("listAllNftsPurchased", listAllPurchased);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("listAllNftsPurchased.jsp");       
	        dispatcher.forward(request, response);
	    }
	    
	    private void listAllSold(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        System.out.println("In listAllSold() in ControlServerlet.java");
	    	List<nft> listAllSold = nftDAO.listAllNftsSold(currentUser);
	        request.setAttribute("listAllNftsSold", listAllSold);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("listAllNftsSold.jsp");       
	        dispatcher.forward(request, response);
	    }
	    
	    private void listAllMinted(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	        System.out.println("In listAllMinted() in ControlServerlet.java");
	    	List<nft> listAllMinted = nftDAO.listAllNftsMinted(currentUser);
	        request.setAttribute("listAllNftsMinted", listAllMinted);       
	        RequestDispatcher dispatcher = request.getRequestDispatcher("listAllNftsMinted.jsp");       
	        dispatcher.forward(request, response);
	    }
	    
	    private void listSale(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.print("In listSale() in ControlServerlet.java");
	   	 	String nftListed = request.getParameter("nftListed");
	   	 	String nftSeller = (String) session.getAttribute("username");
	   	 	double nftPrice = Double.parseDouble((String)request.getParameter("price"));
	   	 	System.out.println("This is the NFT Price: ");
	   	 	System.out.println(nftPrice);
	   	 	String datePosted = request.getParameter("postingDate");
	   	 	String endingDate = request.getParameter("endingDate");
            saleListing saleListings = new saleListing(nftListed, nftSeller, nftPrice, datePosted, endingDate, 0); // 0 for nftSold argument. SQL does not take bool values. Only tinyInt 
            saleListingDAO.insert(saleListings);
   	 		System.out.println("LISTING SUCCESS! Added to database");
   	 		response.sendRedirect("activitypage.jsp");
	    }     
	    
	    
	    private void transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("In transfer() in ControlServerlet.java");
	   	 	String nftToBeTransfered = request.getParameter("nftToBeTransferred");
	   	 	String transferredFrom = (String) session.getAttribute("username");
	   	 	String transferredTo = request.getParameter("transferredTo");
	   	 	
	   	 	// Current Date of Transfer
	   	 	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd");
		   	LocalDate localDate = LocalDate.now();
	   	 	String transferDate = dtf.format(localDate);
	   	 	
            transferOrder transferOrders = new transferOrder(nftToBeTransfered, transferredTo, transferredFrom, transferDate);
            System.out.print("Created transfer Object ");
            System.out.println(transferOrders);
            transferOrderDAO.insert(transferOrders);
            transferOrderDAO.transferNFT(transferOrders);
   	 		System.out.println("TRANSER SUCCESS! Added to database");
   	 		response.sendRedirect("activitypage.jsp");
	    }

	    private void searchNft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
	    	System.out.println("In searchNft() in ControlServerlet.java");
	    	
	    	String nftToSearch = request.getParameter("nftName");
	    	Boolean doesNftExist = nftDAO.doesNftExist(nftToSearch);
	    	System.out.println("In searchNft() in ControlServerlet.java");
	    	System.out.println("doesNftExist: " + String.valueOf(doesNftExist));
	    	if (doesNftExist == true) {
	    		System.out.println("NFT EXISTS!");
		    	nft nfts = nftDAO.getNftInfoByName(nftToSearch);
		    	System.out.println("NFT ID: " + nfts.getNftId());
		    	session.setAttribute("nftInfo", nfts);
		    	request.setAttribute("doesNftExist", doesNftExist);
            	user currUser = userDAO.getUser(currentUser);
		    	
	    	}
	    		String page = "/SearchForNft.jsp";
	            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
	            requestDispatcher.forward(request, response);
	    	
	    	
	    }
	    
	    private void renderNftPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
	    	System.out.println("In renderNftPage in ControlServerlet.java");
	    	
	    	request.setAttribute("doesNftExist", true);
    		System.out.println("NFT EXISTS!");
	    	nft nfts = (nft) session.getAttribute("nftInfo");
	    	
	    	System.out.println("NFT ID: " + nfts.getNftId());
	    	request.setAttribute("nftInfo2", nfts);
        	user currUser = userDAO.getUser(currentUser);
        	
	    	saleListing doesSaleListingExist = saleListingDAO.doesSaleListingExist(nfts.getNftId());
	    	System.out.println("Does Sale Listing Exist: " + doesSaleListingExist);
	    	
	    	if (doesSaleListingExist != null) {
		    	request.setAttribute("saleListingExists", doesSaleListingExist);
		    	
	    		System.out.println("In sale listing exists true clause");
		    	//get Sale Listing end date and compare to current date. Only pass values if the current date is before the end date
	    		saleListing currSaleListing = doesSaleListingExist;
        		request.setAttribute("saleListing", currSaleListing);
        		saleListingGlobal = currSaleListing.getListId();
		    	if (!(currUser.getEmail().equals(currSaleListing.getNftSeller()))) {
		    		System.out.println("This is check Date: " + currSaleListing.getEndingDate());
		            LocalDate currentDate = LocalDate.now();
		            System.out.println(currentDate.toString());
		            LocalDate endingDate = LocalDate.parse(currSaleListing.getEndingDate());
		            System.out.println(endingDate.toString());
		            
		            if (currentDate.isBefore(endingDate) || currentDate.isEqual(endingDate)) {
		            	System.out.println("Date is within the buying Range");
		            	boolean dateWithinListingDateRange = true;
		            	request.setAttribute("dateWithinListingDateRange", dateWithinListingDateRange);
		            	if (currUser.getBalance() >= currSaleListing.getPrice()) {
		            		boolean canUserBuyNft = true;
		            		//session.setAttribute("listIdForSale", doesSaleListingExist.get);
		            		request.setAttribute("canUserBuyNft", canUserBuyNft);
		            		
		            	}
		            	
		            }
		    	}
	    	}

		    	
	    	
    		String page = "/nftPage.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
	    	
	    	
	    }
	    
	    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ParseException {
	    	System.out.println("In searchUser() in ControlServerlet.java");
	    	String UserToSearch = request.getParameter("userName");
	    	Boolean doesUserExist = userDAO.doesUserExist(UserToSearch);
	    	System.out.println("doesUserExist: " + String.valueOf(doesUserExist));
	    	if (doesUserExist == true) {
	    		System.out.println("User EXISTS!");
		    	user user = userDAO.getUser(UserToSearch);
		    	List<nft> listAllMinted = nftDAO.listAllNFTs(UserToSearch);
		    	System.out.println("User ID: " + userDAO.getUser(UserToSearch));
		    	request.setAttribute("userInfo", user);
		    	request.setAttribute("doesUserExist", doesUserExist);
		    	request.setAttribute("listAllNFTs", listAllMinted);   
		    	
	    	}
	    	
	    		String page = "/SearchForUser.jsp";
	            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
	            requestDispatcher.forward(request, response);
	    	
	    	
	    }
	    
    	
	    private void buyNft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  {
			// TODO Auto-generated method stub
	    	System.out.println("In buyNft() in ControlServerlet.java");
	    	
	    	// get current user's name
	   	 	String nftBuyer = (String) session.getAttribute("username");
	   	 	int listID = saleListingGlobal;
	    	String nftToSearch = request.getParameter("nftName");
	    	saleOrder saleOrders = saleOrderDAO.createSaleOrderByNftName(nftToSearch, nftBuyer, listID);
	    	saleOrderDAO.insert(saleOrders);
	    	// change eth from seller and buyer
	    	saleOrderDAO.exchangeEth(saleOrders);
	    	// transfer the nftOwnership to the buyer
	    	saleOrderDAO.transferNftOwnership(saleOrders);
	    	// Change the nftSold column to 1 (true) in sale_listings
	    	saleListingDAO.updateNftSold(saleOrders);
	    	
   	 		System.out.println("Buy NFT SUCCESS! Added to database");
   	 		response.sendRedirect("activitypage.jsp");
			
		}
	    
	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    

	     
        
	    
	    
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    
