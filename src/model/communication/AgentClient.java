package model.communication;

import java.io.BufferedOutputStream;
import java.net.Socket;

/**
 * Created by Vlad on 17/12/2016.
 * Represents the Client socket of an agent, it allows him to send messages to others
 */
class AgentClient extends AgentConnector {

    AgentClient(){};

    void send(int agentRecipient, String message){
        try {
            Socket socket = new Socket("localhost", AgentConnector.BASE_SERVER_PORT + agentRecipient);
            this.OS = socket.getOutputStream();
            this.BOS = new BufferedOutputStream(OS);

            this.BOS.write(message.getBytes());
            this.BOS.flush();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
