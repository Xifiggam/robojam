package eu.robo.veit.robojam.yumiDriver;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by dbe on 20.05.2017.
 */
public class MRIConnector extends AsyncTask {

    private String _hostname;
    private int _port;
    private Socket _socket;

    public MRIConnector(String hostname, int port)
    {
        _hostname=hostname;
        _port=port;
    }

    private void connect()
    {

        try {
            _socket = new Socket("localhost", _port);
            System.out.println("Connected to port "+_port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void disconnect()
    {
        try {
            _socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendCommand(IRobotMessage cmd)
    {
        byte[] bytes = ProtocolProcessor.Serialize(cmd);
        if(bytes==null)
        {
            System.out.println("Buffer is null");
            return;
        }
    try {
        OutputStream outputStream = _socket.getOutputStream();

        outputStream.write(bytes);
        outputStream.flush();
        Thread.sleep(1000);
    }
         catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {
            switch ((String)params[0]){
                case "connect": connect(); break;
                case "sendCommand": sendCommand((IRobotMessage)params[1]);break;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
