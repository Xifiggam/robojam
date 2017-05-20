package eu.robo.veit.robojam;

import android.os.AsyncTask;

import java.io.*;
import java.net.*;
import java.util.concurrent.TimeUnit;

public class URDriver extends AsyncTask implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4102899145656714814L;

	public URDriver() {
	}


	@Override
	protected Object doInBackground(Object[] params) {

		int pos  = (int)params[0];
		Socket socket;
		DataInputStream recv;
		PrintStream send;
		//byte[] buffer = new byte[1024];
		String host = "192.168.1.101";
//		double a = 1.3962634015954636;
//		double v = 1.0471975511965976;


		try {
			socket = new Socket(host, 30002);
			recv = new DataInputStream(socket.getInputStream());
			send = new PrintStream(socket.getOutputStream());

			TimeUnit.MILLISECONDS.sleep(50);
			send.println("set_digital_out(2,True)");
			TimeUnit.MILLISECONDS.sleep(100);
			send.println("set_digital_out(7,True)");
			moveJ(send,-89.3, -109.7 , -68.5 , -2.2 , 89.0 , 0.81); //NULLPUNKT (0)
			TimeUnit.SECONDS.sleep(3);
			moveToPosition(send,pos);

			send.println("set_digital_out(2,False)");
			TimeUnit.MILLISECONDS.sleep(50);
			send.println("set_digital_out(7,False)");
			TimeUnit.SECONDS.sleep(1);

			send.close();
			recv.close();
			socket.close();


		}
		catch (UnknownHostException e) {
			System.err.println("Can't resolve host: " + host);
		}
		catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't get I/O for the connection to: " + host);
		}
		catch(Exception e) {
			System.err.println(e);
		}
		return null;
	}

	public static void moveToPosition(PrintStream send, int pos) throws InterruptedException {
		switch (pos){
			case 0:  moveJ(send,-89.3, -109.7 , -68.5 , -2.2 , 89.0 , 0.81);	break;
			case 1:  moveJ(send,-10.4, -146.2 , -42.9 , 8.35 , 12.4 , 0.83);	break;
			case 2:  moveJ(send,8.4, -119.0 , 0.67 , -62.9 , -7.72 , 0.7);		break;
			case 3:  moveJ(send,-78.3, -107.0 , 17.7 , -94.51 , 68.96 , 0.77);	break;
			case 4:  moveJ(send,-88.9, -163.2 , -61.2 , 46.4 , 89.0 , 0.76);	break;
		}
	}

	public static void moveJ(PrintStream send, double a, double b, double c, double d, double e, double f) throws InterruptedException {
		a = a * Math.PI /180;
		b = b * Math.PI /180;
		c = c * Math.PI /180;
		d = d * Math.PI /180;
		e = e * Math.PI /180;
		f = f * Math.PI /180;

		send.println("movej([" + a + "," + b + "," + c + ","+ d + ","+ e + ","+ f + "], a=1.55, v=0.55)");
		TimeUnit.SECONDS.sleep(5);
	}

}
