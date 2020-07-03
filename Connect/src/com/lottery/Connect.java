package com.lottery;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Connect implements Closeable {

    private final Socket socket;
    private final BufferedReader bufferedReader;
    private final BufferedWriter bufferedWriter;

//    Создание сокета на стороне клиента
    public Connect(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
            this.bufferedReader = createReader();
            this.bufferedWriter = createWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    Создание сокета на стороне сервера
    public Connect(ServerSocket serverSocket) {
        try {
            this.socket = serverSocket.accept();
            this.bufferedReader = createReader();
            this.bufferedWriter = createWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    Метод для написания обращений
    public void write(String message) {
        try {
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    Метод для чтения обращений
    public String read() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    Метод для принятия обращений
    private BufferedReader createReader() {
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    Метод для принятия обращений
    private BufferedWriter createWriter()  {
        try {
            return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    Реализация интерфейса Closeable
    @Override
    public void close() throws IOException {
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();
    }
}
