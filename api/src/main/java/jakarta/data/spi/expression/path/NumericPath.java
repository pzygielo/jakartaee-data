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
package jakarta.data.spi.expression.path;

import jakarta.data.expression.NavigableExpression;
import jakarta.data.expression.NumericExpression;
import jakarta.data.metamodel.NumericAttribute;

public interface NumericPath<T, U, N extends Number & Comparable<N>>
        extends Path<T, U>, NumericExpression<T, N> {
    static <T, U, N extends Number & Comparable<N>> NumericPath<T, U, N>
    of(NavigableExpression<T, U> expression, NumericAttribute<U, N> attribute) {
        return new NumericPathRecord<>(expression, attribute);
    }
}
