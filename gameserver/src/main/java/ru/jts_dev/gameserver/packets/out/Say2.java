package ru.jts_dev.gameserver.packets.out;

import ru.jts_dev.common.packets.OutgoingMessageWrapper;
import ru.jts_dev.gameserver.constants.ChatType;

import javax.validation.Payload;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Java-man
 * @since 10.01.2016
 */
public class Say2 extends OutgoingMessageWrapper implements Payload {
    private int objectId;
    private int textType;
    private String charName;
    private int charId;
    private int friendType;
    private int level;

    private String text;
    private int npcString = -1;
    private List<String> parameters;

    public Say2(int objectId, ChatType messageType, String charName, String text, int friendType, int level) {
        this.objectId = objectId;
        textType = messageType.ordinal();
        this.charName = charName;
        this.text = text;
        this.friendType = friendType;
        this.level = level;
    }

    public Say2(int objectId, ChatType messageType, String charName, String text) {
        this.objectId = objectId;
        textType = messageType.ordinal();
        this.charName = charName;
        this.text = text;
    }

	/*
    public Say2(int objectId, ChatType messageType, int charId, NpcStringId npcString)
	{
		objectId = objectId;
		textType = messageType.ordinal();
		charId = charId;
		npcString = npcString.getId();
	}

	public Say2(int objectId, ChatType messageType, String charName, NpcStringId npcString)
	{
		objectId = objectId;
		textType = messageType.ordinal();
		charName = charName;
		npcString = npcString.getId();
	}

	public Say2(int objectId, ChatType messageType, int charId, SystemMessageId sysString)
	{
		objectId = objectId;
		textType = messageType.ordinal();
		charId = charId;
		npcString = sysString.getId();
	}*/

    /**
     * String parameter for argument S1,S2,.. in npcstring-e.dat
     *
     * @param text
     */
    public void addStringParameter(String text) {
        if (parameters == null) {
            parameters = new ArrayList<>();
        }
        parameters.add(text);
    }

    @Override
    public void write() {
        putByte(0x4a);

        putInt(objectId);
        putInt(textType);

        if (textType == 11) {
            putInt(charId);
            putInt(npcString);
        } else {
            if (charName != null) {
                putString(charName);
                putInt(npcString);
                putString(text);
                putByte(friendType);
                putByte(level);
            } else if (npcString == -1) {
                putString("");
                putInt(npcString);
                putString(text);
                putByte(friendType);
                putByte(level);
            } else {
                putString("");
                putInt(npcString);
                if (parameters != null) {
                    // SSSSS
                    parameters.forEach(this::putString);
                }
            }
        }
    }
}