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
import java.sql.PreparedStatement;


public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private nftDAO nftDAO = new nftDAO();
	    private transferOrderDAO transferOrderDAO = new transferOrderDAO();
	    private saleListingDAO saleListingDAO = new saleListingDAO();
	    private String currentUser;
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
	    	case "/myProfile":
	    		System.out.println("In case myProfile");
	    		myProfile(request, response);
	    		break;}}
	    
        	
	    
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
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
            nft nfts = new nft(nftName, nftDescription, nftOwner, imageURL);
            nftDAO.insert(nfts);
   	 		System.out.println("MINTING SUCCESS! Added to database");
   	 		response.sendRedirect("activitypage.jsp");
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
            saleListing saleListings = new saleListing(nftListed, nftSeller, nftPrice, datePosted, endingDate);
            saleListingDAO.insert(saleListings);
   	 		System.out.println("LISTING SUCCESS! Added to database");
   	 		response.sendRedirect("activitypage.jsp");
	    }     
	    
	    
	    private void transfer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("In transfer() in ControlServerlet.java");
	   	 	String nftToBeTransfered = request.getParameter("nftToBeTransferred");
	   	 	String transferredFrom = (String) session.getAttribute("username");
	   	 	String transferredTo = request.getParameter("transferredTo");
	   	 	String transferDate = request.getParameter("transferDate");
            transferOrder transferOrders = new transferOrder(nftToBeTransfered, transferredTo, transferredFrom, transferDate);
            System.out.print("Created transfer Object ");
            System.out.println(transferOrders);
            transferOrderDAO.insert(transferOrders);
            transferOrderDAO.transferNFT(transferOrders);
   	 		System.out.println("TRANSER SUCCESS! Added to database");
   	 		response.sendRedirect("activitypage.jsp");
	    }
	    
	  
	    

	    private void searchNft(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("In searchNft() in ControlServerlet.java");
	    	
	    	String nftToSearch = request.getParameter("nftName");
	    	nft nfts = nftDAO.getNftInfoByName(nftToSearch);
	    	
	    	System.out.println("Created nft object");
	    	
//    		int nftId = nfts.getNftId();
//    		String nftOwner = nfts.getNFTOwner();
//    		String nftName = nfts.getNFTname();
//    		String nftDescription = nfts.getNFTDescription();
//    		String nftImageUrl = nfts.getImageLink();
//    		
//    		request.setAttribute("nftName", nftName);
//    		request.setAttribute("nftOwner", nftOwner);
//    		request.setAttribute("nftId", nftId);
//    		request.setAttribute("nftDescription", nftDescription);
//    		request.setAttribute("nftImageUrl", nftImageUrl);
	    	
	    	request.setAttribute("nfttest", nfts);
    		
    		String page = "/SearchForNft.jsp";
    		
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response); 	
	    	
	    }
	    
	    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("In searchUser() in ControlServerlet.java");
	    	
	    	String userToSearch = request.getParameter("nftName");
	    	user user = userDAO.getUserInfoByName(userToSearch);
	    	nft nfts = nftDAO.getNftInfoByName(userToSearch);
	    	
	    	System.out.println("Created userObject object");
	    	System.out.println("Created nftObject object");
	    	
//    		int nftId = nfts.getNftId();
//    		String nftOwner = nfts.getNFTOwner();
//    		String nftName = nfts.getNFTname();
//    		String nftDescription = nfts.getNFTDescription();
//    		String nftImageUrl = nfts.getImageLink();
//    		
//    		request.setAttribute("nftName", nftName);
//    		request.setAttribute("nftOwner", nftOwner);
//    		request.setAttribute("nftId", nftId);
//    		request.setAttribute("nftDescription", nftDescription);
//    		request.setAttribute("nftImageUrl", nftImageUrl);
	    	
	    	request.setAttribute("nfttest", user);
	    	request.setAttribute("nfttest2", nfts);
    		
    		String page = "/SearchForUser.jsp";
    		
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response); 	
	    	
	    }
	    
	    private void myProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	System.out.println("profile view");
	    	
	    	String data = (String) session.getAttribute("username");
	    	user user = userDAO.getUser(data);
	    	request.setAttribute("listUser", user);
	    	String page = "/myProfile.jsp";
    		
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response); 	
	    }
	    
	    
	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    

	     
        
	    
	    
	    
	    
	    
}
	        
	        
	    