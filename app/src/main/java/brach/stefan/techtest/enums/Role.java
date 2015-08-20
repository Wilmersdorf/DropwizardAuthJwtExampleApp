package brach.stefan.techtest.enums;

import brach.stefan.techtest.helper.EnumHelper;

public enum Role {
    ADMIN, NORMAL, UNKNOWN;

    public static Role parse(String s) {
        return EnumHelper.valueOfIgnoreCase(Role.class, s, UNKNOWN);
    }
}
