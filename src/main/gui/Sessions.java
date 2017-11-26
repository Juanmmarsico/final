package main.gui;

public class Sessions implements Runnable{
    private Login log;

    public Sessions(Login log) {
        this.log = log;
    }

    @Override
    public void run() {
        log = new Login();
    }


    public Login getLog() {
        return log;
    }
}
