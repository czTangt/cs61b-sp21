package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing aListNoResizing = new AListNoResizing();
        BuggyAList buggyAList = new BuggyAList();
        aListNoResizing.addLast(4);
        aListNoResizing.addLast(5);
        aListNoResizing.addLast(6);

        buggyAList.addLast(4);
        buggyAList.addLast(5);
        buggyAList.addLast(6);

        assertEquals(aListNoResizing.size(), buggyAList.size());
        assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
        assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
        assertEquals(aListNoResizing.removeLast(), buggyAList.removeLast());
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList B = new BuggyAList();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                assertEquals(L.size(), B.size());
            } else if (operationNumber == 1) {
                if (L.size() != 0){
                    assertEquals(L.getLast(), B.getLast());
                }
            } else if (operationNumber == 2) {
                if (L.size() != 0){
                    assertEquals(L.removeLast(), B.removeLast());
                }
            }
        }
    }
}
