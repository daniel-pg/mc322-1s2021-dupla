package gameclient;

import gameserver.Options;

public interface IOptionsReceiver {
    public void listenOptions(Options options);
}
