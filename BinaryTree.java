import java.io.Serializable;
import java.util.Scanner;

public class BinaryTree implements Serializable {
    private BinaryTreeNode root;

    public BinaryTree(String data) {
        root = new BinaryTreeNode(data);
    }

    //method for playing the game
    public boolean playGame() {
        Scanner scanner = new Scanner(System.in);
        BinaryTreeNode current = root;
        BinaryTreeNode parent = null;
        String response;

        //loops until a leaf node is found
        while (current.left != null || current.right != null) {
            System.out.println("Is it a " + current.data + "? (y/n)");
            response = scanner.nextLine().toLowerCase();

            parent = current;

            //moves to the next node based on the users response
            if (response.equals("y") && current.left != null) {
                current = current.left;
            } else if (response.equals("n") && current.right != null) {
                current = current.right;
            } else {
                System.out.println("Invalid response. Please enter 'y' or 'n'.");
            }
        }

        //computers final guess
        System.out.println("Is it a " + current.data + "? (y/n)");
        response = scanner.nextLine().toLowerCase();

        //if the guess is wrong, the user is able to add the correct answer to the tree
        if (response.equals("n")) {
            System.out.println("What is the correct answer?");
            String newAnswer = scanner.nextLine();

            System.out.println("Please provide a yes/no question to distinguish " + newAnswer + " from " + current.data);
            String newQuestion = scanner.nextLine();

            BinaryTreeNode newAnswerNode = new BinaryTreeNode(newAnswer);
            BinaryTreeNode oldAnswerNode = new BinaryTreeNode(current.data);
            BinaryTreeNode newQuestionNode = new BinaryTreeNode(newQuestion);

            //updates the tree based on the users input
            System.out.println("For " + newAnswer + ", is the answer yes or no? (y/n)");
            response = scanner.nextLine().toLowerCase();

            if (response.equals("y")) {
                newQuestionNode.left = newAnswerNode;
                newQuestionNode.right = oldAnswerNode;
            } else {
                newQuestionNode.left = oldAnswerNode;
                newQuestionNode.right = newAnswerNode;
            }

            //connects the new node to the tree
            if (parent != null) {
                if (parent.left == current) {
                    parent.left = newQuestionNode;
                } else {
                    parent.right = newQuestionNode;
                }
            }

            //asks if the user wants to play again
            System.out.println("Game Over! Do you want to play again? (y/n)");
            response = scanner.nextLine().toLowerCase();

            if (response.equals("y")) {
                //restarts the game
                return playGame();
            } else {
                //ends the game
                return false;
            }
        }

        System.out.println("Game Over!");
        return response.equals("y");
    }

    //main method that is used to start the game
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Twenty Questions game!");
        System.out.println("Think of an object, and I'll try to guess it.");
        System.out.println("Let's start with the first question...");

        //asks the user for the initial object
        System.out.println("What is the initial object you're thinking of?");
        String initialObject = scanner.nextLine();

        //creates a binary tree with the initial object
        BinaryTree binaryTree = new BinaryTree(initialObject);
        binaryTree.playGame();
    }

    //binary tree node class
    class BinaryTreeNode implements Serializable {
        String data;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(String data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
}

