# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Hoang duc nguyen, S189261, s189261@oslomet.no
* ...

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Oppgaven ble løst alene
# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å lage en konstruktør. den tar imot verdien og setter verdien i fra halen og mot venstre. Det ble også laget to metodene antall og tom. antall teller verdiene i listen som ikke er tom, mens tom returner true om listen er tom. 

I oppgave 2 så brukte jeg stringbuilder for å sette sammen string i array. toString metoden sjekker om en midlertidig node-peker satt til hode er null for å vite om listen er tom. en while løkke kjøres gjennom for å sjekke om neste node ogsp er tom og sete hasnext til false og veriden settes i strengen,om det er tilfellen.
Dersom node pekeren ikke er null, printes verdien til string og det nodepekere flette til neste.

I onvendtString ble metoden tom() brukt for å sjekke om listen er tom. deroms listen ikke er tom, blie nodepeker satt til halen, verdien skrives ut til strengen og node-peker flyttes til forrige peker. kjøres så lenger node ikke er tom 

oppgave 2B

det starter med en sjekk om verdi !=null. Dersom listen er tom, blir halen og hode satt til nodeverdi. Dersom listen ikke er tom , blir node2(nynode) sin neste er null, node2.forrige= hale og halen er nå node2.  

Oppgave 3a

hjelpemetoden finnNode() ble brukt for å hjelpe metoden hent() for å verdien på node ved gitt indeks og returner verdien. oppdater() medtoden finner gammelverdi på gitt indeks og erstatte den med nyverdi. Det registrer at en endring har skjedd men antall endres ikke siden tabell har samme lengde. 

oppgave 3b)
subListe() metoden opprette en tom subliste som skal kopier verdi fra en annen liste i intervallet [fra,til>]. Hjelpemetoden fratilkontroll() ble laget for for å kontrollere at indeksen og antall ikke er utenfor gitte grense..


