package gameserver;

public interface IContentReceiver {
    public void noticeContentUpdate();
    public void setContentProducer(IContentProducer contentProducer);
}
