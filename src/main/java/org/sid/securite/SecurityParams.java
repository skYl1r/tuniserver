package org.sid.securite;

public class SecurityParams {
    public static final String JWT_HEADER_NAME="Authorization";
    public static final String SECRET="securityCode";
    public static final  long EXPRIRATION=10*24*3600*1000;
    public static  final String HEADER_PREFIX ="Bearer ";
}
