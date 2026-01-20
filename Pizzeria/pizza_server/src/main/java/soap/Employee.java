package soap;

import soap.service.Pizza;
import soap.service.PizzaService;
import soap.service.Status;

import java.util.Scanner;

public class Employee {

    public static void main(String[] args) {
        new Employee();
    }

    public Employee() {
        PizzaService Service = new PizzaService();
        Pizza pizza = Service.getPizzaPort();
        Scanner scanner = new Scanner(System.in);

        while (true){

            System.out.println("1:Получить список всех имеющихся в продаже видов пицц");
            System.out.println("2:Добавить новый вид пиццы");
            System.out.println("3:Удалить вид пиццы");
            System.out.println("4:Изменить статус заказа");

            switch (scanner.nextInt()){
                case 1->{
                    scanner.nextLine();
                    System.out.println(pizza.getTypes());
                    break;
                }
                case 2->{
                    scanner.nextLine();
                    System.out.println(pizza.getTypes());

                    System.out.print("Укажите наименование пиццы ");
                    String name = scanner.nextLine();

                    System.out.print("Укажите описание пиццы ");
                    String description = scanner.nextLine();

                    System.out.print("Укажите стоимость пиццы ");
                    int price = scanner.nextInt();

                    int id = pizza.addType(name, description, price);

                    System.out.println("Создан новый вид пиццы #" + id);
                    break;
                }
                case 3->{
                    scanner.nextLine();
                    System.out.println(pizza.getTypes());

                    System.out.print("Укажите номер удаляемого вида пиццы ");
                    int id = scanner.nextInt();

                    if(pizza.removeType(id)){
                        System.out.println("Операция успешно выполнена");
                    }
                    else {
                        System.out.println("Ошибка!");
                    }
                    break;
                }
                case 4->{
                    scanner.hasNextLine();

                    System.out.println("Все заказы");
                    System.out.println(pizza.getAllOrder());

                    System.out.print("У какого заказа подтвердить выполнение? ");
                    pizza.changeStatus(scanner.nextInt(), Status.DELIVERED);

                    System.out.println();
                }
            }
        }
    }
}
