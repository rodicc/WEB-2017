# WEB-2017

Projektni zadatak iz predmeta Web programiranje 2016./2017. godina.
Potrebno je realizovati Web aplikaciju za razmenu multimedijalnog sadržaja. Ovu aplikaciju trebaju da koriste tri grupe (uloge) korisnika: obični korisnici, moderatori i administratori. Svaki korisnik je opisan sledećim podacima:
    • Korisničko ime (jedinstveno)
    • Lozinka
    • Ime
    • Prezime
    • Uloga
    • Kontakt telefon
    • Email
    • Datum registracije
    • Spisak praćenih podforuma
    • Spisak snimljenih tema
    • Spisak snimljenih komentara
  Aplikacija rukuje sledećim elementima:
    • Podforum – entitet koji poseduje teme
        o Naziv (jedinstven)
        o Opis
        o Ikonica
        o Spisak pravila
        o Odgovorni moderator
        o Moderatori
    • Tema – entitet koji predstavlja multimedijalni sadržaj
        o Podforum kome pripada
        o Naslov – jedinstven u okviru podforuma
        o Tip – tip može biti:
          ▪ Tekst
          ▪ Slika
          ▪ Link
        o Autor – korisnik koji je napravio temu
        o Komentari
        o Sadržaj
        o Datum kreiranja
        o Broj pozitivnih glasovi (like)
        o Broj negativnih glasova (dislike)
    • Komentar
        o Tema kojoj pripada
        o Autor
        o Datum komentara
        o Roditeljski komentar
        o Podkomentari
        o Tekst komentara
        o Broj pozitivnih glasovi (like)
        o Broj negativnih glasova (dislike)
        o Oznaka da li je komentar izmenjen
    • Poruka – entitet direktne komunikacije između dva korisnika
        o Pošiljalac – korisnik koji je poslao poruku
        o Primalac – korisnik koji prima poruku
        o Sadržaj poruke
        o Oznaka da li je poruka pročitana
    
Implementirati sledeće funkcionalnosti:
    • Registracija – omogućiti registraciju korisnika. Prilikom registracije tip korisnika može biti samo običan korisnik.
    • Prijava na sistem – neprijavljeni korisnik odlazi na stranicu za prijavu i unosi korisničko ime i lozinku dok ne unese korisničko ime i lozinku korisnika koji je registrovan. Nakon toga, korisnik je prijavljen i može da izvršava aktivnosti predviđene njegovom ulogom.
    • Pregled sadržaja – prijavljeni i neprijavljeni korisnici mogu da pregledaju sadržaj svih podforuma.
    • Dodeljivanje pozitivnih i negativnih glasova temama i komentarima – samo prijavljeni korisnici mogu da dodele samo jedan pozitivan ili negativan glas komentaru ili temi.
    • Komentarisanje tema – prijavljeni korisnici mogu da ostave komentar na temu.
    • Logičko brisanje komentara – autori, moderator i administratori mogu da vrše logičko brisanje komentara, pri čemu se za taj komentar više ne prikazuje sadržaj i autor komentara.
    • Izmena komentara – autori i odgovorni moderatori (za podforum u okviru koga se nalazi tema kojoj pripada komentar) mogu da vrše izmenu komentara. Izmena komentara koju vrši odgovorni moderator ne utiče na oznaku da li je komentar izmenjen.
    • Kreiranje tema – prijavljeni korisnici mogu da kreiraju temu o okviru izabranog podforuma.
    • Brisanje i izmena tema – autori, odgovorni moderatori za podforum u kome se nalazi tema, i administratori mogu da izbrišu ili izmene napravljenu temu.
    • Kreiranje podforuma – moderatori i administratori mogu da kreiraju podforum, pri čemu automatski postaju odgovorni moderatori za taj podforum.
    • Brisanje podforuma – samo administrator ili odgovorni moderator mogu da izbrišu izabrani podforum.
    • Promena tipa korisnika – administratori mogu da promene tip korisnika.
    • Pretraga - Svi korisnici mogu da vrše pretragu sledećih entiteta po navedenim kriterijumima:
       o Podforumi:
          ▪ Naslov
          ▪ Opis
          ▪ Odgovornom moderatoru
       o Teme:
          ▪ Naslov
          ▪ Sadržaj
          ▪ Autor
          ▪ Podforum
       o Korisnici:
          ▪ Korisničko ime
    • Omogućiti pretragu entiteta kombinovanjem prethodno navedenih kriterijuma
    • Poruke – Prijavljeni korisnici mogu slati poruke drugim registrovanim korisnicima.
    • Žalbe – Omogućiti mehanizam žalbi na podforume, teme i komentare. Žalba se sastoji od teksta žalbe, datuma žalbe, entiteta žalbe i korisnika koji je uložio žalbu. Ukoliko korisnik uloži žalbu na temu ili komentar, žalba se prosleđuje odgovornom moderatoru i administratorima, dok se žalbe za podforume prosleđuju samo administratorima. Žalbe mogu biti regulisane na tri načina:
        o Brisanje entiteta – autori žalbe i entiteta trebaju biti obavešteni odgovarajućom porukom
        o Upozorenje autora entiteta – autori žalbe i entiteta trebaju biti obavešteni odgovarajućom porukom
        o Odbijanje žalbe – autor žalbe treba biti obavešten odgovarajućom porukom
    • Snimanje entiteta – Prijavljeni korisnici mogu da snime željene entitete u odgovarajuće liste snimljenih entiteta.
    • Korisnička stranica – Registrovani korisnik može na svojoj korisničkoj stranici da vidi:
        o Spisak svih sačuvanih entiteta
        o Spisak pozitivnih i negativnih glasova
        o Spisak podforuma koje prati
        o Spisak svih primljenih poruka
    • Početna stranica – Početna stranica treba sadrži da spisak tema iz podforuma koje korisnik prati
Dizajn korisničkog interfejsa kao i stilovi (CSS) su prepušteni studenti i obavezni su. Aplikacija treba da trajno čuva podatke u fajlovima. Aplikaciju implementirati uz korišćenje JSP i Servleta (MVC Model 2). Aplikacija mora biti razvijena oslanjajući se na verziju 1.8 Java programskog jezika. Aplikacija mora da ima unapred ubačene test podatke. Web server na kome aplikacija mora biti podignuta je Apache Tomcat verzije 6 ili 7.
Za ocenu 9 i 10 neophodno je aplikaciju implementirati uz korišćenje jQuery biblioteke (ili nekog ekvivalenta), REST-a i AJAX poziva, pri čemu se između klijenta i servera razmenjuju JSON objekti.
Za dodatnih 5 bodova implementirati preporučivanja tema. Mehanizam preporučivanja prepušta se studentu.



