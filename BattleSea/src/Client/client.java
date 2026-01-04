package Client;
import Server.serverImpl;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.lang.Math;
public class client {
    boolean initType = false;
    public serverImpl stub;
    public char fl = 'a';
    public int Number;
    Scanner in = new Scanner(System.in);
    public client() {
        try {
            // Getting the registry
            Registry registry = LocateRegistry.getRegistry(1001);

            // Looking up the registry for the remote object
            stub = (serverImpl) registry.lookup("BattleShip");
            Number = stub.connectPlayer();
            // Calling the remote method using the obtained object
            // System.out.println("Remote method invoked");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
    public serverImpl getStub(){
        return stub;
    }
    public void initShips() throws RemoteException {
        if(initType) {
            System.out.println("Пожалуйста укажите куда расставить ваши корабли: (стартовая позиция + вертикально или горизонтально. Пример a0 v");
            for (int i = 4; i > 0; i--) {
                for (int j = 1; j < 6 - i; j++) {
                    printShips();
                    System.out.print(i);
                    System.out.println("-палубный");
                    String line[] = in.nextLine().split(" ");
                    stub.setShipOnField(((int) (line[0].charAt(0)) - (int) fl), Character.getNumericValue(line[0].charAt(1)), i, line[1].charAt(0), Number);
                }
            }
        }else{
            stub.setShipOnField(0,0, 4, 'v', Number);
            for (int i = 3; i > 0; i--) {
                for (int j = 1; j < 6 - i; j++) {
                    stub.setShipOnField((int) ((Math.random() * (5 - 0)) + 0),(int) ((Math.random() * (9 - 0)) + 0), i, 'v', Number);
                }
            }
            printShips();
        }
    }
    public void myMove() throws RemoteException {
        System.out.println("Выберите куда нанести удар");
        String cm = in.nextLine();
        System.out.println(stub.playerMove(Number,((int)(cm.charAt(0)) -(int)fl), Character.getNumericValue(cm.charAt(1))));
        printShips();
    }
    public void printShips() throws RemoteException{
        System.out.println(stub.strDesc1(Number));
        System.out.println(stub.strDesc2(Number));
    }
    public static void main(String[] args) throws RemoteException {
        client cl = new client();
        cl.initShips();
        cl.printShips();
        while(cl.getStub().areGameStillContinue(cl.Number)){
            if(cl.getStub().whoSTurn() == cl.Number){
                cl.printShips();
                cl.myMove();
            }
        }
        if(cl.getStub().amIWin(cl.Number)){
            System.out.println("Вы победили!!!");
        }else{
            System.out.println("Вы проиграли(");
        }
    }
}