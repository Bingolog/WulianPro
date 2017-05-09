package android_serialport_api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Looper;

public class WmSocket {
	 InputStream in;
	 OutputStream out;
	 public boolean isConnected = false;
	    Socket wmSocket = null;

	public WmSocket(final String ip, final int port) {
		
		 if (!isConnected) {
	            new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    Looper.prepare();
	                 // TODO Auto-generated constructor stub
	          		  try {
	          			 wmSocket = new Socket(ip, port);
	          			 //获取输入流
	                      in = wmSocket.getInputStream();
	                      out = wmSocket.getOutputStream();
	          		} catch (UnknownHostException e) {
	          			// TODO Auto-generated catch block
	          			e.printStackTrace();
	          		} catch (IOException e) {
	          			// TODO Auto-generated catch block
	          			e.printStackTrace();
	          		}
	                }
	            }).start();
	        } else {
	            try {
	                if (wmSocket != null) {
	                    wmSocket.close();
	                    wmSocket = null;
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	          
	            isConnected = false;
	        }
		
		 
	}
	
	// Getters and setters
	public InputStream getInputStream() {
		return in;
	}

	public OutputStream getOutputStream() {
		return out;
	}
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
