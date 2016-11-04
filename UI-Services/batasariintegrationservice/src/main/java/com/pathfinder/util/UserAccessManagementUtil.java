package com.pathfinder.util;

import com.google.gson.Gson;
import com.pathfinder.exception.BatasariWSException;

public class UserAccessManagementUtil {

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
}
