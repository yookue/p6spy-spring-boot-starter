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


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.yookue.commonplexus.javaseutil.util.StackTraceWraps;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest(classes = MockApplicationInitializer.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
class MockApplicationTest {
    @Autowired
    private MockApplicationService service;

    @Test
    void queryNone() {
        String methodName = StackTraceWraps.getExecutingMethodName();
        String result = service.queryDb();
        log.info("{}: Query result data is '{}'", methodName, result);
        Assertions.assertNotNull(result);
    }
}
