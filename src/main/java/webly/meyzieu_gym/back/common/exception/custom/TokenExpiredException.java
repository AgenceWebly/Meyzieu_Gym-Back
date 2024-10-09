package webly.meyzieu_gym.back.common.exception.custom;

public class TokenExpiredException extends RuntimeException {

    public TokenExpiredException(String message) {
        super(message);
    }

}
