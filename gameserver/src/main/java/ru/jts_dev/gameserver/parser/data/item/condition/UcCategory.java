package ru.jts_dev.gameserver.parser.data.item.condition;

import java.util.List;

/**
 * @author Camelion
 * @since 14.01.16
 */
public class UcCategory extends Condition {
    private final List<String> categories;

    public UcCategory(List<String> categories) {
        this.categories = categories;
    }
}