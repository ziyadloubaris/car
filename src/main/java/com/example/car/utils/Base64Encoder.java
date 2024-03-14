package com.example.car.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class Base64Encoder {
	public static String encodeMultipartFileToBase64(MultipartFile multipartFile) throws IOException {
		byte[] fileBytes = multipartFile.getBytes();
		return Base64.getEncoder().encodeToString(fileBytes);
	}
}
