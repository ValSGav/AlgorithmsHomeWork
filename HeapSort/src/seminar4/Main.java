package seminar4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String answer = "";

        Scanner sc = new Scanner(System.in, "cp866");

        RedBlackBinTree<Integer> tree = new RedBlackBinTree<>();
        while (!answer.matches("exit")){
            answer = sc.nextLine();
            try {
                Integer numb = Integer.parseInt(answer);
                tree.addNode(numb);
            } catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }

        }
    }
}
