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

package com.yookue.springstarter.p6spy;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
class MockApplicationService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String queryDb1() {
        String sql = "select test_name from t_test_table t where t.test_code = 'tab1'";
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}
