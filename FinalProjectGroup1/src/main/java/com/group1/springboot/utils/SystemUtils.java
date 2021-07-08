package com.group1.springboot.utils;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Blob;
import java.sql.Clob;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import org.springframework.core.io.ClassPathResource;

public class SystemUtils {
	
	// 常數固定大寫
	// SystemUtils.getExtFilename
	public static final String PLACE_IMAGE_FOLDER = "C:\\images\\place";
	public static final String NO_IMAGE_PATH = "\\static\\images\\default.jpg";
	
	//給我一個檔名，我給你一個副檔名
	//檔案名稱假如是 abc.jpg    a是index 0，所以 . 是index 3
	public static String getExtFilename(String filename) {
		int n = filename.lastIndexOf(".");
		if(n >= 0 ) {
			return filename.substring(n);
		}else {
			return "";
		}
		
	}
	
	//只要有下面這兩個參數就可以轉成data:
	//這邊可以送回預設圖片
	public static String blobToDataProtocol(String mimeType, Blob picture) {
		if(picture == null || mimeType == null) {
			
			picture= pathToBlob(NO_IMAGE_PATH);
			mimeType="image/jpg";
		}
		
		
		StringBuffer result = new StringBuffer("data:" + mimeType + ";base64,");
		try(
			InputStream is = picture.getBinaryStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		){
			
			int len = 0;
			byte[] b = new byte[81920]; //512的整數即可
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len); //此敘述的口訣: A.O.L.(array, offset, length)
			}
			byte[] bytes = baos.toByteArray();
			//Encoder是Base64裡面的一個類別
			Base64.Encoder be = Base64.getEncoder();
			//be.encode(bytes))-> 將base64轉出來的位元組編碼轉成string陣列
			result.append(new String(be.encode(bytes)));
			
//			byte[] ba = be.encode(bytes);
//			String tmp = new String(ba,"UTF-8");
//			result.append(tmp);
//			
			
			
			//解碼成位元組陣列
//			byte[] decoded = Base64.getDecoder().decode(data);
			//將位元組陣列轉為字串
//			result += new String(decoded, "UTF-8"); //Dati8t
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return result.toString();
	}
	
	
	
	
	//把靜態資源的資料轉成blob
	public static Blob pathToBlob(String path) {
		Blob blob = null;

		try {
			ClassPathResource cpr = new ClassPathResource(path);
			File file = cpr.getFile();
			if (!file.exists()) {
				return null;
			}
			// 要把inputStream這個圖片檔轉成位元組陣列
			// 從 InputStream讀，往ByteArrayOutputStream寫
			InputStream is = cpr.getInputStream();
			// 下放是寫到記憶體
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// 下方這欄寫到硬體裡
//			FileOutputStream fos = new FileOutputStream("...");

			int len = 0;
			// 昇陽java預設值 81920
			byte[] b = new byte[81920]; //512的整數即可
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len); //此敘述的口訣: A.O.L.
			}
			byte[] data = baos.toByteArray();
			//SerialBlob ???
			blob=new SerialBlob(data);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return blob;
	}		
	
		//比較會用到的方法
		public static Blob inputStreamToBlob(InputStream is) {
			Blob blob = null;
			
			try {
//				ClassPathResource cpr = new ClassPathResource(path);
//				File file = cpr.getFile();
//				if (!file.exists()) {
//					return null;
//				}
//				InputStream is = cpr.getInputStream();
				// 下放是寫到記憶體
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				// 下方這欄寫到硬體裡
//			FileOutputStream fos = new FileOutputStream("...");
				
				int len = 0;
				byte[] b = new byte[81920]; //512的整數即可
				while ((len = is.read(b)) != -1) {
					baos.write(b, 0, len); //此敘述的口訣: A.O.L.
				}
				byte[] data = baos.toByteArray();
				blob=new SerialBlob(data);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return blob;
	}
	//處理文字
	public static Clob pathToClob(String path) {
		Clob clob = null;

		try {
			ClassPathResource cpr = new ClassPathResource(path);
			File file = cpr.getFile();
			if (!file.exists()) {
				return null;
			}
			//如果檔案存在的話做下列的動作
			InputStream is = cpr.getInputStream();
			//下方把inputStream轉成字元
			InputStreamReader isr = new InputStreamReader(is);
			// 下放是寫到記憶體
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			//new = Alt+/
			//位元組陣列
			CharArrayWriter caw = new CharArrayWriter();
			// 下方這欄寫到硬體裡
//			FileOutputStream fos = new FileOutputStream("...");

			int len = 0;
			char[] b = new char[81920]; //512的整數即可
			while ((len = isr.read(b)) != -1) {
				caw.write(b, 0, len); //此敘述的口訣: A.O.L.
			}
			char[] data = caw.toCharArray();
			clob=new SerialClob(data);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return clob;
	}
	
	public static Clob StringToClob(String str) {
		Clob clob = null;
		
		try {
			
			char[] data = str.toCharArray();
			clob=new SerialClob(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clob;
	}
}
