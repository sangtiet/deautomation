package com.CucumberCraft.SupportLibraries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiUtils {

	private static String API_AuthCode;
	private static String XML_ElementValue;
	private static HashMap<String, Object> API_Response = new HashMap<String, Object>();

	private static String getValueXMLString(String Elementname, String xml)
			throws ParserConfigurationException, SAXException, IOException {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(xml));

			Document doc = builder.parse(src);
			XML_ElementValue = doc.getElementsByTagName(Elementname).item(0).getTextContent();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return XML_ElementValue;
	}

	public static String getAPIAuthenticationCode(String EndPointURL, String Username, String Password)
			throws MalformedURLException {
		try {
			// XML HTTP POST request
			int num;
			URL url = new URL(EndPointURL);
			String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\r\n"
					+ "<env:Envelope xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\r\n"
					+ "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n"
					+ "xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + "	<env:Body>\r\n"
					+ "		<n1:login xmlns:n1=\"urn:partner.soap.sforce.com\">\r\n" + "			<n1:username>"
					+ Username + "</n1:username>\r\n" + "			<n1:password>" + Password + "</n1:password>\r\n"
					+ "</n1:login>\r\n" + "		</env:Body>\r\n" + "</env:Envelope>";
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setDoOutput(true);
			httpConnection.setRequestMethod("POST");
			httpConnection.setRequestProperty("Content-Type", "text/xml");
			httpConnection.setRequestProperty("SoapAction", "login");
			OutputStreamWriter out = new OutputStreamWriter(httpConnection.getOutputStream());
			// Request Data
			out.write(xmlData);
			out.flush();
			out.close();
			// Return Response Body
			InputStreamReader reader = new InputStreamReader(httpConnection.getInputStream());
			StringBuilder buf = new StringBuilder();
			char[] cbuf = new char[2048];
			while (-1 != (num = reader.read(cbuf))) {
				buf.append(cbuf, 0, num);
			}
			String xmlResponseBody = buf.toString();
			// Read XML
			try {
				API_AuthCode = getValueXMLString("sessionId", xmlResponseBody);
			} catch (ParserConfigurationException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return API_AuthCode;
	}

	public static String createJsonRequestBody(Object entity) {
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = null;
		try {
			jsonBody = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonBody;
	}

	public static HashMap<String, Object> createHttpRequest(String method, String EndpointURL, String ContentType,
			Object JsonModel) throws Exception {
		String requestBody = null;
		StringEntity entity = null;

		HttpClient httpClient = HttpClientBuilder.create().build();
		// HttpGet request_GET = null;
		HttpPost request_POST = null;
		HttpPut request_PUT = null;
		HttpResponse response = null;

		switch (method) {
		case "GET":
			// Statements
			break; // optional

		case "POST":
			requestBody = createJsonRequestBody(JsonModel);
			System.out.println("Request Body:\n" + requestBody);
			System.out.println("!!!Sending HTTP request, please wait...");
			entity = new StringEntity(requestBody.toString());
			entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, ContentType));

			request_POST = new HttpPost(EndpointURL);
			request_POST.setHeader("Accept", ContentType);
			request_POST.setHeader("Content-Type", ContentType);
			request_POST.setEntity(entity);
			response = httpClient.execute(request_POST);
			break; // optional

		case "PUT":
			requestBody = createJsonRequestBody(JsonModel);
			System.out.println("Request Body:\n" + requestBody);
			System.out.println("!!!Sending HTTP request, please wait...");
			entity = new StringEntity(requestBody.toString());
			entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, ContentType));

			request_PUT = new HttpPut(EndpointURL);
			request_PUT.setHeader("Accept", ContentType);
			request_PUT.setHeader("Content-Type", ContentType);
			request_PUT.setEntity(entity);
			response = httpClient.execute(request_PUT);
			break; // optional
		}

		// Get Response HTTP
		int statusCode = response.getStatusLine().getStatusCode();

		// Return Result
		API_Response.clear();
		API_Response.put("StatusCode", String.valueOf(statusCode));
		if (statusCode == 200 || statusCode == 201) {
			BufferedReader reader = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
			String strResponse = reader.readLine();
			API_Response.put("ResponseBody", strResponse);
		} else
			API_Response.put("ResponseBody", "N/A");

		return API_Response;
	}
}
