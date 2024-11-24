<h1>Jednostavan sistem e-trgovine</h1>
<hr><p>Ovo je projekat jednostavnog sistema e-trgovine razvijen korištenjem Spring Boot okvira. Aplikacija omogućava korisnicima registraciju, prijavu, pregled i naručivanje proizvoda uz podršku za administrativne funkcionalnosti. Korišteni su Spring Security za autentifikaciju, PostgreSQL za bazu podataka, te Thymeleaf za frontend.</p>
<h2>Korištene tehnologije</h2>
<hr><ul>
<li>HTML</li>
</ul><ul>
<li>CSS</li>
</ul><ul>
<li>JavaScript</li>
</ul><ul>
<li>Java</li>
</ul><ul>
<li>Spring Boot</li>
</ul><ul>
<li>Thymeleaf</li>
</ul><h2>Mogućnosti</h2>
<hr><ul>
<li>Pregled artikala</li>
</ul><ul>
<li>Registracija i prijava</li>
</ul><ul>
<li>Uloge (USER i ADMIN)</li>
</ul><ul>
<li>Naručivanje artikala</li>
</ul><ul>
<li>Dodavanje artikala</li>
</ul><h2>Setup</h2>
<hr><p>Instalirajte Java 17 ili noviju verziju.</p>
<p>Instalirajte Maven za upravljanje zavisnostima.</p>
<p>Instalirajte i pokrenite PostgreSQL bazu podataka.</p><h5>Steps</h5><ul>
<li>Preko pgAdmin4 kreirajte bazu podataka pod imenom "ptf", te šemu "public" ukoliko ne postoji.</li>
</ul><ul>
<li>Preuzmite projekat: Klonirajte repozitorij ili kopirajte projektne fajlove u lokalni direktorij.</li>
</ul><ul>
<li>Otvorite projekat u IntelliJ IDEA:  Otvorite IntelliJ, idite na File -&gt; Open i izaberite root direktorij projekta.</li>
</ul><ul>
<li>Postavite Maven:  U IntelliJ-u, desni klik na pom.xml i odaberite Add as Maven Project.</li>
</ul><ul>
<li>Preuzimanje zavisnosti:  IntelliJ automatski preuzima Maven zavisnosti na osnovu pom.xml fajla. Ako to ne uradi, desni klik na projekat i odaberite Reload Maven Projects.</li>
</ul><ul>
<li>Pokrenite aplikaciju:  Pronađite JednostavanSistemETrgovineApplication klasu u src/main/java/com/ahmedfaris/demo. Desni klik na klasu i odaberite Run.</li>
</ul><ul>
<li>Koristiti email: "admin@gmail.com", password: "password" za pristup admin privilegijama.</li>
</ul><ul>
<li>Registracija i prijava korisnika:  Korisnici se mogu registrirati putem button "Register" ili preko /register. Prijava je omogućena putem /login.</li>
</ul>
