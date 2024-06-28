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

package com.yookue.springstarter.p6spy.config;


import java.util.Map;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import com.p6spy.engine.spy.P6ModuleManager;
import com.p6spy.engine.spy.P6SpyDriver;
import com.p6spy.engine.spy.P6SpyOptions;
import com.yookue.commonplexus.javaseutil.annotation.PropertyAlias;
import com.yookue.commonplexus.javaseutil.constant.StringVariantConst;
import com.yookue.commonplexus.springutil.util.AnnotationUtilsWraps;
import com.yookue.commonplexus.springutil.util.ClassUtilsWraps;
import com.yookue.commonplexus.springutil.util.ReflectionUtilsWraps;
import com.yookue.springstarter.p6spy.property.P6spyProperties;
import lombok.RequiredArgsConstructor;


/**
 * Configuration for p6spy
 *
 * @author David Hsing
 * @reference "https://github.com/klboke/p6spy-spring-boot-starter"
 * @see "com.p6spy.engine.spy.option.SystemProperties"
 */
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(value = P6SpyDriver.class)
@ConditionalOnProperty(prefix = P6spyAutoConfiguration.PROPERTIES_PREFIX, name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(value = P6spyProperties.class)
@RequiredArgsConstructor
@SuppressWarnings({"JavadocDeclaration", "JavadocLinkAsPlainText"})
public class P6spyAutoConfiguration implements InitializingBean {
    public static final String PROPERTIES_PREFIX = "spring.p6spy";    // $NON-NLS-1$

    private final P6spyProperties properties;

    @Override
    public void afterPropertiesSet() {
        Map<String, String> options = P6SpyOptions.getActiveInstance().getDefaults();
        ReflectionUtils.doWithFields(P6spyProperties.class, field -> {
            String fieldName = field.getName();
            if (StringUtils.equalsIgnoreCase(fieldName, StringVariantConst.ENABLED)) {
                return;
            }
            String fieldValue = null;
            if (ClassUtils.isAssignable(Boolean.class, field.getType())) {
                fieldValue = BooleanUtils.toStringTrueFalse(ReflectionUtilsWraps.getFieldAs(field, true, properties, Boolean.class));
            } else if (ClassUtils.isAssignable(String.class, field.getType())) {
                fieldValue = ReflectionUtilsWraps.getFieldAs(field, true, properties, String.class);
                if (StringUtils.isNotBlank(fieldValue) && StringUtils.equalsIgnoreCase(fieldName, P6SpyOptions.LOGFILE)) {
                    fieldValue = org.springframework.util.StringUtils.cleanPath(fieldValue);
                }
            } else if (ClassUtils.isAssignable(Class.class, field.getType())) {
                fieldValue = ClassUtilsWraps.getQualifiedName(ReflectionUtilsWraps.getFieldAs(field, true, properties, Class.class));
            }
            if (StringUtils.isBlank(fieldValue)) {
                return;
            }
            String fieldAlias = AnnotationUtilsWraps.getAnnotationAttributeAsString(field, PropertyAlias.class, StringVariantConst.VALUE);
            options.put(StringUtils.defaultIfBlank(fieldAlias, fieldName), fieldValue);
        });
        P6SpyOptions.getActiveInstance().load(options);
        P6ModuleManager.getInstance().reload();
    }
}
