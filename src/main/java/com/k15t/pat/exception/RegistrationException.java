package com.k15t.pat.exception;

/**
 * Generic Exception class to handle user registration related exceptions
 *
 * @author Gayathri
 */
public class RegistrationException extends Exception {

    private static final long serialVersionUID = 1L;

    private String errorCode;
    private String errMsg;

    /**
     * This method returns the Error Code
     * @return
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * This method sets the Error Code
     * @param errorCode
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * This method returns the Error Message
     * @return
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * This method sets the Error Message
     * @param errMsg
     */

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    /**
     * A constructor with Error Code and Error Message
     * @param errorCode
     * @param errMsg
     */
    public RegistrationException(String errorCode, String errMsg) {
        this.errorCode = errorCode;
        this.errMsg = errMsg;
    }

}
