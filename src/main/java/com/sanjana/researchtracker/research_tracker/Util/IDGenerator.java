package com.sanjana.researchtracker.research_tracker.Util;

import java.util.UUID;

public class IDGenerator {
    public static String projectIdGen(){
        return "PRO-"+ UUID.randomUUID();
    }
    public static String docIdGen(){
        return "DOC-"+ UUID.randomUUID();
    }
    public static String msIdGen(){
        return "MS-"+ UUID.randomUUID();
    }
    public static String userIdGen(){
        return "USR-"+ UUID.randomUUID();
    }

    public static String adminIdGen(){
        return "ADM-"+ UUID.randomUUID();
    }
    public static String documentIdGen(){
        return "DOC-"+ UUID.randomUUID();
    }
    public static String milestoneIdGen(){
        return "MIL-"+ UUID.randomUUID();
    }
    public static String piIdGen(){
        return "PI-"+ UUID.randomUUID();
    }
    public static String researchMemberIdGen(){
        return "RMI-"+ UUID.randomUUID();
    }
}
