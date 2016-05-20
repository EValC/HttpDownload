package HttpDownload;

import java.io.*;
import java.net.*;

public class HttpDownload 
{
	 private static final int BUFFER_SIZE = 4096 ;
	    public static void downloadFile(String urlfile, String dir)
	            throws IOException 
	    {
	        URL url = new URL(urlfile);
	        HttpURLConnection con = (HttpURLConnection) url.openConnection();
	        con.setRequestMethod("GET");
	        con.setRequestProperty("X-COUPA-API-KEY", "096eff7825f0a7c6914f03af66dfb7ce3a5a2dc2");
	        con.setRequestProperty("Accept", "application/xml");
	  		String urlParameters = "X-COUPA-API-KEY:096eff7825f0a7c6914f03af66dfb7ce3a5a2dc2&Accept:application/xml";
	  		con.setRequestProperty("content-type","application/x-www-form-urlencoded");
	        int responseCode = con.getResponseCode();
	        if (responseCode == HttpURLConnection.HTTP_OK) 
	        {
	            String filename = "";
	            String disposition = con.getHeaderField("Content-Disposition");
	            String contentType = con.getContentType();
	            int contentLength = con.getContentLength();
	            if (disposition != null) 
	            {
	                int index = disposition.indexOf("filename=");
	                if (index > 0) 
	                {
	                    filename = disposition.substring(index + 10,disposition.length() - 1);
	                }
	            } else 
	            {
	                
	                filename = urlfile.substring(urlfile.lastIndexOf("/") + 1, urlfile.length());
	            }
	            System.out.println("Content-Type = " + contentType);
	            System.out.println("Content-Disposition = " + disposition);
	            System.out.println("Content-Length = " + contentLength);
	            System.out.println("filename = " + filename);
	            System.out.println("Post parameters : " + urlParameters);
	            InputStream inputStream = con.getInputStream();
	            String saveFilePath = dir + File.separator + filename;
	            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
	            int bytesRead = -1;
	            byte[] buffer = new byte[BUFFER_SIZE];
	            while ((bytesRead = inputStream.read(buffer)) != -1) 
	            {
	                outputStream.write(buffer, 0, bytesRead);
	            }
	            outputStream.close();
	            inputStream.close();
	            System.out.println("File downloaded. IF FILE IS ALREADY THERE WITH SAME NAME THEN IT IS REPLACED");
	        } else 
	        {
	            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
	        }
	        con.disconnect();
	    }
}