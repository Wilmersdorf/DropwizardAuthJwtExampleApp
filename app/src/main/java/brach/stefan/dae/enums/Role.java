package brach.stefan.dae.enums;

import brach.stefan.dae.helper.EnumHelper;

public enum Role {
    ADMIN, NORMAL, UNKNOWN;

    public static Role parse(String s) {
        return EnumHelper.valueOfIgnoreCase(Role.class, s, UNKNOWN);
    }
}
