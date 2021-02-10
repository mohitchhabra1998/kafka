package com.techprimers.kafka.springbootkafkaconsumerexample.Enums;

public enum RoomCatEnum {
    POOL_FACING_ROOM(1),DELUXE_ROOM(2),TWIN_ROOM(3),CLASSIC_ROOM(4);

    int value;
    RoomCatEnum(int value){
        this.value=value;
    }
    public int getValue(){
        return this.value;
    }
}
