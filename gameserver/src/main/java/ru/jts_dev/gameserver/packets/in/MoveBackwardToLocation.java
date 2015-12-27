package ru.jts_dev.gameserver.packets.in;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.jts_dev.common.packets.IncomingMessageWrapper;
import ru.jts_dev.gameserver.model.GameCharacter;
import ru.jts_dev.gameserver.movement.MovementService;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * @author Java-man
 * @since 17.12.2015
 */
@Scope(SCOPE_PROTOTYPE)
@Component
public class MoveBackwardToLocation extends IncomingMessageWrapper {
    private int targetX;
    private int targetY;
    private int targetZ;
    private int originX;
    private int originY;
    private int originZ;
    private int movementType;

    private MovementService movementService;

    @Autowired
    public MoveBackwardToLocation(MovementService movementService) {
        this.movementService = movementService;
    }

    @Override
    public void prepare() {
        targetX = readInt();
        targetY = readInt();
        targetZ = readInt();
        originX = readInt();
        originY = readInt();
        originZ = readInt();
        movementType = readInt(); // is 0 if cursor keys are used 1 if mouse is used
    }

    @Override
    public void run() {
        if (movementType == 1)
            targetZ += 27;

        // TODO
        GameCharacter character = null;
        Vector3D end = new Vector3D(targetX, targetY, targetZ);

        movementService.moveTo(character, end);
    }
}