/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.processor.aggregator;

import junit.framework.TestCase;
import org.apache.camel.processor.aggregate.OptimisticLockRetryPolicy;

public class OptimisticLockRetryPolicyTest extends TestCase {

    private static long precision = 10L; // give or take 10ms

    public void testRandomBackOff() throws Exception {
        OptimisticLockRetryPolicy policy = new OptimisticLockRetryPolicy();
        policy.setRandomBackOff(true);
        policy.setExponentialBackOff(false);
        policy.setMaximumRetryDelay(500L);

        for (int i = 0; i < 10; i++) {
            long elapsed = doDelay(policy, i);

            assertTrue(elapsed <= policy.getMaximumRetryDelay() + precision && elapsed >= 0);
        }
    }

    public void testExponentialBackOff() throws Exception {
        OptimisticLockRetryPolicy policy = new OptimisticLockRetryPolicy();
        policy.setRandomBackOff(false);
        policy.setExponentialBackOff(true);
        policy.setMaximumRetryDelay(0L);
        policy.setRetryDelay(50L);

        for (int i = 0; i < 6; i++) {
            long elapsed = doDelay(policy, i);

            assertDelay(50L << i, elapsed);
        }
    }

    public void testExponentialBackOffMaximumRetryDelay() throws Exception {
        OptimisticLockRetryPolicy policy = new OptimisticLockRetryPolicy();
        policy.setRandomBackOff(false);
        policy.setExponentialBackOff(true);
        policy.setMaximumRetryDelay(200L);
        policy.setRetryDelay(50L);

        for (int i = 0; i < 10; i++) {
            long elapsed = doDelay(policy, i);

            switch (i) {
            case 0:
                assertDelay(50L, elapsed);
                break;
            case 1:
                assertDelay(100L, elapsed);
                break;
            default:
                assertDelay(200L, elapsed);
                break;
            }
        }
    }

    public void testRetryDelay() throws Exception {
        OptimisticLockRetryPolicy policy = new OptimisticLockRetryPolicy();
        policy.setRandomBackOff(false);
        policy.setExponentialBackOff(false);
        policy.setMaximumRetryDelay(0L);
        policy.setRetryDelay(50L);

        for (int i = 0; i < 10; i++) {
            long elapsed = doDelay(policy, i);

            assertDelay(50L, elapsed);
        }
    }

    public void testMaximumRetries() throws Exception {
        OptimisticLockRetryPolicy policy = new OptimisticLockRetryPolicy();
        policy.setRandomBackOff(false);
        policy.setExponentialBackOff(false);
        policy.setMaximumRetryDelay(0L);
        policy.setMaximumRetries(2);
        policy.setRetryDelay(50L);

        for (int i = 0; i < 10; i++) {
            switch (i) {
            case 0:
            case 1:
                assertTrue(policy.shouldRetry(i));
                break;
            default:
                assertFalse(policy.shouldRetry(i));
            }
        }
    }

    private long doDelay(OptimisticLockRetryPolicy policy, int i) throws InterruptedException {
        long start = System.currentTimeMillis();
        policy.doDelay(i);
        long elapsed = System.currentTimeMillis() - start;
        return elapsed;
    }

    private void assertDelay(long expectedDelay, long actualDelay) {
        assertTrue(actualDelay <= expectedDelay + precision);
        assertTrue(actualDelay >= expectedDelay - precision);
    }
}