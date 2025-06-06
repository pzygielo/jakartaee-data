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
package jakarta.data.spi.expression.function;

import jakarta.data.expression.NumericExpression;
import jakarta.data.messages.Messages;

record NumericCastRecord<T, N extends Number & Comparable<N>>
        (NumericExpression<T, ?> expression, Class<N> type)
        implements NumericCast<T, N> {

    NumericCastRecord {
        if (expression == null) {
            throw new NullPointerException(
                    Messages.get("001.arg.required", "expression"));
        }

        if (type == null) {
            throw new NullPointerException(
                    Messages.get("001.arg.required", "type"));
        }
    }

    @Override
    public String toString() {
        String expr = expression.toString();
        String typeName = type.getSimpleName().toUpperCase();
        StringBuilder cast =
                new StringBuilder(10 + expr.length() + typeName.length());
        cast.append("CAST(")
            .append(expr)
            .append(" AS ")
            .append(typeName)
            .append(')');
        return cast.toString();
    }
}
