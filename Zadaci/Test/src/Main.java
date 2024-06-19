import java.util.Scanner;

class Student
{
    private String ime;
    private String prezime;
    private int index;
    private double prosek;

    public Student() {}

    public Student(String ime, String prezime, int index, double prosek) {
        this.ime = ime;
        this.prezime = prezime;
        this.index = index;
        this.prosek = prosek;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public double getProsek() {
        return prosek;
    }

    public void setProsek(double prosek) {
        this.prosek = prosek;
    }

    @Override
    public String toString()
    {
        return ime + " " + prezime + " Index: " + index + " Prosek: " + prosek;
    }

}
public class Main
{

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Vnesi ime, prezime, index i prosek na student!");
        String ime = new String();
        ime = input.next();
        String prezime = input.next();
        int index = input.nextInt();
        double prosek = input.nextDouble();
        Student s1 = new Student(ime,prezime,index,prosek);
        System.out.println(s1.toString());
        Student s2 = new Student("Marija", "Stojceva", 435, 9.8);
        System.out.println(s2);
        Student s3 = s2;
        System.out.println(s3);
    }
}