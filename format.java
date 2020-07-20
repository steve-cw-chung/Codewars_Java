public class format {
    public static void main (String [] args) {
        System.out.println(String.format("%7$d", 1,2,3,4,5,"6",7));
        System.out.println(String.format("|%-20d|",93));
        System.out.println(String.format("|%020d|",93));
        System.out.println(String.format("|%+20d|", 93));
        System.out.println(String.format("|% d|", -93));
        System.out.println(String.format("|%d|", -93));
        System.out.println(String.format("|%,d|", 1000000000));
        System.out.println(String.format("|%#o|", 1000000000));
        System.out.println(String.format("|%#x|", 1000000000));
        System.out.println(String.format("|%-12s|", "Hello World"));
        System.out.println(String.format("|%.5s|","Hello World"));
        System.out.println(String.format("|%30.5s|", "Hello World"));

    }
}