/*
 * Copyright 2014, Stratio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stratio.cassandra.index.schema.mapping;

import com.google.common.base.Objects;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.marshal.AsciiType;
import org.apache.cassandra.db.marshal.BooleanType;
import org.apache.cassandra.db.marshal.UTF8Type;
import org.codehaus.jackson.annotate.JsonCreator;

/**
 * A {@link ColumnMapper} to map a boolean field.
 *
 * @author Andres de la Pena <adelapena@stratio.com>
 */
public class ColumnMapperBoolean extends ColumnMapperKeyword {

    /** The {@code String} representation of a true value. */
    private static final String TRUE = "true";

    /** The {@code String} representation of a false value. */
    private static final String FALSE = "false";

    /**
     * Builds a new {@link ColumnMapperBlob}.
     */
    @JsonCreator
    public ColumnMapperBoolean() {
        super(new AbstractType<?>[]{AsciiType.instance, UTF8Type.instance, BooleanType.instance}, new AbstractType[]{});
    }

    /** {@inheritDoc} */
    @Override
    public String baseValue(String name, Object value, boolean checkValidity) {
        if (value == null) {
            return null;
        } else if (value instanceof Boolean) {
            return (Boolean) value ? TRUE : FALSE;
        } else if (value instanceof String) {
            String s = (String) value;
            if (s.equalsIgnoreCase(TRUE)) {
                return TRUE;
            } else if (s.equalsIgnoreCase(FALSE)) {
                return FALSE;
            } else if (!checkValidity) {
                return s;
            }
        }
        throw new IllegalArgumentException();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return Objects.toStringHelper(this).toString();
    }

}
