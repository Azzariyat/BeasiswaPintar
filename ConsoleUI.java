public class ConsoleUI implements UserInterface {
    @Override
    public void display(String message) {
        System.out.println("[Tampilan]: " + message);
    }
}