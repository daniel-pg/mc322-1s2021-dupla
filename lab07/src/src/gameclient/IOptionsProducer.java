package gameclient;

import gameserver.Options;

public interface IOptionsProducer {
    public void addOptionsListener(IOptionsReceiver listener);
    public void fireOptions(Options options);
}
