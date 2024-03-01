Potrebno je izraditi web-aplikaciju koja će služiti za praćenje natjecanja koja se odvijaju po jednokružnom sustavu natjecanja.

Natjecanje može definirati prijavljeni korisnik tako da na početnoj stranici unese naziv natjecanja, popis natjecatelja (natjecatelji su odvojeni točkom zarez ili novim redom) i odabere sustav bodovanja u obliku pobjeda/remi/poraz, npr. 3/1/0 (nogomet), 1/0,5/0 (šah), 2/0/1 (košarka) i slično.

U slučaju da su podaci valjani, aplikacija treba generirati cjelokupni raspored po kolima i omogućiti unos rezultata korisniku koji je stvorio natjecanje (ali samo njemu, ne i ostalim korisnicima).

Napomena: Korisnik ne unosi raspored (raspored generira aplikacija), već samo unosi/mijenja rezultate nakon čega aplikacija ažurira poredak natjecatelja temeljem dotadašnjih rezultata.

Više o sustavu "svatko sa svakim" možete pročitati na https://hrcak.srce.hr/file/350365 uz opasku da ne morate implementirati algoritam, već možete koristiti unaprijed pripremljene rasporede za podržane brojeve natjecatelja. Broj natjecatelja ograničiti na 4 do 8.

Stranica s rezultatima i trenutnim poretkom nekog natjecanja mora biti javno dostupna preko posebno generirane poveznice za to natjecanje. Poveznica mora biti vidljiva korisniku koji je stvorio natjecanje kako bi je mogao podijeliti s natjecateljima.

Prijava korisnika u aplikaciju odvija se korištenjem protokola OpenId Connect (OIDC) i servisa Auth0. Korisnike na servisu Auth0 možete dodati kroz opciju User management/Users na Auth0. Za potrebe testiranja aplikacije pripremiti jedan račun ili na Auth0 omogućiti prijavu preko Googlea.

Za pohranu podataka koristiti PostgreSQL na Renderu ili neku drugu bazu podataka po izboru (npr. Firebase).
