package model.communication.events;

/**
 * Created by Vlad on 17/12/2016.
 * Defines a method to handle messages
 */
public interface MessageReceivedListener{
    public void messageReceived(MessageReceivedEvent event);
}
