package com.utils.inject.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * It makes it clear that the method accepts null values, and that if you override the method, you should also accept
 * null values.
 * It also serves as a hint for code analyzers like FindBugs. For example, if such a method dereferences its argument
 * without checking for null first, FindBugs will emit a warning.
 * <p>
 * IntelliJ IDEA How-To <a href="http://www.jetbrains.com/idea/documentation/howto.html"> Nullable How-To</a>.
 * @author Chaos
 * @see SuppressWarnings
 * @since 2014年3月12日下午12:53:22
 */
@Documented
// @TypeQualifierNickname
// @Nonnull(when = When.UNKNOWN)
// @Retention(RetentionPolicy.RUNTIME)
@Retention(RetentionPolicy.SOURCE)
public @interface Nullable {

}
