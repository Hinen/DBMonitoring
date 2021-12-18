public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                DBManager.get().start();

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }).start();
    }
}
