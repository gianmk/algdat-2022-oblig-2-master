package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;


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
            // tar ikke med null verdi.
            if(a[i] == null) continue;
            antall++;
            // første verdi
            if (hode == null) {
                hode = new Node<>(a[i], null, null);
                hale = hode;
                node1 = hode;
                //neste verdi
            } else {
                Node<T> node2 = new Node<>(a[i]);
                node1.neste = node2;
                node2.forrige = node1;
                node1 = node2;
            }
        }
        hale = node1;
    }

    public Liste<T> subliste(int fra, int til) {
        // throw new UnsupportedOperationException();

        // sjekk om indeks [fra, til> er lovlig
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
            // listen er ikek tom
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
    public void leggInn(int indeks, T verdi) {

    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        endringer ++;
        return finnNode(indeks).verdi;
    }


    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        Objects.requireNonNull(nyverdi, "Ikke tillatt med null-verdier!");

        Node<T> curr = finnNode(indeks);

        T gammelVerdi = curr.verdi;

        // vi erstatter  gammel verdi på plass indeks med ny verdi
        curr.verdi = nyverdi;

        endringer++;
        // returnere det som lå der fra før
        return gammelVerdi;
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
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

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
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

        // hvis listen ikke er tom
        if (!tom()) {
            Node<T> p;

            // deler liste i 2 og sjkekker om indeks ligger til høyre eller venstre for listen
            if(indeks <= (antall / 2)) {
                p = hode;

                // sjekker fra hode (venstre) mot midten
                for (int i = 0; i <= indeks; i++) {
                    if (indeks == i) {
                        return p;
                    } else {
                        p = p.neste;
                    }
                }
            } else {            // sjekker fra hale (høyre) mot midten
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
        }else {                 // hvis listen er tom
            throw new NullPointerException("Indeks er tom!");
        }

    }
    public static void main(String[] args) {
        Character[] c = {'A','B','C','D','E','F','G','H','I','J',};
        DobbeltLenketListe<Character> liste = new DobbeltLenketListe<>(c);
        System.out.println(liste.subliste(3,8));  // [D, E, F, G, H]
        System.out.println(liste.subliste(5,5));  // []
        System.out.println(liste.subliste(8,liste.antall()));  // [I, J]
        // System.out.println(liste.subliste(0,11));  // skal kaste unntak


    }

} // class DobbeltLenketListe


