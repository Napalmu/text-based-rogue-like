# Päätös
Peli noudattaa *MVC-arkkitehtuuria* mahdollisimman tiukasti.
# Syy
MVC-arkkitehtuuri otettiin käyttöön, jotta data ja sen visualisointi saadaan vahvasti erotettua. Malli hoitaa datan tallennuksen ja mallintamisen. Näkymä mallin antaman datan näyttämisen käyttäjälle.
# Konteksti
Peli kehitetään tekstipohjaiseksi seikkailuksi, mutta tulevaisuudessa tavoitteena on vaihtaa tekstipohjainen näkymä graafiseen käyttöliittymään. Muutos (toivottavasti) vaatii vain näkymän uudelleenkirjoittamisen, eikä yhteenkään mallin luokkaan tarvitse koskea.