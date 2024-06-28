/*
 * Copyright (c) 2022 Yookue Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yookue.springstarter.p6spy.strategy;


import org.apache.commons.lang3.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;


/**
 * Compact to single line strategy for p6spy
 *
 * @author David Hsing
 * @see com.p6spy.engine.spy.appender.SingleLineFormat
 */
@SuppressWarnings("unused")
public class CompactSingleLineFormat implements MessageFormattingStrategy {
    private static final String MESSAGE_TEMPLATE = "{connection: %d, elapsed: %d} statement: %s";    // $NON-NLS-1$

    @Override
    public String formatMessage(int connectionId, String currentTime, long executionTime, String category, String effectiveSql, String sql, String url) {
        String compact = StringUtils.isBlank(sql) ? null : sql.replaceAll("(\\r?\\n|\\t)+", StringUtils.SPACE).replaceAll("\\s{2,}", StringUtils.SPACE).trim();    // $NON-NLS-1$ // $NON-NLS-2$
        return String.format(MESSAGE_TEMPLATE, connectionId, executionTime, compact);
    }
}
