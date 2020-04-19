package com.amsidh.mvc;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.amsidh.mvc.model.UserActivity;
import com.amsidh.mvc.model.UserDetail;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

public class AzureApp {

	public static final String storageConnectionString = // "UseDevelopmentStorage=true;";
			"DefaultEndpointsProtocol=https;" + "AccountName=pacmanfansdb20191009;"
					+ "AccountKey=vFyB4Q/Zgnkrf3adY6gyknVM9a4LC4lo7BGd1v0Nuv+3ek5F83Yr+V7Fiauxnxp2W3mxiJiN1ovMKO/2v8C2DA==";
	private final static String CONTENT_TYPE = "application/json";

	public static void main(String[] args) {
		System.out.println("Azure Blob storage quick start sample");
		CloudStorageAccount storageAccount;
		CloudBlobClient blobClient = null;
		CloudBlobContainer container = null;

		try {
			// Parse the connection string and create a blob client to interact with Blob
			// storage
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			blobClient = storageAccount.createCloudBlobClient();
			container = blobClient.getContainerReference("pacmanfanscontainer");

			// Create the container if it does not exist with public access.
			System.out.println("Creating container: " + container.getName());
			container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(),
					new OperationContext());

			UserDetail userDetail = new UserDetail(123, "amsidhlokhande", 100.00);
			
			
			

			// Getting a blob reference
						try {
				 List<UserActivity> listOfActivities = new ArrayList<>();
				 
				 listOfActivities.add(UserActivity.createUserActivity("amsidhlokhande", "Plant"));
				 listOfActivities.add(UserActivity.createUserActivity("amsidhlokhande", "Clothbag"));
				 listOfActivities.add(UserActivity.createUserActivity("amsidhlokhande", "Public Transport"));
				 listOfActivities.add(UserActivity.createUserActivity("amsidhlokhande", "Private Transport"));
				 listOfActivities.add(UserActivity.createUserActivity("amsidhlokhande", "Beef"));
				 
				 for(UserActivity activity: listOfActivities) {
					 
					

					 
					 StringBuffer fileName = createUserPath(userDetail);
						//fileName.append("UserMaster.json");
						fileName.append("TX_");
						fileName.append(System.currentTimeMillis());
						fileName.append(".json");
						
						 CloudBlockBlob blob = container.getBlockBlobReference(fileName.toString());

							blob.getProperties().setContentType(CONTENT_TYPE);

							System.out.println("Uploading the activity  file ");
						
						 String activityJson = fromJavaToJson(activity); 
						 InputStream stream = new
								 ByteArrayInputStream(activityJson.getBytes());
								 blob.upload(stream,stream.available()); 
					 
					 
				 }
				 
				 
				
				
				/*
				 * if (blob.exists()) {
				 * System.out.println("!!!Record already exists so update the record!!!");
				 * ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				 * blob.download(outputStream); String retrivedString = new
				 * String(outputStream.toByteArray()); System.out.println(retrivedString);
				 * UserDetail oldUserDetail = (UserDetail) fromJsonToJava(retrivedString,
				 * UserDetail.class); oldUserDetail.setCarbonValue(95.00); String
				 * updateduserDetailsJson = fromJavaToJson(oldUserDetail); InputStream
				 * updatedStream = new ByteArrayInputStream(updateduserDetailsJson.getBytes());
				 * blob.upload(updatedStream, updatedStream.available()); } else { String
				 * userDetailsJson = fromJavaToJson(userDetail); InputStream stream = new
				 * ByteArrayInputStream(userDetailsJson.getBytes()); blob.upload(stream,
				 * stream.available()); }
				 */
				
				
				
			} catch (StorageException storageException) {

				storageException.printStackTrace();

			}

			// Listing contents of container
			/*
			 * for (ListBlobItem blobItem : container.listBlobs()) {
			 * System.out.println("URI of blob is: " + blobItem.getUri()); }
			 */

		} catch (StorageException ex) {
			System.out.println(String.format("Error returned from the service. Http code: %d and error code: %s",
					ex.getHttpStatusCode(), ex.getErrorCode()));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			System.out.println("The program has completed successfully.");

		}
	}

	public static StringBuffer createUserPath(UserDetail userDetail) {
		StringBuffer fileName = new StringBuffer();
		fileName.append("userdetail");
		fileName.append(File.separator);
		fileName.append(userDetail.getUserName());
		fileName.append(File.separator);
		return fileName;
	}

	public static Object fromJsonToJava(String json, Class type)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		return jsonMapper.readValue(json, type);
	}

	public static String fromJavaToJson(Serializable object)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper jsonMapper = new ObjectMapper();
		return jsonMapper.writeValueAsString(object);
	}
}
