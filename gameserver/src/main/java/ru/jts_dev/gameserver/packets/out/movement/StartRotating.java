package ru.jts_dev.gameserver.packets.out.movement;

import ru.jts_dev.common.packets.OutgoingMessageWrapper;
import ru.jts_dev.gameserver.model.GameCharacter;

/**
 * @author Java-man
 * @since 26.01.2016
 */
public class StartRotating extends OutgoingMessageWrapper {
    private final int charId;
    private final int degree;
    private final int side;
    private final int speed;

    public StartRotating(GameCharacter character, int degree, int side, int speed) {
        charId = character.getObjectId();
        this.degree = degree;
        this.side = side;
        this.speed = speed;
    }

    @Override
    public final void write() {
        writeByte(0x7a);
        writeInt(charId);
        writeInt(degree);
        writeInt(side); // side (1 = right, -1 = left)
        writeInt(speed);
    }
}
