package Decide.src.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.After;
import Decide.src.main.LaunchInterceptor;
import Decide.src.main.Connector;
import java.util.Random;

public class LaunchInterceptorPUMCalculationTest {
    private int size = 15;
    private LaunchInterceptor li;
    private Random rand = new Random();

    @After
    public void tearDown() {
        li = null;
    }

    @Test
    public void pum_RandomizedInput() {
        int connectorSize = 3; // ANDD, ORR, NOTUSED

        for (int repeat = 0; repeat < 100; repeat++) {
            Connector[][] lcm = new Connector[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    int selectionI = rand.nextInt(connectorSize);
                    switch (selectionI) {
                        case 0: {
                            lcm[i][j] = Connector.ANDD;
                            break;
                        }
                        case 1: {
                            lcm[i][j] = Connector.ORR;
                            break;
                        }
                        case 2: {
                            lcm[i][j] = Connector.NOTUSED;
                            break;
                        }
                        default:
                            break;
                    }
                }
            }

            li = new LaunchInterceptor(-1, null, null, lcm, null);

            for (int i = 0; i < size; i++) {
                li.cmv[i] = rand.nextBoolean();
            }

            li.calculatePUM();

            for (int i = 0; i < size; i++) {
                for (int j = i + 1; j < size; j++) {
                    switch (lcm[i][j]) {
                        case ANDD: {
                            assertEquals(li.pum[i][j], li.cmv[i] && li.cmv[j]);
                            break;
                        }
                        case ORR: {
                            assertEquals(li.pum[i][j], li.cmv[i] || li.cmv[j]);
                            break;
                        }
                        case NOTUSED: {
                            assertTrue(li.pum[i][j]);
                            break;
                        }
                        default: {
                            fail();
                            break;
                        }
                    }
                }
            }

            // reset between loops.
            lcm = null;
            li = null;
        }
    }

    @Test
    public void pum_AllTrueIfLCMFilledWithNOTUSED() {
        Connector[][] lcm = new Connector[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                lcm[i][j] = Connector.NOTUSED;
            }
        }

        li = new LaunchInterceptor(-1, null, null, lcm, null);
        li.calculatePUM();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == j)
                    continue;
                assertTrue(li.pum[i][j]);
            }
        }
    }
}
