package Server;

import SeaBattle.Engine;

import java.rmi.RemoteException;

public class serverRes implements serverImpl{
    int pNum = -1;
    int turn =0;
    Engine engine = new Engine();
    @Override
    public int connectPlayer() throws RemoteException {
        pNum += 1;
        return pNum;
    }
    @Override
    public void setShipOnField(int x1, int y1, int len, char type, int pNum)throws RemoteException{
        if(type == 'v'){
            engine.setShipOnFiled(pNum,x1,x1+len, y1, y1+1);
        }else{
            engine.setShipOnFiled(pNum,x1,x1+1, y1,y1+len);
        }
    }
    @Override
    public int whoSTurn() {
        return turn;
    }
    @Override
    public String playerMove(int pNum, int x, int y){
        int exT = turn;
        turn = engine.playersMove(pNum, x, y);
        return (exT == turn)? "Попали!" : " Не попали!";
    }
    @Override
    public boolean areGameStillContinue(int pNum){
        return engine.IsItNotFinished(pNum);
    }
    @Override
    public boolean amIWin(int pNum){
        return engine.winnerWinnerChikenDinner(pNum);
    }
    @Override
    public String strDesc1(int pNum){
        int [][] d1 = engine.getMyDesckOne(pNum);
        String st ="";
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(d1[i][j] == 0){
                    st+=" 0 ";
                }
                if(d1[i][j] == 1){
                    st+=" m ";
                }
                if(d1[i][j] == 2){
                    st+=" x ";
                }
            }
            st+="\n";
        }
        return st;
    }
    @Override
    public String strDesc2(int pNum){
        int [][] d1 = engine.getMyDesckTwo(pNum);
        String st ="";
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                if(d1[i][j] == 0){
                    st+=" 0 ";
                }
                if(d1[i][j] == 2){
                    st+=" x ";
                }
                if(d1[i][j]==3){
                    st+=" 7 ";
                }
            }
            st+="\n";
        }
        return st;
    }
}