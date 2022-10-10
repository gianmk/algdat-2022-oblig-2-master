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

oppgave 4  
nodepeker "node" er satt til hode. Det sjekkes om verdien er null, dersom det er returne det -1. Om det ikke er null, kjøres det en for-løkke. posisjon (int ) økes hver gang det kjøre gjennom. Når verdien er funnet ( node.verdi ==verdi) returner posisjonen. Peker flyytes også hver gang det kjørs gjennom. dersom verdi ikke bli funnet vil det returne -1  

oppgave 5  
Det sjekkes først om indeks og verdi er gyldig. Metoden legginn() oppretter en ny node med gitte verdi. Den skal legges i listen på riktig indeks. Dette gjøres ved å sette peker ut fra ut fra i listen "inserting" skal skje, om det er først, siste eller mellom to nodene. Endring og antall økes med en. 
  
Oppgave 6  
Bolean fjern( T verdi) ble ny variabel "node" opprettes, og satt = hode. Funnet bolean er satt til false. Om listen er tom, vil det returne false, om listen ikke er tom til det starte en while-løkke, der verdi ikke kan være null. Om Verdi ikke er null, sjekkes det om verdi i peker == verdi, og dersom dette er tilfelle vil løkken avslutte. Om det ikke er tilfellen, flyttes pekere til neste. Når verdien er funnet blir det sjekk om verdien er hode, hale eller mellom disse. Noden fjernes ved å justere pekere, og deretter økes endringer mens antall reduseres.  
T fjern (int indeks) sjekker det først med indeks kontroll om indeksen er innefor grensen. En hjelpevariabel (fjern) bli oprette. Først blir det sjekket om listen har kun 1 verdi (antall=1), deretter sjekker det om hoden som skal fjernes ( indeks = 0) eller om halen som skal fjernes (indeks==antall-1). Dersom det er ingen av disse tilfellene, er indeksen mellom halen og hode. Da blir node "current" opprettes, som settes som like noden til indeksen ved hjelpe av finnnode(indeks) metoden. Pekere til node(current) endres for å fjerne. Antall reduseres og endringer økes, og indeksen til noden som er fjerne blir returnert  




