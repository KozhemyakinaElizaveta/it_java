package Lab_78;

import java.util.LinkedList;
import java.net.*;
import java.io.*;

public class CrawlerTask implements Runnable{ //наследует интерфейс с одним методом run, программа понимает, что мы можем создать поток
    private static final String URL_PREFIX = "http://";
    private static final String URL_HREF = "<a href=";
    private final URLPool pool; // пул из главного потока
    private int maxDepth; //максимальная глубина
    public CrawlerTask(URLPool pool, int maxDepth){ //конструктор класса
        this.pool = pool;
        this.maxDepth = maxDepth;
    }

    private LinkedList<URLDepthPair> read(URLDepthPair pair){
        Socket socket;
        try{
            socket = new Socket(pair.getUrl().getHost(), 80); //создаём сокет
        } catch (UnknownHostException e) {
            System.out.println("Ошибка: неизвестный хост: " + pair.getUrl().getHost());
            return null;
        } catch (IOException e) {
            System.out.println("Ошибка: ввода вывода при создании сокета: " + pair.getUrl().getHost());
            return null;
        }
        try{
            socket.setSoTimeout(1000); //устанавливаем таймаут
        } catch (SocketException e) {
            System.out.println("Ошибка: установки таймаута сокета: " + pair.getUrl().getHost());
            return null;
        }
        BufferedReader in; //для получения данных сервера
        PrintWriter out; //для обращения к серверу
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //поток ввода вывода
            out = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            System.out.println("Ошибка: создания потоков ввода вывода: " + pair.getUrl().getHost());
            return null;
        }
        out.println("GET " + pair.getUrl().getPath()+" HTTP/1.1"); //строка на запрос
        out.println("Host: " + pair.getUrl().getHost());
        out.println("Connection: close");
        out.println();
        LinkedList<URLDepthPair> links = new LinkedList<>(); //массив для ссылок
        String line;
        try {
            while ((line = in.readLine()) != null){
                //построчно получаем ответ (Html документ)
                if (!line.contains(URL_HREF)) continue; //смотрим чтобы всё соответствовало
                int startIndex = line.indexOf(URL_PREFIX);
                int endIndex = line.indexOf("\"", startIndex);
                if (startIndex == -1 || endIndex == -1) continue; //если чего-то нет то пропускаем
                URLDepthPair newPair;
                try{
                    newPair = new URLDepthPair(line.substring(startIndex,endIndex), pair.getDepth() + 1);
                } catch (MalformedURLException e) {
                    continue;
                }
                links.add(newPair); //всё нормально - добавляем в список ссылок
            }
        } catch (IOException e) {
            System.out.println("Ошибка: получения Html документа: " + pair.getUrl().getHost());
            return null;
        }
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Ошибка: закрытия сокета: " + pair.getUrl().getHost());
            return null;
        }
        return links; //вернёт список обработанных ссылок

    }
    @Override
    public void run() {
        while (true) {
            URLDepthPair pair = pool.getPair(); //получили ссылку из списка необработанных
            if (pair.getDepth() > maxDepth) continue;
            LinkedList<URLDepthPair> links = read(pair);
            if (links == null) continue;
            for (URLDepthPair elem: links){
                pool.push(elem);
            }
            pool.pushClosed(pair); //добавляем в закрытые ссылки после обработки
        }


    }
}