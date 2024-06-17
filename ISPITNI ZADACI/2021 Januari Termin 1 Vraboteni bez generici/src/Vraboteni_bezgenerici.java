import java.util.Scanner;

class SLLNode {
    int id;
    int age;
    protected SLLNode succ;

    public SLLNode(int id,int age, SLLNode succ) {
        this.id = id;
        this.age = age;
        this.succ = succ;
    }
}
class SLL {
    private SLLNode first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        SLLNode dvizi = first;
        while(dvizi!=null){
            ret = ret + dvizi.id + " ";
            dvizi = dvizi.succ;
        }
        return ret;

    }

    public void insertFirst(int id,int age) {
        SLLNode ins = new SLLNode(id,age, first);
        first = ins;
    }

    public void insertAfter(int id,int age, SLLNode node) {
        if (node != null) {
            SLLNode ins = new SLLNode(id,age, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(int id,int age, SLLNode before) {

        if (first != null) {
            SLLNode tmp = first;
            if (first == before) {
                this.insertFirst(id,age);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode ins = new SLLNode(id,age, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(int id,int age) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(id,age, null);
            tmp.succ = ins;
        } else {
            insertFirst(id,age);
        }
    }

    public int deleteFirst() {
        if (first != null) {
            SLLNode tmp = first;
            first = first.succ;
            return tmp.id;
        } else {
            System.out.println("Listata e prazna");
            return -1;
        }
    }

    public int delete(SLLNode node) {
        if (first != null) {
            SLLNode tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.id;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return -1;
            }
        } else {
            System.out.println("Listata e prazna");
            return -1;
        }

    }

    public SLLNode getFirst() {
        return first;
    }
}
public class Vraboteni_bezgenerici {
    public static void alterTeams(SLL dev, SLL qa){
        SLLNode min = qa.getFirst();
        SLLNode dvizi = qa.getFirst();
        while(dvizi!=null){
            if(dvizi.age < min.age){
                min = dvizi;
            }
            dvizi = dvizi.succ;
        }
        dvizi = dev.getFirst();
        int size = dev.length();

        for(int i=0;i<size/2;i++){
            dvizi = dvizi.succ;
        }

        if(size%2==0){
            dev.insertBefore(min.id,min.age,dvizi);
        }else{
            dev.insertAfter(min.id,min.age,dvizi);
        }
        qa.delete(min);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        SLL dev = new SLL();
        SLL qa = new SLL();

        int N = in.nextInt();
        int M = in.nextInt();

        for(int i=0;i<N;i++){
            int id = in.nextInt();
            int age = in.nextInt();
            dev.insertLast(id, age);
        }
        for(int i=0;i<M;i++){
            int id = in.nextInt();
            int age = in.nextInt();
            qa.insertLast(id, age);
        }

        alterTeams(dev,qa);

        System.out.println(dev);
        System.out.println(qa);
    }
}