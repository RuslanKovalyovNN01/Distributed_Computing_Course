package soap;

import soap.service.Pizza;
import soap.service.PizzaService;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
    ArrayList<Integer> order;

    public static void main(String[] args) {
        new Client();
    }

    public Client() {
        PizzaService Service = new PizzaService();
        Pizza pizza = Service.getPizzaPort();
        Scanner scanner = new Scanner(System.in);
        order = new ArrayList<>();

        while (true){

            System.out.println("1:Получить список всех имеющихся в продаже видов пицц");
            System.out.println("2:Заказать пиццу");
            System.out.println("3:Проверить готовность пиццы");

            switch (scanner.nextInt()){
                case 1 ->{
                    scanner.nextLine();
                    System.out.println(pizza.getTypes());
                    break;
                }
                case 2->{
                    scanner.nextLine();
                    System.out.println(pizza.getTypes());

                    System.out.print("Укажите количество заказываемых видов пицц ");
                    int count = scanner.nextInt();

                    ArrayList<Integer> id = new ArrayList<>();
                    ArrayList<Integer> size = new ArrayList<>();

                    for(int i = 0; i < count; i++){
                        System.out.print("Укажите номер вида пиццы ");
                        id.add(scanner.nextInt());

                        System.out.print("Укажите количество ");
                        size.add(scanner.nextInt());
                    }
                    var num_and_sum = pizza.createOrder(id, size);
                    order.add(num_and_sum.get(0));

                    System.out.println("Ваш заказ #" + num_and_sum.get(0) + " оформлен на сумму " + num_and_sum.get(1) + "\n");
                }
                case 3->{
                    scanner.nextLine();

                    System.out.print("Количество заказов : " + order.size() + " , какой желаете посмотреть? ");
                    int num = scanner.nextInt();

                    if(num < order.size()){
                        var status = pizza.checkStatus(order.get(num));
                        System.out.println(status.value());
                    }
                    else {
                        System.out.println("У вас такого заказа нет");
                    }
                    break;
                }
            }
        }
    }
}