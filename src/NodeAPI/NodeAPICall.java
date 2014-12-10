package NodeAPI;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
public class NodeAPICall {
	private static final String NodeURL = "http://54.215.210.231:3000";
	String[] medicalRecord;
    private List<CptRecord> cptCollection;
    public static Map<String, List<CptRecord>> medicalBillMap;
    private boolean savedValueInMap = true;
    int medicalbillid = 0;
    JSONObject jsonFormattedObject;
    //private static final String NodeURL = "http://192.168.1.64:3000";
	public boolean createUser(JSONObject user) throws ClientProtocolException,
			IOException {
		HttpClient httpclient = new DefaultHttpClient();
		boolean flag = false;
		// System.out.println("node api called");
		HttpPost httppost = new HttpPost(NodeURL + "/addUser");
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("details", user.toString()));
		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		HttpResponse httpresponse = httpclient.execute(httppost);
		int code = httpresponse.getStatusLine().getStatusCode();
		if (code == 200) {
			flag = true;
		}
		System.out.println("Status code: " + code);

		return flag;
	}

	public boolean updateUser(JSONObject user) throws ClientProtocolException,
			IOException, JSONException {
		HttpClient httpclient = new DefaultHttpClient();
		boolean flag = false;
		// System.out.println("node api called");
		HttpPut httpput = new HttpPut(NodeURL + "/updateUser/"
				+ user.getString("emailid").toString());
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("details", user.toString()));
		httpput.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		HttpResponse httpresponse = httpclient.execute(httpput);
		int code = httpresponse.getStatusLine().getStatusCode();
		if (code == 200) {
			flag = true;
		}
		System.out.println("Status code: " + code);

		return flag;
	}

	public boolean getUserByAccount(String emailid)
			throws ClientProtocolException, IOException, JSONException {
		String sret = "";
		JSONObject user = null;
		JSONArray jsonarray = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(NodeURL + "/EmailExists/" + emailid);
		request.addHeader("Cache-Control", "no-cache");
		HttpResponse response = httpclient.execute(request);
		HttpEntity httpEntity = response.getEntity();
		InputStream in = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		StringBuilder str = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println("line : " + line);
			str.append(line + "\n");
		}
		in.close();

		sret = str.toString();

		if (sret != null) {
			System.out.println("String is not null " + sret);
			jsonarray = new JSONArray(sret);
			user = jsonarray.getJSONObject(0);

		}

		return Boolean.valueOf(user.getString("result"));
	}

	public boolean IsValidUser(String emailid, String pwd,
			HttpSession userSession) throws ClientProtocolException,
			IOException, JSONException {
		String sret = "";
		JSONObject user = null;
		boolean flag = false;

		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(NodeURL + "/getUserByAccount/" + emailid);
		//request.addHeader("Cache-Control", "no-cache");
		HttpResponse response = httpclient.execute(request);
		HttpEntity httpEntity = response.getEntity();
		InputStream in = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder str = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println("line : " + line);
			str.append(line + "\n");
		}
		in.close();
		sret = str.toString();
		if (sret != null) {
			System.out.println("String is not null " + sret);

			user = new JSONObject(sret);
			if (user.getString("user").toString().equals("Exists")) {
				if (user.getString("password").toString().equals(pwd)) {
					System.out.println("matched");
					flag = true;
					userSession.setAttribute("userEmail", emailid);
					userSession.setAttribute("userName",
							user.getString("username").toString());
					userSession.setAttribute("useraddress",
							user.getString("address").toString());
					userSession.setAttribute("usersecurityqn",
							user.getString("securityqn").toString());
					userSession.setAttribute("usersecurityans",
							user.getString("securityans").toString());
					userSession.setAttribute("userphno",
							user.getString("userphno").toString());
				}
			} else
				flag = false;

		}

		return flag;
	}

	public JSONObject getUserByEmailID(String emailid)
			throws ClientProtocolException, IOException, JSONException {
		String sret = "";
		JSONObject user = null;
		JSONArray jsonarray = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(NodeURL + "/getUserByEmail/" + emailid);
		request.addHeader("Cache-Control", "no-cache");
		HttpResponse response = httpclient.execute(request);
		HttpEntity httpEntity = response.getEntity();
		InputStream in = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		StringBuilder str = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println("line : " + line);
			str.append(line + "\n");
		}
		in.close();

		sret = str.toString();

		if (sret != null) {
			System.out.println("String is not null " + sret);
			jsonarray = new JSONArray(sret);
			user = jsonarray.getJSONObject(0);

		}

		return user;
	}

	public JSONArray getMyBills(String emailid) throws ClientProtocolException,
			IOException, JSONException {
		String sret = "";
		// JSONObject bills = null;
		JSONArray jsonarray = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(NodeURL + "/mybills/" + emailid);
		request.addHeader("Cache-Control", "no-cache");
		HttpResponse response = httpclient.execute(request);
		HttpEntity httpEntity = response.getEntity();
		InputStream in = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		StringBuilder str = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println("line : " + line);
			str.append(line + "\n");
		}
		in.close();

		sret = str.toString();

		if (sret != null) {
			System.out.println("String is not null " + sret);
			jsonarray = new JSONArray(sret);
			// bills = jsonarray.getJSONObject(0);

		}

		return jsonarray;
	}

	public void RunFraud(String billid) {

		String sret = "";
		JSONArray jsonarray = null;
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(NodeURL + "/getFraudById/"
				+ billid.toString());
		request.addHeader("Cache-Control", "no-cache");
		try {
			HttpResponse response = client.execute(request);
			HttpEntity httpEntity = response.getEntity();
			InputStream in = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				str.append(line + "\n");
			}
			in.close();
			sret = str.toString();
			if (sret != null) {
				System.out.println("String is not null " + sret);
				jsonarray = new JSONArray(sret);
				// jsonobject = jsonarray.getJSONObject(0);
				// adddata(jsonarray);
			}
		} catch (JSONException e) {

		} catch (Exception ex) {
			ex.printStackTrace();

			sret = "Error";
		}

	}

	public JSONObject GetBillByBillID(String billid)
			throws ClientProtocolException, IOException, JSONException {
		String sret = "";
		JSONArray jsonarray = null;
		JSONObject jsonobject = null;
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(NodeURL + "/getbillbybillid/"
				+ billid.toString());
		request.addHeader("Cache-Control", "no-cache");

		HttpResponse response = client.execute(request);
		HttpEntity httpEntity = response.getEntity();
		InputStream in = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder str = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			str.append(line + "\n");
		}
		in.close();
		sret = str.toString();
		if (sret != null) {
			System.out.println("String is not null " + sret);
			jsonarray = new JSONArray(sret);
			jsonobject = jsonarray.getJSONObject(0);
			// adddata(jsonarray);
		}
		return jsonobject;
	}

	public JSONArray GetFraudOnChargeBillID(String billid)
			throws ClientProtocolException, IOException, JSONException {
		String sret = "";
		JSONArray jsonarray = null;
		JSONObject jsonobject = null;
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(NodeURL + "/getFradOnChargeByBillID/"
				+ billid.toString());
		request.addHeader("Cache-Control", "no-cache");

		HttpResponse response = client.execute(request);
		HttpEntity httpEntity = response.getEntity();
		InputStream in = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder str = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			str.append(line + "\n");
		}
		in.close();
		sret = str.toString();
		if (sret != null) {
			System.out.println("String is not null " + sret);
			jsonarray = new JSONArray(sret);

			// adddata(jsonarray);
		}
		return jsonarray;
	}

	public JSONArray GetFraudOnProcedureBillID(String billid)
			throws ClientProtocolException, IOException, JSONException {
		String sret = "";
		JSONArray jsonarray = null;
		JSONObject jsonobject = null;
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(NodeURL + "/getFradOnProcedureByBillID/"
				+ billid.toString());
		request.addHeader("Cache-Control", "no-cache");

		HttpResponse response = client.execute(request);
		HttpEntity httpEntity = response.getEntity();
		InputStream in = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder str = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			str.append(line + "\n");
		}
		in.close();
		sret = str.toString();
		if (sret != null) {
			System.out.println("String is not null " + sret);
			jsonarray = new JSONArray(sret);

			// adddata(jsonarray);
		}
		return jsonarray;
	}

	public boolean ForgotPassword(String emailid)
			throws ClientProtocolException, IOException, JSONException {
		boolean flag = false;
		String sret = "";
		JSONArray jsonarray = null;
		JSONObject jsonobject = null;
		HttpClient client = new DefaultHttpClient();

		HttpGet request = new HttpGet(NodeURL + "/ForgotPassword/"
				+ emailid.toString());
		request.addHeader("Cache-Control", "no-cache");

		HttpResponse response = client.execute(request);
		HttpEntity httpEntity = response.getEntity();
		InputStream in = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder str = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			str.append(line + "\n");
		}
		in.close();
		sret = str.toString();
		if (sret != null) {
			System.out.println("String is not null " + sret);
			jsonarray = new JSONArray(sret);
			jsonobject = jsonarray.getJSONObject(0);
			if (jsonobject.getString("er").toString().equals("sent"))
				flag = true;
			else
				flag = false;
			// adddata(jsonarray);
		}
		return flag;

	}

	public void setUserInfo(String emailid, HttpSession userSession)
			throws ClientProtocolException, IOException, JSONException {
		String sret = "";
		JSONObject user = null;
		JSONArray jsonarray = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpGet request = new HttpGet(NodeURL + "/getUserByEmail/" + emailid);
		request.addHeader("Cache-Control", "no-cache");
		HttpResponse response = httpclient.execute(request);
		HttpEntity httpEntity = response.getEntity();
		InputStream in = httpEntity.getContent();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));

		StringBuilder str = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			System.out.println("line : " + line);
			str.append(line + "\n");
		}
		in.close();

		sret = str.toString();

		if (sret != null) {
			System.out.println("String is not null " + sret);
			jsonarray = new JSONArray(sret);
			user = jsonarray.getJSONObject(0);
			userSession.setAttribute("userName", user.getString("username")
					.toString());
			userSession.setAttribute("useraddress", user.getString("address")
					.toString());
			userSession.setAttribute("usersecurityqn",
					user.getString("securityqn").toString());
			userSession.setAttribute("usersecurityans",
					user.getString("securityans").toString());
			userSession.setAttribute("userphno", user.getString("phno")
					.toString());
		}

	}

	public boolean deleteUser(String emailid, HttpSession userSession)
			throws ClientProtocolException, IOException, JSONException {
		boolean flag = false;
		String sret = "";
		JSONObject user = null;
		JSONArray jsonarray = null;
		HttpClient httpclient = new DefaultHttpClient();
		HttpDelete request = new HttpDelete(NodeURL + "/deleteUser/" + emailid);
		request.addHeader("Cache-Control", "no-cache");
		HttpResponse response = httpclient.execute(request);
		int code = response.getStatusLine().getStatusCode();
		if (code == 200) {
			flag = true;
		}

		return flag;
	}
	
		public JSONArray GetFraudOnChargeUserID(String emailid)
				throws ClientProtocolException, IOException, JSONException {
			String sret = "";
			JSONArray jsonarray = null;
			JSONObject jsonobject = null;
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(NodeURL + "/getFradOnChargeByUserID/"
					+ emailid.toString());
			request.addHeader("Cache-Control", "no-cache");
			HttpResponse response = client.execute(request);
			HttpEntity httpEntity = response.getEntity();
			InputStream in = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				str.append(line + "\n");
			}
			in.close();
			sret = str.toString();
			if (sret != null) {
				System.out.println("String is not null " + sret);
				jsonarray = new JSONArray(sret);
			}
			return jsonarray;
		}

		public JSONArray GetFraudOnProcedureUserID(String emailid)
				throws ClientProtocolException, IOException, JSONException {
			String sret = "";
			JSONArray jsonarray = null;
			JSONObject jsonobject = null;
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(NodeURL + "/getFradOnProcedureByUserID/"
					+ emailid.toString());
			request.addHeader("Cache-Control", "no-cache");
			HttpResponse response = client.execute(request);
			HttpEntity httpEntity = response.getEntity();
			InputStream in = httpEntity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				str.append(line + "\n");
			}
			in.close();
			sret = str.toString();
			if (sret != null) {
				System.out.println("String is not null " + sret);
				jsonarray = new JSONArray(sret);
			}
			return jsonarray;
		}
		//disease propagation
				public JSONArray GetTwitterData(String country) throws ClientProtocolException, IOException, JSONException
				{
					String sret = "";
					JSONArray jsonarray = null;
					JSONObject jsonobject = null;
					HttpClient client = new DefaultHttpClient();
					
					HttpGet request = new HttpGet(NodeURL+"/diseasePropagation/" + country);
					request.addHeader("Cache-Control", "no-cache");
					
						HttpResponse response = client.execute(request);
						HttpEntity httpEntity = response.getEntity();
						InputStream in = httpEntity.getContent();
						BufferedReader reader = new BufferedReader(
								new InputStreamReader(in));
						StringBuilder str = new StringBuilder();
						String line = null;
						while ((line = reader.readLine()) != null) {
							str.append(line + "\n");
						}
						in.close();
						sret = str.toString();
						if (sret != null) {
							System.out.println("String is not null " + sret);
							jsonarray = new JSONArray(sret);
							//jsonobject = jsonarray.getJSONObject(0);
							
						}
						return jsonarray;
				}






		//vaccine recommendation
						public JSONArray GetVaccineData(String diseaseName) throws ClientProtocolException, IOException, JSONException
						{
							String sret = "";
							JSONArray jsonarray = null;
							JSONObject jsonobject = null;
							HttpClient client = new DefaultHttpClient();

							HttpGet request = new HttpGet(NodeURL+"/vaccineRecommendation/" + diseaseName.toString());
							request.addHeader("Cache-Control", "no-cache");
							
							//System.out.println(diseaseName.toString());
								HttpResponse response = client.execute(request);
								
								HttpEntity httpEntity = response.getEntity();
								InputStream in = httpEntity.getContent();
								BufferedReader reader = new BufferedReader(
										new InputStreamReader(in));
								StringBuilder str = new StringBuilder();
								String line = null;
								while ((line = reader.readLine()) != null) {
									str.append(line + "\n");
								}
								in.close();
								sret = str.toString();
								System.out.println(sret.length());
								System.out.println(sret);
								if (sret != null) {
									System.out.println("String is not null " + sret);
									if(sret.isEmpty())
										sret = null;
									jsonarray = new JSONArray(sret);
									//jsonobject = jsonarray.getJSONObject(0);
									
								}
								return jsonarray;
						}
						public boolean sendImageOCR(JSONObject jsonObject, File tempFile)
								throws ClientProtocolException, IOException, JSONException {					
							final String SERVICE_URL = "http://api.ocrapiservice.com/1.0/rest/ocr";
							final String PARAM_IMAGE = "image";
							final String PARAM_LANGUAGE = "language";
							final String PARAM_APIKEY = "apikey";						
							int responseCode;				
							String apikey = "e7H75azKsJ";					
							final HttpClient httpclient = new DefaultHttpClient();
							final HttpPost httppost = new HttpPost(SERVICE_URL);			
							String language = (String) jsonObject.get("language");
							//String filePath = (String) jsonObject.get("filepath");
							String emailID =  (String) jsonObject.get("emailid");
							String medicalbillname = (String) jsonObject.get("medicalbillname");						
							String result = null;
									String finalString;					
							StringBuilder sb = null;
							final FileBody image = new FileBody(tempFile);
							final MultipartEntity reqEntity = new MultipartEntity();
							try {
								reqEntity.addPart(PARAM_IMAGE, image);
								reqEntity.addPart(PARAM_LANGUAGE, new StringBody(language));
								reqEntity.addPart(PARAM_APIKEY, new StringBody(apikey));
							httppost.setEntity(reqEntity);
								final HttpResponse response = httpclient.execute(httppost);
								responseCode = response.getStatusLine().getStatusCode();
								if (responseCode == 200) {
									final HttpEntity resEntity = response.getEntity();
									sb = new StringBuilder();
									if (resEntity != null) {
										final InputStream stream = resEntity.getContent();
										byte bytes[] = new byte[4096];
										int numBytes;
										while ((numBytes = stream.read(bytes)) != -1) {
											if (numBytes != 0) {
												sb.append(new String(bytes, 0, numBytes));}}	}
										} 
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							if(sb != null){
							result = sb.toString();
							finalString = result.replaceAll("\t", "");
							finalString = finalString.replaceAll("\\s+", "");

							try {
						    tempFile.delete();
						} catch (Exception e) {
						    e.printStackTrace();
						} 
						
							ocrStringtoList(finalString);
							medicalRecordListToHashMap(medicalRecord);
							try {
								GetMaxMedicalBIllID();
							} catch (IOException | JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							generateJsonObject(emailID, medicalbillname);
							
							try {
								saveMedicalBillTask(jsonFormattedObject);
							} catch (IOException | JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							return true;
							
							}
							else{
												
								return false;
							}
							
							
							
							
						} 
						
						public void ocrStringtoList(String medicalData) {
							medicalRecord = medicalData.split("\\${2}|\\${1}");					
						}
						
						
						public void medicalRecordListToHashMap(String[] medicalRecord) {
							String key = null;

							medicalBillMap = new HashMap<String, List<CptRecord>>();

							int sizeOfArray = medicalRecord.length;
							String[] medicalRecordFormatted = null;

							
							if(sizeOfArray != 0){
							String totalPrice = medicalRecord[sizeOfArray - 1];					

							medicalRecordFormatted = Arrays.copyOfRange(medicalRecord, 6,
									(sizeOfArray - 1));
							}
							
							int numberOfRecords = (int) Math.round(medicalRecordFormatted.length / 5.0);

							List<List<String>> splittedArray = new ArrayList<List<String>>();
							List<String> recordRow = null;
							int count = -1;
							for (int i = 0; i < numberOfRecords; i++) {
								recordRow = new ArrayList<String>();
								for (int j = 0; j < 5; j++) {
									count++;
									if(count < (medicalRecordFormatted.length) && medicalRecordFormatted[count] != null ) {
									recordRow.add(medicalRecordFormatted[count]);
									}
									else{
										break;
									}
								}
								if(recordRow != null && !(recordRow.isEmpty())){
								splittedArray.add(recordRow);
								}
							}

							for (int i = 0; i < numberOfRecords; i++) {

								String nextKey = null;
								boolean lastRecord = false;

								CptRecord cptRecord = new CptRecord();
								List<String> rowFromSplittedArray = splittedArray.get(i);
								List<String> nexRowFromSplittedArray = null;

								if (i != (numberOfRecords - 1)) {
									nexRowFromSplittedArray = splittedArray.get(i + 1);
								} else {
									lastRecord = true;
								}

								if (savedValueInMap) {
									key = rowFromSplittedArray.get(0);
									cptCollection = new ArrayList<CptRecord>();
									savedValueInMap = false;
								}

								if (nexRowFromSplittedArray != null) {
									nextKey = nexRowFromSplittedArray.get(0).replaceAll("\t", "");
								}

								// if (key != null) {//create a new ArrayList for new ICD
								// cptCollection = new ArrayList<CptRecord>();
								// }

								cptRecord.setIcdDescription(rowFromSplittedArray.get(1));
								cptRecord.setCptCode(Integer.parseInt(rowFromSplittedArray.get(2)));
								cptRecord.setCptDescription(rowFromSplittedArray.get(3));
								cptRecord.setCharge(rowFromSplittedArray.get(4).replaceAll("\\r\\n|TOTALS|TOTAL", ""));

								cptCollection.add(cptRecord);

								if ((nextKey != null && !nextKey.isEmpty()) || lastRecord) { // check
																								// if
																								// the
																								// next
																								// key
																								// is
																								// null;
									medicalBillMap.put(key, cptCollection);
									savedValueInMap = true;
								}
							}
						

						}
						
						
						public void GetMaxMedicalBIllID() throws ClientProtocolException, IOException, JSONException
						{

							HttpClient httpclient = new DefaultHttpClient();
							HttpGet originalUrl = new HttpGet(
									"http://54.215.210.231:3000/getMaxMedicalBillId");
							String sret = "";
							HttpResponse response;
							try {
								response = httpclient.execute(originalUrl);

								HttpEntity httpEntity = response.getEntity();
								InputStream in = httpEntity.getContent();
								BufferedReader reader = new BufferedReader(
										new InputStreamReader(in));
								StringBuilder str = new StringBuilder();
								String line = null;
								while ((line = reader.readLine()) != null) {
									str.append(line + "\n");
								}
								in.close();
								sret = str.toString();
								sret = sret.replaceAll("[^\\d.]", "");
								if (sret != null) {
									medicalbillid = Integer.parseInt(sret);
									
									medicalbillid++;
									System.out.println("String: " + sret);
									
								}
							} catch (Exception ex) {
								ex.printStackTrace();						
								sret = "Error";
							}
							
						}
						
						
						public Timestamp GetCurrentTimeStamp() 
						{
						    
							 Date date= new Date();
							 return new Timestamp(date.getTime());
						    
						}
						
						
						public void generateJsonObject(String emailID, String medicalbillname) {
							
							Map<String, List<CptRecord>> record = medicalBillMap;
							JSONObject oneJsonObject = new JSONObject();
							JSONObject twoJsonObject = new JSONObject();
							
							

							JSONArray threeJsonArray = new JSONArray();
							Gson gson = new Gson();
							try {
								oneJsonObject.put("medicalbillid", medicalbillid);
								oneJsonObject.put("emailid", emailID);								
								oneJsonObject.put("medicalbillname", medicalbillname);
								oneJsonObject.put("Timestamp", GetCurrentTimeStamp());

								Iterator it = record.entrySet().iterator();
								while (it.hasNext()) {
									Map.Entry pairs = (Map.Entry) it.next();
									String key = (String) pairs.getKey();
									JSONObject threeJsonObject = new JSONObject();
									threeJsonObject.put("icdcode", key);
									ArrayList cptArray = (ArrayList) pairs.getValue();

									JSONArray jsonArray = new JSONArray();
									for (int i = 0; i < cptArray.size(); i++) {
										CptRecord cptRecord = (CptRecord) cptArray.get(i);
										
										String charge = cptRecord.getCharge();
										charge = charge.replaceAll("\\r\\n|\\bTOTAL\\b", "");
										cptRecord.setCharge(charge);
										
										if(!(cptRecord.getIcdDescription().isEmpty())){
										threeJsonObject.put("descriptionicd", cptRecord.getIcdDescription());
										}
										
										String cptString = gson.toJson(cptRecord);					
										JSONObject obj = new JSONObject(cptString);
										jsonArray.put(obj);
									}
									
									//String str = jsonArray.toString();
									
//									str = str.replaceAll("\r\n|\n|\n\r", "");
//									JSONArray array = new JSONArray(str);
									threeJsonObject.put("cpts", jsonArray);
									threeJsonArray.put(threeJsonObject);
								}
								oneJsonObject.put("icd", threeJsonArray);

							} catch (JSONException e) {
								e.printStackTrace();
							}
							
							//String str = oneJsonObject.toString().replaceAll("\\r" , "");
							//String str = oneJsonObject.toString().replaceAll("(\r\n|\n|\n\r)", "");
							jsonFormattedObject =  oneJsonObject;			

						}
						
						
						public void saveMedicalBillTask(JSONObject jsonObject)
								throws ClientProtocolException, IOException, JSONException {
							
						Integer code;
						HttpClient httpclient = new DefaultHttpClient();

						String url = "http://54.215.210.231:3000/addmedicalbill";
						

						String sret = "";

						HttpPost httppost = new HttpPost(url);
						httppost.addHeader("Cache-Control", "no-cache");

						try {
							List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
							nameValuePairs.add(new BasicNameValuePair("details", jsonObject.toString() ));
							// httppost.setHeader("Content-Type", "application/json");
							httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						
							System.out.println("namevalue: " + nameValuePairs);

							HttpResponse httpresponse = httpclient.execute(httppost);
							 code = httpresponse.getStatusLine().getStatusCode();
							System.out.println("Status code: " + code);
						} catch (Exception ex) {
							ex.printStackTrace();
							sret = "Error";
						}

						}


		
}
