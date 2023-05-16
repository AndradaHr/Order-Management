package org.example;

import org.example.presentation.Controller;
import org.example.presentation.MainWindow;

public class App
{
    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow();
        Controller controller = new Controller(mainWindow);
    }
}
