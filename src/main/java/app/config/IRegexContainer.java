package app.config;

/**
 * Created by student on 26.09.2017.
 */
public interface IRegexContainer {
    // Cyrillic name
    String REGEX_NAME_UKR = "^[А-ЯІЇЄ][а-яіїє']{1,20}$";
    // Latin name
    String REGEX_NAME_LAT = "^[A-Z][a-z]{1,20}$";
/*    // login
    String REGEX_LOGIN_UKR = "^[А-ЯІЇЄ][а-яіїє'0-9_-]{8,20}$";*/
    // login
    String REGEX_LOGIN = "^[A-Za-z0-9_-]{8,20}$";
    //password
    String REGEX_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$";
}
