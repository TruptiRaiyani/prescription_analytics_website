package prescriptionanalytics;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import NodeAPI.NodeAPICall;
/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*
		 * String getaction = request.getParameter("action"); HttpSession
		 * userSession = request.getSession(false);
		 * if(getaction.equalsIgnoreCase("getmyBills")) {
		 * System.out.println("get my bills" +
		 * userSession.getAttribute("userEmail").toString()); }
		 */
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		HttpSession userSession = request.getSession(false);
		NodeAPICall nodeAPI = new NodeAPICall();
		if (action.equalsIgnoreCase("signup")) {

			try {
				if (nodeAPI.getUserByAccount(request.getParameter("txtemail"))) {
					System.out.println("email exists.");
					RequestDispatcher rd = request
							.getRequestDispatcher("/signup.jsp");
					request.setAttribute("Message",
							"Email address already exists.");

					rd.forward(request, response);

				} else {
					System.out.println("email does not exists.");
					JSONObject user = new JSONObject();

					user.put("username", request.getParameter("txtusername")
							.toString());
					user.put("emailid", request.getParameter("txtemail")
							.toString());
					user.put("password", request.getParameter("txtpwd")
							.toString());
					user.put("cpassword", request.getParameter("txtcpwd")
							.toString());
					user.put("address", request.getParameter("txtaddress")
							.toString());
					user.put("phno", request.getParameter("txtphno").toString());
					user.put("securityqn", request.getParameter("ddlQue")
							.toString());
					user.put("securityans", request.getParameter("txtans")
							.toString());

					if (nodeAPI.createUser(user)) {
						RequestDispatcher rd = request
								.getRequestDispatcher("/signin.jsp");
						// request.setAttribute("Message","Registration Link has been sent to your Email Address.Click on the link to activate your account.");
						rd.forward(request, response);
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("signIN")) {
			try {
				if(request.getParameter("txtemail") != null && request.getParameter("txtpassword") != null)
				{
				if (nodeAPI.IsValidUser(request.getParameter("txtemail")
						.toString(), request.getParameter("txtpassword")
						.toString(), userSession)) {
					// userSession.setAttribute("userEmail",
					// request.getParameter("txtemail").toString());
					System.out.println(userSession.getAttribute("userEmail")
							.toString());
					System.out.println(userSession.getAttribute("userName")
							.toString());
					request.setAttribute("fraudType","none1");
					RequestDispatcher rd = request
							.getRequestDispatcher("/home.jsp");

					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request
							.getRequestDispatcher("/signin.jsp");
					request.setAttribute("Message",
							"User name or password invalid.");

					rd.forward(request, response);

				}
				}
				else
					return;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("homeLoad")) {
			System.out
					.println(userSession.getAttribute("userEmail").toString());

		} else if (action.equalsIgnoreCase("getmyBills")) {
			System.out.println("get my bills in post"
					+ userSession.getAttribute("userEmail").toString());
		
			try {
				JSONArray userBills = nodeAPI.getMyBills(userSession
						.getAttribute("userEmail").toString());

				if (userBills != null) {
					// response.setContentType("application/json;charset=utf-8");
					request.setAttribute("userBills", userBills.toString());
					JSONObject j = userBills.getJSONObject(0);
					System.out.println(j.getString("medicalbillname")
							.toString());

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("/mybills.jsp");
			rd.forward(request, response);
		} else if (action.equalsIgnoreCase("RunFraud")) {
			String billID = request.getParameter("billID").toString();
			System.out.println("fraud bill id is : " + billID);
			nodeAPI.RunFraud(request.getParameter("billID").toString());
			System.out.println("succeffully executed");
			
			try {
				JSONArray userBills = nodeAPI.getMyBills(userSession
						.getAttribute("userEmail").toString());

				if (userBills != null) {
					// response.setContentType("application/json;charset=utf-8");
					request.setAttribute("userBills", userBills.toString());
					JSONObject j = userBills.getJSONObject(0);
					System.out.println(j.getString("medicalbillname")
							.toString());

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("runFraudMessage","Fraud has been detected for the medical bill. Please click on View Fraud button to view details.");
			RequestDispatcher rd = request.getRequestDispatcher("/mybills.jsp");
			rd.forward(request, response);
			
		} else if (action.equalsIgnoreCase("ViewFraud")) {
			String billID = request.getParameter("billID").toString();
			System.out.println("view bill id is : " + billID);
			try {
				JSONObject billByBillID = nodeAPI.GetBillByBillID(request
						.getParameter("billID").toString());
				request.setAttribute("billByBillID", billByBillID.toString());

				JSONArray UserBillFraudByProcedure = nodeAPI
						.GetFraudOnProcedureBillID(request.getParameter(
								"billID").toString());
				request.setAttribute("UserBillFraudByProcedure",
						UserBillFraudByProcedure.toString());

				JSONArray UserBillFraudByCharge = nodeAPI
						.GetFraudOnChargeBillID(request.getParameter("billID")
								.toString());
				request.setAttribute("UserBillFraudByCharge",
						UserBillFraudByCharge.toString());

				RequestDispatcher rd = request
						.getRequestDispatcher("/viewfraud.jsp");
				rd.forward(request, response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("forgotpassword")) {
			try {
				JSONObject userDetails = nodeAPI.getUserByEmailID(request
						.getParameter("txtEmail").toString());
				if(userDetails.getString("emailid").toString().equalsIgnoreCase("invalid"))
				{
					request.setAttribute("Message",
							"Invalid EmailID");
					
					RequestDispatcher rd = request
							.getRequestDispatcher("/forgotpassword.jsp");
					rd.forward(request, response);
				}
				else{
				System.out.println(userDetails.getString("securityqn")
						.toString());
				request.setAttribute("securityqn",
						userDetails.getString("securityqn").toString());
				request.setAttribute("securityans",
						userDetails.getString("securityans").toString());
				
				userSession.setAttribute("userEmail",
						userDetails.getString("emailid").toString());
				
				RequestDispatcher rd = request
						.getRequestDispatcher("/securityCheck.jsp");
				rd.forward(request, response);
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("updatepassword")) {
			System.out.println("there you go");
			JSONObject user = new JSONObject();

			try {

				user.put("emailid", userSession.getAttribute("userEmail")
						.toString());
				user.put("password", request.getParameter("txtpassword")
						.toString());
				out.println("<script type=\"text/javascript\">");
				if (nodeAPI.updateUser(user))
					out.println("alert('Password Changed Successfully');");
				else
					out.println("alert('Unable to change password.');");

				out.println("location='forgotpassword.jsp';");
				out.println("</script>");
				RequestDispatcher rd = request
						.getRequestDispatcher("/signin.jsp");
				// request.setAttribute("Message","Registration Link has been sent to your Email Address.Click on the link to activate your account.");
				rd.forward(request, response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("updateUserInfo")) {
			JSONObject user = new JSONObject();

			try {

				user.put("username", request.getParameter("txtusername")
						.toString());
				user.put("emailid", userSession.getAttribute("userEmail")
						.toString());
				user.put("address", request.getParameter("txtaddress")
						.toString());
				user.put("phno", request.getParameter("txtphno").toString());
				user.put("securityqn", request.getParameter("ddlQue")
						.toString());
				user.put("securityans", request.getParameter("txtans")
						.toString());

				if (nodeAPI.updateUser(user)) {
					request.setAttribute("Message",
							"Profile Edited Successfuuly.");
				} else {
					request.setAttribute("Message", "Unable to edit profile.");
				}

				nodeAPI.setUserInfo(userSession.getAttribute("userEmail")
						.toString(), userSession);

				RequestDispatcher rd = request
						.getRequestDispatcher("/editprofile.jsp");
				// request.setAttribute("Message","Registration Link has been sent to your Email Address.Click on the link to activate your account.");
				rd.forward(request, response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (action.equalsIgnoreCase("deleteProfile")) {
			try {
				if (nodeAPI.deleteUser(userSession.getAttribute("userEmail")
						.toString(), userSession)) {
					RequestDispatcher rd = request
							.getRequestDispatcher("/signin.jsp");
					rd.forward(request, response);
				} else {
					request.setAttribute("Message", "Unable to delete profile.");
					RequestDispatcher rd = request
							.getRequestDispatcher("/deleteprofile.jsp");

					rd.forward(request, response);
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		else if(action.equalsIgnoreCase("fraudChart")){
			JSONArray UserBillFraudByCharge;
			try {
			/*	if(request.getParameter("ddlFraudType").toString().equalsIgnoreCase("Fraud By Charges"))
				{
					System.out.println("charge");*/
				UserBillFraudByCharge = nodeAPI
						.GetFraudOnChargeUserID(userSession.getAttribute("userEmail")
								.toString());
				request.setAttribute("UserBillFraudByCharge",
						UserBillFraudByCharge.toString());
			/*	request.setAttribute("fraudType", "charge".toString());
				System.out.println("done1");
				}
				else
				{*/
					System.out.println("procedure");
					JSONArray UserBillFraudByProcedure = nodeAPI
							.GetFraudOnProcedureUserID(userSession.getAttribute("userEmail")
									.toString());
					request.setAttribute("UserBillFraudByProcedure",
							UserBillFraudByProcedure.toString());
			/*		request.setAttribute("fraudType", "procedure".toString());
					System.out.println("done");
				}*/
				RequestDispatcher rd = request
						.getRequestDispatcher("/fraudChart.jsp");
				rd.forward(request, response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		else if(action.equalsIgnoreCase("twitter")){
			String country = request.getParameter("country").toString();
			country = country.replace(" ", "%20");
			//System.out.println(country);
			try {
				JSONArray twitterData = nodeAPI.GetTwitterData(country);
				request.setAttribute("twitterData", twitterData.toString());
				RequestDispatcher rd = request
						.getRequestDispatcher("/diseaseProp.jsp");
				rd.forward(request, response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}







else if(action.equalsIgnoreCase("vaccine")){
			String diseaseName = request.getParameter("diseaseName").toString();		
			JSONObject vaccineObj = new JSONObject();
			try {JSONArray vaccineData = nodeAPI.GetVaccineData(request.getParameter("diseaseName").toString());
				if(vaccineData == null || vaccineData.isNull(0)){
					vaccineObj.put("vaccineName","null");
					vaccineObj.append("diseaseName", diseaseName);
					request.setAttribute("vaccineData",vaccineObj);	}
				else{
				Double whoIndex = vaccineData.getJSONObject(0).getDouble("whoIndex");
				Double whoThreshold = vaccineData.getJSONObject(0).getDouble("whoThreshold");
				String pathogenName = vaccineData.getJSONObject(0).getString("pathogenName");
				if(whoIndex > whoThreshold){
				String url =
	    				"http://www.violinet.org/v-utilities/fpathogen.php?ptg=n_"+pathogenName+"&datafield=vaccine&returntype=n";
	    				URL obj = new URL(url);
	    				HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	    				con.setRequestMethod("GET");
	    				int responseCode = con.getResponseCode();
	    				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	    				DocumentBuilder db = null;
						try {	db = dbf.newDocumentBuilder();	} catch (ParserConfigurationException e) {
							e.printStackTrace();	}
	    				Document doc = db.parse(new URL(url).openStream());
	    				doc.getDocumentElement().normalize();
	    				NodeList nList = doc.getElementsByTagName("vaccine");
	    				Element eElement = null;
	    				for (int temp = 0; temp < nList.getLength(); temp++) {	 
	    					Node nNode = nList.item(temp);
	    					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	    						eElement = (Element) nNode;
	    						if((eElement.getElementsByTagName("vaccine_name").item(0))==null){
	    							vaccineObj.append("vaccineName","null");
	    							}else{    						
	    						vaccineObj.append("vaccineName", eElement.getElementsByTagName("vaccine_name").item(0).getTextContent());	    						}}}		
	    				vaccineObj.append("diseaseName", diseaseName);
				request.setAttribute("vaccineData", vaccineObj);
				request.setAttribute("vaccineData", vaccineObj);	}
				else{
					//no need to recommend vaccinations
					vaccineObj.put("vaccineName","null");
					vaccineObj.append("diseaseName", diseaseName);
					request.setAttribute("vaccineData",vaccineObj);}}
				RequestDispatcher rd = request
						.getRequestDispatcher("/diseaseProp.jsp");
				rd.forward(request, response);
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

else if(action.equalsIgnoreCase("uploadbill")){	
	String contentType = null;
	byte[] data = null;
	String origFileName = null;
	String givenFileName = null;
	String attKey = null;
	InputStream stream = null;
	BufferedInputStream bis = null;
	
	File tempFile = null;

	boolean isMultipart = ServletFileUpload.isMultipartContent(request);		
	
	try {
		ServletFileUpload upload = new ServletFileUpload();				
		FileItemIterator iterator = upload.getItemIterator(request);

		while (iterator.hasNext()) {
			FileItemStream item = iterator.next();
			stream = item.openStream();
			if (contentType == null)
				contentType = item.getContentType();

			if (item.isFormField()) {
				if (item.getFieldName() == null)
					continue;						
										
				if (item.getFieldName().equals("billname")) {
					givenFileName = Streams.asString(stream);
				}						

				continue;
			}				

			origFileName = item.getName();
			// some browsers return the full file path !!
			if (origFileName != null) {
				origFileName = FilenameUtils.getName(origFileName);
			}

			bis = new BufferedInputStream(stream);						
			try{
				data = IOUtils.toByteArray(bis);
			}catch(Exception e){
				e.printStackTrace();						
				return;
			}		
			
			try{
				tempFile = new File(origFileName);
				FileUtils.writeByteArrayToFile(tempFile, data);

			}catch(Exception e){
				
			}
		
		}//while end

		if (data == null)
			return;

		
	} catch (FileUploadException e) {
		e.printStackTrace();
		response.addHeader("Content-Type", "text/html");
		response.addHeader("Cache-Control", "no-store, no-cache");	
	}			
	
	JSONObject medicalbill = new JSONObject();	
	

	try {
			
		medicalbill.put("language", "en");
		//medicalbill.put("file", "C:\\MedicalBill1");
		medicalbill.put("emailid", userSession.getAttribute("userEmail")
				.toString());
		medicalbill.put("medicalbillname", givenFileName);
		
		
		if (nodeAPI.sendImageOCR(medicalbill, tempFile)) {
			request.setAttribute("Message",
					"Medical Bill Successfully scanned and saved to the Database!");
			
			try {
				JSONArray userBills = nodeAPI.getMyBills(userSession
						.getAttribute("userEmail").toString());

				if (userBills != null) {
					// response.setContentType("application/json;charset=utf-8");
					request.setAttribute("userBills", userBills.toString());
					JSONObject j = userBills.getJSONObject(0);
					System.out.println(j.getString("medicalbillname")
							.toString());

				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			RequestDispatcher rd = request
					.getRequestDispatcher("/mybills.jsp");
			//request.setAttribute("billid", billid);
					
			// request.setAttribute("Message","Medical Bill is successfully scanned and stored in the database");
			rd.forward(request, response);
		} else {
			request.setAttribute("Message", "Medical Bill cannot be successfully scanned through OCR. Please try again!");
			RequestDispatcher rd = request
					.getRequestDispatcher("/uploadfile.jsp");
			
			rd.forward(request, response);
			
		}
		
	
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
}


	}

}
