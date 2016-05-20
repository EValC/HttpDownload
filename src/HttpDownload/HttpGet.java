package HttpDownload;

import java.io.*;

public class HttpGet 
{
	 public static void main(String[] args) 
	 {
	        String urlfile = "https://knott-demo.coupacloud.com/api/attachments/retrieve?id=12819";
	        String dir = "C://Z";
	        try 
	        {
	            HttpDownload.downloadFile(urlfile, dir);
	        } catch (IOException ex) 
	        {
	            ex.printStackTrace();
	        }
	    }
}