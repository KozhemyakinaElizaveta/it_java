package Lab_78;

import java.net.*;

public class URLDepthPair { //хранит совместно юрл и глубину (текущую для этой ссылки)
    private URL url;
    private int depth;

    public URLDepthPair(String url, int depth) throws MalformedURLException{ //конструктор
        this.url = new URL(url); //создаёт юрл
        this.depth = depth; //сохраняет глубину
    }

    //геттеры получают приватные переменные
    public URL getUrl(){
        return url;
    }

    public int getDepth() {
        return depth;
    }

    public String toString(){ //выводит объект в консоль
        return url.toString() + " " + depth;
    }
}