import org.junit.Test;
import static org.junit.Assert.*;

public class LifeTest {
    
    @Test
    public void createNewCell() {
        
        // Arrange: drei lebende Zellen
        Life l = new Life();
        l.setAlive(0, 0);
        l.setAlive(0, 1);
        l.setAlive(0, 2);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = l.nextGeneration();

        // Assert: Rasterpunkt mit drei Nachbarn sollte jetzt leben

        assertTrue(nextGen.isAlive(1, 1));
    }


    @Test
    public void destroyLonelyCell() {

      // Arrange: eine einzelene Zelle
        Life l = new Life();
        l.setAlive(0, 0);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = l.nextGeneration();

        // Assert: Rasterpunkt mit Zelle sollte tot sein

        assertTrue(!nextGen.isAlive(0, 0));
    }


    @Test
    public void keepAliveCell() {

      // Arrange: drei lebende Zellen
        Life l = new Life();
        l.setAlive(1, 1);
        l.setAlive(2, 2);
        l.setAlive(3, 3);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = l.nextGeneration();

        // Assert: Rasterpunkt in der Mitte sollte ha ha ha yeah, stay alive

        assertTrue(nextGen.isAlive(2, 2));
    }


    @Test
    public void destroyCrowdedCell() {

      // Arrange: eine Zelle wird von 4 anderen "umzingelt" und dannach mit ihren
      // "Armen" "umarmt" weil sie sich unglaublich extraordinär
      // außergewöhnlich äußerst lang und feste doll "liebhaben".
      // Dadurch wird die Zelle mit gaaaaaanz viel "Liebe" überschüttet, was
      // zu ihrem baldigen "Tod?" führt. UNLUCKY  ¯\_(ツ)_/¯
      // #biggusdickus-assignment-5
        Life l = new Life();
        l.setAlive(0, 0); 
        l.setAlive(1, 0);
        l.setAlive(0, 1);
        l.setAlive(1, 1);
        l.setAlive(0, 2);

        // Act: Berechnung der Folgegeneration
        ILife nextGen = l.nextGeneration();

        // Assert: Rasterpunkt in der Mitte sollte jetzt dead sein xdddd

        assertTrue(!nextGen.isAlive(1, 1));
    }
}
