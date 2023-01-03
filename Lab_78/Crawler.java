package Lab_78;

import java.net.MalformedURLException;

public class Crawler {
    public static final String ERROR = "usage: java Crawler <URL><depth><numThreads>";
    public static void main(String[] args){
        if(args.length != 3){ //проверяет что на вход приходят три аргумента
            System.out.println(ERROR);
            return;
        }
        int numThreads; //колво потоков
        int maxDepth; //максимальная глубина
        try{
            maxDepth = Integer.parseInt(args[1]); //переделываем из строк в числа
            numThreads = Integer.parseInt(args[2]);
        } catch (NumberFormatException e) {
            System.out.println(ERROR); //возвращаем ошибку если что-то не так
            return;
        }
        URLDepthPair firstPair;
        try{
            firstPair = new URLDepthPair(args[0], 0);
        } catch (MalformedURLException e) {
            System.out.println(ERROR);
            return;
        }
        URLPool pool = new URLPool();
        pool.push(firstPair); //добавляем первую пару
        Thread[] threads = new Thread[numThreads]; //создаем массив, который хранит ссылки, которые управляют потоками
        for (int i = 0; i < numThreads; i++){
            CrawlerTask task = new CrawlerTask(pool, maxDepth); //добавляем потоки с задачей кроулер таск
            threads[i] = new Thread(task);
            threads[i].start(); //запускаем потоки
        }
        while (pool.getNumWaiters() != numThreads) {
            try {
                Thread.sleep(500); //основной поток каждые полсекунды проверяет доработали ли потоки
            } catch (InterruptedException e) {
            }
        }
        for (int i=0; i < numThreads; i++) {
            threads[i].stop(); //как только дождался, он стопорит все остальные потоки
        }
        System.out.println("Обработка завершена");
        for (URLDepthPair pair: pool.getClosedLinks()) {
            System.out.println(pair); //выводит результат
        }
    }
}