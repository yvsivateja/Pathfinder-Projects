package com.pathfinder.util;

import java.text.DecimalFormat;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderRequest;
import com.google.code.geocoder.model.GeocoderResult;
import com.google.code.geocoder.model.LatLng;
import com.google.gson.Gson;
import com.pathfinder.exception.BatasariWSException;

public class UserAccessManagementUtil {

	public static String encrypt(String cleartext) throws Exception {
		return PasswordEncrypter.encrypt(cleartext);
	}

	public static String decrypt(String encrypted) throws Exception {
		return PasswordEncrypter.decrypt(encrypted);
	}

	public static boolean checkIsEmpty(String value) {
		try {
			if (!value.isEmpty()) {
				return false;
			} else {
				return false;
			}
		} catch (NullPointerException ex) {
			return true;
		}
	}

	public static <T> Object convertToJava(String object, Class<T> className) {
		try {
			Gson jsonObj = new Gson();
			return jsonObj.fromJson(object, className);
		} catch (Exception e) {
			throw new BatasariWSException("102",
					"Server Encountered error,please try after sometime", false);
		}
	}

	public static String convertToJson(Object classObject) {
		try {
			Gson jsonObj = new Gson();
			return jsonObj.toJson(classObject);
		} catch (Exception e) {
			throw new BatasariWSException("102",
					"Server Encountered error,please try after sometime", false);
		}
	}

	public static String getGeoCodeAddress(String latitude, String longitude) {
		try {
			if (latitude.contentEquals("0.0")) {
				return "Not known";
			}
			final Geocoder geocoder = new Geocoder();
			LatLng latLng = new LatLng(latitude, longitude);
			GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
					.setLocation(latLng).setLanguage("en").getGeocoderRequest();
			GeocodeResponse geocodeResponse = geocoder.geocode(geocoderRequest);
			String address = "";
			for (GeocoderResult geocoderResult : geocodeResponse.getResults()) {

				address += geocoderResult.getFormattedAddress() + "\n";
				break;
			}
			return address;
		} catch (Exception e) {
			System.err.println("Error Getting Address : " + e.getMessage());
			return "Not known";
		}
	}

	public static String haverSineDistance(String latitude1, String longitude1,
			String latitude2, String longitude2) {
		try {
			if (latitude1.contentEquals("0.0")) {
				return "Not calculated";
			} else if (latitude2.contentEquals("0.0")) {
				return "0 kms";
			}
			// convert to radians
			double lat1 = Double.parseDouble(latitude1);
			double lng1 = Double.parseDouble(longitude1);
			if (longitude1 == null || longitude1 == "") {
				latitude2 = latitude1;
				longitude2 = longitude1;
			}
			double lat2 = Double.parseDouble(latitude2);
			double lng2 = Double.parseDouble(longitude2);

			lat1 = Math.toRadians(lat1);
			lng1 = Math.toRadians(lng1);
			lat2 = Math.toRadians(lat2);
			lng2 = Math.toRadians(lng2);

			Double earthRadius = 6371.0;

			double dlon = lng2 - lng1;
			double dlat = lat2 - lat1;

			double a = Math.pow((Math.sin(dlat / 2)), 2) + Math.cos(lat1)
					* Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);

			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			Double distanceInKms = earthRadius * c;
			DecimalFormat df = new DecimalFormat("#0.###");
			return df.format(distanceInKms) + " kms";
		} catch (Exception e) {
			System.err.println("Error Calc distance : " + e.getMessage());
			return "Unknown";
		}
	}
}
