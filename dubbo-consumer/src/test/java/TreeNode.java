import java.util.LinkedList;

/**
 * Created by 滕柳 on 2018/9/16.
 */
public class TreeNode<E> {
    public  E e;
    public  TreeNode left;
    public TreeNode right;

    public TreeNode(E e) {
        this.e = e;
        left=null;
        right=null;
    }

    //前序遍历
    public void PrintBinaryTreePreRecur(TreeNode<E> root){
        if (root!=null){
            System.out.println(root.e); //根
            PrintBinaryTreePreRecur(root.left); //左
            PrintBinaryTreePreRecur(root.right); //右

        }
    }

    //中序遍历
    public void PrintBinaryTreeMidRecur(TreeNode<E> root){
        if (root!=null){

            PrintBinaryTreeMidRecur(root.left); //左
            System.out.println(root.e); //根
            PrintBinaryTreeMidRecur(root.right); //右
        }
    }

    //后序遍历
    public void PrintBinaryTreeBackRecur(TreeNode<E> root){
        if (root!=null){

            PrintBinaryTreeBackRecur(root.left); //左
            PrintBinaryTreeBackRecur(root.right); //右
            System.out.println(root.e); //根
        }
    }

    //层次遍历
    public void PrintBinaryTreeLayerRecur(TreeNode<E> root){
        LinkedList<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node= queue.remove();
            System.out.println(node.e);
            if (node.left!=null){
                queue.add(node.left);
            }
            if (node.right!=null){
                queue.add(node.right);
            }

        }
    }


}
