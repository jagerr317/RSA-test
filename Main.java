import java.math.BigInteger;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

   private static final BigInteger ZERO = BigInteger.ZERO;
    private static final BigInteger ONE = BigInteger.ONE;
    private static final BigInteger TWO = new BigInteger("2");
    private static final BigInteger THREE = new BigInteger("3");

    public static int getNumber(char betű){
        int count = 0;
        List<Character> characters = new ArrayList<Character>();
        characters.add('a'); characters.add('b'); characters.add('c');
        characters.add('d'); characters.add('e'); characters.add('f');
        characters.add('g'); characters.add('h'); characters.add('i');
        characters.add('j'); characters.add('k'); characters.add('l');
        characters.add('m'); characters.add('n');
        characters.add('o'); characters.add('p'); characters.add('q');
        characters.add('r'); characters.add('s'); characters.add('t');
        characters.add('u'); characters.add('v'); characters.add('w');
        characters.add('x'); characters.add('y'); characters.add('z');
        characters.add('A'); characters.add('B'); characters.add('C');
        characters.add('D'); characters.add('E'); characters.add('F');
        characters.add('G'); characters.add('H'); characters.add('I');
        characters.add('J'); characters.add('K'); characters.add('L');
        characters.add('M'); characters.add('N');
        characters.add('O'); characters.add('P'); characters.add('Q');
        characters.add('R'); characters.add('S'); characters.add('T');
        characters.add('U'); characters.add('V'); characters.add('W');
        characters.add('X'); characters.add('Y'); characters.add('Z');
        characters.add('_'); characters.add('*'); characters.add('.');
        characters.add('+'); characters.add('/'); characters.add(',');
        characters.add('-'); characters.add(')'); characters.add('(');
        characters.add('"'); characters.add('!'); characters.add('=');
        characters.add(';'); characters.add('<'); characters.add('>');
        characters.add('0'); characters.add('1'); characters.add('2');
        characters.add('3'); characters.add('4'); characters.add('5');
        characters.add('6'); characters.add('7'); characters.add('8');
        characters.add('9'); characters.add(' ');

        characters.add('á'); characters.add('Á'); characters.add('é');
        characters.add('É'); characters.add('ö'); characters.add('Ö');
        characters.add('ő'); characters.add('Ő'); characters.add('ú');
        characters.add('Ú'); characters.add('ü'); characters.add('Ü');
        characters.add('ű'); characters.add('Ű'); characters.add('ó');
        characters.add('Ó'); characters.add('í'); characters.add('Í');

        for(Character n : characters){
            if(betű == n){
                return count;
            }
            count++;
        }
        return -1;
    }

    public static Character getLetter(BigInteger szam){
        List<Character> characters = new ArrayList<Character>();
        characters.add('a'); characters.add('b'); characters.add('c');
        characters.add('d'); characters.add('e'); characters.add('f');
        characters.add('g'); characters.add('h'); characters.add('i');
        characters.add('j'); characters.add('k'); characters.add('l');
        characters.add('m'); characters.add('n');
        characters.add('o'); characters.add('p'); characters.add('q');
        characters.add('r'); characters.add('s'); characters.add('t');
        characters.add('u'); characters.add('v'); characters.add('w');
        characters.add('x'); characters.add('y'); characters.add('z');
        characters.add('A'); characters.add('B'); characters.add('C');
        characters.add('D'); characters.add('E'); characters.add('F');
        characters.add('G'); characters.add('H'); characters.add('I');
        characters.add('J'); characters.add('K'); characters.add('L');
        characters.add('M'); characters.add('N');
        characters.add('O'); characters.add('P'); characters.add('Q');
        characters.add('R'); characters.add('S'); characters.add('T');
        characters.add('U'); characters.add('V'); characters.add('W');
        characters.add('X'); characters.add('Y'); characters.add('Z');
        characters.add('_'); characters.add('*'); characters.add('.');
        characters.add('+'); characters.add('/'); characters.add(',');
        characters.add('-'); characters.add(')'); characters.add('(');
        characters.add('"'); characters.add('!'); characters.add('=');
        characters.add(';'); characters.add('<'); characters.add('>');
        characters.add('0'); characters.add('1'); characters.add('2');
        characters.add('3'); characters.add('4'); characters.add('5');
        characters.add('6'); characters.add('7'); characters.add('8');
        characters.add('9'); characters.add(' ');

        characters.add('á'); characters.add('Á'); characters.add('é');
        characters.add('É'); characters.add('ö'); characters.add('Ö');
        characters.add('ő'); characters.add('Ő'); characters.add('ú');
        characters.add('Ú'); characters.add('ü'); characters.add('Ü');
        characters.add('ű'); characters.add('Ű'); characters.add('ó');
        characters.add('Ó'); characters.add('í'); characters.add('Í');

        return characters.get(szam.intValue());
    }

    public static BigInteger kinai(BigInteger[] modulus_tomb, BigInteger[] alap_tomb){

        BigInteger n = ONE;
        BigInteger[] M_tomb = new BigInteger[modulus_tomb.length];

        for(int j = 0;j<modulus_tomb.length;j++){
            n = n.multiply(modulus_tomb[j]);
        }
        System.out.println("Az n értéke:"+n);
        for(int i = 0;i < modulus_tomb.length;i++){
            M_tomb[i] = n.divide(modulus_tomb[i]);
        }
        BigInteger[] y_tomb = new BigInteger[modulus_tomb.length];


        for(int i = 0; i < y_tomb.length;i++){
            y_tomb[i] = multiplikativ(M_tomb[i],modulus_tomb[i])[0].mod(modulus_tomb[i]);
        }

        BigInteger x1 = ZERO;

        for(int j = 0; j < y_tomb.length;j++){
            x1 =  x1.add(y_tomb[j].multiply(alap_tomb[j].multiply(M_tomb[j])));
        }
        System.out.println("Az x1 értéke "+x1);
        // System.out.println(y1+" "+" "+y2+" " + y3+" "+n);
        BigInteger x = x1;

        if(x.compareTo(ZERO) < 0 ){
            x = n.add(x);
        }
        System.out.println("Az x értéke "+x);
        System.out.println("Az n értéke "+n);
        x = x.mod(n);
        System.out.println("Az x értéke "+x);

        return x;
    }

    static BigInteger exp(BigInteger x,BigInteger y, BigInteger p)
    {
        BigInteger eredmeny = ONE; //eredmény inicializálás
        x = x.mod(p); //egyszerűsítés ha lehetséges

        while (y.compareTo(ZERO) > 0) // amíg a hatványkitevő nem 1
        {
            if((y.and(ONE).compareTo(ONE) == 0))
               eredmeny = (eredmeny.multiply(x)).mod(p);

            y = y.shiftRight(1);
            x = (x.multiply(x)).mod(p);
        }
        return eredmeny;
    }


  public static  BigInteger LNKO(BigInteger n1, BigInteger n2) {
        if (n2.compareTo(ZERO) == 0) {
            return n1;
        }
        return LNKO(n2, n1.mod(n2));
    }


    public static boolean isProbablePrime(BigInteger n, int k) {
        Random rand  = new Random();
        if (n.compareTo(ONE) == 0)
            return false;
        if (n.compareTo(THREE) < 0)
            return true;
        int s = 0;
        BigInteger d = n.subtract(ONE);
        while (d.mod(TWO).equals(ZERO)) {
            s++;
            d = d.divide(TWO);
        }
        for (int i = 0; i < k; i++) {
            BigInteger a = new BigInteger(n.bitLength(), rand);
            while(true) {
                if((a.compareTo(n.subtract(ONE)) < 0) && (a.compareTo(ZERO) > 0)){
                    break;
                }
                a = new BigInteger(n.bitLength(), rand);
            }

            BigInteger x = a.modPow(d, n);
            if (x.equals(ONE) || x.equals(n.subtract(ONE)))
                continue;
            int r = 0;
            for (; r < s; r++) {
                x = x.modPow(TWO, n);
                if (x.equals(ONE))
                    return false;
                if (x.equals(n.subtract(ONE)))
                    break;
            }
            if (r == s) // None of the steps made x equal n-1.
                return false;
        }
        return true;
    }


    public static BigInteger[] multiplikativ(BigInteger elso, BigInteger masodik){

        BigInteger x1 = BigInteger.ONE;
        BigInteger x2 = BigInteger.ZERO;
        BigInteger y1 =  BigInteger.ZERO;
        BigInteger y2  = BigInteger.ONE;
        BigInteger x2_seged =  BigInteger.ZERO;
        BigInteger y2_seged =  BigInteger.ZERO;

        BigInteger x_vegeredmeny =  BigInteger.ZERO;
        BigInteger y_vegeredmeny =  BigInteger.ZERO;

        int kitevo_count = 0;

        BigInteger r1 =  BigInteger.ZERO;
        BigInteger r2 =  BigInteger.ZERO;
        BigInteger seged =  BigInteger.ZERO;
        List<BigInteger> q = new ArrayList<>();

        r1 = elso;
        r2 = masodik;

        while(r2 !=  BigInteger.ZERO){
            q.add(r1.divide(r2));
            kitevo_count++;
            seged = r2;
            r2 = r1.mod(r2);
            r1 = seged;

            if(kitevo_count>1){
                x2_seged = x2;
                y2_seged = y2;
                x2 = q.get(kitevo_count-2).multiply(x2).add(x1);
                y2 = q.get(kitevo_count-2).multiply(y2).add(y1);
                x1 = x2_seged;
                y1 = y2_seged;
            }
        }

        if((kitevo_count % 2) == 0){
            x_vegeredmeny =  x2;
            y_vegeredmeny = y2.multiply(BigInteger.valueOf(-1));
        }
        else{
            x_vegeredmeny =  x2.multiply(BigInteger.valueOf(-1));
            y_vegeredmeny = y2;
        }

        BigInteger[] vegeredmeny = new BigInteger[2];
        vegeredmeny[0] = x_vegeredmeny;
        vegeredmeny[1] = y_vegeredmeny;
        return vegeredmeny;
    }

     public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        BigInteger p = new BigInteger(1024,rand);
        BigInteger q = new BigInteger(1024,rand);

        System.out.println("A p értéke :"+p);
        System.out.println("A q értéke: "+q);


        BigInteger n;
        BigInteger Phin;
        BigInteger e;
        BigInteger d = ONE;

        while(true){
            if(!isProbablePrime(p,1)){
                p = new BigInteger(p.bitLength(),rand);
            }

            if(!isProbablePrime(q,1)){
                q =  new BigInteger(p.bitLength(),rand);

            }
            if(isProbablePrime(p,1) && isProbablePrime(q,1)){
                if(p != q)  break;
            }
        }

        System.out.println("A p új értéke :"+p);
        System.out.println("A q új  értéke: "+q);

        n = p.multiply(q);
        Phin = p.subtract(ONE).multiply(q.subtract(ONE));
        System.out.println("A Phin  értéke: "+Phin);

        while(true){
            e =   new BigInteger(p.bitLength(),rand);
            if((e.compareTo(Phin) < 0) && (e.compareTo(ONE) > 0)){
                if(LNKO(Phin,e).compareTo(ONE)== 0){
                    break;
                }
            }
        }
       // System.out.println("A e  értéke: "+e);
        d = multiplikativ(Phin,e)[1];
        //System.out.println("A d  értéke: "+d);
        while(d.compareTo(ZERO)< 0){
            d = d.add(Phin);
        }
      //  System.out.println("A d új értéke: "+d);
        System.out.println("Kérem adja meg a titkosítandó szöveget!:");
        String bemenet = sc.nextLine();
        List<BigInteger> titkositott_lista = new ArrayList<BigInteger>();
         BigInteger titkos_u = ZERO;
        for(int i = 0; i < bemenet.length();i++) {
            titkos_u = BigInteger.valueOf(getNumber(bemenet.charAt(i)));
            titkositott_lista.add(exp(titkos_u,e,n));
        }
         BigInteger visszafejtett_character = ZERO;
        String visszafejtett = "";
         for(BigInteger k : titkositott_lista) {
             BigInteger c1 = exp(k, d, p);
             BigInteger c2 = exp(k, d, q);

             BigInteger[] alap_tomb = new BigInteger[2];
             BigInteger[] modulo_tomb = new BigInteger[2];

             alap_tomb[0] = c1;
             alap_tomb[1] = c2;

             modulo_tomb[0] = p;
             modulo_tomb[1] = q;

             visszafejtett_character = kinai(modulo_tomb, alap_tomb);
             visszafejtett+= getLetter(visszafejtett_character);
         }

        System.out.println("A visszafejtett üzenet értéke: "+visszafejtett);
    }
}


