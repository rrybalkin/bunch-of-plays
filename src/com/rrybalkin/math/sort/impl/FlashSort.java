package com.rrybalkin.math.sort.impl;

import com.rrybalkin.math.sort.SortMethod;

/**
 * Created by Roman Rybalkin
 * 18.10.15
 * <p>
 * Code taken from http://habrahabr.ru/post/195968/
 */
public class FlashSort implements SortMethod {

    @Override
    public String getMethodInfo() {
        return "Flash";
    }

    @Override
    public int[] sort(int[] a) {
        flashSort(a);
        insertionSort(a);
        return a;
    }

    public void flashSort(int[] a) {
        int n = a.length; //Размерность массива
        int m = (int) (n * 0.42); //Количество классов
        int[] l = new int[m]; //Вспомогательный массив

        int i = 0, j = 0, k = 0; //Счётчики в циклах
        int anmin = a[0]; //Минимальный элемент
        int nmax = 0; //Индекс максимального элемента

        //Ищем минимальный и максимальный элементы
        for (i = 1; i < n; i++) {
            if (a[i] < anmin)
                anmin = a[i];
            if (a[i] > a[nmax])
                nmax = i;
        }

        //Минимум = максимум? Тогда массив
        //состоит из одинаковых элементов.
        //А значит, он отсортирован!
        if (anmin == a[nmax])
            return;

        //Неизменяемая часть квантиля
        double c1 = ((double) m - 1) / (a[nmax] - anmin);

        //Заполняем массив распределения
        //Каждый элемент вспомогательного массива -
        //это количество чисел соответствующего класса
        for (i = 0; i < n; i++) {
            k = (int) (c1 * (a[i] - anmin));
            l[k]++;
        }

        //Во вспомогательном массиве каждый элемент
        //(начиная со 2-го)увеличим на величину предыдущего.
        //После этого каждый элемент вспомогательного массива
        //это индекс элемента в основном массиве на котором
        //должны заканчиваются числа соответсвующего класса
        for (k = 1; k < m; k++) {
            l[k] += l[k - 1];
        }

        //Меняем местами первый и максимальный элемент в массиве
        //Это делается для того чтобы в основном цикле алгоритма
        //максимальный элемент сразу поставить на своё место
        int hold = a[nmax];
        a[nmax] = a[0];
        a[0] = hold;

        //Основной алгоритм

        //Количество элементов, перемещённых
        // в их правильные классы
        int nmove = 0;

        //Временный контейнер, в которую будем помещать элементы
        //на чьи места только что вставили "правильные" элементы
        int flash;

        //Индекс неупорядоченного элемента
        //начинающего новый класс, элементы которого ещё
        //не перемещены
        j = 0;

        //Класс очередного перемещаемого элемента
        //это число всегда в пределах от 1..m-1
        k = m - 1;

        while (nmove < n - 1) {
            while (j > (l[k] - 1)) {
                j++;
                k = (int) (c1 * (a[j] - anmin));
            }
            flash = a[j];
            while (!(j == l[k])) {
                k = (int) (c1 * (flash - anmin));

                hold = a[l[k] - 1];
                a[l[k] - 1] = flash;
                flash = hold;

                l[k]--;
                nmove++;
            }
        }
    }

    //Финальная сортировка простыми вставками
    //Досортировывает то что не отсортировало FlashSort
    private void insertionSort(int[] a) {
        int i, j, hold;
        for (i = a.length - 3; i >= 0; i--) {
            if (a[i + 1] < a[i]) {
                hold = a[i];
                j = i;
                while (a[j + 1] < hold) {
                    a[j] = a[j + 1];
                    j++;
                }
                a[j] = hold;
            }
        }
    }
}
