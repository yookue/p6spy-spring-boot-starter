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

package com.yookue.springstarter.p6spy.property;


import java.io.Serializable;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.p6spy.engine.spy.appender.P6Logger;
import com.p6spy.engine.spy.appender.Slf4JLogger;
import com.yookue.commonplexus.javaseutil.annotation.PropertyAlias;
import com.yookue.springstarter.p6spy.config.P6spyAutoConfiguration;
import com.yookue.springstarter.p6spy.strategy.CompactSingleLineFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * Properties for p6spy
 * <p>
 * Note: Property names are case-sensitive, as same as p6spy
 *
 * @author David Hsing
 * @reference "https://p6spy.readthedocs.io/en/latest/configandusage.html"
 * @see com.p6spy.engine.spy.P6SpyOptions
 */
@ConfigurationProperties(prefix = P6spyAutoConfiguration.PROPERTIES_PREFIX)
@Getter
@Setter
@ToString
@SuppressWarnings({"JavadocDeclaration", "JavadocLinkAsPlainText"})
public class P6spyProperties implements Serializable {
    /**
     * Indicates whether to enable this starter or not
     * <p>
     * Default is {@code true}
     */
    private Boolean enabled = true;

    /**
     * For flushing per statement
     */
    @PropertyAlias(value = P6SpyOptions.AUTOFLUSH)
    private Boolean autoFlush;

    /**
     * A comma separated list of JDBC drivers to load and register
     * <p>
     * Note: This is normally only needed when using P6Spy in an application server environment with a JNDI data source or when
     * using a JDBC driver that does not implement the JDBC 4.0 API (specifically automatic registration).
     */
    @PropertyAlias(value = P6SpyOptions.DRIVERLIST)
    private String driverList;

    /**
     * Name of logfile to use
     * <p>
     * Used for {@code com.p6spy.engine.spy.appender.FileLogger} only
     * <p>
     * Default is {@code "p6spy.log"}
     * <p>
     * Note: Windows users should make sure to use forward slashes (/) in their pathname
     */
    @PropertyAlias(value = P6SpyOptions.LOGFILE)
    private String logFile;

    /**
     * Class to use for formatting log messages
     * <p>
     * The original default is {@code com.p6spy.engine.spy.appender.SingleLineFormat}
     */
    private Class<? extends MessageFormattingStrategy> logMessageFormat = CompactSingleLineFormat.class;

    /**
     * Append to the p6spy log file
     * <p>
     * if this is set to false the log file is truncated every time (file logger only)
     * <p>
     * Default is {@code true}
     */
    private Boolean append = true;

    /**
     * Sets the date format using {@link java.text.SimpleDateFormat}
     * <p>
     * Default is {@code "yyyy-MM-dd HH:mm:ss"}
     */
    @PropertyAlias(value = P6SpyOptions.DATEFORMAT)
    private String dateFormat;

    /**
     * Specifies the appender to use for logging
     * <p>
     * Only the properties read from the configuration file
     * <p>
     * Note: reload means forgetting all the previously settings (even those set during runtime - via JMX) and starting with the clean table
     * <p>
     * The original default is {@code com.p6spy.engine.spy.appender.FileLogger}
     * <pre><code>
     * appender = com.p6spy.engine.spy.appender.NoopLogger
     * appender = com.p6spy.engine.spy.appender.Slf4JLogger
     * appender = com.p6spy.engine.spy.appender.StdoutLogger
     * appender = com.p6spy.engine.spy.appender.FileLogger
     * </code></pre>
     */
    private Class<? extends P6Logger> appender = Slf4JLogger.class;

    /**
     * Module list adapts the modular functionality of P6Spy
     * <p>
     * Only modules listed are active
     * <p>
     * Default is {@code com.p6spy.engine.logging.P6LogFactory} and {@code com.p6spy.engine.spy.P6SpyFactory}
     * <p>
     * Note: the core module (P6SpyFactory) can't be deactivated
     * <br>
     * Unlike the other properties, activation of the changes on this one requires reload
     */
    @PropertyAlias(value = P6SpyOptions.MODULELIST)
    private String moduleList;

    /**
     * Prints a stack trace for every statement logged
     */
    @PropertyAlias(value = P6SpyOptions.STACKTRACE)
    private Boolean stackTrace;

    /**
     * If stacktrace is {@code true}, specifies the stack trace to print
     */
    @PropertyAlias(value = P6SpyOptions.STACKTRACECLASS)
    private Class<?> stackTraceClass;

    /**
     * Determines if property file should be reloaded
     * <p>
     * Note: reload means forgetting all the previously settings (even those set during runtime - via JMX) and starting with the clean table
     */
    @PropertyAlias(value = P6SpyOptions.RELOADPROPERTIES)
    private Boolean reloadProperties;

    /**
     * Determines how often should be reloaded in seconds
     * <p>
     * Default is {@code 60}
     */
    @PropertyAlias(value = P6SpyOptions.RELOADPROPERTIESINTERVAL)
    private Integer reloadPropertiesInterval;

    /**
     * JNDI DataSource lookup
     * <p>
     * If you are using the DataSource support outside an app server, you will probably need to define the JNDI Context environment.
     * <p>
     * If the P6Spy code will be executing inside an app server then do not use these properties, and the DataSource lookup will
     * use the naming context defined by the app server.
     * <p>
     * The two standard elements of the naming environment are {@code jndiContextFactory} and {@code jndiContextProviderUrl}.
     * If you need additional elements, use the {@code jndiContextCustom} property.
     * <p>
     * You can define multiple properties in {@code jndiContextCustom} in name value pairs.
     * Separate the name and value with a semicolon, and separate the pairs with commas.
     * <p>
     * The example shown here is for a standalone program running on a machine that is also running JBoss, so the JNDI context
     * is configured for JBoss (3.0.4)
     * <pre><code>
     * jndiContextFactory = org.jnp.interfaces.NamingContextFactory
     * jndiContextProviderUrl = localhost:1099
     * jndiContextCustom = java.naming.factory.url.pkgs;org.jboss.naming:org.jnp.interfaces
     * </code></pre>
     */
    @PropertyAlias(value = P6SpyOptions.JNDICONTEXTFACTORY)
    private String jndiContextFactory;

    /**
     * JNDI DataSource lookup
     * <p>
     * If you are using the DataSource support outside an app server, you will probably need to define the JNDI Context environment.
     * <p>
     * If the P6Spy code will be executing inside an app server then do not use these properties, and the DataSource lookup will
     * use the naming context defined by the app server.
     * <p>
     * The two standard elements of the naming environment are {@code jndiContextFactory} and {@code jndiContextProviderUrl}.
     * If you need additional elements, use the {@code jndiContextCustom} property.
     * <p>
     * You can define multiple properties in {@code jndiContextCustom} in name value pairs.
     * Separate the name and value with a semicolon, and separate the pairs with commas.
     * <p>
     * The example shown here is for a standalone program running on a machine that is also running JBoss, so the JNDI context
     * is configured for JBoss (3.0.4)
     * <pre><code>
     * jndiContextFactory = org.jnp.interfaces.NamingContextFactory
     * jndiContextProviderUrl = localhost:1099
     * jndiContextCustom = java.naming.factory.url.pkgs;org.jboss.naming:org.jnp.interfaces
     * </code></pre>
     */
    @PropertyAlias(value = P6SpyOptions.JNDICONTEXTPROVIDERURL)
    private String jndiContextProviderUrl;

    /**
     * JNDI DataSource lookup
     * <p>
     * If you are using the DataSource support outside an app server, you will probably need to define the JNDI Context environment.
     * <p>
     * If the P6Spy code will be executing inside an app server then do not use these properties, and the DataSource lookup will
     * use the naming context defined by the app server.
     * <p>
     * The two standard elements of the naming environment are {@code jndiContextFactory} and {@code jndiContextProviderUrl}.
     * If you need additional elements, use the {@code jndiContextCustom} property.
     * <p>
     * You can define multiple properties in {@code jndiContextCustom} in name value pairs.
     * Separate the name and value with a semicolon, and separate the pairs with commas.
     * <p>
     * The example shown here is for a standalone program running on a machine that is also running JBoss, so the JNDI context
     * is configured for JBoss (3.0.4)
     * <pre><code>
     * jndiContextFactory = org.jnp.interfaces.NamingContextFactory
     * jndiContextProviderUrl = localhost:1099
     * jndiContextCustom = java.naming.factory.url.pkgs;org.jboss.naming:org.jnp.interfaces
     * </code></pre>
     */
    @PropertyAlias(value = P6SpyOptions.JNDICONTEXTCUSTOM)
    private String jndiContextCustom;

    /**
     * DataSource replacement
     * <p>
     * Replace the real DataSource class in your application server configuration with the name com.p6spy.engine.spy.P6DataSource
     * (that provides also connection pooling and xa support). then add the JNDI name and class name of the real DataSource here
     * <p>
     * Values set in this item cannot be reloaded using the {@code reloadProperties} variable. Once it is loaded, it remains
     * in memory until the application is restarted
     * <p>
     * The example shown here is for mysql
     * <pre><code>
     * realDatasource = /RealMySqlDS
     * </code></pre>
     */
    @PropertyAlias(value = P6SpyOptions.REALDATASOURCE)
    private String realDatasource;

    /**
     * DataSource class replacement
     * <p>
     * The example shown here is for mysql
     * <pre><code>
     * realDatasourceClass = com.mysql.jdbc.jdbc2.optional.MysqlDataSource
     * </code></pre>
     */
    @PropertyAlias(value = P6SpyOptions.REALDATASOURCECLASS)
    private Class<? extends DataSource> realDatasourceClass;

    /**
     * DataSource properties
     * <p>
     * If you are using the DataSource support to intercept calls to a DataSource that requires properties for proper setup,
     * define those properties here. Use name value pairs, separate the name and value with a semicolon, and separate the
     * pairs with commas.
     * <p>
     * The example shown here is for mysql
     * <pre><code>
     * realDatasourceProperties = port;3306,serverName;host,databaseName;jbossdb,foo;bar
     * </code></pre>
     */
    @PropertyAlias(value = P6SpyOptions.REALDATASOURCEPROPERTIES)
    private String realDatasourceProperties;

    /**
     * Custom log message format used ONLY if {@code logMessageFormat} is set to com.p6spy.engine.spy.appender.CustomLineFormat
     * <p>
     * Default is %(currentTime)|%(executionTime)|%(category)|connection%(connectionId)|%(sqlSingleLine)
     * <p>
     * Available placeholders are:
     * <pre><code>
     * %(connectionId)            the id of the connection
     * %(currentTime)             the current time expressing in milliseconds
     * %(executionTime)           the time in milliseconds that the operation took to complete
     * %(category)                the category of the operation
     * %(effectiveSql)            the SQL statement as submitted to the driver
     * %(effectiveSqlSingleLine)  the SQL statement as submitted to the driver, with all new lines removed
     * %(sql)                     the SQL statement with all bind variables replaced with actual values
     * %(sqlSingleLine)           the SQL statement with all bind variables replaced with actual values, with all new lines removed
     * </code></pre>
     */
    private String customLogMessageFormat;

    /**
     * Format that is used for logging of the {@link java.util.Date} implementations
     * <p>
     * Has to be compatible with {@link java.text.SimpleDateFormat}
     * <p>
     * Default is {@code "yyyy-MM-dd HH:mm:ss"}
     */
    private String databaseDialectDateFormat;

    /**
     * Format that is used for logging of the {@link java.sql.Timestamp} implementations
     * <p>
     * Has to be compatible with {@link java.text.SimpleDateFormat}
     * <p>
     * Default is {@code "yyyy-MM-dd HH:mm:ss.SSS"}
     */
    private String databaseDialectTimestampFormat;

    /**
     * Format that is used for logging booleans, possible values: boolean, numeric
     * <p>
     * Default is {@code "boolean"}
     */
    private String databaseDialectBooleanFormat;

    /**
     * Whether to expose options via JMX or not
     * <p>
     * Default is {@code true}
     */
    private Boolean jmx = true;

    /**
     * If exposing options via jmx (see {@code jmx}), what should be the prefix used?
     * <p>
     * jmx naming pattern constructed is: com.p6spy(.<jmxPrefix>)?:name=<optionsClassName>
     * <p>
     * Note, if there is already such a name in use it would be unregistered first (the last registered wins)
     */
    private String jmxPrefix;
}
