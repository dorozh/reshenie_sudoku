import java.util.ArrayList;
import java.util.Collections;

public class Slot {
    static int DK = 3;
    int str;
    int stlb;
    int index = 0;
    static int sudoku[][] = Main.sudoku;
    ArrayList myList = new ArrayList<>();

    public Slot(int str,int stlb) {
        this.str = str;
        this.stlb = stlb;
        this.myList =  spisok_vozhm_znach();


    }
    public int get_value_slot(int ind){
        if(myList.size() == 0){

            return -1;
        }
        else {

        int res = (int) myList.get(ind);

        return res;
        }
    }

    public int getStr(){
        return str;
    }
    public int getStlb(){
        return stlb;
    }
    public int getIndex(){
        return index;
    }

    public void setStr(int value){
        this.str = value;
    }
    public void setStlb(int value){
        this.stlb = value;
    }
    public void setIndex(int value){
        this.index = value;
    }






    static void check_is_null() {
        System.out.println("null");
        for (int i = 0; i < sudoku[0].length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                if (sudoku[i][j] == 0) {

                    //System.out.println("размер списка"+ myList.size());
                    //System.out.println("поле "+i+j+" равно нулю");
                } else {
                    //System.out.println("поле "+i+j+" НЕ равно нулю");
                }
            }
        }
    }

    static int check_string(int x, int y) {
        int chis = sudoku[x][y];
        //System.out.println("значение в поле " + x + " " + y + " равно " + chis);
        int i = x;
        int j = 0;
        int schet_sovpad = 0;
        while (j < 9) {
            if (sudoku[i][j] == sudoku[x][y]) {
                schet_sovpad++;
            }
            j++;
        }
        //System.out.println("количество совпадений в строке равно " + schet_sovpad + "\n");
        return schet_sovpad;
    }

    static int check_stolb(int x, int y) {
        int chis = sudoku[x][y];
        //System.out.println("значение в поле " + x + " " + y + " равно " + chis);
        int i = 0;
        int j = y;
        int schet_sovpad = 0;
        while (i < 9) {
            if (sudoku[i][j] == sudoku[x][y]) {
                schet_sovpad++;
            }
            i++;
        }
        //System.out.println("количество совпадений в столбце равно " + schet_sovpad + "\n");
        return schet_sovpad;
    }

    static int check_kvadrat(int str, int stlb) {
        //System.out.println("проверка поля " + str + " " + stlb);
        int cornSTR = 0;
        int cornSTLB = 0;
        int schet_sovpad = 0;

        if (str < DK && stlb < DK) {
            //System.out.println("проверка 1 квардрата");
            cornSTR = 0;
            cornSTLB = 0;
            check_sovpad_ot_ugla(cornSTR, cornSTLB, str, stlb);
        }
        if (str < DK && stlb >= DK && stlb < 2 * DK) {
           // System.out.println("проверка 2 квардрата");
            cornSTR = 0;
            cornSTLB = DK;
            check_sovpad_ot_ugla(cornSTR, cornSTLB, str, stlb);
        }
        if (str < DK && stlb >= 2 * DK) {
            //System.out.println("проверка 3 квардрата");
            cornSTR = 0;
            cornSTLB = 2 * DK;
            check_sovpad_ot_ugla(cornSTR, cornSTLB, str, stlb);
        }
        if (str >= DK && str < 2 * DK && stlb < DK) {
            //System.out.println("проверка 4 квардрата");
            cornSTR = DK;
            cornSTLB = 0;
            check_sovpad_ot_ugla(cornSTR, cornSTLB, str, stlb);
        }
        if (str >= DK && str < 2 * DK && stlb >= DK && stlb < 2 * DK) {
            //System.out.println("проверка 5 квардрата");
            cornSTR = DK;
            cornSTLB = DK;
            check_sovpad_ot_ugla(cornSTR, cornSTLB, str, stlb);
        }
        if (str >= DK && str < 2 * DK && stlb >= 2 * DK) {
           // System.out.println("проверка 6 квардрата");
            cornSTR = DK;
            cornSTLB = 2 * DK;
            check_sovpad_ot_ugla(cornSTR, cornSTLB, str, stlb);
        }

        if (str >= 2 * DK && stlb < DK) {
           // System.out.println("проверка 7 квардрата");
            cornSTR = 2 * DK;
            cornSTLB = 0;
            check_sovpad_ot_ugla(cornSTR, cornSTLB, str, stlb);
        }
        if (str >= 2 * DK && stlb >= DK && stlb < 2 * DK) {
            //System.out.println("проверка 8 квардрата");
            cornSTR = 2 * DK;
            cornSTLB = DK;
            check_sovpad_ot_ugla(cornSTR, cornSTLB, str, stlb);
        }
        if (str >= 2 * DK && stlb >= 2 * DK) {
            //System.out.println("проверка 9 квардрата");
            cornSTR = 2 * DK;
            cornSTLB = 2 * DK;
            check_sovpad_ot_ugla(cornSTR, cornSTLB, str, stlb);
        }
        return check_sovpad_ot_ugla(cornSTR, cornSTLB, str, stlb);
    }

    static int check_sovpad_ot_ugla(int corSTR, int corSTLB, int str, int stlb) {
        int sovpadenie = 0;
        int i = 0;
        while (i < 3) {
            int k = 0;
            while (k < 3) {
                //System.out.println(sudoku[corSTR+k][corSTLB+i]);
                if (sudoku[corSTR + i][corSTLB + k] == sudoku[str][stlb]) {
                    sovpadenie++;
                   // System.out.println("совпадений " + sovpadenie);
                }
                k++;
            }
            i++;
        }
        return sovpadenie;
    }

     ArrayList spisok_vozhm_znach() {

        int str = this.str;
        int stlb = this.stlb;



        ArrayList list_vozm_znach = new ArrayList();
        ArrayList list_stlb = new ArrayList();
        ArrayList list_str = new ArrayList();
        ArrayList list_kvadr = new ArrayList();
        int DK = 3;


        int cornSTR = 0;
        int cornSTLB = 0;

        for (int i = 1; i < 10; i++) {
            //вписываем в листо возможных значений числа от 1 до 9
            list_vozm_znach.add(i);
        }

        for (int i = 0; i < 9; i++) {
            if (sudoku[i][stlb] != 0) {
                list_stlb.add(sudoku[i][stlb]);
                //System.out.println("в столбце " + stlb + " есть число" + sudoku[i][stlb]);
            }
        }

        for (int i = 0; i < 9; i++) {
            if (sudoku[str][i] != 0) {
                list_str.add(sudoku[str][i]);
                //System.out.println("в строке  " + str + " есть число" + sudoku[str][i]);
            }
        }
        if (str < DK) {
            cornSTR = 0;
        }
        if (str >= DK && str < 2 * DK) {
            cornSTR = 3;
        }
        if (str >= 2 * DK) {
            cornSTR = 6;
        }
        if (stlb < DK) {
            cornSTLB = 0;
        }
        if (stlb >= DK && str < 2 * DK) {
            cornSTLB = 3;
        }
        if (stlb >= 2 * DK) {
            cornSTLB = 6;
        }


        for (int i = 0;i<DK;i++){
            for (int k = 0;k<DK;k++) {
                if (sudoku[cornSTR + i][cornSTLB + k] != 0) {
                    list_kvadr.add(sudoku[cornSTR + i][cornSTLB + k]);
                }
            }
        }

        for (int k = 0; k<list_kvadr.size();k++){
            // System.out.println("в квадрате "+cornSTR+cornSTLB+ "есть значение " + list_kvadr.get(k));
        }

        //нужно сравнивать лист строк(стобцов или квадратов) с листом значений а не наоборот!!!!
        //ОДИН элемент листа строк сравнивается со КАЖДЫМ элементами из листа возможных значений, после этого сравнивается следующий элемента листа строк с каждым элементов из листа значений
        //сравнение и удаление производится начиная с ПОСЛЕДНЕГО элемента массива, иначе элементы будут "перескакивать"

        //нужно сравнивать лист стоблцов с листов Возможных значений а не наоборот!!!!
        for(int i = list_stlb.size()-1;i>=0 ;i--){
            for(int k = list_vozm_znach.size()-1 ; k>0 ; k--){
                if(list_stlb.get(i)==list_vozm_znach.get(k)){
                    list_vozm_znach.remove(k);
                }
            }
        }
        for(int i = list_str.size()-1;i>=0 ;i--){
            for(int k = list_vozm_znach.size()-1 ; k>=0 ; k--){
                if(list_str.get(i)==list_vozm_znach.get(k)){
                    list_vozm_znach.remove(k);
                }
            }
        }
        for(int i = list_kvadr.size()-1;i>=0 ;i--){
            for(int k = list_vozm_znach.size()-1 ; k>=0 ; k--){
                if(list_kvadr.get(i)==list_vozm_znach.get(k)){
                    list_vozm_znach.remove(k);
                }
            }
        }
        //сортировка листа в порядке возрастания
        Collections.sort(list_vozm_znach);
        //отобразить возможные значения в консоли
        //System.out.println("\nдля поля " + str+" " + stlb + " возможны значения");
        for (int i = 0;i<list_vozm_znach.size();i++){
           // System.out.print(list_vozm_znach.get(i) + " ");
        }
       //System.out.println("\n");
        return list_vozm_znach;

    }
}



