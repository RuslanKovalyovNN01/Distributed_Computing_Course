package SeaBattle;

public class Engine {
    int [][][] players = new int[2][10][10];
    int [] shipsCount = new int[2];
    public Engine(){
        for(int k =0;k<2;k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    players[k][i][j] = 0;
                }
            }
            shipsCount[k] = 0;
        }
    }
    public void setShipOnFiled(int pNum, int a, int b, int c, int d){
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        for(int i=a;i<b;i++){
            for(int j=c;j<d;j++){
                players[pNum][i][j] = 1;
                shipsCount[pNum]+=1;
            }
        }
    }
    public int playersMove(int pNum, int x, int y){
        if(players[1- pNum][x][y] == 1){
            players[1- pNum][x][y] = 2;
            shipsCount[1-pNum]-=1;
            return pNum;
        }else{
            players[1- pNum][x][y] = 3;
            return 1-pNum;
        }
    }
    public boolean IsItNotFinished(int pNum){
        return shipsCount[pNum]!=0;
    }
    public boolean winnerWinnerChikenDinner(int pNum){
        return shipsCount[pNum]>0;
    }
    public int[][] getMyDesckOne(int pNum){
        return players[pNum];
    }
    public int[][] getMyDesckTwo(int pNum){
        int[][] temp =  new int[10][10];
        for(int i=0;i<10;i++){
            for( int j=0;j<10;j++){
                if(players[1 - pNum][i][j]!=1)temp[i][j] = players[1-pNum][i][j];
                else  temp[i][j] = 0;
            }
        }
        return temp;
    }
}
