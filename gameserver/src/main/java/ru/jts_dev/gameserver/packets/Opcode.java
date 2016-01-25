package ru.jts_dev.gameserver.packets;

import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * @author Camelion
 * @since 05.01.16
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@Scope(SCOPE_PROTOTYPE)
public @interface Opcode {
    int CLIENT_SWITCH_OPCODE = 0xD0;

    /**
     * Alias for {@link #first}.
     *
     * @see #first
     */
    @AliasFor("first") int value() default CLIENT_SWITCH_OPCODE; // switch opcode

    /**
     * first packet opcode (byte)
     *
     * @return - expected opcode
     */
    @AliasFor("value") int first() default CLIENT_SWITCH_OPCODE; // switch opcode

    /**
     * second packet opcode (short)
     *
     * @return - expected opcode
     */
    int second() default Integer.MIN_VALUE;
}
