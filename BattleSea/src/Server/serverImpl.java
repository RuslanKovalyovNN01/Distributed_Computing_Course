package Server;
import java.rmi.*;
public interface serverImpl extends Remote{
    public int connectPlayer() throws RemoteException;
    void setShipOnField(int x1, int y1, int len, char type, int pNum)throws RemoteException;
    int whoSTurn() throws RemoteException;
    String playerMove(int pNum, int x, int y) throws RemoteException;
    boolean areGameStillContinue(int pNum) throws RemoteException;
    boolean amIWin(int pNum) throws RemoteException;
    String strDesc1(int pNum) throws RemoteException;
    String strDesc2(int pNum) throws RemoteException;
}
