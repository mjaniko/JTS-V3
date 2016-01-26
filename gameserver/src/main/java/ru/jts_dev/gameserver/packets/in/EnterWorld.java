package ru.jts_dev.gameserver.packets.in;

import org.springframework.beans.factory.annotation.Autowired;
import ru.jts_dev.common.packets.IncomingMessageWrapper;
import ru.jts_dev.gameserver.model.GameCharacter;
import ru.jts_dev.gameserver.model.GameSession;
import ru.jts_dev.gameserver.packets.Opcode;
import ru.jts_dev.gameserver.packets.out.ClientSetTime;
import ru.jts_dev.gameserver.packets.out.ExBasicActionList;
import ru.jts_dev.gameserver.packets.out.UserInfo;
import ru.jts_dev.gameserver.parser.data.action.Action;
import ru.jts_dev.gameserver.parser.impl.PcParametersHolder;
import ru.jts_dev.gameserver.parser.impl.UserBasicActionsHolder;
import ru.jts_dev.gameserver.service.BroadcastService;
import ru.jts_dev.gameserver.service.GameSessionService;
import ru.jts_dev.gameserver.service.PlayerService;
import ru.jts_dev.gameserver.time.GameTimeService;

import java.util.Collection;
import java.util.List;

import static ru.jts_dev.gameserver.parser.impl.PcParametersHolder.toPCParameterName;

/**
 * @author Camelion
 * @since 03.01.16
 */
@Opcode(0x11)
public class EnterWorld extends IncomingMessageWrapper {
    @Autowired
    private GameTimeService timeService;
    @Autowired
    private BroadcastService broadcastService;
    @Autowired
    private GameSessionService sessionService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PcParametersHolder parametersData;
    @Autowired
    private UserBasicActionsHolder userBasicActionsHolder;

    @Override
    public void prepare() {
        // Do nothing
    }

    @Override
    public void run() {
        GameSession session = sessionService.getSessionBy(getConnectionId());
        GameCharacter character = playerService.getCharacterBy(getConnectionId());

        // TODO: 03.01.16 ItemList packet, ShortCutInit, BookMarkInfo, BasicAction, QuestList, EtcStatusUpdate, StorageMaxCount, FriendList,
        // TODO: 03.01.16 System Message : Welcome to Lineage, SkillCoolTime, ExVoteSystemInfo, Spawn player,
        // TODO: 03.01.16 HennaInfo, SkillList, broadcast CharInfo

        Collection<Action> actions = userBasicActionsHolder.getActionsData().values();
        broadcastService.send(session, new ExBasicActionList(actions));

        long gameTimeInMinutes = timeService.getGameTimeInMinutes();
        broadcastService.send(session, new ClientSetTime((int) gameTimeInMinutes));

        // TODO: 04.01.16 broadcast CharInfo, send UserInfo
        // send UserInfo

        String pcParameterName = toPCParameterName(character.getSex(), character.getStat().getClass_());
        assert parametersData.getCollisionBoxes().containsKey(pcParameterName);

        List<Double> collisions = parametersData.getCollisionBoxes().get(pcParameterName);

        broadcastService.send(session, new UserInfo(character, collisions));
    }
}
