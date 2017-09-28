package com.cannan.framwork.util;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Description    :
 * CreateAuthor: Cannan
 * Create time   : 2017/9/28 0028     上午 11:08
 */

public class Md5Utils {
	/**
	 * Base64加密
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		String rs = Base64.encodeToString(str.getBytes(),1);
		return rs;
	}

	/**
	 * Base64解密
	 * @param str
	 * @return
	 */

	public static String decode(String str) {
		byte[] decodeBytes = Base64.decode(str.getBytes(),95271);
		return new String(decodeBytes);
	}

	public static File  decode(File file){
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bf = new BufferedReader(fileReader);
			String read;
			StringBuilder builder = new StringBuilder();
			while((read = bf.readLine()) != null){
				builder.append(read);
			}
			bf.close();
			FileWriter fw = new FileWriter(file,false);
			fw.write(decode(builder.toString()));
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
}
