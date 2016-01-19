grammar UserBasicAction;

import Lang;

@header {
import ru.jts_dev.gameserver.constants.ActionHandlerType;
import ru.jts_dev.gameserver.constants.ActionHandlerType.*;
import ru.jts_dev.gameserver.parser.data.action.Action;
}

file: action+;

action returns[Action value]:
    'action_begin'
    id
    handler?
    option?
    'action_end'
    ;

id
    returns[int value]: 'id' '=' io=int_object {$ctx.value = $io.value;};

handler
    returns[ActionHandlerType value]: 'handler' '=' '[' handler_type ']';

option
    returns[Object value]: 'option' '=' (
    io=int_object {$ctx.value = $io.value;}
    | iobj=identifier_object {$ctx.value = $iobj.value;}
    );

handler_type
    returns[ActinoHandlerType value]:
    SIT_STAND {$ctx.value = SIT_STAND;}
    | WALK_RUN {$ctx.value = WALK_RUN;}
    | ATTACK {$ctx.value = ATTACK;}
    | TRADE {$ctx.value = TRADE;}
    | CLIENT_ACTION {$ctx.value = CLIENT_ACTION;}
    | PRIVATE_STORE {$ctx.value = PRIVATE_STORE;}
    | PACKAGE_PRIVATE_STORE {$ctx.value = PACKAGE_PRIVATE_STORE;}
    | SOCIAL_ACTION {$ctx.value = SOCIAL_ACTION;}
    | PET_ACTION {$ctx.value = PET_ACTION;}
    | PET_DEPOSIT {$ctx.value = PET_DEPOSIT;}
    | SUMMON_ACTION {$ctx.value = SUMMON_ACTION;}
    | SUMMON_DESPAWN {$ctx.value = SUMMON_DESPAWN;}
    | PRIVATE_BUY {$ctx.value = PRIVATE_BUY;}
    | MAKE_ITEM {$ctx.value = MAKE_ITEM;}
    | MAKE_ITEM2 {$ctx.value = MAKE_ITEM2;}
    | RIDE {$ctx.value = RIDE;}
    | TELEPORT_BOOKMARK {$ctx.value = TELEPORT_BOOKMARK;}
    | BOT_REPORT {$ctx.value = BOT_REPORT;}
    | AIRSHIP_ACTION {$ctx.value = AIRSHIP_ACTION;}
    | COUPLE_ACTION {$ctx.value = COUPLE_ACTION;}
    ;

SIT_STAND: 'SIT_STAND';
WALK_RUN: 'WALK_RUN';
ATTACK: 'ATTACK';
TRADE: 'TRADE';
CLIENT_ACTION: 'CLIENT_ACTION';
PRIVATE_STORE: 'PRIVATE_STORE';
SOCIAL_ACTION: 'SOCIAL_ACTION';
PET_ACTION: 'PET_ACTION';
PET_DEPOSIT: 'PET_DEPOSIT';
SUMMON_ACTION: 'SUMMON_ACTION';
PRIVATE_BUY: 'PRIVATE_BUY';
MAKE_ITEM: 'MAKE_ITEM';
MAKE_ITEM2: 'MAKE_ITEM2';
RIDE: 'RIDE';
SUMMON_DESPAWN: 'SUMMON_DESPAWN';
PACKAGE_PRIVATE_STORE: 'PACKAGE_PRIVATE_STORE';
TELEPORT_BOOKMARK: 'TELEPORT_BOOKMARK';
BOT_REPORT: 'BOT_REPORT';
AIRSHIP_ACTION: 'AIRSHIP_ACTION';
COUPLE_ACTION: 'COUPLE_ACTION';