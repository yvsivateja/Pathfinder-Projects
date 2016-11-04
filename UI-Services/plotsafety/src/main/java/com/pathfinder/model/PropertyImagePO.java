package com.pathfinder.model;

import java.io.UnsupportedEncodingException;

import org.springframework.security.crypto.codec.Base64;

public class PropertyImagePO {

	private String propertyImageID;
	private String propertyID;
	private String imageName;
	private byte[] image;

	public String getPropertyImageID() {
		return propertyImageID;
	}
	public void setPropertyImageID(String propertyImageID) {
		this.propertyImageID = propertyImageID;
	}
	public String getPropertyID() {
		return propertyID;
	}
	public void setPropertyID(String propertyID) {
		this.propertyID = propertyID;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getEncodedImage() throws UnsupportedEncodingException {
		byte[] encodeBase64 = Base64.encode(image);
		String base64Encoded = new String(encodeBase64, "UTF-8");
		return base64Encoded;
	}
}
