/*
 * Copyright (c) 2022,2023 Contributors to the Eclipse Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package jakarta.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

class LimitTest {

    @Test
    @DisplayName(("Should return exception when limit maxResults is negative"))
    void shouldReturnErrorWhenMaxResultsIsNegative() {
        assertThatIllegalArgumentException().isThrownBy(() -> Limit.of(-1));
    }

    @Test
    @DisplayName(("Should return exception when limit maxResults is zero"))
    void shouldReturnErrorWhenMaxResultsIsZero() {
        assertThatIllegalArgumentException().isThrownBy(() -> Limit.of(0));
    }

    @Test
    @DisplayName(("Should return an exception when startAt is greater than endAt"))
    void shouldReturnErrorWhenStartAtIsGreaterThanEndAt() {
        assertThatIllegalArgumentException().isThrownBy(() -> Limit.range(2, 1));
    }

    @Test
    @DisplayName(("Should return exception when limit ends less than its start"))
    void shouldRaiseErrorWhenEndAtIsLessThanStartAt() {
        assertThatIllegalArgumentException().isThrownBy(() -> Limit.range(10, 1));
    }

    @Test
    @DisplayName(("Should return exception when limit startAt is negative"))
    void shouldReturnErrorWhenStartAtIsNegative() {
        assertThatIllegalArgumentException().isThrownBy(() -> Limit.range(-1, 10));
    }

    @Test
    @DisplayName(("Should return exception when limit startAt is zero"))
    void shouldReturnErrorWhenStartAtIsZero() {
        assertThatIllegalArgumentException().isThrownBy(() -> Limit.range(0, 100));
    }

    @Test
    @DisplayName(("Should raise exception when the range from startAt to endAt exceeds Integer.MAX_VALUE."))
    void shouldReturnErrorWhenStartAtToEndAtExceedsMaxInteger() {
        assertThatIllegalArgumentException().isThrownBy(() -> Limit.range(3L, 2147483650L));
    }

    @Test
    @DisplayName(("Should create limit with default maxResults"))
    void shouldCreateLimitWithDefaultMaxResults() {
        Limit limit = Limit.of(1);

        assertSoftly(soft -> {
            soft.assertThat(limit).isNotNull();
            soft.assertThat(limit.maxResults()).isEqualTo(1);
            soft.assertThat(limit.startAt()).isEqualTo(1L);
        });
    }

    @Test
    @DisplayName(("Should create limit with default startAt"))
    void shouldCreateLimitWithDefaultStartAt() {
        Limit limit = Limit.of(10);

        assertSoftly(soft -> {
            soft.assertThat(limit).isNotNull();
            soft.assertThat(limit.maxResults()).isEqualTo(10);
            soft.assertThat(limit.startAt()).isEqualTo(1L);
        });
    }

    @Test
    @DisplayName(("Should create limit with equals range"))
    void shouldCreateLimitWithEqualsRange() {
        Limit limit = Limit.range(1, 1);

        assertSoftly(soft -> {
            soft.assertThat(limit).isNotNull();
            soft.assertThat(limit.maxResults()).isEqualTo(1);
            soft.assertThat(limit.startAt()).isEqualTo(1L);
        });
    }

    @Test
    @DisplayName(("Should create limit with the maximum possible range"))
    void shouldCreateLimitWithMaximumRange() {
        Limit limit = Limit.range(4L, 2147483650L);

        assertSoftly(soft -> {
            soft.assertThat(limit).isNotNull();
            soft.assertThat(limit.maxResults()).isEqualTo(2147483647);
            soft.assertThat(limit.startAt()).isEqualTo(4L);
        });
    }

    @Test
    @DisplayName(("Should create limit with a range"))
    void shouldCreateLimitWithRange() {
        Limit limit = Limit.range(2, 11);

        assertSoftly(soft -> {
            soft.assertThat(limit).isNotNull();
            soft.assertThat(limit.maxResults()).isEqualTo(10);
            soft.assertThat(limit.startAt()).isEqualTo(2L);
        });
    }

    @Test
    @DisplayName("should return correct maxResults and startAt values")
    void shouldReturnMaxResultsAndStartAt() {
        var limit = Limit.range(5, 15);

        assertSoftly(softly -> {
            softly.assertThat(limit.maxResults()).isEqualTo(11);
            softly.assertThat(limit.startAt()).isEqualTo(5);
        });
    }
}
