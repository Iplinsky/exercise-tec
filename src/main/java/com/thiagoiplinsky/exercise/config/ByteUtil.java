package com.thiagoiplinsky.exercise.config;

public class ByteUtil {

	public static byte[] convertByteArray(Byte[] value) {
		byte[] byteValue = new byte[value.length];
		int i = 0;
		for (byte b : value) {
			byteValue[i++] = b;
		}
		return byteValue;
	}

	public static Byte[] convertByteArray(byte[] value) {
		Byte[] byteValue = new Byte[value.length];
		int i = 0;
		for (byte b : value) {
			byteValue[i++] = b;
		}
		return byteValue;
	}
}