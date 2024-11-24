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
</ul>
<ul>
<li>Brisanje artikala preko: <code>/products/delete/{id}</code></li>
</ul>
<ul>
<li>Brisanje korisnika i mijenjanje podataka korisnika (zahtjeva admin privilegije) preko button-a na: <code>/users</code></li>
</ul>
<h2>Setup</h2>
<hr><p>Instalirajte <b> Java 17</b>  ili noviju verziju.</p>
<p>Instalirajte <b>Maven</b>  za upravljanje zavisnostima.</p>
<p>Instalirajte i pokrenite <b>PostgreSQL</b>  bazu podataka.</p><h5>Koraci</h5><ul>
<li>Preko <b>pgAdmin4</b>  kreirajte bazu podataka pod imenom "ptf", te šemu "public" ukoliko ne postoji.</li>
</ul><ul>
<li><b>Preuzmite projekat:</b>  Klonirajte repozitorij ili kopirajte projektne fajlove u lokalni direktorij.</li>
</ul><ul>
<li><b>Otvorite projekat u IntelliJ IDEA:</b>   Otvorite IntelliJ, idite na File -&gt; Open i izaberite root direktorij projekta.</li>
</ul><ul>
<li><b>Postavite Maven:</b>   U IntelliJ-u, desni klik na pom.xml i odaberite Add as Maven Project.</li>
</ul><ul>
<li><b>Preuzimanje zavisnosti:</b>   IntelliJ automatski preuzima Maven zavisnosti na osnovu pom.xml fajla. Ako to ne uradi, desni klik na projekat i odaberite Reload Maven Projects.</li>
</ul><ul>
<li><b>Pokrenite aplikaciju:</b>  Pronađite JednostavanSistemETrgovineApplication klasu u src/main/java/com/ahmedfaris/demo. Desni klik na klasu i odaberite Run.</li>
</ul>
<ul>
  
<li><b>Aplikacija radi na</b>  <code>localhost:8081</code></li>
</ul>
<ul>
<li><b>Koristiti email:</b> <code>admin@gmail.com</code>, password: <code>password</code> za pristup admin privilegijama.</li>
</ul><ul>
<li><b>Registracija i prijava korisnika:</b>  Korisnici se mogu registrirati putem button "Register" ili preko /register. Prijava je omogućena putem /login.</li>
</ul>
