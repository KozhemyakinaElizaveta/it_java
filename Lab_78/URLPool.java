package Lab_78;

import java.util.LinkedList;
public class URLPool {
    private final LinkedList<URLDepthPair> openedLinks = new LinkedList<>(); //список открытых необработанных ссылок
    private final LinkedList<URLDepthPair> closedLinks = new LinkedList<>();

    private int numWaiters = 0; //колво ожидающих потоков

    public synchronized URLDepthPair getPair(){ //несколько потоков не выполняли один метод
        while(openedLinks.size() == 0){
            numWaiters++;
            try{
                wait(); // заставляет поток ждать, если ссылок нет
            } catch (InterruptedException e) {
            }
            numWaiters--;
        }
        return openedLinks.removeFirst();
    }

    public synchronized void push(URLDepthPair pair){ //добавляет в список необработанных если ее ещё там нет
        if (!openedLinks.contains(pair)) openedLinks.add(pair); //проверяем, есть ли такая ссылка, если нет, то добавляем
        notify(); // выводит поток из ожидания (нотифай уведомляет, что ссылка пришла)
    }

    public void pushClosed(URLDepthPair pair){ //то же самое для обработанных ссылок, только синхронизация не нужна
        if (!closedLinks.contains(pair)) closedLinks.add(pair);
    }

    //геттеры для закрытых ссылок и количества потоков
    public LinkedList<URLDepthPair> getClosedLinks(){
        return closedLinks;
    }

    public int getNumWaiters() {
        return numWaiters;
    }
}