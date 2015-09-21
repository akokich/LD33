package com.akx2.LD33;

public enum RaiderMode {
    SETUP,              // the raider is setup and ready to move to the first way point
    MOVE_ENTRY,         // the raider has reached the first waypoint and is ready to move to a random spot in it's assignment
    MOVE_ATTACK,
    MOVE_RETREAT,
    MOVE_EVADE,
    ATTACK_RANGED,
    ATTACK_MELEE,
    HEAL,
    DIE,
    DEAD
}
