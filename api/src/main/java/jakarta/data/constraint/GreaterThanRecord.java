/*
 * Copyright (c) 2025 Contributors to the Eclipse Foundation
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
package jakarta.data.constraint;

import jakarta.data.expression.ComparableExpression;
import jakarta.data.messages.Messages;

record GreaterThanRecord<V extends Comparable<?>>(
        ComparableExpression<?, V> bound)
        implements GreaterThan<V> {
    public GreaterThanRecord {
        if (bound == null) {
            throw new NullPointerException(
                    Messages.get("001.arg.required", "lowerBound"));
        }
    }

    @Override
    public AtMost<V> negate() {
        return AtMost.max(bound);
    }

    @Override
    public String toString() {
        return "> " + bound.toString();
    }
}
