package model.communication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Vlad on 17/12/2016.
 * Provides common parameters (streams) in order to establish communication
 */
class AgentConnector {
    static int BASE_SERVER_PORT = 4000;

    InputStream IS;
    OutputStream OS;
    BufferedInputStream BIS;
    BufferedOutputStream BOS;
}
