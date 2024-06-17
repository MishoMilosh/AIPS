import java.util.Scanner;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
class SLL<E> {
    private SLLNode<E> first;

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
            SLLNode<E> tmp = first;
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
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp + " ";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + " ";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == before) {
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if (first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }
}

public class Diskusija{
    public static  void forum(SLL<Discussion> health, SLL<Discussion> finance) {
        SLLNode<Discussion> dvizi = finance.getFirst();
        SLLNode<Discussion> min = finance.getFirst();
        SLLNode<Discussion> max = health.getFirst();
        while (dvizi != null) {
            if (dvizi.element.importance() < min.element.importance()) {
                min = dvizi;
            }
            dvizi = dvizi.succ;
        }
        finance.delete(min);

        dvizi = health.getFirst();
        while(dvizi!=null){
            if(dvizi.element.importance() > max.element.importance()){
                max = dvizi;
            }
            dvizi = dvizi.succ;
        }
        finance.insertLast(max.element);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        SLL<Discussion> health = new SLL();
        SLL<Discussion> finance = new SLL();

        int N = in.nextInt();

        int M = in.nextInt();

        for(int i =0;i<N;i++){
            int id = in.nextInt();
            int pop = in.nextInt();
            int users = in.nextInt();
            health.insertLast(new Discussion(id, pop, users));
        }
        for(int i =0;i<M;i++){
            int id = in.nextInt();
            int pop = in.nextInt();
            int users = in.nextInt();
            finance.insertLast(new Discussion(id, pop, users));
        }

        forum(health,finance);

        System.out.println(health);
        System.out.println(finance);
    }
}
class Discussion{
    private int id;
    private int popularity;
    private int users;

    public Discussion(int id, int popularity, int users) {
        this.id = id;
        this.popularity = popularity;
        this.users = users;
    }

    public int importance(){
        return popularity * users;
    }

    public String toString(){
        return String.valueOf(id);
        // moze i return id + " ";
    }
}