package brach.stefan.dae.enums;

public enum Code {
    UNAUTHORIZED(401), CONFLICT(409), NOT_FOUND(404), IOEXCEPTION(-1), OK(200), BAD_REQUEST(400), CREATED(201), SERVER_ERROR(500), FORBIDDEN(403);
    private int value;

    private Code(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
