package tester;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import student.StudentArrayDeque;


public class TestArrayDequeEC {
    @Test
    public void TestArrayDeque1() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ad = new ArrayDequeSolution<>();

        int sum = 30;
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < sum; i++) {
            int random = StdRandom.uniform(0, 4);
            if (random == 0) {
                sad.addLast(i);
                ad.addLast(i);
                message.append("addLast(").append(i).append(")\n");
                Assert.assertEquals(message.toString(), sad.size(), ad.size());
            } else if (random == 1) {
                sad.addFirst(i);
                ad.addFirst(i);
                message.append("addFirst(").append(i).append(")\n");
                Assert.assertEquals(message.toString(), sad.size(), ad.size());
            } else if (random == 2) {
                if (sad.isEmpty() || ad.isEmpty()) {
                    continue;
                }
                Integer removeFirst1 = sad.removeFirst();
                Integer removeFirst2 = ad.removeFirst();
                message.append("removeFirst(").append(")\n");
                Assert.assertEquals(message.toString(), removeFirst1, removeFirst2);
            } else {
                if (sad.isEmpty() || ad.isEmpty()) {
                    continue;
                }
                Integer removeLast1 = sad.removeLast();
                Integer removeLast2 = ad.removeLast();
                message.append("removeLast(").append(")\n");
                Assert.assertEquals(message.toString(), removeLast1, removeLast2);
            }
        }
    }

    @Test
    public void TestremoveLast() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ad = new ArrayDequeSolution<>();
        int sum = 7;
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < sum; i++) {
            sad.addLast(i);
            ad.addLast(i);
            message.append("addLast(").append(i).append(")\n");
        }
        for (int i = 0; i < sum; i++) {
            Integer removeLast1 = sad.removeLast();
            Integer removeLast2 = ad.removeLast();
            message.append("removeLast(").append(")\n");
            Assert.assertEquals(message.toString(), removeLast1, removeLast2);
        }
    }
}
