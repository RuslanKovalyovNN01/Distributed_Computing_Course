package soap;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.HashMap;

@WebService
public class Pizza {
    public enum Status{
        PENDING,
        DELIVERED
    }

    HashMap<Integer, Status> order;

    int lastID;

    static class PizzaType{

        String name;

        String descriptor;

        int price;

        public PizzaType(String name, String descriptor, int price){
            this.name = name;
            this.descriptor = descriptor;
            this.price = price;
        }
    }

    ArrayList<PizzaType> types;

    @WebMethod
    public String GetTypes(){
        StringBuilder str = new StringBuilder();

        int i = 0;
        for(var ind : types){
            str.append(i)
                    .append(":")
                    .append(types.get(i).name)
                    .append("\n")
                    .append(types.get(i).descriptor)
                    .append("\n")
                    .append("Цена:")
                    .append(types.get(i).price)
                    .append("\n\n");
            i++;
        }

        return str.toString();
    }

    @WebMethod
    public int AddType(String name, String descriptor, int price){
        PizzaType newType = new PizzaType(name, descriptor, price);
        types.add(newType);
        return types.size() - 1;
    }

    @WebMethod
    public boolean RemoveType(int ID){
        return ID < types.size() && types.remove(types.get(ID));
    }

    @WebMethod
    public void ChangeStatus(int ID, Status stat){
        if(order.get(ID) != null) {
            order.put(ID, stat);
        }
    }

    @WebMethod
    public ArrayList<Integer> CreateOrder(ArrayList<Integer> id, ArrayList<Integer> count){
        int sum_order = 0;

        int i = 0;
        for (var ind : id){
            if(ind >=0 && ind < types.size()){
                sum_order += types.get(ind).price * count.get(i);
                System.out.println(types.get(ind).price + " " + count.get(i));
            }
            i++;
        }

        int o = order.size();
        order.put(o, Status.PENDING);

        ArrayList<Integer> al = new ArrayList<>();
        al.add(o);
        al.add(sum_order);

        return al;
    }

    @WebMethod
    public Status checkStatus(int ID){
        return order.get(ID);
    }

    @WebMethod
    public String GetAllOrder(){
        StringBuilder str = new StringBuilder();

        for(var o : order.keySet()){
            str.append(o)
                    .append(":")
                    .append(order.get(o))
                    .append("\n");
        }

        return str.toString();
    }

    public Pizza(){
        types = new ArrayList<>();
        types.add(new PizzaType("pizza#0", "description#0", 800));
        types.add(new PizzaType("pizza#1", "description#1", 1100));
        types.add(2, new PizzaType("pizza#2", "description#2", 950));
        order = new HashMap<>();
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8080/Pizza", new Pizza());
        System.out.println("service create");
    }
}
