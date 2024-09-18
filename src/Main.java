import java.util.ArrayList;


//  если у текущего слота индекс равен или больше длины массива возможных значений то
//  присваиваем ему индекс 0, и значение в поле судоку тоже 0
//  у предыдущего слота повышаем индекс на единицу и делаем шаг назад(i--)
//

public class Main {
    static ArrayList<Slot> list = new ArrayList();
    static int[][] sudoku =
                    {{7, 8, 9,    0, 0, 0,    0, 0, 0},
                    {0, 0, 0,     0, 0, 0,    0, 0, 0},
                    {0, 0, 0,     0, 0, 0,   0, 0, 0},

                    {0, 0, 0,      0, 0, 0,   0, 0, 0},
                    {0, 0, 0,      0, 0, 0,   0, 0, 0},
                    {0, 0, 0,      0, 0, 0,   0, 0, 0},

                    {0, 0, 0,     0, 0, 0,     0, 0, 0},
                    {0, 0, 0,     0, 0, 0,     0, 0, 0},
                    {0, 0, 0,     0, 0, 0,     3, 2, 1}};


    public static void main(String[] args) {
        create_list();
        //reshenie();

        // в методе get_value_slot(int ind) вероятно ошибка его нужно исправить или удалить,
        // возможно из за него не работает цикл "reshenie()"
//        System.out.println(list.get(0).poisk_vozhm_znacheniy(list.get(0).getStr(),list.get(0).getStlb()));
//
//        System.out.println(list.get(0).get_value_slot(list.get(0).getIndex()));
//        list.get(0).setIndex(list.get(0).getIndex()+1);
//        System.out.println(list.get(0).get_value_slot(list.get(0).getIndex()));
        try {
            reshenie();
            System.out.println("\nИТОГ \n");
            otobrazit_sudoku();
        } catch (Exception e) {

            System.out.println("\nИТОГ \n");
            otobrazit_sudoku();

            e.printStackTrace();
        }
    }

    static void reshenie() {
        int i = 0;
        int schetchik = 0;

        //i<list.size()
        while (i<list.size()) {
            System.out.println("счетчик " + schetchik);
            System.out.println("int i =  " + i);
            System.out.println(list.get(i).myList);
            if (list.get(i).getIndex() >= list.get(i).myList.size()) {

                System.out.println("\nАНАЛИЗ ПОЛЯ  " + list.get(i).getStr() + "  " + list.get(i).getStlb());
                System.out.println("индекс поля " + list.get(i).getStr() + " " + list.get(i).getStlb() + " равен " + list.get(i).getIndex());
                System.out.println("полю судоку" + list.get(i).getStr() + "  " + list.get(i).getStlb() + "присвоено значение 0 индекс аннулирован");
                System.out.println("ШАГ НАЗАД");

                sudoku[list.get(i).getStr()][list.get(i).getStlb()] = 0;
                list.get(i).setIndex(0);
                list.get(i - 1).setIndex(list.get(i - 1).getIndex() + 1);
                i--;
                System.out.println("\n");
                otobrazit_sudoku();
                schetchik++;
            } else {
                System.out.println("\nАНАЛИЗ ПОЛЯ  " + list.get(i).getStr() + "  " + list.get(i).getStlb());
                System.out.println("int i =  " + i);
                System.out.println("лист возможных значений" + list.get(i).myList);
                //присваиваем первому полю судоку первое значение из списка возможных значений
                sudoku[list.get(i).getStr()][list.get(i).getStlb()] = list.get(i).get_value_slot(list.get(i).getIndex());
                if (is_correctly(list.get(i))) {
                    System.out.println("полю судоку " + list.get(i).getStr() + " " + list.get(i).getStlb() + " присвоено значение " + list.get(i).get_value_slot(list.get(i).getIndex()));
                    i++;
                    System.out.println("\n");
                    otobrazit_sudoku();
                    schetchik++;
                } else {
                    System.out.println("некоректно, полю судоку " + list.get(i).getStr() + " " + list.get(i).getStlb() + " повышаем индекс ");
                    sudoku[list.get(i).getStr()][list.get(i).getStlb()] = 0;
                    list.get(i).setIndex(list.get(i).getIndex() + 1);
                    System.out.println("индекс поля " + list.get(i).getStr() + " " + list.get(i).getStlb() + " равен " + list.get(i).getIndex());
                    System.out.println("\n");
                    otobrazit_sudoku();
                    schetchik++;
                }
            }
        }
    }

    static void otobrazit_sudoku() {
        int TRI = 0;
        int DVADCZATSEM = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(sudoku[i][j] + " ");
                TRI++;
                DVADCZATSEM++;
                if (TRI == 3) {
                    System.out.print("   ");
                    TRI = 0;
                }
                if (DVADCZATSEM == 27) {
                    System.out.print("\n");
                    DVADCZATSEM = 0;
                }
            }
            System.out.println("");
        }
    }

    static void create_list() {
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku.length; j++) {
                if (sudoku[i][j] == 0) {
                    list.add(new Slot(i, j));
                }
            }
        }
    }

    public static boolean is_correctly(Slot slot) {
        boolean bool = false;
        //проверка ПРИСВОЕННОГО значения на корректность(отсутствие совпадений)
        int a = Slot.check_string(slot.getStr(), slot.getStlb());
        int b = Slot.check_stolb(slot.getStr(), slot.getStlb());
        int c = Slot.check_kvadrat(slot.getStr(), slot.getStlb());


        if (a == 1 && b == 1 && c == 1) {
            bool = true;
        }
        return bool;
    }
}
