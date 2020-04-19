package com.amsidh.mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.BlobContainerPublicAccessType;
import com.microsoft.azure.storage.blob.BlobRequestOptions;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

public class ImageWriteToAzure {

	public static final String storageConnectionString = // "UseDevelopmentStorage=true;";
			"DefaultEndpointsProtocol=https;" + "AccountName=pacmanfansdb20191009;"
					+ "AccountKey=vFyB4Q/Zgnkrf3adY6gyknVM9a4LC4lo7BGd1v0Nuv+3ek5F83Yr+V7Fiauxnxp2W3mxiJiN1ovMKO/2v8C2DA==";
	private final static String CONTENT_TYPE = "application/octet-stream";

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

			File file = new File("C:\\Users\\amsid\\OneDrive\\Pictures\\Saved Pictures\\photo.jpg");

			// Getting a blob reference
			CloudBlockBlob blob = container.getBlockBlobReference("userdetail"+"/images/photo" + ".jpg");

			blob.getProperties().setContentType(CONTENT_TYPE);

			System.out.println("Uploading the sample file ");
			try {
				if (!blob.exists()) {
					
					InputStream stream = new FileInputStream(file);
					blob.upload(stream, stream.available());
				} 

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
	

}
