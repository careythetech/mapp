package com.movieapp.demo.jwt;

public class JwtProperties {
    // made properties needed to construct json token
    public static final String SECRET = "péopoijopjç_'&gé'àçètàéç'èté"; //  secret key would be encryption of credentials
            public static final int EXPIRATION_TIME = 86400000; // after ten days token expries
            public static final String TOKEN_PREFIX = "Bearer "; // Authencation type
            public  static final String HEADER_STRING = "Authorization"; // Authorization header of token
}
