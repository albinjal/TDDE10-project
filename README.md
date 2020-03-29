# **Kravspecifikation Projektuppgift TDDE10**


# Vision

Vi ska göra ett spel inspirerat av det klassiska arkadspelet Asteroids. Spelet går ut på att samla poäng och att undvika att elimineras. Spelaren förlorar genom att tappa alla sina liv vilket görs genom interaktion med olika fiender.

Standardfienden är asteroider av olika storlekar som skadar spelaren vid direkt kollision. Asteroiderna glider över spelplanen och spelaren kan eliminera dem. Stora asteroider kan när de blir eliminerade dela sig till mindre asteroider. Det ska även finnas andra fiender än asteroider så som aliens som även kan skjuta mot spelaren. Spelaren ska kunna eliminera dessa fiender.

I spelet ska det även finnas powerups som påverkar spelet på olika sätt. Det skulle kunna vara allt från att spelaren får möjligheten att eliminera fiender på nya sätt till extra liv.

Spelaren ska kontrollera sitt rymdskepp genom att vrida på det och gasa och skeppet ska gärna kunna behålla moment dvs fortsätta glida efter att gasen släpps. När spelaren åker in i sidan på spelplanen ska skeppet komma ut på motsatt sida av planen.

Spelaren får poäng genom att eliminera fiender och spelet avslutas när spelaren förlorat alla sina liv. Totala poängen kan då väljas att spara i en high score lista som sparas på disk. Det skall även finnas en meny som spelaren kan öppna där listan med highscores kan visas.

Fienderna ska komma i vågor som skiljer sig på olika sätt. Vågorna ska bli svårare och svårare vilka fördelningar av fiender som kommer de olika vågorna ska skilja sig. En ny våg ska startas när alla fiender på den föregående har eliminerats.

En vidareutveckling  av spelet skulle kunna vara ett Co-op mode där två spelare kan spela tillsammans för att klara sig längre i spelet. Det skulle t.ex. kunna göras genom att ena spelaren spelar på piltangenterna och andra på wasd.


# 


# Kravspecifikation


<table>
  <tr>
   <td>
   </td>
   <td><strong>Krav</strong>
   </td>
   <td><strong>Prio</strong>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td>1
   </td>
   <td>Spelaren ska kunna skjuta fiender
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>2
   </td>
   <td>Spelarens karaktär ska kunna förflytta sig i 2D
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>3
   </td>
   <td>Spelarens karaktär ska “drifta” då man slutar tillföra fart på den
   </td>
   <td>3
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>4
   </td>
   <td>Spelaren ska ha fler liv
   </td>
   <td>2
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>5
   </td>
   <td>Spelet ska ha minst två olika fiender
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>6
   </td>
   <td>Fiender ska kunna skada karaktären
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>7
   </td>
   <td>Spelet ska hålla koll på poäng för en spelsession
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>8
   </td>
   <td>Spelet ska hålla koll på highscore mellan spelsessioner
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>9
   </td>
   <td>Spelet ska innehålla “powerups” för karaktären
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>10
   </td>
   <td>En powerup ska vara en sköld
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>11
   </td>
   <td>Vissa fiender ska ha två lägen för olika skadetillstånd
   </td>
   <td>3
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>12
   </td>
   <td>En powerup ska vara ett extra liv
   </td>
   <td>1
   </td>
   <td>ISH
   </td>
  </tr>
  <tr>
   <td>13
   </td>
   <td>Fiender ska kunna förflytta sig
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>14
   </td>
   <td>En av fienderna ska vara en asteroid som endast åker, har inga “actions”
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>15
   </td>
   <td>En annan fiende ska vara en raket som är som en målsökande asteroid
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>16
   </td>
   <td>Asteroider ska se olika ut
   </td>
   <td>3
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>17
   </td>
   <td>Spelet ska starta med en meny med highscore, avsulta och nytt spel
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>18
   </td>
   <td>Spelet ska innehålla olika fiendevågor som successivt blir svårare. Fiendekompositionen ska skilja sig mellan olika vågor.
   </td>
   <td>1
   </td>
   <td>OK
   </td>
  </tr>
  <tr>
   <td>19
   </td>
   <td>Man ska kunna spela två spelare samtidigt och hjälpas åt, detta har separat highscore
   </td>
   <td>3
   </td>
   <td>
   </td>
  </tr>
</table>