package ru.job4j.gc;

import java.util.Arrays;

public class MemoryUsage {

    /*

    Объект с пустым именем будет весить:
    - заголовок 16 байт
    - поле id 4 байт
    - поле name: 32 байт
    -- заголовок 16 байт
    -- 3 * 4 = 12 байт
    -- ссылка на массив 4 байт
    итого: 16 + 4 + 32 = 42 => выравнивание => 48

    * */
    private static class User {

        private int id;
        //private String name;

        public User(int id) {
            this.id = id;
            //this.name = name;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.printf("Utilized object with id %d%n", id);
        }
    }

    private static void info() {
        System.out.println("=== Memory state ===");
        final int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;
        System.out.printf("Total: %d%n", totalMemory / mb);
        System.out.printf("Free: %d%n", freeMemory / mb);
        System.out.printf("Used: %d%n", usedMemory / mb);
    }

    public static void main(String[] args) {
        info();
        System.out.println();
        User[] users = new User[1024 * 1024];
        for (int i = 0; i < 1024 * 1024; i++) {
            users[i] = new User(i);
            users[i] = null;
        }
        info();
    }
}
// Нужно найти информацию. Сколько памяти занимает пустой объект без полей
/*

Объект состоит из:
- Заголовок объекта; (8 байт для х86 и 16 байт для х64)
- Память для примитивных типов;
- Память для ссылочных типов;
- Смещение/выравнивание — по сути, это несколько неиспользуемых байт, что размещаются после данных самого объекта.
  Это сделано для того, чтобы адрес в памяти всегда был кратным машинному слову,
  для ускорения чтения из памяти + уменьшения количества бит для указателя на объект + предположительно для
  уменьшения фрагментации памяти. Стоит также отметить, что в java размер любого объекта кратен 8 байтам!

Итого, пустой объект хранит только заголовок объекта и будет в весить:
- в х86, 8 байт
- в х64, 16 байт

* */