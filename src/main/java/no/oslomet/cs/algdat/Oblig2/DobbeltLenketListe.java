package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;


public class DobbeltLenketListe<T> implements Liste<T> {



    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        //throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {

        if (a == null) {
            throw new NullPointerException("Tabellen er null!"); // sjekker om listen er tomt og gi feil melding
        }

        Node<T> node1 = null;

        for (int i = 0; i < a.length; i++) {

            if(a[i] == null) continue;  // verdi som er null hoppes over.
            antall++;

            if (hode == null) {   //første verdi
                hode = new Node<>(a[i], null, null);
                hale = hode;
                node1 = hode;

            } else {
                Node<T> node2 = new Node<>(a[i]); // neste verdi settes inn
                node1.neste = node2;
                node2.forrige = node1;
                node1 = node2;
            }
        }
        hale = node1;
    }

    public Liste<T> subliste(int fra, int til) {


//sjekekr om fra til er lovlig
        fraTilKontroll(antall, fra, til);

        Liste<T> subliste = new DobbeltLenketListe<>();
        if ((fra == 0) && (til == 0)) {
            return subliste;
        }
        Node<T> start = finnNode(fra);
        for (int i = fra; i < til; i++) {
            // første verdi settes
            if (i == fra) {
                subliste.leggInn(start.verdi);
                start = start.neste;
                // neste verdiene settes
            } else {
                subliste.leggInn(start.verdi);
                start = start.neste;
            }
        }
        return subliste;
    }
    private static void fraTilKontroll(int antall, int fra, int til) {
        if((fra < 0 ) || (til > antall)) {
            throw new IndexOutOfBoundsException("Ugyldig intervall! Må være mellom [0, " + antall + ">.");
        }
        if (fra > til){
            throw new IllegalArgumentException("fra " + fra + "er større en til" + til);
        }
        return;

    }
    @Override
    public int antall() {

        if ( antall != 0) {
            return antall;
        } else {
            return 0;
        }

    }

    @Override
    public boolean tom() {

            if (antall() != 0) {
                return false;
            }
            return true;
        }


        @Override
        public boolean leggInn(T verdi) {

            //Null verdier ikke tillatt, kaster unntak
            Objects.requireNonNull(verdi);

            Node <T> node1 = new Node<>(verdi);

            // tilfellen der listen er tom
            if((hode == null) && (hale == null)) {
                hode = node1;
                hale = node1;
            }
            // listen er ikke tom
            else {
                Node <T> node2 = hale;
                hale = node1;
                node2.neste = node1;
                node1.forrige = node2;

            }
            antall++;
            endringer++;
            return true;
        }


    @Override

    public void leggInn(int indeks, T verdi) { //hako
        Objects.requireNonNull(verdi, "Null-verdier ikke tillatt!");  // ikke lov med null verdi
        if (indeks < 0 || indeks > antall) throw new IndexOutOfBoundsException(indeks);

        Node node = new Node(verdi);  // ny node
        if (tom()) {                  //satt pekere på første node
            hode = hale = node;
        }

        if (indeks == 0) {         //peker på ny hode
            hode.forrige = node;
            node.neste = hode;
            hode = node;
        } else if (indeks == antall) {  // peker på ny hale
            hale.neste = node;
            node.forrige = hale;
            hale = node;
        } else {
            Node prev = finnNode(indeks-1);  // peker ellers
            Node next = prev.neste;
            prev.neste = node;
            node.neste = next;
            node.forrige = prev;
            next.forrige = node;
        }
        antall++;
        endringer++;

    }

    @Override
    public boolean inneholder(T verdi) {

        if(indeksTil(verdi) == -1){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        endringer ++;
        return finnNode(indeks).verdi;
    }


    @Override
    public int indeksTil(T verdi) {

        Node <T> node = hode;


        if(verdi == null){
            return -1;
        }

        for(int i = 0; node != null; i++){
            if(node.verdi.equals(verdi)){
                return i;
            } else {
                node = node.neste;
            }
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");

        Node<T> curr = finnNode(indeks);

        T gammelVerdi = curr.verdi;

//erstatter gammel verdi på indeks til ny verdi.
        curr.verdi = nyverdi;

        endringer++;
//returner gammel verdi
        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        Node<T> node = hode;

        boolean funnet = false;



        while(node != null) {
            if (node.verdi.equals(verdi)) {
                funnet = true;
                break;
            } else {
                node = node.neste;
            }
        }

        if(funnet){
            if(antall == 1) {
                hode = null;
                hale = null;
                node.neste = null;
                node.forrige = null;
                antall = 0;
                endringer++;
                return true;
            }
            if(hode == node){
                hode.neste.forrige = null;
                hode = hode.neste;

                antall--;
                endringer++;
                return true;
            } else if (hale == node){
                hale.forrige.neste = null;
                hale = hale.forrige;

                antall--;
                endringer++;
                return true;
            } else {
                node.neste.forrige = node.forrige;
                node.forrige.neste = node.neste;

                antall--;
                endringer++;
                return true;
            }
        }
        return false;
    }


    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks,false);

        T fjern;

        if (antall == 1){  //sjekk om listen har kun 1 verdi
            fjern = hode.verdi;
            hode = hale = null;
        }

        else if (indeks == 0){  //sjekk om hoden som fjernes
            fjern = hode.verdi;
            hode = hode.neste;
            hode.forrige = null;
        }

        else if (indeks == antall-1){  //sjekk om halen som fjernes
            fjern = hale.verdi;
            hale = hale.forrige;
            hale.neste = null;
        }

        else {
            Node<T> current = finnNode(indeks);
            fjern = current.verdi;
            current.forrige.neste = current.neste;
            current.neste.forrige = current.forrige;
        }

        antall--;
        endringer++;
        return fjern;
    }

    @Override
    public void nullstill() {
        while (hode!=hale){
            hode.neste.forrige = null;
            hode = hode.neste;
            endringer++;
        }
        hode = hale = null;
        endringer++;
        antall=0;
    }

    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();
        boolean hasNext = true;
        Node<T> q = hode;

        // klammeparentes på starten
        s.append('[');

        // om listen er tom, om ikke tom kjøres det gjennom ved å flytte på neste peker
        while (hasNext && q != null) {
            if (q.neste == null) {
                hasNext = false;
                s.append(q.verdi);
            } else {
                s.append(q.verdi).append(',').append(' ');
                q = q.neste;
            }
        }
        s.append(']');
        return s.toString();
    }

    public String omvendtString() {

        StringBuilder s = new StringBuilder();

        s.append('[');

        if (!tom()) {
            Node<T> r = hale;
            s.append(hale.verdi);

            r = r.forrige;

            while (r != null) {
                s.append(',').append(' ').append(r.verdi);
                r = r.forrige;
            }
        }
        s.append(']');

        return s.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks,false);
        return new DobbeltLenketListeIterator(indeks);
    }
    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next(){
            if(endringer != iteratorendringer)
                throw new ConcurrentModificationException("Endringer er ikke oppdatert riktig");
            if(!hasNext())
                throw new NoSuchElementException("Listen har nådd siste noden");

            fjernOK = true;
            T returVerdi = denne.verdi;
            denne = denne.neste;
            return returVerdi;
        }


        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

    private Node<T> finnNode(int indeks) {
        indeksKontroll(indeks, false);


        if (!tom()) {  //om tikke tom liste
            Node<T> p;

//deler listen i halv
            if(indeks <= (antall / 2)) {
                p = hode;
//sjekker fra venstre(hode) til mid
                for (int i = 0; i <= indeks; i++) {
                    if (indeks == i) {
                        return p;
                    } else {
                        p = p.neste;
                    }
                }
            } else {    //sjekke fra høyre/halen til midten
                p = hale;
                for (int i = antall - 1; i > (antall / 2) && (i < antall); i--) {
                    if (indeks == i) {
                        return p;
                    } else {
                        p = p.forrige;
                    }
                }
            }
            return p;
        }else {
            throw new NullPointerException("Indeks er tom!");
        }

    }


} // class DobbeltLenketListe


