package com.khaled.BipFrontend.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	private static final String ABS_PATH = "D:\\Khaled\\sts-workspace\\BIP\\BipFrontend\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = "";

	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);

	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		System.err.println("REAL_PATH: " + REAL_PATH);
		logger.info("REAL_PATH" + REAL_PATH);

		// Create the directories if don't exist
		if (!new File(ABS_PATH).exists()) {
			System.err.println("Creating ABS_PATH directory...");
			new File(ABS_PATH).mkdirs();
		}

		if (!new File(REAL_PATH).exists()) {
			System.err.println("Creating REAL_PATH directory...");
			new File(REAL_PATH).mkdirs();
		}
		
		// uploading the files to server and project 
		try {
			System.err.println("Creating File...");
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			System.err.println("Created...");
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
}
